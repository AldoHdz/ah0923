import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        PointOfSale pointOfSale = new PointOfSale();
        System.out.println(pointOfSale.checkout("JAKR", "9/3/15",5, 10));
    }
}