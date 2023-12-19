package entity;

/**
 * this class is to deal with evidence at incident. 
 */
public class Evidence {
  private int evidenceId;
  private String description;
  private String locationFound;
  private int incidentId;

  public Evidence() {
  }

  /**
   * this is a overloaded constructor.

   * @param evidenceId the primary key
   * @param description of the evidence
   * @param locationFound of evidence
   * @param incidentId to associate with incidents
   */
  public Evidence(int evidenceId, String description, String locationFound, int incidentId) {
    this.evidenceId = evidenceId;
    this.description = description;
    this.locationFound = locationFound;
    this.incidentId = incidentId;
  }

  public int getEvidenceId() {
    return evidenceId;
  }

  public void setEvidenceId(int evidenceId) {
    this.evidenceId = evidenceId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLocationFound() {
    return locationFound;
  }

  public void setLocationFound(String locationFound) {
    this.locationFound = locationFound;
  }

  public int getIncidentId() {
    return incidentId;
  }

  public void setIncidentId(int incidentId) {
    this.incidentId = incidentId;
  }
}
