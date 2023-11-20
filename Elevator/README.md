Elevator

The program: 

The Program is handled by the Simulation.java program. 
    * I initialize the variables for the simulatiuon
        * strutures
        * floors 
        * passengers 
        * elevators 
        * elevatorCapcity
        * duration
    * If there is no properties files then I use the default values from the write up!

    * There is `run()`  method that handles all the elevators and there movement in the simulation. It uses the other classes: passenger, elevator, and floor. At the end of the simulation it prints the results





The Elevator Logic:
    * i tried to make the elevator's logic as realistic as possible. 
    * The Elevator starts unloading, then it load passengers in. This is because in real life you unload first befor loading.
    *  Then I determine whether these passengers are going up or down and then I go to the destinationfloor.

    To change floors, I used very smart logic. I check for the elevator and the passengers in it next floor. Which is the smallest number when going up and the largest number when going down. Then I check for the floors below. If there is a passengers going in the same direction in a floor between the nextfloor from the passenger inside the elevator, then I stop to that floor to pick up that passenger. 







The choice of data structures made in the Elevator.java Simulation Program. 

Here are the data structures I used in the SIMULATION class
    * List for Floors
        * depending on which structures is listed for the simulation, it is with either a linked or an array implementation. It stores floors for the simulation. The list is static so that all the elavators use the same list.
        * Reason: I decided to use a list because is to allow the program to iterate through all the floors using the get() method.
    * List for Elevators
        * depending on which structures is listed for the simulation, it is with either a linked or an array implementation. It store the elevators in the run method which is the method that runs the simulation. 
        * Reason: I use a list som that we can run the simulation on every floor and we can iterate through the elevators for every tick.

Here are the data structures I used in the FLOORS class
    * Queue for passengers going up and going down
        * depending on which structures is listed for the simulation, it is with either a linked or an array implementation of a queue. The queues store passengers going up and and down respectively.
        * Reason: they are stored in queues so that they are considered in orderly manner considering that queue are FIFO data structures

Here are the data structures I used in the Elevator class
    * List for holding the times
        * depending on which structures is listed for the simulation, it is with either a linked or an array implementation. Stores the times taken for all the passengers that have been into the elevator and out. this Allows us to calculate the average time taken for passenger conveyance.
    * List for holding the floors
        * This is the same list that is created in the SIMULATION class
    * Min Heap for elevator going up
        * Because a heap implimentation is always array based, it is not affected by the strucutres variable in the simulation class.
        Reason: It uses a min heap for going up because you need the smallest value/the min to know what is the next floor to stop at.
    * Max Heap for elevator going down
        * Because a heap implimentation is always array based, it is not affected by the strucutres variable in the simulation class.
        Reason: it uses a max heap as when you are going down, the next floor should be the next the maximum floor/the greatest floor from all the passengers in the elevator. 



