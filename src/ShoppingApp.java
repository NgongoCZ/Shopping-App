import java.util.Scanner;

public class ShoppingApp {
    public static void main(String[] args) {

        Scanner myScanner = new Scanner(System.in);
        ShoppingAppLogic programLogic = new ShoppingAppLogic(myScanner);
        programLogic.start();
    }
}
