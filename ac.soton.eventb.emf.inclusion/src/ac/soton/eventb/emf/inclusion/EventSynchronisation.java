/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ac.soton.eventb.emf.inclusion;

import ac.soton.eventb.emf.core.extension.coreextension.EventCases;
import org.eclipse.emf.common.util.EList;
import org.eventb.emf.core.AbstractExtension;

import org.eventb.emf.core.machine.Event;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event Synchronisation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ac.soton.eventb.emf.inclusion.EventSynchronisation#getSynchronisedEvent <em>Synchronised Event</em>}</li>
 *   <li>{@link ac.soton.eventb.emf.inclusion.EventSynchronisation#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link ac.soton.eventb.emf.inclusion.EventSynchronisation#getSynchronisedCases <em>Synchronised Cases</em>}</li>
 *   <li>{@link ac.soton.eventb.emf.inclusion.EventSynchronisation#getActualParameters <em>Actual Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see ac.soton.eventb.emf.inclusion.InclusionPackage#getEventSynchronisation()
 * @model
 * @generated
 */
public interface EventSynchronisation extends AbstractExtension {
	/**
	 * Returns the value of the '<em><b>Synchronised Event</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Synchronised Event</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Synchronised Event</em>' reference.
	 * @see #setSynchronisedEvent(Event)
	 * @see ac.soton.eventb.emf.inclusion.InclusionPackage#getEventSynchronisation_SynchronisedEvent()
	 * @model
	 * @generated
	 */
	Event getSynchronisedEvent();

	/**
	 * Sets the value of the '{@link ac.soton.eventb.emf.inclusion.EventSynchronisation#getSynchronisedEvent <em>Synchronised Event</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Synchronised Event</em>' reference.
	 * @see #getSynchronisedEvent()
	 * @generated
	 */
	void setSynchronisedEvent(Event value);

	/**
	 * Returns the value of the '<em><b>Prefix</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prefix</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prefix</em>' attribute.
	 * @see #setPrefix(String)
	 * @see ac.soton.eventb.emf.inclusion.InclusionPackage#getEventSynchronisation_Prefix()
	 * @model default=""
	 * @generated
	 */
	String getPrefix();

	/**
	 * Sets the value of the '{@link ac.soton.eventb.emf.inclusion.EventSynchronisation#getPrefix <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prefix</em>' attribute.
	 * @see #getPrefix()
	 * @generated
	 */
	void setPrefix(String value);

	/**
	 * Returns the value of the '<em><b>Synchronised Cases</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Synchronised Cases</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Synchronised Cases</em>' reference.
	 * @see #setSynchronisedCases(EventCases)
	 * @see ac.soton.eventb.emf.inclusion.InclusionPackage#getEventSynchronisation_SynchronisedCases()
	 * @model
	 * @generated
	 */
	EventCases getSynchronisedCases();

	/**
	 * Sets the value of the '{@link ac.soton.eventb.emf.inclusion.EventSynchronisation#getSynchronisedCases <em>Synchronised Cases</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Synchronised Cases</em>' reference.
	 * @see #getSynchronisedCases()
	 * @generated
	 */
	void setSynchronisedCases(EventCases value);

	/**
	 * Returns the value of the '<em><b>Actual Parameters</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actual Parameters</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actual Parameters</em>' attribute list.
	 * @see ac.soton.eventb.emf.inclusion.InclusionPackage#getEventSynchronisation_ActualParameters()
	 * @model
	 * @generated
	 */
	EList<String> getActualParameters();

} // EventSynchronisation
