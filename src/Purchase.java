import java.util.ArrayList;

public class Purchase {

    private final long purchaseId;
    private final ArrayList<Product> productsPurchased;
    private double price;
    private int customerId;
    private String shoppingCenterName;

    public Purchase(long purchaseId, double price, int customerId, String shoppingCenterName){
        this.purchaseId = purchaseId;
        this.customerId = customerId;
        this.shoppingCenterName = shoppingCenterName;
        this.price = price;
        productsPurchased = new ArrayList<>();
    }

    public String printAllProducts(){
        String product = "";
        for (Product item : productsPurchased){
            product += item.getProductName();
        }
        return product;
    }

    @Override
    public String toString(){
        return "Purchase ID: " + purchaseId + "\nCustomers ID: " + customerId + "\nIn Shopping Center: " + shoppingCenterName + "\nFinal Price: " + price + "\nProducts purchased:" + printAllProducts();
    }


}

/*
same question as in the Product class
 */
