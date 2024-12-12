# AlgorithmsFinal
String Matching Game

This project utilizes 4 various string matching algorithms, and uses them in a matching "game."
The project is directly played in the terminal, so there are no libraries or wahtnot that need to be installed.

The four matching algorithms implemented are:
* Brute-Force Method, which directly compares each position for matching letters
* Horspool's Algorithm, which utilizes a shift table to find occurences
* Rabin-Karp, which checks hash values before comparing strings
* BNDM (Backwards Non-deterministic DAWG Matching), which utilizes bitmasks to compare across an entire window

To run the game, run the StringMatchingGame.java file. 

The game requires the user to input a long string as the main text, and a pattern to search for in said text. 
It then prints out the number of matches found by each algorithm, and a randomly-shuffled assortment of times. 
After this, the user must enter which algorithm they think produced which time, going from left to right. 
The names inputted _must_ match the names used in the parentheses exactly, as otherwise the comparison will not work. 
If the user does not guess correctly, the times are reprinted and they must guess again. 
If they succeed, they are prompted if they want to play again, restarting if they say 'Y' or exiting if they say 'N'.

The other string matching algorithms have main methods for testing purposes. 

The game also prints out a "solution code" to shwow what the correct order of inputs should be. 
(c = BNDM, j = Brute Force, e = Horspool's, a = Rabin-Karp).

