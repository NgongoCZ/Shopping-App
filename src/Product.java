public class Product {

    private final String productName;
    private final double productPrice;
    private final long productId;

    public Product(String productName, double productPrice, long productId){
        this.productName = productName;
        this.productPrice = productPrice;
        this.productId = productId;
    }

    public String getProductName(){
        return productName;
    }

    public double getProductPrice(){
        return productPrice;
    }

    public long getProductId(){
        return productId;
    }

    @Override
    public String toString(){
        return "Product name: " + productName + "\nProduct Price: " + productPrice + "\nProduct ID: " + productId + "\n";
    }
}

/*
do I need to create a new class for product
or should I just create new products as an ArrayList
and then save each of them in one general ArrayList ?
 */