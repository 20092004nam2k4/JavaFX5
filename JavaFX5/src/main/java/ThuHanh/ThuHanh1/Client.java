package ThuHanh.ThuHanh1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        String serverIP = "localhost";
        int serverPort = 5555;

        //1 tạo đối tượng DatagramSocket để kết nối tới máy chủ ThuHanh.ThuHanh1.Client
        DatagramSocket clientDataSocket = new DatagramSocket();
        System.out.println("đã kết nối dến máy chủ...!");

        //2 tạo một mảng byte để chứa dữ liệu tin nhắn cần gửi
        Scanner scanner = new Scanner(System.in);
        String clientMessage = scanner.nextLine();
        byte [] sendClient = clientMessage.getBytes();
        //3 gửi gói tin đến server
        InetAddress address = InetAddress.getByName(serverIP);
        DatagramPacket clientDataPacket = new DatagramPacket(sendClient,sendClient.length,address,serverPort);
        clientDataSocket.send(clientDataPacket);
        //4 chuẩn bị mảng byte để nhận dữ liệu từ server

        byte [] receiveServer = new byte[1024];
        DatagramPacket receiveDataPacket = new DatagramPacket(receiveServer,receiveServer.length);
         clientDataSocket.receive(receiveDataPacket);//5 nhận gói tin từ server

        //6 chuyển đổi dữ liệu thành dạng chuỗi và hiển thị
        String dataString = new String(receiveDataPacket.getData(),0,receiveDataPacket.getLength());
        System.out.println("nhan tin nhan server: " + dataString);
        //7 đóng kết nối DatagramSocket
        clientDataSocket.close();

    }
}
