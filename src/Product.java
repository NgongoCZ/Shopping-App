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
        return "Product name: " + productName + "\nProduct Price: " + productPrice + "\nProduct ID: " + productId;
    }
}
