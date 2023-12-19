package entity;

import java.util.ArrayList;
import java.util.Collection;

/**
 * this class basically creates a case for an incident.
 */
public class Case {
  private int caseId;
  private String caseDescription;
  private int incidentId;
  private Collection<Incident> associatedIncidents;

  /**
   * This is overloaded constructor.

   * @param caseId the primary key
   * @param caseDescription details of incident
   * @param incidentId corresponding incident
   */
  public Case(int caseId, String caseDescription, int incidentId) {
    this.caseId = caseId;
    this.caseDescription = caseDescription;
    this.setIncidentId(incidentId);
    this.associatedIncidents = new ArrayList<>();
  }

  public int getCaseId() {
    return caseId;
  }

  public void setCaseId(int caseId) {
    this.caseId = caseId;
  }

  public String getCaseDescription() {
    return caseDescription;
  }

  public void setCaseDescription(String caseDescription) {
    this.caseDescription = caseDescription;
  }

  public Collection<Incident> getAssociatedIncidents() {
    return associatedIncidents;
  }

  public void setAssociatedIncidents(Collection<Incident> associatedIncidents) {
    this.associatedIncidents = associatedIncidents;
  }

  public void addIncident(Incident incident) {
    this.associatedIncidents.add(incident);
  }

  public int getIncidentId() {
    return incidentId;
  }

  public void setIncidentId(int incidentId) {
    this.incidentId = incidentId;
  }
  
  /**
   * this method is to print all the attributes of case object.
   */
  public void printDetails() {
    System.out.println("Case ID: " + caseId);
    System.out.println("Case Description: " + caseDescription);
    System.out.println("Incident ID: " + incidentId);
    System.out.println("----------------------");
  }
}
