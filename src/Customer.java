public class Customer {

    private final String firstName;
    private final String secondName;
    private final int customerId;
    private final String customerPassword;
    private int numberOfVisits;

    public Customer(String firstName, String secondName, int customerId, String customerPassword){
        this.firstName = firstName;
        this.secondName = secondName;
        this.customerId = customerId;
        this.customerPassword = customerPassword;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getSecondName(){
        return secondName;
    }

    public int getCustomerId(){
        return customerId;
    }

    public String getCustomerPassword(){
        return customerPassword;
    }

    public int getNumberOfVisits(){
        return numberOfVisits;
    }

    public String showPassword(){
        return "Customer ID: " + customerId + "\nCustomer Password: " + customerPassword + "\n";
    }

    @Override
    public String toString(){
        return "First name: " + firstName + "\nSecond name: "+ secondName + "\nCustomer ID: " + customerId + "\nNumber of visits: " + numberOfVisits + "\n";
    }
}
