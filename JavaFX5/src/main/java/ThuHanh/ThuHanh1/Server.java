package ThuHanh.ThuHanh1;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {

        int serverPort = 5555;
        //tạo đối tượng DatagramSocket để liên kết vs cổng cụ thể 5555 trên máy chủ
       DatagramSocket serverDataSocket = new DatagramSocket(serverPort);
        System.out.println("Đạng kết nối toiws ThuHanh.ThuHanh1.Client...!");


        //tạo một mảng byte để nhận dữ liệu từ gói tin nhận
        byte [] receiveData = new byte[1024];

        // Tạo đối tượng Datagramacket để nhận dữ liệu từ client
        DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);

        //nhận gói tin từ client
        serverDataSocket.receive(receivePacket);
        System.out.println("");
        //lấy địa chỉ IP của cổng client
        InetAddress addressServer = receivePacket.getAddress();
        int clientPort = receivePacket.getPort();

        //chuyển dữ liệu thành dạng chuỗi
        String dataString = new String(receivePacket.getData(),0,receiveData.length);
        System.out.println("tin nhắn từ ThuHanh.ThuHanh1.Client: " + dataString);

        //tao mot mang byte chu du lieu can gui
        Scanner scanner = new Scanner(System.in);
        System.out.println("nhap tin nhan: ");
        String messageServer = scanner.nextLine();
        byte [] serverSend = messageServer.getBytes();


        //chuẩn bị dữ liệu gửi  đi
        DatagramPacket datagramPacketServer = new DatagramPacket(serverSend,serverSend.length,addressServer,clientPort);
        serverDataSocket.send(datagramPacketServer);//tạo DatagramPacket để guiwr tin đến client


        serverDataSocket.close();
    }
}
