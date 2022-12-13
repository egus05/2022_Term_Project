import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class A_ClientMain {

    A_ClientMain(){
        String serverIP = "";
        int serverPort = 0;

        //read server information from server_info.dat
        String temp;
        File dataFile = new File("./src/server_info.dat");
        FileReader inDat;
        BufferedReader readFile;

        try {
            inDat = new FileReader(dataFile);
            readFile = new BufferedReader(inDat);
            //open .dat file! if Error occurs, catch clause will be executed

            temp = readFile.readLine();
            serverIP = temp;
//            System.out.println(serverIP);
            //save serverIP

            temp = readFile.readLine();
            serverPort = Integer.parseInt(temp);
            System.out.println(serverPort);
            //save nPort

            readFile.close();
            inDat.close();
            //close FileReader(Stream)

        } catch (FileNotFoundException e) {
            System.out.println("File does not exist or could not be found.");
            System.err.println("FileNotFoundException: " + e.getMessage());

        } catch (IOException e) {
            System.out.println("Could not read the file.");
            System.err.println("IOException: " + e.getMessage());
        }

        if(serverIP!="" && serverPort !=0){
            Socket socket = null;
            try {
                socket = new Socket(serverIP, serverPort);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            ExecutorService pool = Executors.newFixedThreadPool(10);
            pool.execute(new A_ReceiveAndSend(socket));

        }
    }
}