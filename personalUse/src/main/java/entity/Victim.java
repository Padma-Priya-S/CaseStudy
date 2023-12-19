package entity;

import java.util.Date;

/**
 * this class deals with the victim of the incident. 
 */
public class Victim {
  private int victimId;
  private String firstName;
  private String lastName;
  private Date dateOfBirth;
  private String gender;
  private String contactInformation;

  public Victim() {
  }

  /**
   * this is an overloaded constructor.

   * @param victimId the primary key
   * @param firstName of the victim
   * @param lastName of the victim
   * @param dateOfBirth of the victim
   * @param gender of the victim
   * @param contactInformation of the victim
   */
  public Victim(int victimId, String firstName, String lastName, 
      Date dateOfBirth, String gender, String contactInformation) {
    this.victimId = victimId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
    this.contactInformation = contactInformation;
  }

  public int getVictimId() {
    return victimId;
  }

  public void setVictimId(int victimId) {
    this.victimId = victimId;
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

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getContactInformation() {
    return contactInformation;
  }

  public void setContactInformation(String contactInformation) {
    this.contactInformation = contactInformation;
  }
}
