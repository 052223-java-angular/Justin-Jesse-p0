package com.Revature.eCommerce;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import com.Revature.eCommerce.services.RouterService;
import com.Revature.eCommerce.utils.ConnectionFaction;
import com.Revature.eCommerce.utils.Session;
//import com.Revature.eCommerce.services.ProductService;
//import com.Revature.eCommerce.dao.ProductDAO;
import com.Revature.eCommerce.models.Product;
public class eCommerce {
  public static void main(String args[]) throws ClassNotFoundException, IOException, SQLException {
    System.out.println(ConnectionFaction.getInstance());//if hashcode displays connection is successful, will delete
    Scanner scan = new Scanner(System.in);
    RouterService router = new RouterService(new Session(), new Product());
    router.navigate("/home", scan, "");
    scan.close();
  }
}
