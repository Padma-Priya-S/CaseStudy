package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * this class deals with the incident that occur.
 */
public class Incident {
  private int incidentId;
  private String incidentType;
  private Date incidentDate;
  private double latitude;
  private double longitude;
  private String description;
  private String status;
  private int victimId;
  private int suspectId;
  private int agencyId;

  public Incident() {
    this.incidentId = 0;
  }

  /**
   * Parameterized constructor.

   * @param incidentId the primary key
   * @param incidentType type of incident that has occured
   * @param incidentDate the date of incident
   * @param latitude of the incident
   * @param longitude of the incident
   * @param description of the incident
   * @param status of the incident
   * @param victimId to associate with victims
   * @param suspectId to associate with suspects
   * @param agencyId to associate with aganecy
   */
  public Incident(int incidentId, String incidentType, 
      Date incidentDate, double latitude, double longitude,
                    String description, String status, int victimId, int suspectId, int agencyId) {
    this.incidentId = incidentId;
    this.incidentType = incidentType;
    this.setIncidentDate(incidentDate);
    this.setLatitude(latitude);
    this.setLongitude(longitude);
    this.setDescription(description);
    this.setStatus(status);
    this.setVictimId(victimId);
    this.setSuspectId(suspectId);
    this.setAgencyId(agencyId);
  }
    
  public int getIncidentId() {
    return incidentId;
  }

  public void setIncidentId(int incidentId) {
    this.incidentId = incidentId;
  }

  public String getIncidentType() {
    return incidentType;
  }

  public void setIncidentType(String incidentType) {
    this.incidentType = incidentType;
  }

  public Date getIncidentDate() {
    return incidentDate;
  }

  public void setIncidentDate(Date incidentDate) {
    this.incidentDate = incidentDate;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getSuspectId() {
    return suspectId;
  }

  public void setSuspectId(int suspectId) {
    this.suspectId = suspectId;
  }

  public int getAgencyId() {
    return agencyId;
  }

  public void setAgencyId(int agencyId) {
    this.agencyId = agencyId;
  }

  public int getVictimId() {
    return victimId;
  }

  public void setVictimId(int victimId) {
    this.victimId = victimId;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  /**
   * this method is to print all the attributes of incident object.
   */
  public void printDetails() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    System.out.println("Incident ID: " + incidentId);
    System.out.println("Incident Type: " + incidentType);
    System.out.println("Incident Date: " + dateFormat.format(incidentDate));
    System.out.println("Latitude: " + latitude);
    System.out.println("Longitude: " + longitude);
    System.out.println("Description: " + description);
    System.out.println("Status: " + status);
    System.out.println("Victim ID: " + victimId);
    System.out.println("Suspect ID: " + suspectId);
    System.out.println("Agency ID: " + agencyId);
  }
   
}

