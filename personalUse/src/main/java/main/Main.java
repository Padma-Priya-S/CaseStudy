package main;

import c.myexceptions.IncidentNumberNotFoundException;
import dao.CrimeAnalysisServiceImpl;
import entity.Case;
import entity.Incident;
import entity.Status;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

/**
 * This is the main class of the project.
 * it mainly deals with user interaction.
 */
public class Main {

  /**
  * the main method.

  * @param args command line arguments.
 * @throws IncidentNumberNotFoundException a custom exception
  */
  public static void main(String[] args) throws IncidentNumberNotFoundException {
    CrimeAnalysisServiceImpl service = new CrimeAnalysisServiceImpl();
    Scanner scanner = new Scanner(System.in);
    boolean exit = false;
    while (!exit) {
      System.out.println("Choose an option:");
      System.out.println("1. Create Incident");
      System.out.println("2. Update Incident Status");
      System.out.println("3. Get Incidents in Date Range");
      System.out.println("4. Search Incidents by Type");
      System.out.println("5. Generate Incident Report");
      System.out.println("6. Create Case");
      System.out.println("7. Get Case Details");
      System.out.println("8. Update Case Details");
      System.out.println("9. Get All Cases");
      System.out.println("0. Exit");
      int option = scanner.nextInt();
      scanner.nextLine();
      switch (option) {
        case 0:
          exit = true;
          break;
        case 1:
          Incident newIncident = new Incident();
          System.out.println("Enter Incident Type:");
          String incidentType = scanner.nextLine();
          newIncident.setIncidentType(incidentType);
          System.out.println("Enter Incident Date (yyyy-MM-dd):");
          String incidentDateStr = scanner.nextLine();
          try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date incidentDate = dateFormat.parse(incidentDateStr);
            newIncident.setIncidentDate(incidentDate);
          } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            break;
          }
          System.out.println("Enter Latitude:");
          double latitude = Double.parseDouble(scanner.nextLine());
          newIncident.setLatitude(latitude); 
          System.out.println("Enter Longitude:");
          double longitude = Double.parseDouble(scanner.nextLine());
          newIncident.setLongitude(longitude);
          System.out.println("Enter Description:");
          String description = scanner.nextLine();
          newIncident.setDescription(description);
          System.out.println("Enter Status (OPEN, CLOSED, UNDER_INVESTIGATION):");
          String status = scanner.nextLine();
          newIncident.setStatus(status);
          System.out.println("Enter Victim ID:");
          int victimId = Integer.parseInt(scanner.nextLine());
          newIncident.setVictimId(victimId);
          System.out.println("Enter Suspect ID:");
          int suspectId = Integer.parseInt(scanner.nextLine());
          newIncident.setSuspectId(suspectId);
          System.out.println("Enter Agency ID:");
          int agencyId = Integer.parseInt(scanner.nextLine());
          newIncident.setAgencyId(agencyId);
          boolean created = service.createIncident(newIncident);
          if (created) {
            System.out.println("Incident created successfully. Incident ID: " 
                + newIncident.getIncidentId());
          } else {
            System.out.println("Failed to create incident.");
          }
          break;

        case 2:
          System.out.println("Enter Incident ID:");
          int incidentIdToUpdate = Integer.parseInt(scanner.nextLine());
          System.out.println("Enter New Status (OPEN, CLOSED, UNDER_INVESTIGATION):");
          String newStatus = scanner.nextLine();
          Status statusEnum = Status.valueOf(newStatus);
          boolean updated = service.updateIncidentStatus(statusEnum, incidentIdToUpdate);
          if (updated) {
            System.out.println("Incident status updated successfully.");
          } else {
            System.err.println("Failed to update incident status.");
          }
          break;
        case 3:
          System.out.println("Enter Start Date (yyyy-MM-dd):");
          String startDateStr = scanner.nextLine();
          System.out.println("Enter End Date (yyyy-MM-dd):");
          String endDateStr = scanner.nextLine();
          try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);
            Collection<Incident> incidentsInRange = service.getIncidentsInDateRange(
                startDate, endDate);
            if (!incidentsInRange.isEmpty()) {
              System.out.println("Incidents within the specified date range:");
              for (Incident incident : incidentsInRange) {
                incident.printDetails();
              }
            } else {
              System.err.println("No incidents found within the specified date range.");
            }
          } catch (ParseException e) {
            System.err.println("Invalid date format. Please use yyyy-MM-dd.");
          }
          break;
        case 4:
          System.out.println("Enter Incident Type to search:");
          String incidentTypee = scanner.nextLine();
          Collection<Incident> foundIncidents = service.searchIncidents(incidentTypee);
          if (!foundIncidents.isEmpty()) {
            System.out.println("Incidents found based on the specified criteria:");
            for (Incident incident : foundIncidents) {
              incident.printDetails();;
            }
          } else {
            System.err.println("No incidents found based on the specified criteria.");
          }
          break;
        case 5:
          System.out.println("Enter Incident ID to generate the report:");
          int incidentIdForReport = Integer.parseInt(scanner.nextLine());
          try {
            Incident requestedIncident = service.getIncidentById(incidentIdForReport);
            if (requestedIncident != null) {
              service.generateIncidentReport(requestedIncident);
            } else {
              System.out.println("Incident not found.");
            }
          } catch (IncidentNumberNotFoundException e) {
            System.err.println("Error generating incident report: " + e.getMessage());
          }
          break;
        case 6:
          System.out.println("Enter Case ID:");
          int caseId = Integer.parseInt(scanner.nextLine());
          System.out.println("Enter Case Description:");
          String caseDescription = scanner.nextLine();
          System.out.println("Enter Incident ID:");
          int incidentId = Integer.parseInt(scanner.nextLine());
          if (service.createCase(caseDescription, caseId, incidentId)) {
            System.out.println("Case Created Successfully");
          } else {
            System.err.println("Case can not be created");
          }
          break;
        case 7:
          System.out.println("Enter Case ID:");
          int caseIdToFetch = Integer.parseInt(scanner.nextLine());
          Case retrievedCase = service.getCaseDetails(caseIdToFetch);
          if (retrievedCase != null) {
            System.out.println("Case Details:");
            retrievedCase.printDetails();
          } else {
            System.err.println("Case not found.");
          }
          break;
        case 8:
          System.out.println("Enter Case ID to update:");
          int caseIdToUpdate = Integer.parseInt(scanner.nextLine());
          System.out.println("Enter New Case Description:");
          String newCaseDescription = scanner.nextLine();
          Case caseToUpdate = service.getCaseDetails(caseIdToUpdate);
          if (caseToUpdate != null) {
            caseToUpdate.setCaseDescription(newCaseDescription);
            boolean updatedd = service.updateCaseDetails(caseToUpdate);
            if (updatedd) {
              System.out.println("Case details updated successfully.");
            } else {
              System.err.println("Failed to update case details.");
            }
          } else {
            System.err.println("Case not found.");
          }
          break;
        case 9:
          Collection<Case> allCases = service.getAllCases();
          if (!allCases.isEmpty()) {
            System.out.println("All Cases:");
            for (Case individualCase : allCases) {
              individualCase.printDetails();
            }
          } else {
            System.err.println("No cases found.");
          }
          break;
        default:
          System.err.println("Invalid option. Please choose again.");
          break;
      }
    }
    scanner.close();
  }
}
