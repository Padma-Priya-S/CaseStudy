package entity;

import java.util.Date;

/**
 * this class is to deal with the suspects of the incident.
 */
public class Suspect {
  private int suspectId;
  private String firstName;
  private String lastName;
  private Date dateOfBirth;
  private String gender;
  private String contactInformation;

  public Suspect() {
  }

  /**
   * this is a parameterized constructor.

   * @param suspectId the primary key
   * @param firstName of suspect
   * @param lastName of suspect
   * @param dateOfBirth of suspect
   * @param gender of suspect
   * @param contactInformation of suspect
   */
  public Suspect(int suspectId, String firstName, String lastName, 
      Date dateOfBirth, String gender, String contactInformation) {
    this.suspectId = suspectId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
    this.contactInformation = contactInformation;
  }

  public int getSuspectId() {
    return suspectId;
  }

  public void setSuspectId(int suspectId) {
    this.suspectId = suspectId;
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
