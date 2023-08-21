package ThuHanh.ThuHanh2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        // Khởi tạo một đối tượng Scanner để đọc đầu vào từ người dùng
        Scanner scanner = new Scanner(System.in);
        // Tạo đối tượng DatagramSocket để gửi và nhận dữ liệu qua giao thức UDP
        DatagramSocket clientDataSocket = new DatagramSocket();
        // Lấy địa chỉ IP localhost
        InetAddress address = InetAddress.getLocalHost();
        int port = 12354;


        // Đọc phép tính từ người dùng
        while (true){
            System.out.println("Nhan phep tinh duoi dang chuoi");
            System.out.println("so_hang_1 || (+-*/) ||so_hang_2");
            String inp = scanner.nextLine();

            // Chuyển đổi chuỗi phép tính thành mảng byte để gửi đi
            byte [] buf = inp.getBytes();

            // Tạo DatagramPacket để gửi dữ liệu đến máy chủ (port 12344)
            DatagramPacket clientDataPacket = new DatagramPacket(buf, buf.length,address,port);
            // Gửi gói dữ liệu đi
            clientDataSocket.send(clientDataPacket);


            // Nếu người dùng nhập "bye" thì thoát khỏi vòng lặp
            if (inp.equals("bye")) {
                break;
            }

            // Reset lại kích thước mảng byte để nhận dữ liệu mới
            buf = new byte[44444];
            // Tạo DatagramPacket để nhận dữ liệu từ máy chủ
            DatagramPacket receiveDataPacket = new DatagramPacket(buf, buf.length);
            // Nhận gói dữ liệu từ máy chủ
            clientDataSocket.receive(receiveDataPacket);

            // Hiển thị kết quả tính toán từ máy chủ
            String result = new String(receiveDataPacket.getData(),receiveDataPacket.getOffset(),receiveDataPacket.getLength());
            System.out.println("ket qua: " + result);
        }

        clientDataSocket.close();
    }
}
