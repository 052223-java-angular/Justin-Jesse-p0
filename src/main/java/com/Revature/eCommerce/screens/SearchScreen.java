package com.Revature.eCommerce.screens;
import java.util.Scanner;
import com.Revature.eCommerce.utils.Session;
import com.Revature.eCommerce.models.Product;
import com.Revature.eCommerce.models.Category;
import com.Revature.eCommerce.services.RouterService;
import com.Revature.eCommerce.services.ProductService;
import com.Revature.eCommerce.services.CategoryService;
import java.util.List;
//import com.Revature.eCommerce.dao.ProductDAO;
//import com.Revature.eCommerce.dao.CategoryDAO;
//@AllArgsConstructor
//@NoArgsConstructor
public class SearchScreen implements IScreen {
    private Session session;
    //private Product product;
    private final RouterService router;
    private final ProductService productService;
    private final CategoryService categoryService;
    
    public SearchScreen(RouterService router, Session session, ProductService productService, CategoryService categoryService)
    {
        this.router = router; 
        this.session = session;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void start(Scanner scan) {
        String input = "";

        exit: {
            while (true) {
                clearScreen();
                System.out.println("------------------Product Search--------------------" );
                System.out.println("\nPlease select your option:\n");
                System.out.println("Press [1] to Search By Products Name");
                System.out.println("Press [2] to Search By Category");
                System.out.println("Press [3] to Search By Price");
                System.out.println("Press [X] to return to Menu");

                System.out.print("\nEnter: ");
                input = scan.nextLine();

                switch (input.toLowerCase()) {
                    case "1":
                    DisplaySearchedProductName(scan);
                        break exit;
                    case "2":
                    CategoryDisplay(scan);
                        break exit;
                    case "3":
                    DisplaySearchedProductPricing(scan);
                        break exit;

                    case "x":
                    clearScreen();
                    router.navigate("/menu", scan, "");
                        break exit;

                    default:
                        clearScreen();
                        System.out.println("Invalid Option!");
                        System.out.print("\nPress enter to continue...");
                        scan.nextLine();
                        break exit;
                }
            }
        }
    }
    

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public void DisplaySearchedProductName(Scanner scan) {
        clearScreen();
        System.out.println("Enter Product Name: ");
        String name = scan.nextLine();
        List<Product> productList = productService.findProductByName(name);
        int listSize = productList.size();
        boolean exit = false; 
        int index = 0;
        if (listSize == 0) {
            System.out.println("No products found.");
            System.out.println("Press Enter to return to Menu Screen.");
            scan.nextLine();
            clearScreen();
            router.navigate("/menu", scan, "");
            return;
        }

        while(!exit){

        Product product = productList.get(index);
            System.out.println("Product List:");
            System.out.println("-----------------------------");
            System.out.println("Username: " + session.getUsername() + "\n");
            System.out.println("Product ID: " + product.getProductId());
            System.out.println("Name: " + product.getProductName());
            System.out.println("Category ID: " + product.getCategoryId());
            System.out.println("Pricing: $" + product.getPricing());
            System.out.println("Description: " + product.getDescription());
            System.out.println("-----------------------------");


            System.out.println("\n \nPress Enter to go to next page.");
            System.out.println("Press [B] to go back.");
            System.out.println("Press [A] to go add product to cart.");
            System.out.println("Press [R] to View Reviews.");
            System.out.println("Press [X] to back to Menu Screen.");
            System.out.print("");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("R")) {
                clearScreen();
                exit = true;   
                router.navigate("/reviews", scan, product.getProductId());
 
            }
            if (input.equalsIgnoreCase("X")){
                clearScreen();
                exit = true;
                router.navigate("/menu", scan, "");
                scanner.close();

            }
            else{

                clearScreen();
            if (input.equalsIgnoreCase("B")){
                index--;
            }
            else{
                index++;
            }
                if (index>=listSize){
                    index = 0;
                }
                if(index < 0){
                    index = listSize - 1;
                }
            }
        }
    }

