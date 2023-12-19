package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * this class is to establish database connection.
 */
public class DbConnection {
  private static Connection connection = null;
  
  /**
   * this method creates database connection.

   * @return a reference to connection.
   */
  public static Connection getConnection() {
    try {
      if (connection == null || connection.isClosed()) {
        String connectionString = PropertyUtil.getPropertyString();
        if (connectionString != null) {
          connection = DriverManager.getConnection(connectionString);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }
  
  public static void main(String[] args) {
    System.out.println(getConnection());
  }
}
