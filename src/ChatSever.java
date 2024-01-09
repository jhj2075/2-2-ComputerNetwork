import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatSever {
    public static void main(String argv[]) throws Exception {
        String clientMessage; // 클라이언트로 부터 receive 한 메세지
        String serverResponse; // 클라이언트로 send 할 메세지
        ServerSocket welcomeSocket = new ServerSocket(23003); // 서버 소켓 생성

        System.out.println("연결 대기중...");
        while (true) {
            Socket connectionSocket = welcomeSocket.accept(); // 클라이언트와 연결되면 welcomeSocket의 정보를 받은 connectionSocket이 만들어짐
            System.out.println("연결 되었습니다.");

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); // 소켓에 attach된 input stream 생성
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream()); // 소켓에 attach된 output stream 생성

            while (true) {
                clientMessage = inFromClient.readLine(); // 클라이언트로 부터 receive 한 메세지 저장

                if (clientMessage.equalsIgnoreCase("bye")) { // 클라이언트로 부터 receive 한 메세지가 "bye"일 경우 문장 출력 후 반복 종료
                    System.out.println("클라이언트가 나갔습니다.");
                    break;
                }

                System.out.println("클라이언트 >> " + clientMessage); // 클라이언트로 부터 receive 한 메세지 출력


                BufferedReader serverInputReader = new BufferedReader(new InputStreamReader(System.in)); // 키보드로 부터 입력 받을 stream 생성
                System.out.print("보내기 >> ");
                serverResponse = serverInputReader.readLine(); // 키보드로 부터 받은 input을 변수에 저장

                outToClient.writeBytes(serverResponse + '\n'); // 클라이언트로 메세지 send
                outToClient.flush();
            }

            connectionSocket.close();
        }
    }
}
