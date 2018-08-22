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
		if(sourceElement instanceof EventSynchronisation && !((EventSynchronisation)sourceElement).getSynchronisedCases().eContents().isEmpty())			
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
			Event e2 = (Event) Make.event(name, e.isExtended(), e.getConvergence(), e.getRefinesNames(), e.getComment());
			// how to distinguish between formal paranmeters and arguments 
			// i assume here are the arguments but how to find the formal parameters and what happens in case of nested grouping??
			
			// this is the arguments
			EList<FormalParameter> arguments = eventSynch.getSynchronisedCases().getFormalParameters();
			
			
			// formal parameters
			Machine included_mch = (Machine) synchronisedCases.eContainer();
			ArrayList<String> formalParNames = getFormalParameterNames(included_mch, synchronisedCases , pref);
			
			// need to create gurads related to formal parametes
			// add these guards to e2 
			// what about prefixing???? is this affected by prefixing
			
			//similar to copy event in event synchronisation
			ArrayList<Guard> grds = generateEventGroupGuards(formalParNames, arguments, pref);
			e2.getGuards().addAll(grds);
			Utilities.copyEvents(e2, evt, pref);
			
			ret.add(Make.descriptor(mch, events, e2, 2));
        }
	   
		//--
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
	private ArrayList<String> getFormalParameterNames(Machine sourceMachine, EventCases evtGroup, String prefix){
		ArrayList <String> parList = new ArrayList<String>();
		for(FormalParameter par: evtGroup.getFormalParameters()){
			String name = "";
			if(prefix != "")
				name = prefix + "_" + par.getName();
			else
				name = par.getName();
			
			//Parameter newPar = (Parameter) Make.parameter(name, par.getComment());
			parList.add(name);
		}
		return parList;
	}
	
	// Define the guards that relate formal  parameters to arguments
	private ArrayList<Guard> generateEventGroupGuards(ArrayList <String> formalParameters, EList<FormalParameter> arguments, String prefix){
		ArrayList <Guard> grdList = new ArrayList<Guard>();
		for(int i = 0; i < formalParameters.size(); i++) {
		  //for(String formalName : formalParameters) {
			String predicate = "";
			if(prefix == "")
				predicate = formalParameters.get(i) + "=" + ((FormalParameter) arguments.get(i)).getName();
			else
				predicate = prefix + "_" + formalParameters.get(i) + "=" + ((FormalParameter) arguments.get(i)).getName();
			
		    String name = "grd_" + formalParameters.get(i);
			Guard newGrd = (Guard) Make.guard(name, false, predicate, "");
			
			grdList.add(newGrd);	
		}
		return grdList;
	}
}
