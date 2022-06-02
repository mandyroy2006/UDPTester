import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UDPTester {

    // Sender's code to send an UDP packet when choosing an IP address and a port. 
    public static void Sender(InetAddress address, int port) throws Exception{

        DatagramSocket ds = new DatagramSocket(port);
        
        
        System.out.println("Sender: Trying to send the packet...");
    
        byte[] messageToSend = "Sender: Sent Packet.".getBytes();
        DatagramPacket sentPacket = new DatagramPacket(messageToSend, messageToSend.length, address, port);
        boolean confirmation = true;
        int nbrOfTry = 0;
        // Sending the packet 5 times if no confirmation is received. 
        if(nbrOfTry<=4 || confirmation == true){
            do{
                try{
                    confirmation = true;
                    ds.send(sentPacket);
                }
                catch(Exception e){
                    System.out.println("Sender: An error occurred... The packet could not be sent.");
                    System.exit(1);
                }
                System.out.println("Sender: Packet was sent successfully, waiting for a reply...");

                byte[] receivedData = new byte[64];
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                ds.setSoTimeout(4500);
                try{
                    ds.receive(receivedPacket);
                    String receivedMessage = new String(receivedData);
                    System.out.println(receivedMessage);
            
                }
                catch(SocketTimeoutException e){
                    System.out.println();
                    System.out.println("Sender: Could not confirm packet reception with the receiver.");
                    System.out.println();
                    confirmation = false;
                    nbrOfTry = nbrOfTry + 1;
                }
                if(nbrOfTry<5 && confirmation == false){
                    System.out.println("Trying again...");
                }

            }
            while(confirmation == false && nbrOfTry <=4 );
            if(nbrOfTry ==5 && confirmation == false){
                System.out.println("Confirmation was never received...");
            }
            }
        ds.close();
    }

    // Receiver's code.
    public static void Receiver(int port) throws Exception{

        DatagramSocket ds = new DatagramSocket(port);
        System.out.println("Receiver: Listening...");

        byte[] receivedData = new byte[64]; 
        DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
        ds.receive(receivedPacket);

        System.out.println("Receiver: A packet was received...");
        InetAddress senderIPAddress = receivedPacket.getAddress();
        String messageReceived = new String(receivedPacket.getData());
        System.out.println(messageReceived);

        byte[] messageToSend = "Receiver: Packet was received.".getBytes();
        DatagramPacket sentPacket = new DatagramPacket(messageToSend, messageToSend.length, senderIPAddress, port);
        
        ds.send(sentPacket);

        System.out.println("Receiver: Confirmation was sent.");
        ds.close();
    }
    
    public static String YesNo(String question){
        String yesNo;
        Scanner scan = new Scanner(System.in);
        do{
            System.out.println(question);
            yesNo = scan.next();
            }
            while(!yesNo.equalsIgnoreCase("yes") && !yesNo.equalsIgnoreCase("no"));

        return yesNo;
    }

    // Main method from which to choose whether you are the sender or the receiver. 
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Welcome to your UDP connexion tester utility.");
        System.out.println("Are you the receiver or the sender?");
        String senderReceiver;
        do{
            senderReceiver = sc.nextLine();
        }
        while(!senderReceiver.equalsIgnoreCase("receiver") && !senderReceiver.equalsIgnoreCase("sender"));
        String addressToUse;
        boolean validAddress = true;
        InetAddress address = null;
        int port;
        if(senderReceiver.equalsIgnoreCase("sender")){
            System.out.println("Sender: Which IP address would you like to connect to?");
        
        do {
            validAddress = true;
            addressToUse = sc.next();
            try{          
                address = InetAddress.getByName(addressToUse);
            }
            catch (UnknownHostException e){
                System.out.println("Sender: The IP address you entered is invalid.");
                System.out.println("Sender: Please enter a valid IP address.");
                validAddress = false;
            }
        }
        while (validAddress == false);


        System.out.println("Sender: The IP address to reach was set to " + addressToUse);

        System.out.println("Sender: What port would you like to use?");     
        while(!sc.hasNextInt()){
            sc.next();
            System.out.println("Sender: Please enter a valid port number.");
        }
        String portToUse = sc.next();
        port = Integer.parseInt(portToUse);
        System.out.println("Sender: The port was set to " + port);
            Sender(address, port);
        }
        else{
            System.out.println("Receiver: What port would you like to use?");     
        
            while(!sc.hasNextInt()){
                sc.next();
                System.out.println("Receiver: Please enter a valid port number.");
            }
        String portToUse = sc.next();
        port = Integer.parseInt(portToUse);
        System.out.println("Receiver: The port was set to " + port);
            Receiver(port);
        }
        System.out.println();
        Boolean againOrNot= true;
        System.out.println();
        do{
            if (YesNo("Would you like to do an other test? (Yes, No)").equalsIgnoreCase("yes")){
                againOrNot = true;
            
                if(YesNo("Would you like to keep your " + senderReceiver + " status? (Yes, No)").equalsIgnoreCase("no")){
                    if(senderReceiver.equalsIgnoreCase("sender")){
                        senderReceiver = "receiver";
                    }
                    else{
                        senderReceiver = "sender";
                    }
                }
                if (senderReceiver.equalsIgnoreCase("sender")){
                    if(address == null){
                        System.out.println("What IP address would  you like to use?");
                        do {
                            validAddress = true;
                            addressToUse = sc.next();
                            try{          
                                address = InetAddress.getByName(addressToUse);
                            }
                            catch (UnknownHostException e){
                                System.out.println("Sender: The IP address you entered is invalid.");
                                System.out.println("Sender: Please enter a valid IP address.");
                                validAddress = false;
                            }
                        }
                        while (validAddress == false);
                    }
                    else{
                    if(YesNo("Would you like to reach the same IP address? (Yes, No)").equalsIgnoreCase("no")){
                    System.out.println("What IP address would you like to reach next?");
                    do {
                        validAddress = true;
                        addressToUse = sc.next();
                        try{          
                            address = InetAddress.getByName(addressToUse);
                        }
                        catch (UnknownHostException e){
                            System.out.println("Sender: The IP address you entered is invalid.");
                            System.out.println("Sender: Please enter a valid IP address.");
                            validAddress = false;
                        }
                    }
                    while (validAddress == false);
                    }
                }
                }
                if(YesNo("Would you like to use the same port? (Yes, No)").equalsIgnoreCase("no")){
                    System.out.println("What port would you like to use?");
                    while(!sc.hasNextInt()){
                        sc.next();
                        System.out.println("Sender: Please enter a valid port number.");
                    }
                    String portToUse = sc.next();
                    port = Integer.parseInt(portToUse);
                    System.out.println("Sender: The port was set to " + port);
                } 
                if(senderReceiver.equalsIgnoreCase("sender")){
                    Sender(address, port);
                }                         
                else{
                    Receiver(port);
                } 
            }
            else{
                againOrNot=false;
            }
        }
        while(againOrNot==true);
        System.out.println("Thank you for using the utility :) \n     By Amanda Plouffe");

        sc.close();
    }
}
