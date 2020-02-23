import COMP250A2_W2020.*;

/**
 * COMP250 Winter 2020 Assignment 2 Tester:
 *
 * @author Maya Marsonia
 * @author Sasha Aleshchenko
 */
class Tester extends TrainRide {
    public static void main(String[] args) {
        System.out.println("Welcome to the Confusing Railroad!");

        // Constructs a train network
        TrainNetwork tNet = generateTrainNetwork();

        // Prints the train network plan
        tNet.printPlan();

        // Travels from Little Whinging to Hogwarts.
        tNet.travel("1.Little Whinging", "Scarlet", "5.Hogwarts", "Purple");
        tNet.undance();
        //New Tests
        //Same Departure and arrival
        tNet.travel("1.Little Whinging", "Scarlet", "1.Little Whinging", "Scarlet");
        tNet.undance();
        //One station Away
        tNet.travel("1.Little Whinging", "Scarlet", "2.Wizard Hat", "Scarlet");
        tNet.undance();
        //One transfer Away
        tNet.travel("4.Diagon Alley- 1/3", "Scarlet", "2.Diagon Alley - 2/3", "Grey");
        tNet.undance();
        //Starts at the right terminus
        tNet.travel("5.St Mungo's", "Scarlet", "4.Diagon Alley- 1/3", "Scarlet");
        tNet.undance();
        try {
            tNet.travel("5.St Mungo's", "Scarlet", "3. Leaky Cauldron", "Scarlet");
            System.out.println("Successfully executes the situation where given an invalid destination");
        }
        catch (Exception e){
            System.out.println("This threw an unexpected exception, program should have continued to 168 hours " + e +'\n');
        }
        try{

            tNet.getLineByName("Scarlet").getNext(tNet.getLineByName("Scarlet").findStation("FakeStaion"));
        }
        catch (StationNotFoundException e){
            System.out.println("Your getNext function the appropriate exception\n");
        }
        try{
            tNet.getLineByName("Scarlet").findStation("FakeStation");
        }
        catch (StationNotFoundException e){
            System.out.println("Your findStation function the appropriate exception\n");
        }
        try{
            tNet.getLineByName("Scarlet").travelOneStation(tNet.getLineByName("Scarlet").findStation("FakeStaion"), null);
        }
        catch (StationNotFoundException e){
            System.out.println("Your travelOneStation function the appropriate exception\n");
        }




        tNet.printPlan();
        // Resets the network to its initial position
        System.out.println("Resetting the network");
        tNet.undance();
        tNet.printPlan();
    }

    // Calls constructors and methods to implement the network shown in the handout
    // map.
    public static TrainNetwork generateTrainNetwork() {
        // creating line 1
        TrainStation s1 = new TrainStation("1.Little Whinging");
        TrainStation s5 = new TrainStation("5.St Mungo's");
        s1.setRight(s5);
        s5.setLeft(s1);
        TrainLine l1 = new TrainLine(s1, s5, "Scarlet", true);
        //System.out.println("This line has length: " + l1.getSize());

        TrainStation s2 = new TrainStation("2.Wizard Hat");
        l1.addStation(s2);
        //System.out.println("This line has length: " + l1.getSize());

        TrainStation s3 = new TrainStation("3.Hogsmeade");
        l1.addStation(s3);
        //.out.println("This line has length: " + l1.getSize());

        TrainStation s4 = new TrainStation("4.Diagon Alley- 1/3");
        l1.addStation(s4);
        //System.out.println("This line has length: " + l1.getSize());


        // creating line 2
        TrainStation t1 = new TrainStation("1.Gringotts");
        TrainStation t5 = new TrainStation("5.Leaky Cauldron");

        t1.setRight(t5);
        t5.setLeft(t1);

        TrainLine l2 = new TrainLine(t1, t5, "Grey", true);

        TrainStation t2 = new TrainStation("2.Diagon Alley - 2/3");
        l2.addStation(t2);
        TrainStation t3 = new TrainStation("3.Ollivanders");
        l2.addStation(t3);
        TrainStation t4 = new TrainStation("4.King's Cross - 3/5");
        l2.addStation(t4);

        s4.setConnection(l2, t2);
        t2.setConnection(l1, s4);

        // creating line 3
        TrainStation u1 = new TrainStation("1.King's Cross - 4/5");
        TrainStation u5 = new TrainStation("5.Hogwarts");

        u1.setRight(u5);
        u5.setLeft(u1);

        TrainLine l3 = new TrainLine(u1, u5, "Purple", true);

        TrainStation u2 = new TrainStation("2.Ministry of Magic");
        l3.addStation(u2);
        TrainStation u3 = new TrainStation("3.Snowy Owl");
        l3.addStation(u3);
        TrainStation u4 = new TrainStation("4.Godric's Hollow");
        l3.addStation(u4);

        u1.setConnection(l2, t4);
        t4.setConnection(l3, u1);

        TrainNetwork tNet = new TrainNetwork(1);
        TrainLine[] lines = { l1, l2, l3 };
        tNet.addLines(lines);

        return tNet;
    }
}

/**Exceptions from COMP250 Provided Content
 *
 * @author McGill University COMP250 Teaching Staff
 */
class StationNotFoundException extends RuntimeException {
    String name;

    public StationNotFoundException(String n) {
        name = n;
    }

    public String toString() {
        return "StationNotFoundException[" + name + "]";
    }
}
class LineNotFoundException extends RuntimeException {
    String name;

    public LineNotFoundException(String n) {
        name = n;
    }

    public String toString() {
        return "LineNotFoundException[" + name + "]";
    }
}