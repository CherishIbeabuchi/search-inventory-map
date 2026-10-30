package com.pluralsight;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;


public class MapInventory {

    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String YELLOW = "\u001B[33m";

    //? The main method
    public static void main(String[] args) {

        /// Always cScans for my User input
        Scanner cScans = new Scanner(System.in);

        //TODO New Character unlocked?? Load my products from my file to the map
        HashMap<Integer, Product > cInventory = loadInventory();

        //? Stores if the user's decision to continue searching
        String cChoice  ="yes";

        //?A fancy Header
        System.out.println(
                YELLOW +
                        """
                 \n♞>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>♞
                  ───────────────────────────────────────
                         ♞ KNIGHT INVENTORY MAP ♞
                  ───────────────────────────────────────
                ♞<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<♞
                """ +
                        RESET);

        /// ? Continue searching while the user answers "yes"
        while(cChoice.equalsIgnoreCase("yes")) {

            /// Ask the user for the Product ID
            System.out.print( WHITE +
                    "Enter Product ID: " + RESET);

            int productId = cScans.nextInt();

            //? Get product using the hashmap key
            Product product = cInventory.get(productId);

            /// Display Product info if a Matching ID exists
            if (product != null) {

                System.out.println( CYAN + """
                ♞===================================♞
                            PRODUCT FOUND
                ♞====================================♞
                """ + RESET);
                System.out.println("Product ID       : " + product.getProductId());
                System.out.println("Name of Product  : " + product.getName());
                System.out.printf("Price of Product : $%.2f%n " , product.getPrice());

            //? if it doesn't then I'm sorry
            } else {

                System.out.println(WHITE +"Product not found." + RESET);

            }
            cScans.nextLine(); /// clear the line

            //? Ask my user for another search
            System.out.println(WHITE + "\nDo you want to search again?" + RESET);

             cChoice = cScans.nextLine();

        }
        System.out.println( BLUE +"""
        ====================================================
                       Inventory Search Complete
        ><><><><><><><><><><><><><><><><><><><><><><>><><><>
               Thank you for using the Program Goodbye!
        ====================================================
        """ + RESET);

        cScans.close(); /// Close up Scans
    }
    /// Reads my file and load my products into a Hashmap
    public static HashMap<Integer, Product> loadInventory() {

        //? Stores My Product objects using the Product ID as the key
        HashMap<Integer, Product> inventory = new HashMap<>();

        /// Open up my inventory.csv for a deep dive (reading)
        try {
            BufferedReader cReader = new BufferedReader(new FileReader("inventory.csv"));
            String cLine;

            //? Read one a line at a time until the end of my file
            while ((cLine = cReader.readLine()) != null) {

                /// Split the line...
                String[] productInfo = cLine.split("\\|");

                /// Convert Product from String to Integer
                int productId = Integer.parseInt(productInfo[0]);

                //? Store my Product name
                String name = productInfo[1];

                /// Convert to Double
                double price = Double.parseDouble(productInfo[2]);

                //?Now Create //<></>
                Product cProduct = new Product(productId,name,price);
                inventory.put(productId,cProduct);

            }


        } catch (Exception e) {

            /// If we can't find your file then oops.
            System.out.println("Error reading this file.");
        }
        //? Se Fi Ni.
        return inventory;
    }
}
