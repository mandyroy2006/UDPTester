# UDP CONNECTIVITY TEST


This tool can be used to verify the connectivity between a sender and a receiver over a specificied port. <br>
It will send a small packet via UDP from the sender to the receiver. (64 bytes)  
If the connexion is estabished and the packet is delivered, the receiver will send a confirmation to the sender. 
 
Once the verification is done, you can either do an other test, or close the tool. <br>
When doing an other test, you can either keep your sender/receiver status or change it. You will then have to specify
the parameter of the new connexion. 

Once in the receiver position, the program will be listening until he receives a packet from the sender. 

When the sender sends the packet, it will wait for the packet's reception confirmation from the receiver. 
If it doesn't get a confirmation, it will try to send the packet back up to five times. If the sender doesn't get any response, it will prompt that it was unable to confirm the packet delivery. 

---------------------------------------
## HOW TO RUN THE PROGRAM
To run the program, you need to have the Java Developpement Kit installed. (Was tested on JDK 18.0.1.1 on Windows 10 and on MACOSX) <br>
You must then run the program from the Terminal (Mac) or the Command Prompt (Windows). <br>
Copy/Paste the UDPTester file on the computer. <br>
From the Terminal, navigate to where the file "UDPTester" resides, where you can find the UDPTester.jar. <br> 
To run use:  java -jar UDPTester.jar  <br>
Follow the steps displayed through the Terminal/Command Prompt. <br>

---------------------------------------
## FUTURE IMPROVEMENTS

Allow for users to correct their mistakes while making choices and writting down their port/IP configurations. <br>
Create a GUI version.

----------------------------------------
## NOTES

This is the first program I build by myself. I'm currently studying to eventually become a programmer. I have completed my first course with Java on mai 2022. Since it was my first course, I still have a lot to learn to complete the University program.

BY: Amanda Plouffe
