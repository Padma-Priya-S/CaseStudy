package entity;

/**
 * this class deals with agency corresponding to an incident.
 */
public class LawEnforcementAgency {
  private int agencyId;
  private String agencyName;
  private String jurisdiction;
  private String contactInformation;

  public LawEnforcementAgency() {
  }

  /**
   * Parameterized constructor.

   * @param agencyId the primary key
   * @param agencyName that deals with an incident
   * @param jurisdiction what is the punishment
   * @param contactInformation of the agency
   */
  public LawEnforcementAgency(int agencyId, String agencyName, 
      String jurisdiction, String contactInformation) {
    this.agencyId = agencyId;
    this.agencyName = agencyName;
    this.jurisdiction = jurisdiction;
    this.contactInformation = contactInformation;
  }

  public int getAgencyId() {
    return agencyId;
  }

  public void setAgencyId(int agencyId) {
    this.agencyId = agencyId;
  }

  public String getAgencyName() {
    return agencyName;
  }

  public void setAgencyName(String agencyName) {
    this.agencyName = agencyName;
  }

  public String getJurisdiction() {
    return jurisdiction;
  }

  public void setJurisdiction(String jurisdiction) {
    this.jurisdiction = jurisdiction;
  }

  public String getContactInformation() {
    return contactInformation;
  }

  public void setContactInformation(String contactInformation) {
    this.contactInformation = contactInformation;
  }
}
