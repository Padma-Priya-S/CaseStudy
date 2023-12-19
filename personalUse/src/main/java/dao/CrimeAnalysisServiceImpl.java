package dao;

import c.myexceptions.IncidentNumberNotFoundException;
import entity.Case;
import entity.Incident;
import entity.Report;
import entity.Status;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import util.DbConnection;

/**
 * this class is a dao layer.
 */
public class CrimeAnalysisServiceImpl implements IcrimeAnalysisService {
  private Connection connection;
  private int currentIncidentId;
  
  public CrimeAnalysisServiceImpl() {
    this.connection = DbConnection.getConnection();
    this.currentIncidentId = getMaxIncidentIdFromDb();
  }

  @Override
  public boolean createIncident(Incident incident) {
    incident.setIncidentId(currentIncidentId);
    String insertQuery = "INSERT INTO Incidents (IncidentId, IncidentType, IncidentDate,"
        + " Latitude, Longitude, Description, Status, VictimID, SuspectID, AgencyID) "
                             + "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(
        insertQuery, Statement.RETURN_GENERATED_KEYS)) {
      preparedStatement.setInt(1, incident.getIncidentId());
      preparedStatement.setString(2, incident.getIncidentType());
      preparedStatement.setDate(3, new java.sql.Date(incident.getIncidentDate().getTime()));
      preparedStatement.setDouble(4, incident.getLatitude());
      preparedStatement.setDouble(5, incident.getLongitude());
      preparedStatement.setString(6, incident.getDescription());
      preparedStatement.setString(7, incident.getStatus());
      preparedStatement.setInt(8, incident.getVictimId());
      preparedStatement.setInt(9, incident.getSuspectId());
      preparedStatement.setInt(10, incident.getAgencyId());           
      currentIncidentId++;
      int rowsAffected = preparedStatement.executeUpdate();
      if (rowsAffected > 0) {
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
          int generatedId = generatedKeys.getInt(1);
          incident.setIncidentId(generatedId); // Set the generated ID back to the Incident object
        }
        return true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean updateIncidentStatus(Status status, int incidentId) {
    String updateQuery = "UPDATE Incidents SET Status = ? WHERE IncidentID = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
      preparedStatement.setString(1, status.name());
      preparedStatement.setInt(2, incidentId);
      int rowsAffected = preparedStatement.executeUpdate();
      return rowsAffected > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public Collection<Incident> getIncidentsInDateRange(Date startDate, Date endDate) {
    Collection<Incident> incidents = new ArrayList<>();
    String selectQuery = "SELECT * FROM Incidents WHERE IncidentDate BETWEEN ? AND ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
      preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
      preparedStatement.setDate(2, new java.sql.Date(endDate.getTime()));
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Incident incident = new Incident();
        incident.setIncidentId(resultSet.getInt("IncidentID"));
        incident.setIncidentType(resultSet.getString("IncidentType"));
        incident.setIncidentDate(resultSet.getDate("IncidentDate"));
        incident.setLatitude(resultSet.getDouble("Latitude"));
        incident.setLongitude(resultSet.getDouble("Longitude"));
        incident.setDescription(resultSet.getString("Description"));
        incident.setStatus(resultSet.getString("Status"));
        incident.setVictimId(resultSet.getInt("VictimID"));
        incident.setSuspectId(resultSet.getInt("SuspectID"));
        incident.setAgencyId(resultSet.getInt("AgencyID"));
        incidents.add(incident);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return incidents;
  }

  @Override
  public Collection<Incident> searchIncidents(String criteria) {
    Collection<Incident> incidents = new ArrayList<>();
    String selectQuery = "SELECT * FROM Incidents WHERE IncidentType = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
      preparedStatement.setString(1, criteria);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Incident incident = new Incident();
        incident.setIncidentId(resultSet.getInt("IncidentID"));
        incident.setIncidentType(resultSet.getString("IncidentType"));
        incident.setIncidentDate(resultSet.getDate("IncidentDate"));
        incident.setLatitude(resultSet.getDouble("Latitude"));
        incident.setLongitude(resultSet.getDouble("Longitude"));
        incident.setDescription(resultSet.getString("Description"));
        incident.setStatus(resultSet.getString("Status"));
        incident.setVictimId(resultSet.getInt("VictimID"));
        incident.setSuspectId(resultSet.getInt("SuspectID"));
        incident.setAgencyId(resultSet.getInt("AgencyID"));
        incidents.add(incident);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return incidents;
  }

  @Override
  public Report generateIncidentReport(Incident incident) {
    String reportDetails = "Incident Report Details:\n\n";
    reportDetails += "Incident ID: " + incident.getIncidentId() + "\n";
    reportDetails += "Incident Type: " + incident.getIncidentType() + "\n";
    reportDetails += "Incident Date: " + incident.getIncidentDate() + "\n";
    reportDetails += "Location: (" + incident.getLatitude() + ""
        + ", " + incident.getLongitude() + ")\n";
    reportDetails += "Description: " + incident.getDescription() + "\n";
    reportDetails += "Status: " + incident.getStatus() + "\n";
    reportDetails += "Victim ID: " + incident.getVictimId() + "\n";
    reportDetails += "Suspect ID: " + incident.getSuspectId() + "\n";
    reportDetails += "Agency ID: " + incident.getAgencyId() + "\n";
    System.out.println(reportDetails);
    Report report = new Report();
    report.setReportDetails(reportDetails);
    report.setIncidentId(incident.getIncidentId());
    return report;
  }

  /**
   * this method takes caseID as input.
   * And gives corresponding case details.

   * @param caseId the primary key
   * @return case object
   */
  public Case getCaseDetails(int caseId) {
    Case retrievedCase = null;
    String query = "SELECT * FROM Cases WHERE CaseID = ?";           
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setInt(1, caseId);               
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        if (resultSet.next()) {
          int retrievedCaseId = resultSet.getInt("CaseID");
          String retrievedCaseDescription = resultSet.getString("CaseDescription");
          int incidentId = resultSet.getInt("IncidentId");
          retrievedCase = new Case(retrievedCaseId, 
              retrievedCaseDescription, incidentId);                      
          retrievedCase.setAssociatedIncidents(getIncidentsForCase(connection, incidentId));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return retrievedCase;
  }
  
  private Collection<Incident> getIncidentsForCase(Connection connection, 
      int incidentId) throws SQLException {
    Collection<Incident> incidents = new ArrayList<>();       
    String query = "SELECT * FROM Incidents WHERE incidentId = ?";      
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setInt(1, incidentId);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
          int incidentIdd = resultSet.getInt("IncidentID");
          String incidentType = resultSet.getString("IncidentType");
          Date incidentDate = resultSet.getDate("IncidentDate");
          double latitude = resultSet.getDouble("Latitude");
          double longitude = resultSet.getDouble("Longitude");
          String description = resultSet.getString("Description");
          String status = resultSet.getString("Status");
          int victimId = resultSet.getInt("VictimID");
          int suspectId = resultSet.getInt("SuspectID");
          int agencyId = resultSet.getInt("AgencyID");
          Incident incident = new Incident();
          incident.setIncidentId(incidentIdd);
          incident.setIncidentType(incidentType);
          incident.setIncidentDate(incidentDate);
          incident.setLatitude(latitude);
          incident.setLongitude(longitude);
          incident.setDescription(description);
          incident.setStatus(status);
          incident.setVictimId(victimId);
          incident.setSuspectId(suspectId);
          incident.setAgencyId(agencyId);
          incidents.add(incident);
        }
      }
    }
    return incidents;
  }

  @Override
  public boolean updateCaseDetails(Case caseDetails) {
    boolean updated = false;
    String query = "UPDATE Cases SET CaseDescription = ? WHERE CaseID = ?";            
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, caseDetails.getCaseDescription());
      preparedStatement.setInt(2, caseDetails.getCaseId());
      int rowsUpdated = preparedStatement.executeUpdate();
      updated = rowsUpdated > 0;
    }  catch (SQLException e) {
      e.printStackTrace();
    }
    return updated;
  }

  @Override
  public Collection<Case> getAllCases() {
    Collection<Case> allCases = new ArrayList<>();
    String query = "SELECT * FROM Cases";           
    try (PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery()) {
      while (resultSet.next()) {
        int caseId = resultSet.getInt("CaseId");
        String caseDescription = resultSet.getString("CaseDescription");
        int incidentId = resultSet.getInt("IncidentId");
        Case retrievedCase = new Case(caseId, caseDescription, incidentId);
        allCases.add(retrievedCase);
      }
    }  catch (SQLException e) {
      e.printStackTrace();
    }
    return allCases;
  }

  @Override
  public Incident getIncidentById(int incidentId) throws IncidentNumberNotFoundException {
    Incident incident = null;
    String selectQuery = "SELECT * FROM Incidents WHERE IncidentID = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
      preparedStatement.setInt(1, incidentId);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        incident = new Incident();
        incident.setIncidentId(resultSet.getInt("IncidentID"));
        incident.setIncidentType(resultSet.getString("IncidentType"));
        incident.setIncidentDate(resultSet.getDate("IncidentDate"));
        incident.setLatitude(resultSet.getDouble("Latitude"));
        incident.setLongitude(resultSet.getDouble("Longitude"));
        incident.setDescription(resultSet.getString("Description"));
        incident.setStatus(resultSet.getString("Status"));
        incident.setVictimId(resultSet.getInt("VictimID"));
        incident.setSuspectId(resultSet.getInt("SuspectID"));
        incident.setAgencyId(resultSet.getInt("AgencyID"));
      } else {
        throw new IncidentNumberNotFoundException("Error retrieving incident by ID: " + incidentId);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return incident;
  }
  
  private int getMaxIncidentIdFromDb() {
    int maxId = 0;
    String query = "SELECT MAX(IncidentID) AS MaxID FROM Incidents";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery()) {
      if (resultSet.next()) {
        maxId = resultSet.getInt("MaxID");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return maxId + 1;
  }

  @Override
  public boolean createCase(String caseDescription, int caseId, int incidentId) {
    String query = "insert into cases(caseId,caseDescription,incidentId) values(?,?,?)";
    try (
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setInt(1, caseId);
      preparedStatement.setString(2, caseDescription);
      preparedStatement.setInt(3, incidentId);
      int rowsAffected = preparedStatement.executeUpdate();
      return rowsAffected > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
}