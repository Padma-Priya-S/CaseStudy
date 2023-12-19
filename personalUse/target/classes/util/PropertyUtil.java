package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * this class is to create url using properties.
 */
public class PropertyUtil {
  /**
   * this method is used to get properties from a file.

   * @return the url to connect to database
   */
  public static String getPropertyString() {
    Properties properties = new Properties();
    FileInputStream input = null;
    try {
      input = new FileInputStream("database.properties");
      properties.load(input);
      String username = properties.getProperty("username");
      String password = properties.getProperty("password");
      String url = properties.getProperty("url");
      return url + "?user=" + username + "&password=" + password;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
