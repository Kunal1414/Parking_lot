#Project Title
Parking lot System

#Problem Statement (Parking Lot)

I own a parking lot that can hold up to 'n' cars at any given point in time. Each slot is
given a number starting at 1 increasing with increasing distance from the entry point
in steps of one. I want to create an automated ticketing system that allows my
customers to use my parking lot without human intervention.
When a car enters my parking lot, I want to have a ticket issued to the driver. The
ticket issuing process includes us documenting the registration number (number
plate) and  allocating an available parking slot to the car
before actually handing over a ticket to the driver (we assume that our customers are
nice enough to always park in the slots allocated to them). The customer should be
allocated a parking slot which is nearest to the entry. At the exit the customer returns
the ticket with the time the car was parked in the lot, which then marks the slot they
were using as being available. Total parking charge should be calculated as per the
parking time. Charge applicable is $10 for first 2 hours and $10 for every additional
hour.
We interact with the system via a simple set of commands which produce a specific
output. Please take a look at the example below, which includes all the commands
you need to support - they're self explanatory. The system should accept a filename
as a parameter at the command prompt and read the commands from that file.

#language
Java

## Technologies
Project is created with:
* Jre
* Maven 
* Junit for test

#Prerequisites
1. Latest version of JDK.
2. Latest version of maven.

#Build Instructions
Run the following command -
clean install

#Running the project
###Example: File Input

To run the program:
In windows through eclipse:
run -> arguments -> give the path of inputfile in the argument.

Input (in file):

create_parking_lot 6
park KA-01-HH-1234
park KA-01-HH-9999
park KA-01-BB-0001
park KA-01-HH-7777
park KA-01-HH-2701
park KA-01-HH-3141
leave KA-01-HH-3141 4
status
park KA-01-P-333
park DL-12-AA-9999
leave KA-01-HH-1234 4
leave KA-01-BB-0001 6
leave DL-12-AA-9999 2
park KA-09-HH-0987
park CA-09-IO-1111
park KA-09-HH-0123
status

Output (to console, newline after every output):

Created parking lot with 6 slots
Allocated slot number: 1
Allocated slot number: 2
Allocated slot number: 3
Allocated slot number: 4
Allocated slot number: 5
Allocated slot number: 6
Registration number KA-01-HH-3141 with Slot Number 6 is free with Charge 30
Slot No.	Registration
1		registrationNo=KA-01-HH-1234
2		registrationNo=KA-01-HH-9999
3		registrationNo=KA-01-BB-0001
4		registrationNo=KA-01-HH-7777
5		registrationNo=KA-01-HH-2701
Allocated slot number: 6
Sorry, Parking lot is full
Registration number KA-01-HH-1234 with Slot Number 1 is free with Charge 30
Registration number KA-01-BB-0001 with Slot Number 3 is free with Charge 50
Registration number DL-12-AA-9999 not found
Allocated slot number: 1
Allocated slot number: 3
Sorry, Parking lot is full
Slot No.	Registration
1		registrationNo=KA-09-HH-0987
2		registrationNo=KA-01-HH-9999
3		registrationNo=CA-09-IO-1111
4		registrationNo=KA-01-HH-7777
5		registrationNo=KA-01-HH-2701
6		registrationNo=KA-01-P-333

#Running the project
###Example: Interactive

Please Enter 'exit' to end Execution
Input:
create_parking_lot 6
Created parking lot with 6 slots
park KA-01-HH-1234
Allocated slot number: 1
park KA-01-HH-9999
Allocated slot number: 2
park KA-01-BB-0001
Allocated slot number: 3
park KA-01-HH-7777
Allocated slot number: 4
park KA-01-HH-2701
Allocated slot number: 5
park KA-01-HH-3141
Allocated slot number: 6
leave KA-01-HH-3141 4
Registration number KA-01-HH-3141 with Slot Number 6 is free with Charge 30
status
Slot No.	Registration
1		registrationNo=KA-01-HH-1234
2		registrationNo=KA-01-HH-9999
3		registrationNo=KA-01-BB-0001
4		registrationNo=KA-01-HH-7777
5		registrationNo=KA-01-HH-2701
park KA-01-P-333
Allocated slot number: 6
park DL-12-AA-9999
Sorry, Parking lot is full
leave KA-01-HH-1234 4
Registration number KA-01-HH-1234 with Slot Number 1 is free with Charge 30
leave KA-01-BB-0001 6
Registration number KA-01-BB-0001 with Slot Number 3 is free with Charge 50
leave DL-12-AA-9999 2
Registration number DL-12-AA-9999 not found
park KA-09-HH-0987
Allocated slot number: 1
park CA-09-IO-1111
Allocated slot number: 3
park KA-09-HH-0123
Sorry, Parking lot is full
status
Slot No.	Registration
1		registrationNo=KA-09-HH-0987
2		registrationNo=KA-01-HH-9999
3		registrationNo=CA-09-IO-1111
4		registrationNo=KA-01-HH-7777
5		registrationNo=KA-01-HH-2701
6		registrationNo=KA-01-P-333

#Running the tests
Run Junit Test 

   

