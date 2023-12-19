package entity;

/**
 * this class deals with officers who took charge of an incident.
 */
public class Officer {
  private int officerId;
  private String firstName;
  private String lastName;
  private String badgeNumber;
  private String rank;
  private String contactInformation;
  private int agencyId;

  public Officer() {
  }

  /**
   * this is a paramterized constructor.

   * @param officerId the primary key
   * @param firstName of the officer
   * @param lastName of the officer
   * @param badgeNumber of the officer
   * @param rank of the officer
   * @param contactInformation of the officer
   * @param agencyId of the officer
   */
  public Officer(int officerId, String firstName, String lastName, String badgeNumber, String rank,
                   String contactInformation, int agencyId) {
    this.officerId = officerId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.badgeNumber = badgeNumber;
    this.rank = rank;
    this.contactInformation = contactInformation;
    this.agencyId = agencyId;
  }

  public int getOfficerId() {
    return officerId;
  }

  public void setOfficerId(int officerId) {
    this.officerId = officerId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getBadgeNumber() {
    return badgeNumber;
  }

  public void setBadgeNumber(String badgeNumber) {
    this.badgeNumber = badgeNumber;
  }

  public String getRank() {
    return rank;
  }

  public void setRank(String rank) {
    this.rank = rank;
  }

  public String getContactInformation() {
    return contactInformation;
  }

  public void setContactInformation(String contactInformation) {
    this.contactInformation = contactInformation;
  }

  public int getAgencyId() {
    return agencyId;
  }

  public void setAgencyId(int agencyId) {
    this.agencyId = agencyId;
  }
}
