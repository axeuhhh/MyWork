/**********************************************************************************************************************************************************
 * @author axelndombasi
 * Elevator.java: is a program that holds a simulation for elevators. It is able to take in a property files that changes the variables in the simulation.
 * It uses a variety of data structures and is able to run the simulation using either linked or array based structures.
 * I have a attached a file README.md which contains the choices that I have made 
 **********************************************************************************************************************************************************/


import java.util.*;
import java.io.*;


/**
 *Simulation: is the class that reads the property file and contains the values for the simulation
 */
class Simulation{ 
	int longestTime = 0, averageTime = 0, shortestTime = 0;
	static String structures = "linked"; //linked
	static int floors = 3;///// back to 32// UP TICK UP
	static double passengers = 1;// 0.03// DOWN TICK GO DOWN
	static int elevators = 1;// UP TICKS DOWN
	static int elevatorCapacity = 10;// 10//UP TICK DOWN
	static int duration = 500;// MAke it long
	
	static List<Floor> Floors;// stores the floors that the simulation uses. Shared between the elevators
	
	/*
	 * Simulation contains 2 constructors
	 * @constructor1, had no parameters, simply just creates floors 
	 * @constructor2, has one parameter that is the name of the property file which the program uses to rest the Simulation variables
	 */
	Simulation(){
		 createFloors();// Creates the floors with the default values of the Simulation
	}
	Simulation (String fileName) throws IOException{
		FileReader reader = new FileReader(fileName); // create a reader object on the properties file 
		Properties p = new Properties(); // create properties object 
		p.load(reader);  // Add a wrapper around reader object

		// access properties data 
		structures = p.getProperty("structures"); 
		floors = Integer.parseInt(p.getProperty("floors"));
		passengers =  Double.parseDouble(p.getProperty("passengers"));
		elevatorCapacity = Integer.parseInt(p.getProperty("elevatorCapacity"));
		duration = Integer.parseInt(p.getProperty("duration"));
		elevators=Integer.parseInt(p.getProperty("elevators"));
		  
		//testing for property files
		// System.out.println("Floor: " +floors);
		// System.out.println("passengers : " +passengers);
		// System.out.println("elevators : " +elevators );
		// System.out.println("elevator capacity : " +floors);
		// System.out.println("duration : " +floors);
		// System.out.println("structures: " +structures);
		
		createFloors();// creates the floors with values from the properties file. 
	}
	
	/*
	 * createFloors: this method creates the floors and store them in a List structure 
	 * in the Simulation class so that all the elevators can access them.
	 */
	private void createFloors() {
		
		if (Simulation.structures.equals("linked")) {// initializing the data structure to store the floors
			Floors = new LinkedList<Floor>();
		}
		else {
			Floors = new ArrayList<Floor>();
		}
		for(int i = 0; i<Simulation.floors;i++) {
			Floors.add(new Floor());
		}
	}
	
	/*
	 * run(): is the method that runs the simulation.
	 * it creates the elevators and stores them in a data structure
	 */
	public void run() {
		List<Elevator> elevators;
		if (Simulation.structures.equals("linked")) {// initializing the structures that are storing the elevator storing the elavtors
			elevators=new LinkedList<Elevator>();
		}
		else {
			elevators = new ArrayList<Elevator>();
		}
		for(int i = 0;i<Simulation.elevators;i++) {// creating the number of elevators for the simulation
			elevators.add(new Elevator());
		}
		
		for (int i = 0; i<Simulation.duration;i++) {// running the simulation with all the elevators together
			for(int j = 0; j<Simulation.elevators;j++) {
				elevators.get(j).elevator(i);
			}
			elevators.get(0).createPassenger();;// creates passengers after every iteration for every tick

		}
		elevators.get(0).time();
	}
}












/**
 * Passenger: creates passengers for the simulation. 
 * it implements comparable allowing the passengers to be stored in heaps for the elevator
 */
class Passenger implements Comparable<Passenger>{
	int destinationFLoor;
	long startTime = Elevator.tick;
	
	/*
	 * Passenger(): is the contractor that creates passenger objects for the simulation with a random destination
	 * it has on parameter that is the floor number where the passenger is currently on.
	 */
	Passenger(int floorNumber){
		Random rand = new Random();
		// give the passengers a Random floor to go to
		destinationFLoor = rand.nextInt(0,Simulation.floors);
		while(destinationFLoor==floorNumber) {
			destinationFLoor = rand.nextInt(0,Simulation.floors);
		}	
	}
	// Allowing comparison between passengers
	public int compareTo(Passenger p) {// so that passengers can be store in a min/max Heap
		 return (Integer.compare(this.destinationFLoor, p.destinationFLoor));
	}
}











/**
 * Floor: handles creating Passengers and holding them in data structures 
 */
class Floor{

