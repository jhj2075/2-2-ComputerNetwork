import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ProtocolClient {

    public static void main(String[] args) throws IOException{
        //소켓 연결
        Socket socket = null;
        try {
            socket = new Socket("124.52.77.166", 9500);
            System.out.println("연결성공");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //클라이언트 -> 소켓
        String msg= "안녕하세요!";
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.println(msg);  //서버로 데이터를 전송한다.
        writer.flush();   //버퍼 안에 있는 값들을 전부 비워준다.
        System.out.println("데이터 전송 완료!");

        //소켓 -> 클라이언트
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = reader.readLine();
        System.out.println("데이터 받기 성공! :"+line);  //서버와 통신이 완료되어 "안녕하세요"라는 값을 가지고 온다.

        writer.close();
        reader.close();

    }
}