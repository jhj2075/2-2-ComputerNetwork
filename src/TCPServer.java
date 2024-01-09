import java.io.*;
import java.net.*;
class TCPServer {
    public static void main(String argv[]) throws Exception
    {
        String fromclient; // 클라이언트에서 수신 받은 메세지
        String studuentID; // 학번 String

        ServerSocket welcomeSocket = new ServerSocket(23003); // 서버 소켓 생성
        while(true) {
            Socket connectionSocket = welcomeSocket.accept(); // 클라이언트와 연결되면 welcomeSocket의 정보를 받은 connectionSocket이 만들어짐

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); // 소켓에 attach된 input stream 생성
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream()); // 소켓에 attach된 output stream 생성

            fromclient = inFromClient.readLine(); // 클라이언트에서 메세지 receive
            System.out.println(fromclient); // receive한 메세지 출력

            studuentID = "20201499";
            outToClient.writeBytes(studuentID + '\n'); // 클라이언트로 메세지 send
        }
    }
}