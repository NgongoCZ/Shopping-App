import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class ShoppingAppLogic {

    private ArrayList<ShoppingCentre> listOfShoppingCenters;
    private Scanner myScanner;
    private ArrayList<Product> listOfProducts;
    private ArrayList<Purchase> purchaseList;
    private ArrayList<Customer> listOfCustomers;

    public ShoppingAppLogic(Scanner myScanner) {
        this.myScanner = myScanner;
        this.listOfShoppingCenters = new ArrayList<>();
        this.listOfProducts = new ArrayList<>();
        this.purchaseList = new ArrayList<>();
        this.listOfCustomers = new ArrayList<>();
    }

    // start of the program contains main commands
    public void start() {
        String command = "";
        while (true) {
            System.out.println("Hi what do you want to do ? For the list of all commands, write 'list'");

            /*
            ask about this problem:
            if (myScanner.nextLine().equals("list")){
                showCommands();
            }
            prints list of commands but then does not react to any commands
            reacts to the second command only after pressing "enter"
            does not react to any command typed after the second one
             */

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
                showCommands();
            }
        }
    }

    public void switchStart() {

        String command;
        boolean continueReading = true;
        do {
            System.out.println("Hi, enter command. To show all commands, type 'list'");
            command = myScanner.nextLine();

            switch (command) {
                case "list" -> showCommands();
                case "create_customer" -> createCustomer();
                case "create_center" -> createShoppingCentre();
                case "show_center" ->{
                    System.out.println("Which shopping center are you looking for ?");
                    printShoppingCenter(getShoppingCenterByName(myScanner.nextLine()));
                }
                case "create_product" -> createProduct();
                case "show_products" -> printProducts();
                case "create_purchase" -> makePurchase();
                case "exit" -> {
                    System.out.println("Program exit, have a nice day");
                    continueReading = false;
                }
                default -> System.out.println("Wrong input, please try again");
            }
        } while (continueReading);
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

        /*
        is it better to use this condition in the method,
        or should the listOfShoppingCenters be checked
        in the start() or switchStart() method ?
         */
        if (listOfShoppingCenters.isEmpty()) {
            System.out.println("No shopping centers. Please create a shopping center first");
        } else {
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

                // adding new customer to the listOfCustomers
                listOfCustomers.add(newCustomer);


                System.out.println("Do you wat to create another customer ? y/n");
                String answer = myScanner.nextLine();
                if (answer.equals("n")) {
                    System.out.println("Exit customer creation, adding all created customers into the list");
                    break;
                }
            }
        }
    }

    // method for printing all customer names
    public void printAllCustomerNames(){
        for (Customer customer : listOfCustomers){
            System.out.println(customer.getFirstName() + " " + customer.getSecondName());
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

    // method for printing all Shopping Center names
    public void printAllShoppingCenterNames(){
        for (ShoppingCentre shop : listOfShoppingCenters){
            System.out.println(shop.getShopName());
        }
    }

    public void showCommands() {
        System.out.println("exit - 'exits the program'\ncreate_center - 'creates a new shopping center\ncreate_customer - 'creates a new customer'\ncreate_product - 'creates new product'\nshow_center - 'shows a list of shopping centers'\nshow_products - 'shows list of all products'\ncreate_purchase - 'creates a new purchase'");
    }

    // method for creating and saving new product int listOfProducts
    public void createProduct(){
        System.out.println("Welcome to the product creation");
        String command;

        while (true){
            System.out.println("To create a new product type: 'create', to exit product creation type: 'cancel'");
            command = myScanner.nextLine();
            if (command.equals("cancel")){
                System.out.println("Product creation cancelled, have a nice day");
                break;
            }else if(command.equals("create")){
                System.out.println("Insert product name");
                String productName = myScanner.nextLine();
                if (productName.isBlank()){
                    System.out.println("Wrong name, please try again");
                    continue;
                }

                System.out.println("Insert product price");
                double productPrice = Double.parseDouble(myScanner.nextLine());
                if (productPrice <= 0.00 || productPrice > 999999.00){
                    System.out.println("Wrong price, please try again");
                    continue;
                }

                System.out.println("Insert product ID (integers only");

                long productId;
                try{
                    productId = Long.parseLong(myScanner.nextLine());
                    if (productId <= 0 || productId > 999999999){
                        System.out.println("Wrong ID, please try again");
                        continue;
                    }
                }catch (NumberFormatException e){
                    System.out.println("Add only integers");
                    continue;
                }

                System.out.println("A new product has been created. Do you want to 'discard' / 'save' / 'check' the product ?");

                String afterCreation;

                Product newProduct = new Product(productName, productPrice, productId);
                boolean continueReading = true;

                while (continueReading) {
                    afterCreation = myScanner.nextLine();
                    switch (afterCreation){
                        case "discard" -> {
                            System.out.println("A new product discarded");
                            newProduct = null;
                            continueReading = false;
                        }
                        case "save" -> {
                            listOfProducts.add(newProduct);
                            System.out.println("Product has been saved");
                            continueReading = false;
                        }
                        case "check" -> {
                            System.out.println(newProduct);
                            System.out.println("Do you want to 'discard' / 'save' / 'check' the product ?");
                        }
                        default -> {
                            System.out.println("Wrong command, please try again");
                        }
                    }
                }

                System.out.println("Do you want to create a new product ? y/n");
                if (myScanner.nextLine().equals("n")){
                    System.out.println("Product creation cancelled, have a nice day");
                    break;
                }
            }
        }
    }

    public void printProducts(){
        for (Product product : listOfProducts){
            System.out.println(product);
        }
    }

    public Product getProductByName(String productName){
        for (Product product : listOfProducts){
            if (productName.equals(product.getProductName())){
                return product;
            }
        }
        return null;
    }

    // method for creating a purchase
    public void makePurchase(){
        String continuePurchase;

            while (true){
                System.out.println("Do you want to create a new purchase ? y/n");
                continuePurchase = myScanner.nextLine();
                if (continuePurchase.equals("n")){
                    System.out.println("Purchase creation terminated. Have a nice day");
                    break;
                }
                System.out.println("In which shopping center is the purchase made ? ");
                printAllShoppingCenterNames();
                String findCenterName = myScanner.nextLine();
                String shoppingCenterName;
                if (findCenterName.equals(getShoppingCenterByName(findCenterName).getShopName())){
                    shoppingCenterName = findCenterName;
                }else {
                    System.out.println("Wrong shopping center name. Please try again");
                    continue;
                }

                String customerFirstName;
                String customerSecondName;
                try{
                    System.out.println("Which customer makes the purchase ?");
                    printAllCustomerNames();
                    String getFullCustomerNAme = (myScanner.nextLine()).toLowerCase();
                    String[]customerFullName = getFullCustomerNAme.split(" ");
                    customerFirstName = customerFullName[0];
                    customerSecondName = customerFullName[1];
                }catch (IndexOutOfBoundsException e){
                    System.out.println("Wrong name input. Write full name once again");

                    System.out.println("Which customer makes the purchase ?");
                    printAllCustomerNames();
                    String getFullCustomerNAme = (myScanner.nextLine()).toLowerCase();
                    String[]customerFullName = getFullCustomerNAme.split(" ");
                    customerFirstName = customerFullName[0];
                    customerSecondName = customerFullName[1];
                }
                // how to add another exception "ArrayOutOfBoundException"

                int customerID = 0;

                for (Customer customer : listOfCustomers){
                    if (customer.getFirstName().toLowerCase().equals(customerFirstName) && customer.getSecondName().toLowerCase().equals(customerSecondName)){
                        customerID = customer.getCustomerId();
                    }else {
                        System.out.println("Wrong customer name. Please try again");
                        continue;
                    }
                }

                System.out.println("Proceeding to list of products. A new shopping basket created");
                ArrayList<Product>shoppingBasket = new ArrayList<>();
                double price = 0;
                boolean isFull = false;

                do {
                    System.out.println("Which product to buy ?");
                    if (listOfProducts.isEmpty()){
                        System.out.println("There are no product. Sorry");
                        break;
                    }else {
                        String productName = myScanner.nextLine();
                        if (listOfProducts.contains(getProductByName(productName))){
                            shoppingBasket.add(getProductByName(productName));
                            price += (double) getProductByName(productName).getProductPrice();
                        }else {
                            System.out.println("There is no such product. Please try again");
                            continue;
                        }
                    }

                    System.out.println("Do you want to add another product ? y/n");
                    String continueShopping = myScanner.nextLine();
                    isFull = continueShopping.equals("y") ? true : false;

                }while (isFull);

                System.out.println("Generating purchase Id...");
                Random randomNum = new Random();
                long purchaseId = randomNum.nextLong(999) + 1;

                purchaseList.add(new Purchase(purchaseId, price, customerID, shoppingCenterName ));
                System.out.println("A new purchase record was created");
                System.out.println(purchaseList.getLast());
            }
        }
    }


/*
should I write information outputs for user in methods
or should I write them in the start()/ switchStart() methods ?
 */
