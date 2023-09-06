
public class Main {
    public static void main(String[] args) {
        PointOfSale pointOfSale = new PointOfSale();
        //[IfICould] - I'd push back on sending checkout dates as a "M/D/YY" formatted string. Best practices are ISO date strings or time stamp.
        System.out.println(pointOfSale.checkout("JAKR", "9/3/15", 5, 10).toString());
    }
}