	int floorNumber;
	static int increment = 0;
	Queue<Passenger> paxDown;// hold passengers that are going down
	Queue<Passenger> paxUp;// hold passengers going up
	Floor(){
		if (Simulation.structures.equals("linked")) {// initializing the structures for floor class based on Simulation
			paxDown = new LinkedList<Passenger>();
			paxUp = new LinkedList<Passenger>();
		}
		else {
			paxDown = new ArrayDeque<Passenger>();
			paxUp = new ArrayDeque<Passenger>();
		}
		createPassenger();
		floorNumber=increment;
		increment++;
	}
	
	/*
	 * createPassenger(): is the method that calls the passengers constructor to actually create the passengers for the simulation
	 * if uses the probability for passengers appearing which is given at the beginning of the simulation
	 */
	public void createPassenger(){
		Random rand = new Random();
		double passengerProbability = rand.nextDouble();
		if(passengerProbability<=Simulation.passengers && passengerProbability>=0) {// calculates the probability
			Passenger p = new Passenger(floorNumber);
			if (p.destinationFLoor>floorNumber) {
				paxUp.add(p);// storing in a queue all the passengers that are going up
				
				//testing
//				System.out.println(floorNumber+ " passenger goign up is created" );
			} 
			else {
				paxDown.add(p);// storing in a queue all the passengers that are going down
				
				//testing
//				System.out.println(floorNumber+ " passenger down up is created" );
			}
		}
	}
}











/**
 * Elevator: handles the elevators in the simulation and holds the main
 */
public class Elevator{
	final static long MAX_VALUE=9223372036854775807l;
	static int tick = 0;
	int currentFloor, numOfPax;
	static long minTime=9223372036854775807l, maxTime=0;
	List<Long> times;
	List<Floor> Floors;
	PriorityQueue<Passenger> downHeap = new PriorityQueue<>(Collections.reverseOrder()); // max heap that stores the passengers that are going down 
	PriorityQueue<Passenger> upHeap = new PriorityQueue<>();  // min heap storing passengers that are going up
	boolean goingUp;

	/*
	 * Elevator(): is the constructor building elevator objects
	 * initializing their starting position, their direction, and number of passengers in it.
	 */
	Elevator(){
		Floors=Simulation.Floors;
		if (Simulation.structures.equals("linked")) {
			times = new LinkedList<Long>();
		}
		else {
			times = new ArrayList<Long>();
		}

		goingUp=true;
		currentFloor = 0;
		numOfPax=0;
	
	}
	
	
	/*
	 * loadPassengers(): is the method that loads passengers in the elevator
	 */
	private void loadPassengers() {
		
		// testing
//		System.out.println("the Current floor is: "+currentFloor);
//		System.out.println("is the elevator going up? "+ goingUp);
//		if (!goingUp) {
//			System.out.println("do i have passengers going down? "+Floors.get(currentFloor).paxDown);
//		}
//		System.out.println("do i have passengers going up? "+Floors.get(currentFloor).paxDown+"\n");
		
		//loops until passengers capacity of the elevator and 
		Floor f = Floors.get(currentFloor);
		while (goingUp && !(f.paxUp.isEmpty()) && Simulation.elevatorCapacity>numOfPax) { // loading passengers to the Elevator checking for direction and capacity
			upHeap.add(f.paxUp.poll());
			numOfPax++;
		}
		while (!(goingUp) && !(f.paxDown.isEmpty()) && Simulation.elevatorCapacity>numOfPax) {
			downHeap.add(f.paxDown.poll());
			numOfPax++;
		}
	}
	
	/*
	 * unLoadPassengers(): is the method that unloads passengers from the elevator
	 * this method dereferences passengers that reach their destination floors
	 */
	private void unLoadPassengers() { 
		
		if (goingUp) {
			while(!(upHeap.isEmpty())&&upHeap.peek().destinationFLoor==currentFloor) {// remove all the passengers that are getting down at the current floor
				long timeToDestination = tick- upHeap.peek().startTime;
				minTime=Math.min(minTime,timeToDestination);// checks if that time was the min
				maxTime=Math.max(timeToDestination, maxTime);// checks if that time was the max
				times.add(timeToDestination);// adds the time to an array to calculate the average time
				upHeap.remove();
				numOfPax--;
			}
		}
		else {
			while(!(downHeap.isEmpty())&&downHeap.peek().destinationFLoor==currentFloor) {
				long timeToDestination = tick - downHeap.peek().startTime;// this should be stored in a data structure so that we can calculate the average. we can also find the minimum of the full things
				minTime=Math.min(minTime,timeToDestination);
				maxTime=Math.max(timeToDestination, maxTime);
				times.add(timeToDestination);
				downHeap.remove();
				numOfPax--;
			}
		}	
	}
	
	/*
	 * time(): is the method that prints the results of the simulation.
	 * prints the average, minimum and maximum time taken for passenger conveyance to their destination Floor
	 */
	public void time() {
		long sum=0;
		for(int i = 0; i<times.size();i++) {
			sum+=times.get(i);
		}
		if(minTime == MAX_VALUE && maxTime == 0) {// meaning that there was no passengers in the simulation that went on the elevator.
			System.out.println("In this simulation, elevators were not used!");
			System.out.println("The minumum time for passenger conveyance is 0 ticks");
			System.out.println("The maximum time for passenger conveyance is 0 ticks");
			System.out.println("The average time for passenger conveyance is 0 ticks");
			
		}
		else {
			System.out.println("The minumum time for passenger conveyance is " +minTime+ " ticks");
			System.out.println("The maximum time for passenger conveyance is " +maxTime+ " ticks");
			System.out.println("The average time for passenger conveyance is "+(double) sum/(double)times.size()+" ticks");
			
		}
		
	}
	
