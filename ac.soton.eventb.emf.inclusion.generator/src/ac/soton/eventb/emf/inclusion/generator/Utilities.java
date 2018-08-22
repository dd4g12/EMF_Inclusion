package ac.soton.eventb.emf.inclusion.generator;

import java.util.ArrayList;

import org.eventb.emf.core.machine.Action;
import org.eventb.emf.core.machine.Event;
import org.eventb.emf.core.machine.Guard;
import org.eventb.emf.core.machine.Machine;
import org.eventb.emf.core.machine.Parameter;
import org.eventb.emf.core.machine.Variable;
import org.eventb.emf.core.machine.Witness;
import ac.soton.emf.translator.TranslationDescriptor;
import ac.soton.eventb.emf.inclusion.EventSynchronisation;


/**
 * @author dd4g12
 * @version 0.1
 * @see TranslationDescriptor
 * @since 0.2.1
 * This is a collection of static convenience methods for event synchronisation
 * 
 */
public class Utilities {


	//****************************************************************
    // Utility Methods to clone the event related elements
    //****************************************************************
	
	public static Event copyEvents(Event evt, Event synchEvt, String pref){
	
		Machine mch = null;
		if (synchEvt.eContainer() instanceof Machine){
			mch = (Machine) synchEvt.eContainer();
			evt.getParameters().addAll(0,Utilities.copyParameters(synchEvt , pref));
			evt.getActions().addAll(0,Utilities.copyActions(mch, synchEvt, pref));
			evt.getGuards().addAll(0,Utilities.copyGuards(mch, synchEvt, pref));
			evt.getWitnesses().addAll(0,Utilities.copyWitnesses(mch, synchEvt, pref));
		}
						
		return evt;
	}
	
	// Clone Parameter list of event and rename the parameters if required
	public static ArrayList<Parameter> copyParameters(Event evt, String prefix){
		ArrayList <Parameter> parsList = new ArrayList<Parameter>();
		for(Parameter par: evt.getParameters())
		{
			String name = "";
			if(prefix != "")
				name = prefix + "_" + par.getName();
			else
				name = par.getName();
			
			Parameter newPar = (Parameter) Make.parameter(name, par.getComment());
			parsList.add(newPar);
		}		
		return parsList;
	}
	
	
	// Clone Actionlist of event and rename the actions if required
	public static ArrayList<Action> copyActions(Machine sourceMachine, Event evt, String prefix){
		ArrayList <Action> actsList = new ArrayList<Action>();
		for(Action act: evt.getActions()){
			String name = "";
			String exp = "";
			if(prefix != ""){
				name = prefix + "_" + act.getName();
				exp =  renameEvent(sourceMachine, evt, act.getAction(), prefix);
			}
			else{
				name = act.getName();
				exp =  act.getAction();
			}
			 Action newAct = (Action) Make.action(name, exp, act.getComment());
			
			actsList.add(newAct);	
		}
		return actsList;
	}
	
	
	//Clone guards
	public static ArrayList<Guard> copyGuards(Machine sourceMachine, Event evt, String prefix){
		ArrayList <Guard> grdList = new ArrayList<Guard>();
		for(Guard grd: evt.getGuards()){
			String name = "";
			String predicate = "";
			if(prefix != ""){
				name = prefix + "_" + grd.getName();
				predicate =  renameEvent(sourceMachine, evt, grd.getPredicate(), prefix);
			}
			else{
				name = grd.getName();
				predicate =  grd.getPredicate();
			}
			 Guard newGrd = (Guard) Make.guard(name, grd.isTheorem(), predicate, grd.getComment());
			
			grdList.add(newGrd);	
		}
		return grdList;
	}
	
	//Clone witnesses
	public static ArrayList<Witness> copyWitnesses(Machine sourceMachine, Event evt, String prefix){
		ArrayList <Witness> witList = new ArrayList<Witness>();
		for(Witness wit: evt.getWitnesses()){
			String name = "";
			String predicate = "";
			if(prefix != ""){
				name = prefix + "_" + wit.getName();
				predicate =  renameEvent(sourceMachine, evt, wit.getPredicate(), prefix);
			}
			else{
				name = wit.getName();
				predicate =  wit.getPredicate();
			}
			
			 Witness newWit =  (Witness) Make.witness(name, predicate, wit.getComment());
			
			witList.add(newWit);	
		}
		return witList;
	}
   
	// This method is used to help add prefixing for events guards and action
	// This is different to invariant prefixing because it also checks for parameters
	
	public static String renameEvent(Machine sourceMachine, Event event, String predicate, String prefix) {
		String newPredicate = predicate;
		String[] tokens = predicate.split("\\W");
		//Remove repeated tokens
		ArrayList<String> newTokens = removeReptition(tokens);
		
		for (String tok : newTokens){
			for (Variable v : sourceMachine.getVariables()){
				if (tok.equals(v.getName()))
					newPredicate = newPredicate.replaceAll("\\b" + tok + "\\b", prefix + "_" + tok);	
				else{
					for (Parameter p : event.getParameters()){
						if (tok.equals(p.getName()))
							newPredicate = newPredicate.replaceAll("\\b" + tok + "\\b", prefix + "_" + tok);	
					}
				}
			}
		}
		return newPredicate;
	}
	
	// If a utilities class is defined move this method there
	public static ArrayList<String> removeReptition(String[] tokens){
		ArrayList<String> newTokens = new ArrayList<String>();
		for(String tok: tokens){
			if(!newTokens.contains(tok))
				newTokens.add(tok);
		}
		return newTokens;
	}
}
