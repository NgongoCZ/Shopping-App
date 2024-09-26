import java.util.ArrayList;

public class ShoppingCentre {

    private final String shopName;
    private final ArrayList<Customer>customerList;
    private int numberOfCustomers;

    public ShoppingCentre(String shopName){
        this.shopName = shopName;
        customerList = new ArrayList<>();
    }

    // adding a new customer to the list
    public void addCustomer(Customer customer){
        customerList.add(customer);
    }

    public void printAllCustomers(){
        for (Customer customer : customerList){
            System.out.println(customer);
        }
    }

    public int getNumberOfCustomers(){
        return customerList.size();
    }

    public String getShopName(){
        return shopName;
    }

    @Override
    public String toString(){
        String customer = "";

        for (Customer person : customerList){
            customer += person + "\n";
        }

        return "Shop name: " + shopName + "\nNumber of Customers: " + getNumberOfCustomers() + "\nCustomers: " + "\n" + customer;
    }
}
