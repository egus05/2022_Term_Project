import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class A_ServerMain {
	//서버를 실행시킨다

	public static void main(String[] args) {

		try {
			ServerSocket listener = new ServerSocket(9889);
			System.out.println("Server is running...");
			ExecutorService pool = Executors.newFixedThreadPool(10);

			while (true) {
				Socket sock = listener.accept();
				System.out.println("new Client : "+sock.getPort());
				pool.execute(new A_ReceiveAndSend(sock));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}


