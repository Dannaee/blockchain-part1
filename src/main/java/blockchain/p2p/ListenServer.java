package blockchain.p2p;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;




class ListenServer implements Runnable {
	@Override
	public void run() {
		
		 System.out.print("进入函数");
		 
		 
    try {
    	
    	
        ServerSocket serverSocket = new ServerSocket(p2pOnline.i);
        System.out.println("服务端已启动，等待客户端连接..");

        while (true) {
            Socket socket = serverSocket.accept();// 侦听并接受到此套接字的连接,返回一个Socket对象
            SocketThread socketThread = new SocketThread(socket);
            socketThread.start();
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
	}
    
}




class Connect implements Runnable{                      //与服务器8888连接并发送自己的端口号，获取8888服务器中端口列表
    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            //创建Socket对象
            Socket socket=new Socket("localhost",8888);
            
            //根据输入输出流和服务端连接
            OutputStream outputStream=socket.getOutputStream();//获取一个输出流，向服务端发送信息
            PrintWriter printWriter=new PrintWriter(outputStream);//将输出流包装成打印流
            printWriter.print(p2pOnline.i);
            printWriter.flush();
            socket.shutdownOutput();//关闭输出流
            
            InputStream inputStream=socket.getInputStream();//获取一个输入流，接收服务端的信息
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);//包装成字符流，提高效率
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);//缓冲区
            String info="";
            String temp=null;//临时变量
            while((temp=bufferedReader.readLine())!=null){
                info+=temp;
                System.out.println("客户端接收服务端发送信息："+info);
            }
            
            p2pOnline.port=null;
            p2pOnline.port=Function.StrConvertInt(info);  // 将服务端发来的port列表存入int列表中
            System.out.println("将服务端发来的port列表存入int列表中并打印");
            Arrays.toString(p2pOnline.port);
            
            
            //关闭相对应的资源
            bufferedReader.close();
            inputStream.close();
            printWriter.close();
            outputStream.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}


	
	

