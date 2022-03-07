# Blackjack
Simple blackjack application.

## Requirements
- Java 8
- Maven

## Build
To build with Maven run: `mvn clean verify`

## Run
**The application has two modes:**

To run with a randomly shuffled deck, run the application without any arguments. 
E.g. `java -jar target/blackjack-1.0.0.jar`.

To run with a user-supplied deck, 
a file where the first line contains the deck must be supplied (subsequent lines are ignored). 
The line must be on the following format: `CA, D4, H7, SJ,..., S5, S9, D10`. The reference to the file
must be the first argument when running the application.
E.g. `java -jar target/blackjack-1.0.0.jar testfile.txt`

## Valid suits and values

Suits:
* C: Clubs
* D: Diamonds
* H: Hearts
* S: Spades

Values:

* 2: 2
* 3: 3
* ....
* 10: 10
* J: Jack
* Q: Queen
* K: King
* A: Ace