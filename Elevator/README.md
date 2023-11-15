Elevator

In this file, I will be discussing the choice of data structures made in the Elevator.java Simulation Program. 

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