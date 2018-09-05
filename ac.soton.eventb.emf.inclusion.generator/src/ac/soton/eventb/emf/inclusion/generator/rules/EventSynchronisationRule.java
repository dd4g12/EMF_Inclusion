/*******************************************************************************
 * Copyright (c) 2017 University of Southampton.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     University of Southampton - initial API and implementation
 *******************************************************************************/
package ac.soton.eventb.emf.inclusion.generator.rules;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eventb.emf.core.machine.Action;
import org.eventb.emf.core.machine.Event;
import org.eventb.emf.core.machine.Guard;
import org.eventb.emf.core.machine.Machine;
import org.eventb.emf.core.machine.MachinePackage;
import org.eventb.emf.core.machine.Parameter;
import org.eventb.emf.core.machine.Variable;
import org.eventb.emf.core.machine.Witness;
import ac.soton.emf.translator.TranslationDescriptor;
import ac.soton.emf.translator.configuration.AbstractRule;
import ac.soton.emf.translator.configuration.IRule;
import ac.soton.eventb.emf.inclusion.EventSynchronisation;
import ac.soton.eventb.emf.inclusion.generator.Make;
import ac.soton.eventb.emf.inclusion.generator.Utilities;

/**
 * <p>
 * Implementation of {@link AbstractRule} for translating event synchronisation into flattened Event-B event.
 * </p>
 * 
 * @author dd4g12
 * @version 0.2
 * @see TranslationDescriptor
 * @since 0.2.0
 */

public class EventSynchronisationRule extends AbstractRule implements IRule{
		protected static final EReference events = MachinePackage.Literals.MACHINE__EVENTS;
		
		@Override
		public boolean enabled(final EObject sourceElement) throws Exception  {
			if(sourceElement instanceof EventSynchronisation && ((EventSynchronisation)sourceElement).getSynchronisedEvent() != null)
				return true;	
			else
				return false;
		}

		@Override
		public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> translatedElements) throws Exception {
			List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
			EventSynchronisation eventSynch = (EventSynchronisation) sourceElement;
			Machine mch = null;
			if (sourceElement.eContainer().eContainer() instanceof Machine)
				mch = (Machine)sourceElement.eContainer().eContainer();
			Event synchEvt = eventSynch.getSynchronisedEvent();
			Event e = (Event) eventSynch.eContainer();
			String pref = eventSynch.getPrefix();
			Event evt = Utilities.copyEvents(e, synchEvt, pref);
		    ret.add(Make.descriptor(mch, events, evt, 2));
			
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
		
	
	}

