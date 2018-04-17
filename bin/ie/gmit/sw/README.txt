Note 
Arron Healy - G00346883 Four Square Cipher

Assignment Class Descriptions

1. Runner Class

Class with main method creates instance of menu class and calls its show method to run program.


2. Menu Class

Calls Parser & Four square classes

Class contains options for user to enter. Class operates as follows,

- enter 1 enter encrypt menu
- enter 2 option to generate Four square visual
- enter 3 exit application

Encrypt menu options:
1 - enter plain text for encryption and decryption also option to print four square

2 - enter File for encryption and decryption option to output file

program lists three sample files from moodle
	
note: files in same directive as jar file to run.

note: possible program glitch when reading 2 largest text files first time round,

program seems to take alot longer on first try and speeds up after couple attempts.
But this could be my machine.

3 - enter url for encryption and decryption and output to file

program lists 4 options

 1. Enter url - must be correct

 2. google homepage

 3. facebook homepage

 4. gmit homepage



3. Parser Class 

Class handles the reading of files and url from menu class along with Four square class

4 methods - parse takes file for encryption calls decrypt File method

	    decryptFile decrypts chosen file

	    parseUrl takes chosen url for encryption and calls decrypt url

	    decryptUrl decrypts chosen url


4. Four_Square Class

takes text as strings and encrypts and decrypts based on four square created.

two constructors to generate cipher keys randomly or enter user key words for cipher.

method drop duplicate and append handles key words less than 25 chars in length

set squares method sets four square set up as 2D arrays

encrypt and decrypt methods handle bigrams and call methods to get encrypted and decrypted characters returned.

get encrypted char methods search and return encrypted characters from string or stream.

note: search method longer had the math for i / 5 and i % 5 wored out but had issue with file inputstream to parser.

inputstream would read so many lines of file and crash so took longer root of searching characters with nested for loop.

print method prints built four square cipher as in assignment brief.



get 
