package ac.soton.eventb.emf.inclusion.generator.rules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eventb.emf.core.machine.Action;
import org.eventb.emf.core.machine.Event;
import org.eventb.emf.core.machine.Guard;
import org.eventb.emf.core.machine.Machine;
import org.eventb.emf.core.machine.MachinePackage;
import org.eventb.emf.core.machine.Parameter;
import ac.soton.emf.translator.TranslationDescriptor;
import ac.soton.emf.translator.configuration.AbstractRule;
import ac.soton.emf.translator.configuration.IRule;
import ac.soton.eventb.emf.core.extension.coreextension.EventCases;
import ac.soton.eventb.emf.core.extension.coreextension.FormalParameter;
import ac.soton.eventb.emf.inclusion.EventSynchronisation;
import ac.soton.eventb.emf.inclusion.generator.Make;
import ac.soton.eventb.emf.inclusion.generator.Utilities;
/**
 * <p>
 * Implementation of {@link AbstractRule} for translating event synchronisation into flattened Event-B event.
 * </p>
 * 
 * @author dd4g12
 * @version 0.1
 * @see TranslationDescriptor
 * @since 0.2.1
 */

public class EventCasesRule extends AbstractRule implements IRule{
	protected static final EReference events = MachinePackage.Literals.MACHINE__EVENTS;
	@Override
	public boolean enabled(final EObject sourceElement) throws Exception  {
		if(sourceElement instanceof EventSynchronisation && ((EventSynchronisation)sourceElement).getSynchronisedCases() != null)			
			return true;
					
		else
			return false;
	}

	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> translatedElements) throws Exception {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
        //ToDo
	
		EventSynchronisation eventSynch = (EventSynchronisation) sourceElement;
		Machine mch = null;
		if (sourceElement.eContainer().eContainer() instanceof Machine)
			mch = (Machine)sourceElement.eContainer().eContainer();
		
//		Event synchEvt = eventSynch.getSynchronisedEvent();
		Event e = (Event) eventSynch.eContainer();
		String pref = eventSynch.getPrefix();
//		Event evt = Utilities.copyEvents(e, synchEvt, pref);
		
        EventCases synchronisedCases = eventSynch.getSynchronisedCases();
         
  
		for (Event evt : synchronisedCases.getEvents()) {
			String name = e.getName() + "_" + evt.getName();
			// get info from the event before synchronisation
			
			//---------------------------------------
			//check if last or not
			
			if(synchronisedCases.getEvents().indexOf(evt) == synchronisedCases.getEvents().size()-1) {
				e.setName(name);
				EList<String> arguments = eventSynch.getActualParameters();
				
				
				// formal parameters
				Machine included_mch = (Machine) synchronisedCases.eContainer();
				ArrayList<String> formalParNames = getFormalParameterNames(included_mch, synchronisedCases);
				
				// need to create guards related to formal parametes
				// add these guards to e2 
				//similar to copy event in event synchronisation
				
				//Check if both formal parameters and actual parameters are of the same size
				if(arguments.size() == formalParNames.size()) {
					ArrayList<Guard> grds = generateEventGroupGuards(formalParNames, arguments, pref);
					e.getGuards().addAll(grds);
				}
					
				
				Utilities.copyEvents(e, evt, pref);
				
				ret.add(Make.descriptor(mch, events, e, 2));	
			}
			else {
				
			
			Event e2 = (Event) Make.event(name, e.isExtended(), e.getConvergence(), e.getRefinesNames(), e.getComment());
			Utilities.copyEvents(e2, e, "");
	
			EList<String> arguments = eventSynch.getActualParameters();
			
			
			// formal parameters
			Machine included_mch = (Machine) synchronisedCases.eContainer();
			ArrayList<String> formalParNames = getFormalParameterNames(included_mch, synchronisedCases);
			
			// need to create guards related to formal parametes
			// add these guards to e2 
			//similar to copy event in event synchronisation
			
			//Check if both formal parameters and actual parameters are of the same size
			if(arguments.size() == formalParNames.size()) {
				ArrayList<Guard> grds = generateEventGroupGuards(formalParNames, arguments, pref);
				e2.getGuards().addAll(grds);
			}
				
			
			Utilities.copyEvents(e2, evt, pref);
			
			ret.add(Make.descriptor(mch, events, e2, 2));	
			}//end of else
        }
//	  int i =  mch.getEvents().indexOf(e);//remove(e);
//	  
//	  System.out.println("index: " + i + " event " + mch.getEvents().get(i).getName() + "  extensio  no: " + mch.getEvents().get(i).getExtensions().indexOf(eventSynch));
	 
//	 //--------------------------------
//	 //Remove source event if it only synchronise with a group and this is the last group
//		Boolean canDelete = false;
//		if(e.getExtensions().size() > 1) {
//			
//			//if(eventSynch.getSynchronisedEvent() == null) {
//			if(e.getExtensions().indexOf(eventSynch) == (e.getExtensions().size()-1)) {
//				//if no other extension is synchronosed event
//				for(int i = 0; i < e.getExtensions().size()-2; i++) {
//					if(e.getExtensions().get(i) instanceof EventSynchronisation && ((EventSynchronisation)e.getExtensions().get(i)).getSynchronisedEvent() != null) {
//						canDelete = false;
//						break;
//					}
//						
//					else
//						canDelete = true;
//				}
//				
//			}
//					
//			//}		
//		}
//		else 
//			canDelete = true;//just delete
//		if(canDelete) {
//			mch.getEvents().remove(e);
//		}  
//	 //--------------------------------
	  //No need for removing just for the last case instead of copying the event add to it but still need to check if this will affect synchronise with event if thee was one after it.
		return ret;	
	}
	
	@Override
	public boolean dependenciesOK(EObject sourceElement, final List<TranslationDescriptor> translatedElements) throws Exception  {
		return true;
	}
	
	@Override
	public boolean fireLate() {
		return false;
	}
	
	// get formal parameters  of event group and rename the parameters if required
	private ArrayList<String> getFormalParameterNames(Machine sourceMachine, EventCases evtGroup){
		ArrayList <String> parList = new ArrayList<String>();
		for(FormalParameter par: evtGroup.getFormalParameters())
			parList.add(par.getName());
		
		return parList;
	}
	
	// Define the guards that relate formal  parameters to arguments
	private ArrayList<Guard> generateEventGroupGuards(ArrayList <String> formalParameters, EList<String> arguments, String prefix){
		ArrayList <Guard> grdList = new ArrayList<Guard>();
		for(int i = 0; i < formalParameters.size(); i++) {
		  //for(String formalName : formalParameters) {
			String predicate = "";
			String name = "";
			if(prefix == "") {
				predicate = formalParameters.get(i) + "=" +  arguments.get(i);
			    name = "grd_" + formalParameters.get(i);
			}
			
				
			else {
				predicate = prefix + "_" + formalParameters.get(i) + "=" + arguments.get(i);
				name = "grd_" + prefix + "_" + formalParameters.get(i);
			}
				
			
			Guard newGrd = (Guard) Make.guard(name, false, predicate, "");
			
			grdList.add(newGrd);	
		}
		return grdList;
	}
}
