/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ac.soton.eventb.emf.inclusion.provider;


import ac.soton.eventb.emf.core.extension.coreextension.CoreextensionFactory;
import ac.soton.eventb.emf.core.extension.coreextension.CoreextensionPackage;
import ac.soton.eventb.emf.inclusion.EventSynchronisation;
import ac.soton.eventb.emf.inclusion.InclusionFactory;
import ac.soton.eventb.emf.inclusion.InclusionPackage;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eventb.emf.core.CorePackage;

import org.eventb.emf.core.provider.AbstractExtensionItemProvider;

/**
 * This is the item provider adapter for a {@link ac.soton.eventb.emf.inclusion.EventSynchronisation} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class EventSynchronisationItemProvider
	extends AbstractExtensionItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventSynchronisationItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addSynchronisedEventPropertyDescriptor(object);
			addPrefixPropertyDescriptor(object);
			addSynchronisedCasesPropertyDescriptor(object);
			addActualParametersPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Synchronised Event feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSynchronisedEventPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_EventSynchronisation_synchronisedEvent_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_EventSynchronisation_synchronisedEvent_feature", "_UI_EventSynchronisation_type"),
				 InclusionPackage.Literals.EVENT_SYNCHRONISATION__SYNCHRONISED_EVENT,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Prefix feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPrefixPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_EventSynchronisation_prefix_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_EventSynchronisation_prefix_feature", "_UI_EventSynchronisation_type"),
				 InclusionPackage.Literals.EVENT_SYNCHRONISATION__PREFIX,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Synchronised Cases feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSynchronisedCasesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_EventSynchronisation_synchronisedCases_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_EventSynchronisation_synchronisedCases_feature", "_UI_EventSynchronisation_type"),
				 InclusionPackage.Literals.EVENT_SYNCHRONISATION__SYNCHRONISED_CASES,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Actual Parameters feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addActualParametersPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_EventSynchronisation_actualParameters_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_EventSynchronisation_actualParameters_feature", "_UI_EventSynchronisation_type"),
				 InclusionPackage.Literals.EVENT_SYNCHRONISATION__ACTUAL_PARAMETERS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns EventSynchronisation.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/EventSynchronisation"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((EventSynchronisation)object).getReference();
		return label == null || label.length() == 0 ?
			getString("_UI_EventSynchronisation_type") :
			getString("_UI_EventSynchronisation_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(EventSynchronisation.class)) {
			case InclusionPackage.EVENT_SYNCHRONISATION__PREFIX:
			case InclusionPackage.EVENT_SYNCHRONISATION__ACTUAL_PARAMETERS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
		
			
		if (object instanceof EObject && 
			InclusionPackage.Literals.MACHINE_INCLUSION.getEAnnotation("org.eventb.emf.core.extendedMetaClasses") == null  || 
			InclusionPackage.Literals.MACHINE_INCLUSION.getEAnnotation("org.eventb.emf.core.extendedMetaClasses").getReferences().contains(((EObject)object).eClass()))
		
			newChildDescriptors.add
				(createChildParameter
					(CorePackage.Literals.EVENT_BELEMENT__EXTENSIONS,
				 	InclusionFactory.eINSTANCE.createMachineInclusion()));
		
			
		if (object instanceof EObject && 
			InclusionPackage.Literals.EVENT_SYNCHRONISATION.getEAnnotation("org.eventb.emf.core.extendedMetaClasses") == null  || 
			InclusionPackage.Literals.EVENT_SYNCHRONISATION.getEAnnotation("org.eventb.emf.core.extendedMetaClasses").getReferences().contains(((EObject)object).eClass()))
		
			newChildDescriptors.add
				(createChildParameter
					(CorePackage.Literals.EVENT_BELEMENT__EXTENSIONS,
				 	InclusionFactory.eINSTANCE.createEventSynchronisation()));
		
			
		if (object instanceof EObject && 
			CoreextensionPackage.Literals.EVENT_CASES.getEAnnotation("org.eventb.emf.core.extendedMetaClasses") == null  || 
			CoreextensionPackage.Literals.EVENT_CASES.getEAnnotation("org.eventb.emf.core.extendedMetaClasses").getReferences().contains(((EObject)object).eClass()))
		
			newChildDescriptors.add
				(createChildParameter
					(CorePackage.Literals.EVENT_BELEMENT__EXTENSIONS,
				 	CoreextensionFactory.eINSTANCE.createEventCases()));
	}

}
