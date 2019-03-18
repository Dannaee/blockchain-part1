package blockchain.p2p;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

/**
 * Socket多线程处理类 用来处理服务端接收到的客户端请求（处理Socket对象）
 */
public class SocketThread extends Thread {
	Function function=new Function();
    private Socket socket;
    static int i=0;
    public SocketThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
    	
        // 根据输入输出流和客户端连接
        try { 
        	
       	
            InputStream inputStream = socket.getInputStream();
            // 得到一个输入流，接收客户端传递的信息
            InputStreamReader inputStreamReader = new InputStreamReader(
                    inputStream);// 提高效率，将自己字节流转为字符流
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);// 加入缓冲区
            String temp = null;
            String info = "";
            
            while ((temp = bufferedReader.readLine()) != null) {
                info += temp;
                p2pOnline.port[i]=Integer.parseInt(temp);
                
                System.out.println(Arrays.toString(p2pOnline.port));
                System.out.println("已接收到客户端连接");
                System.out.println("服务端接收到客户端信息：" + p2pOnline.port[i] + ",当前客户端ip为："
                        + socket.getInetAddress().getHostAddress());
                
                function.JlistUpdate(p2pOnline.port);
                
                i++;
            }

            OutputStream outputStream = socket.getOutputStream();// 获取一个输出流，向服务端发送信息
            PrintWriter printWriter = new PrintWriter(outputStream);// 将输出流包装成打印流
            printWriter.print(Arrays.toString(p2pOnline.port));    //发送已连接机器的端口
            printWriter.flush();
            socket.shutdownOutput();// 关闭输出流

            // 关闭相对应的资源
            bufferedReader.close();
            inputStream.close();
            printWriter.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}