package crimereportingg;

import static org.junit.Assert.assertTrue;

import dao.CrimeAnalysisServiceImpl;
import entity.Incident;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

/**
 * this is to create test cases.
 */
public class CrimeAnalysisServiceTest {
  private CrimeAnalysisServiceImpl service;

  @Before
  public void setUp() {
    service = new CrimeAnalysisServiceImpl();    
  }

  @Test
  public void testCreateIncident() throws ParseException {
    String dateString = "2023-01-15"; 
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = dateFormat.parse(dateString);       
    Incident incident = new Incident(1, "Robbery", date, 40.7128, 74.0060, 
        "Armed robbery in a store", "closed", 1, 7, 2);
    assertTrue(service.createIncident(incident));
  }  
}