    public void CategoryDisplay(Scanner scan) {
        clearScreen();
        List<Category> categoryList = categoryService.getAllCategories();
        int listSize = categoryList.size();

        int index = 0;

        System.out.println("Category List:");
        System.out.println("-----------------------------");


           // System.out.println("List Size = " + listSize);
           for (int i = 0 ; i < listSize; i++){
            Category category = categoryList.get(i);
            System.out.println("Category " + category.getCategory_ID() + " " + category.getCategory_Name());

           }

            System.out.println("-----------------------------");


            System.out.println("\n \nEnter the Category Number");
            System.out.println("Press [X] to back to Menu Screen.");
            System.out.print("");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().trim();
           

            if (input.equalsIgnoreCase("X")){
                clearScreen();
                router.navigate("/menu", scan, "");
                scanner.close();

            }
           
            try {
                index = Integer.parseInt(input);

            } 
            catch (NumberFormatException e) {
                System.out.println("Invalid Input.");
                System.out.println("Press Enter to return to Menu Screen.");
                scan.nextLine();
                clearScreen();
                router.navigate("/menu", scan, "");
            }
            
            if (index != 0 && index <= listSize ){
               

                clearScreen();
                DisplaySearchedProductCategory(Integer.toString(index));
           
            }
            

            else {
            System.out.println("Invalid Input");
            System.out.println("Press Enter to return to Menu Screen.");
            scan.nextLine();
            clearScreen();
            router.navigate("/menu", scan, "");
            }
        }
    

    public void DisplaySearchedProductCategory(String category) {
        Scanner scan = new Scanner(System.in);
        clearScreen();
        List<Product> productList = productService.findProductByCategory(category);
        int listSize = productList.size();
        boolean exit = false; 
        int index = 0;
        if (listSize == 0) {
            System.out.println("No products found.");
            System.out.println("Press Enter to return to Menu Screen.");
            scan.nextLine();
            clearScreen();
            router.navigate("/menu", scan, "");
            return;
        }

        while(!exit){

        Product product = productList.get(index);
            System.out.println("Product List:");
            System.out.println("-----------------------------");
            System.out.println("Username: " + session.getUsername() + "\n");
            System.out.println("Product ID: " + product.getProductId());
            System.out.println("Name: " + product.getProductName());
            System.out.println("Category ID: " + product.getCategoryId());
            System.out.println("Pricing: $" + product.getPricing());
            System.out.println("Description: " + product.getDescription());
            System.out.println("-----------------------------");


            System.out.println("\n \nPress Enter to go to next page.");
            System.out.println("Press [B] to go back.");
            System.out.println("Press [A] to go add product to cart.");
            System.out.println("Press [R] to View Reviews.");
            System.out.println("Press [X] to back to Menu Screen.");
            System.out.print("");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("R")) {
                clearScreen();
                exit = true;   
                router.navigate("/reviews", scan, product.getProductId());
 
            }
            if (input.equalsIgnoreCase("X")){
                clearScreen();
                exit = true;
                router.navigate("/menu", scan, "");
                scanner.close();

            }
            else{

                clearScreen();
            if (input.equalsIgnoreCase("B")){
                index--;
            }
            else{
                index++;
            }
                if (index>=listSize){
                    index = 0;
                }
                if(index < 0){
                    index = listSize - 1;
                }
            }
        }
    }
    public void DisplaySearchedProductPricing(Scanner scan) {
        clearScreen();
        System.out.println("Enter Minimum Value ");
        int min = scan.nextInt();
        scan.nextLine();
        clearScreen();
    
        System.out.println("Enter Maximum Value ");
        int max = scan.nextInt();
        scan.nextLine();
        clearScreen();

        List<Product> productList = productService.findProductByPricing(min, max);
        int listSize = productList.size();
        boolean exit = false;
        int index = 0;
        
        if (listSize == 0) {
            System.out.println("No products found.");
            System.out.println("Press Enter to return to Menu Screen.");
            scan.nextLine();
            clearScreen();
            router.navigate("/menu", scan, "");
            return;
        }
    
        while (!exit) {
            Product product = productList.get(index);
            System.out.println("Product List:");
            System.out.println("-----------------------------");
            System.out.println("Username: " + session.getUsername() + "\n");
            System.out.println("Product ID: " + product.getProductId());
            System.out.println("Name: " + product.getProductName());
            System.out.println("Category ID: " + product.getCategoryId());
            System.out.println("Pricing: $" + product.getPricing());
            System.out.println("Description: " + product.getDescription());
            System.out.println("-----------------------------");
    
            System.out.println("\n \nPress Enter to go to the next page.");
            System.out.println("Press [B] to go back.");
            System.out.println("Press [A] to add the product to the cart.");
            System.out.println("Press [R] to View Reviews.");
            System.out.println("Press [X] to go back to the Menu Screen.");
            System.out.print("");
            
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("R")) {
                clearScreen();
                exit = true;   
                router.navigate("/reviews", scan, product.getProductId());
 
            }
            if (input.equalsIgnoreCase("X")) {
                clearScreen();
                exit = true;
                router.navigate("/menu", scan, "");
                scanner.close();
                break; // Exit the while loop
            } else {
                clearScreen();
                if (input.equalsIgnoreCase("B")) {
                    index--;
                } else {
                    index++;
                }
                if (index >= listSize) {
                    index = 0;
                }
                if (index < 0) {
                    index = listSize - 1;
                }
            }

        }
    }
}