	/*
	 * nextFloor(): is the method that changes the floor of the elevator allowing it to move up and down
	 * the elevator can't move more than 5 units
	 */
	private void nextFloor(){
		int nextFloor=0;
		if(goingUp) {
			nextFloor = Simulation.floors-1;
			for (int i = currentFloor+1;i<Floors.size();i++) {// sets the next floor to the first floor that has passengers going up
				if(!(Floors.get(i).paxUp.isEmpty())){// need to change passengers array in the floors to a min and a max heap to make it easier to  find the next floor instead of looping
					nextFloor =  Floors.get(i).floorNumber;
					break;
				}
			}
			if(!upHeap.isEmpty()) {// if there is a passenger in the elevator, checks their destination floor, takes the minimum between the next passenger needing an elevator to go up and the passenger in the elevator
				nextFloor=Math.min(nextFloor, upHeap.peek().destinationFLoor);
			}
			if (Math.abs(currentFloor-nextFloor)>5) {// make sure that at every tick we don't go more than 5 floors at once 
				
				if(currentFloor+5>Simulation.floors-1) {
					nextFloor=Simulation.floors-1;
				}
				else {
					nextFloor=currentFloor+5;
				}
			}
		}
		else {
			nextFloor = 0;
			for (int i = currentFloor-1;i>=0;i--) {
				if(!Floors.get(i).paxDown.isEmpty()){// need to change passengers array in the floors to a min and a max heap to make it easier to  find the next floor instead of looping
					nextFloor = Floors.get(i).floorNumber;
					break;
				}
			}
			if(!downHeap.isEmpty()) {
				nextFloor=Math.max(nextFloor, downHeap.peek().destinationFLoor);
			}
			if (Math.abs(nextFloor-currentFloor)>5) {//make sure that at every tick we don't go more than 5 floors at once 
				if (currentFloor-5<0) {
					nextFloor=0;
				}
				else{
					nextFloor=currentFloor-5;
				}
			}
		}
		currentFloor = nextFloor;	
	}
	
	/*
	 * goingUp(): this method determines which direction the elevator is going
	 */
	public void goingUp(){
		
		if (goingUp&&numOfPax==0) {// if we are going up and the elevator is empty
			if (isPaxUp()) {// if there are passengers in higher floors going up
				goingUp=true;// keep going up
			}
			else {
				goingUp=false;//go down
			}
		}
		else if (!goingUp&&numOfPax==0) { // if we are going down and the elevator is empty
			if (isPaxDown()) {// if there are passengers in lower floors going down
				goingUp = true;// keep going down
			}
			else {
				goingUp = false;// go up
			}
		}
		if(goingUp && currentFloor == Simulation.floors-1) {
			goingUp = false;
		}
		if (!goingUp && currentFloor == 0) {
			goingUp = true;
		}
	}
	
	/*
	 * isPaxUp(): checks if there are passengers on floors above so the elevator keeps going up
	 */
	private boolean isPaxUp() {
		for (int i = currentFloor+1;i<Floors.size();i++) {
			if (!Floors.get(i).paxUp.isEmpty()) { //looks at all the floors above the currentfloor to check if there are passengers are going up
				return true;
			}
		}
		return false;
	}
	
	/*
	 * isPaxDown():checks if there are passengers in the floors below so the elevator keeps going down
	 */
	private boolean isPaxDown() {
		for (int i = currentFloor-1;i>=0;i--) {
			if (!Floors.get(i).paxDown.isEmpty()) { //looks at all the floors below the currentfloor to check if there are passengers are down
				return true;
			}
		}
		return false;
	}
	
	/*
	 * createPasenger(): this method creates passengers in every floor by calling the createPassenger methods in the Floors class
	 * important for every tick as passengers are created at every tick
	 */
	public void createPassenger() {
		for (int i = 0;i<Floors.size();i++) {
			Floors.get(i).createPassenger();;
		}
	}
	
	/*
	 * elevator(): this method handles the elevators in the building, it allows them to unload and load passengers, determine whether to go up or down, and going to different floors 
	 */
	public void elevator(int i) {
		Elevator.tick = i;
		unLoadPassengers();
		loadPassengers();
		goingUp();
		nextFloor();
	}
	
	
	//main
	public static void main(String[] args) throws IOException {
		if (args.length>0) {// reading the property file to set variables for the simulation
			Simulation simulation = new Simulation(args[0]);
			simulation.run();
		}
		else {// using the default values for the simulation
			Simulation simulation = new Simulation();
			simulation.run();
		}
		
	}	
}
