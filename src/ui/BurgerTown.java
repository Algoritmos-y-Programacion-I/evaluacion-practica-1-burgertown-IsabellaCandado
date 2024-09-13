package ui;

import java.util.Scanner;

public class BurgerTown {

    public static Scanner reader;
    public static double[] prices;
    public static int[] units;

    public static void main(String[] args) {

        initializeGlobals();
        menu();
    }

    /**
     * Description: This method is responsible for initiating global variables
     * Pre: The Scanner reader must be declared
     * pos: l Scanner reader is initialized
    */
    public static void initializeGlobals() {

        reader = new Scanner(System.in);

    }

    /**
     * Description: This method is responsible for displaying the menu to the user and displaying the result messages for each functionality
     * pre: The Scanner reader must be initialized
     * pre: The Prices Array must be initialized
    */
    public static void menu() {

        System.out.println("Welcome to Burger Town!");

        int dishTypes = quantitySold();

        boolean exit = false;

        do {

            System.out.println("\nMain Menu:");
            System.out.println("1. Request prices ($) and quantities for each product reference sold on the day");
            System.out.println("2. Calculate the total number of units sold in the day");
            System.out.println("3. Calculate the average price of product references sold on the day");
            System.out.println("4. Calculate total sales (money collected) during the day");
            System.out.println("5. Check the number of product references that have exceeded a minimum sales limit on the day.");
            System.out.println("6. Exist");
            System.out.print("\nEnter the option to be performed: ");
            int option = reader.nextInt();

            switch (option) {
                case 1:
                    requestData(dishTypes);
                    break;
                case 2:
                    System.out.println("\nThe total number of units sold on the day was: "+calculateTotalUnitsSold());
                    break;
                case 3:
                    System.out.println("\nThe average price of product references sold on the day was: "+calculateAvaragePrice());
                    break;
                case 4:
                    System.out.println("\nThe total sales (money collected) during the day were: "+calculeTotalSales());
                    break;
                case 5:
                    System.out.print("\nEnter the minimum sales limit to be analyzed: ");
                    double limit = reader.nextDouble();
                    System.out.println("\nOf the "+prices.length+" references sold on the day, "+checkDishesOverLimit(limit)+" exceeded the minimum sales limit of "+limit);
                    break;
                case 6:
                    System.out.println("\nThank you for using our services!");
                    exit = true;
                    reader.close();
                    break;

                default:
                    System.out.println("\nPlease, choose a valid option");
                    break;
            }

        } while (!exit);

    }

    /**
     * Description: This method is responsible for asking the user for the number of different product references 
     * sold on the day and initializes with that value the arrangements in charge of storing prices and quantities
     * pre: The Scanner reader must be initialized
     * Pre: Arrays, prices and units must be declared
     * pos: Prices and units arrays are initialized
     * @return int dishTypes
     */
    public static int quantitySold() {

        System.out.println("\nEnter the number of different product references sold on the day: ");
        int dishTypes = reader.nextInt();

        prices = new double[dishTypes];
        units = new int[dishTypes];

        return dishTypes;
    }


    /**
     * Description: This method is responsible for asking the price and the quantities sold of each of the product sold on the day
     * pre: This method needs the return value of quantitySold
     * @param dishTypes int, number of product references sold
     * pos: Price and unit values are stored in the arrays
     */
    public static void requestData(int dishTypes){

        for (int i = 0; i<dishTypes; i++){

            System.out.print("\nEnter the price of the product " + (i+1) + ": ");
            double priceTemp= reader.nextDouble();
            prices[i]=priceTemp;

            System.out.print("\nEnter the amount sold of the product " + (i+1) + ": ");
            int unitTemp= reader.nextInt();
            units[i]=unitTemp;
        }
    }

    /**
     * Description: This method calculates the total number of dishes sold during the day
     * pre: This method requires values to be entered into the units array
     * @return int totalUnits, total number of dishes sold
     */
    public static int calculateTotalUnitsSold(){

        int totalUnits = 0;

        for (int i = 0; i<units.length; i++){
            totalUnits = totalUnits + units[i];
        }

        return totalUnits;
    }

    /**
     * Description: This method calculates the average price of product references sold during the day
     * pre: This method requires values to be entered into the units prices
     * @return double avarage, average price of products sold
     */
    public static double calculateAvaragePrice(){
        
        double totalPrices = 0;

        for (int i = 0; i<prices.length; i++){
            totalPrices = totalPrices + prices[i];
        }

        double avarage = (totalPrices/prices.length);

        return avarage;
    }

    /**
     * Description: This method calculates the money collected during the day
     * pre: This method requires values to be entered into the units prices
     * @return double totalSales, money collected during the day
     */
    public static double calculeTotalSales(){

        double totalSales = 0;

        for (int i = 0; i<prices.length; i++){
            totalSales = totalSales + (units[i] * prices[i]);
        }

        return totalSales;
    }

    /**
     * Description: This method counts the number of product references that exceeded the minimum sales limit
     * @param double limit, the minimum sales limit
     * @return int  counter, number of references Products that exceed the limit
     */

    public static int checkDishesOverLimit(double limit){

        double totalPerProduct = 0;
        int counter = 0;

        for (int i = 0; i<prices.length; i++){
            totalPerProduct = (units[i] * prices[i]);

            if (totalPerProduct>=limit) {
                counter++;
            }
        }

        return counter;
    }

}
