package com.Revature.eCommerce;
import com.Revature.eCommerce.models.History;
import com.Revature.eCommerce.models.Product;
import com.Revature.eCommerce.services.RouterService;
import com.Revature.eCommerce.utils.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class eCommerce {

    private static final Logger logger = LogManager.getLogger((eCommerce.class));

  /**
   * Main method for the ecommerce class to start the program
   * @param args
   * @throws ClassNotFoundException
   * @throws IOException
   * @throws SQLException
   */
  public static void main(String args[]) throws ClassNotFoundException, IOException, SQLException
  {
    logger.info("--------------------Start Application--------------------");
    Scanner scan = new Scanner(System.in);
    RouterService router = new RouterService(new Session(), new Product(), new History());
    router.navigate("/home", scan, "");
    logger.info("--------------------End Application--------------------");
    scan.close();
  }
}
