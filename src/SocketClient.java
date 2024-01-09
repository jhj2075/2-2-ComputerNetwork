import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketClient {
    public static void main(String argv[]) throws Exception {
        String userInput; // 클라이언트가 입력할 input
        String serverResponse; // 서버로 부터 받아올 String
        Socket clientSocket = new Socket("localhost", 23003); // 클라이언트 소켓 생성

        BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in)); // 키보드로 부터 입력 받을 stream 생성
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); // output stream 생성
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // 소켓에 attach된 input stream 생성

        while (true) {
            System.out.print("보내기 >> ");
            userInput = userInputReader.readLine(); // 키보드로 부터 받은 input을 변수에 저장

            outToServer.writeBytes(userInput + '\n'); // 서버로 메세지 send
            outToServer.flush();

            if (userInput.equalsIgnoreCase("bye")) { // 키보드로 부터 입력한 input이 "bye"이면 반복 종료
                break;
            }

            serverResponse = inFromServer.readLine(); // 서버에서 receive한 메세지 저장
            System.out.println("서버 >> " + serverResponse); // 서버로 부터 receive한 메세지 출력
        }

        clientSocket.close();
    }
}
