package c.myexceptions;

/**
 * this is a custom exception class.
 */
public class IncidentNumberNotFoundException extends Exception {
  /**
   * this is a default serialization Id.
   */
  private static final long serialVersionUID = 1L;
  
  public IncidentNumberNotFoundException(String message) {
    super(message);
  }
}