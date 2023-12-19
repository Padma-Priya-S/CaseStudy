package dao;

import c.myexceptions.IncidentNumberNotFoundException;
import entity.Case;
import entity.Incident;
import entity.Report;
import entity.Status;
import java.util.Collection;
import java.util.Date;

/**
 * this is a service provider Interface.
 */
public interface IcrimeAnalysisService {
  boolean createIncident(Incident incident);
  
  boolean updateIncidentStatus(Status status, int incidentId);
  
  Collection<Incident> getIncidentsInDateRange(Date startDate, Date endDate);
  
  Collection<Incident> searchIncidents(String criteria);
  
  Report generateIncidentReport(Incident incident);
  
  boolean createCase(String caseDescription, int caseId, int incidentId);
   
  Case getCaseDetails(int caseId);
  
  boolean updateCaseDetails(Case caseDetails);
   
  Collection<Case> getAllCases();
   
  Incident getIncidentById(int incidentId) throws IncidentNumberNotFoundException;
}
