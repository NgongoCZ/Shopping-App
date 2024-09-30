import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingAppLogic {

    private final ArrayList<ShoppingCentre> listOfShoppingCenters;
    private final Scanner myScanner;

    public ShoppingAppLogic(Scanner myScanner) {
        this.myScanner = myScanner;
        this.listOfShoppingCenters = new ArrayList<>();
    }

    // start of the program contains main commands
    public void start() {
        String command = "";
        while (true) {
            System.out.println("Hi what do you want to do ? For the list of all commands, write 'list'");
            command = myScanner.nextLine();

            if (command.equals("create_customer")) {
                if (listOfShoppingCenters.isEmpty()) {
                    System.out.println("The list of shopping centers is empty. Please use the 'create_center' command first");
                    continue;
                }
                createCustomer();
            } else if (command.equals("create_center")) {
                createShoppingCentre();
            } else if (command.equals("exit")) {
                System.out.println("Have a nice day");
                break;
            } else if (command.equals("show_center")) {
                System.out.println("Which shopping center do you want to print ?");
                printShoppingCenter(getShoppingCenterByName(myScanner.nextLine()));
            } else if (command.equals("list")) {
                System.out.println(showCommands());
            }
        }
    }

    // creates new shopping center
    public void createShoppingCentre() {
        String answer = "y";
        do {
            System.out.println("Create a new Shopping Centre");
            System.out.println("Enter a name of the shop");
            String shopName = myScanner.nextLine();
            if (shopName.isBlank()) {
                System.out.println("Wrong input, please try again");
                continue;
            }
            ShoppingCentre newShoppingCentre = new ShoppingCentre(shopName);
            listOfShoppingCenters.add(newShoppingCentre);
            System.out.println("Do you want to create another shopping center ? y/n");
            answer = myScanner.nextLine();
        } while (answer.equals("y"));
    }

    // creates a new customer and adds it to a shopping center
    public void createCustomer() {
        while (true) {
            System.out.println("Crate a new customer");
            System.out.println("What is the customers First Name ?");
            String firstName = myScanner.nextLine();
            if (firstName.isBlank()) {
                System.out.println("Wrong input, please try again");
                continue;
            }

            System.out.println("What is the customers Second Name ?");
            String secondName = myScanner.nextLine();
            if (secondName.isBlank()) {
                System.out.println("Wrong input, please try again");
                continue;
            }

            System.out.println("What is the Customer ID ? ID must be higher than 0 and less than 9999999");
            int customerId;
            // checks the input data type
            try {
                customerId = Integer.parseInt(myScanner.nextLine());
                if ((customerId <= 0) || (customerId >= 9999999)) {
                    System.out.println("Wrong input, please try again");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Add only integers");
                continue;
            }

            // setting customer password
            System.out.println("Set up Customer Password. Password must be longer than 4 characters");
            String customerPassword = myScanner.nextLine();
            if ((customerPassword.isBlank()) || (customerPassword.length() < 4)) {
                System.out.println("Wrong input, please try again");
                continue;
            }

            // creating a new customer
            Customer newCustomer = new Customer(firstName, secondName, customerId, customerPassword);
            System.out.println("In which Shopping centre do you want to add this customer ?");
            String choseShoppingCentre = myScanner.nextLine();

            // adding the new customer into the chosen shop
            getShoppingCenterByName(choseShoppingCentre).addCustomer(newCustomer);
            System.out.println("Customer added to the " + choseShoppingCentre + " center");


            System.out.println("Do you wat to create another customer ? y/n");
            String answer = myScanner.nextLine();
            if (answer.equals("n")) {
                System.out.println("Exit customer creating, adding all created customers into the list");
                break;
            }
        }
    }

    // method for picking a particular shopping center from the listOFShoppingCenters by name
    public ShoppingCentre getShoppingCenterByName(String shoppingCentreName) {
        for (ShoppingCentre shoppingCentre : listOfShoppingCenters) {
            if (shoppingCentreName.equals(shoppingCentre.getShopName())) {
                return shoppingCentre;
            }
        }
        return null;
    }

    // method for printing the info about chosen shopping center
    public void printShoppingCenter(ShoppingCentre shoppingCenter) {
        System.out.println(shoppingCenter);
    }

    public String showCommands() {
        return "exit - 'exits the program'\ncreate_center - 'creates a new shopping center\ncreate_customer - 'creates a new customer'";
    }
}
