package entity;

import java.util.Date;

/**
 * this entity class geenrates report with essential parameters.
 */
public class Report {
  private int reportId;
  private int incidentId;
  private int reportingOfficer;
  private Date reportDate;
  private String reportDetails;
  private String status;

  public Report() {
  }

  /**
   * This is a parameterized constructor.

   * @param reportId the primary key.
   * @param incidentId to link with incidents.
   * @param reportingOfficer the officer who took charge
   * @param reportDate when the report has made
   * @param reportDetails description of the report
   * @param status the incident status
   */
  public Report(int reportId, int incidentId, int reportingOfficer, 
      Date reportDate, String reportDetails, String status) {
    this.reportId = reportId;
    this.incidentId = incidentId;
    this.reportingOfficer = reportingOfficer;
    this.reportDate = reportDate;
    this.reportDetails = reportDetails;
    this.status = status;
  }

  public int getReportId() {
    return reportId;
  }

  public void setReportId(int reportId) {
    this.reportId = reportId;
  }

  public int getIncidentId() {
    return incidentId;
  }

  public void setIncidentId(int incidentId) {
    this.incidentId = incidentId;
  }

  public int getReportingOfficer() {
    return reportingOfficer;
  }

  public void setReportingOfficer(int reportingOfficer) {
    this.reportingOfficer = reportingOfficer;
  }

  public Date getReportDate() {
    return reportDate;
  }

  public void setReportDate(Date reportDate) {
    this.reportDate = reportDate;
  }

  public String getReportDetails() {
    return reportDetails;
  }

  public void setReportDetails(String reportDetails) {
    this.reportDetails = reportDetails;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
