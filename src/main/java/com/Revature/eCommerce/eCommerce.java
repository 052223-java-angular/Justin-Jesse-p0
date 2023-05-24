package com.Revature.eCommerce;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import com.Revature.eCommerce.services.routerService;
import com.Revature.eCommerce.utils.Session;
public class eCommerce {
  public static void main(String args[]) throws ClassNotFoundException, IOException, SQLException {
    Scanner scan = new Scanner(System.in);
    routerService router = new routerService(new Session());
    router.navigate("/home", scan);
    scan.close();
  }
}
