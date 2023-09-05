
public class Main {
    //For the purposes of this code demo I will tag commentary on design decisions or things i'd do differently with [IfICould]
    public static void main(String[] args){
        PointOfSale pointOfSale = new PointOfSale();
        //[IfICould] - I'd push back on sending checkout dates as a "M/D/YY" formatted string. Best practices are ISO date strings or time stamp.
        System.out.println(pointOfSale.checkout("JAKR", "9/3/15",5, 10));
    }
}