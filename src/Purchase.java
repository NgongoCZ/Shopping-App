import java.util.ArrayList;

public class Purchase {

    private final long purchaseId;
    private final ArrayList<Product>productList;
    private double price;
    private final int customerId;

    public Purchase(long purchaseId, double price, int customerId){
        this.purchaseId = purchaseId;
        this.customerId = customerId;
        productList = new ArrayList<>();
    }
}

/*
same question as in the Product class
 */
