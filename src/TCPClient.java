import java.io.*;
import java.net.*;
class TCPClient {
    public static void main(String argv[]) throws Exception
    {
        String engSentence; // 영문 메세지 String
        String fromSever; // 서버에서 수신 받은 메세지
        Socket clientSocket = new Socket("localhost", 23003); // 클라이언트 소켓 생성

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); // output stream 생성
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // 소켓에 attach된 input stream 생성

        engSentence = "I love ComputerNetwork";
        outToServer.writeBytes(engSentence + '\n'); // 서버로 메세지 send

        fromSever = inFromServer.readLine(); // 서버에서 메세지 receive
        System.out.println(fromSever); // receive한 메세지 출력

        clientSocket.close();
    }
}