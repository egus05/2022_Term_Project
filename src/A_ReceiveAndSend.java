import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class A_ReceiveAndSend implements Runnable {
    private Socket socket;
    DataInputStream in;
    DataOutputStream out;
    int port;

    A_GUI aGui;

    A_ReceiveAndSend(Socket s) {
        socket = s;
        aGui = new A_GUI(this);
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            port = socket.getPort();
            aGui.setTitle("Chat With "+port);
            aGui.appendMsg("You're Connected With User: "+port+"\nEnter Your Message:");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg){
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (in!=null) {
            try {
                aGui.appendMsg(port + ": " + in.readUTF());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
