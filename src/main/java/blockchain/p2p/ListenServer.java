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
		
		 System.out.print("���뺯��");
		 
		 
    try {
    	
    	
        ServerSocket serverSocket = new ServerSocket(p2pOnline.i);
        System.out.println("��������������ȴ��ͻ�������..");

        while (true) {
            Socket socket = serverSocket.accept();// ���������ܵ����׽��ֵ�����,����һ��Socket����
            SocketThread socketThread = new SocketThread(socket);
            socketThread.start();
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
	}
    
}




class Connect implements Runnable{                      //�������8888���Ӳ������Լ��Ķ˿ںţ���ȡ8888�������ж˿��б�
    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            //����Socket����
            Socket socket=new Socket("localhost",8888);
            
            //��������������ͷ��������
            OutputStream outputStream=socket.getOutputStream();//��ȡһ��������������˷�����Ϣ
            PrintWriter printWriter=new PrintWriter(outputStream);//���������װ�ɴ�ӡ��
            printWriter.print(p2pOnline.i);
            printWriter.flush();
            socket.shutdownOutput();//�ر������
            
            InputStream inputStream=socket.getInputStream();//��ȡһ�������������շ���˵���Ϣ
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);//��װ���ַ��������Ч��
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);//������
            String info="";
            String temp=null;//��ʱ����
            while((temp=bufferedReader.readLine())!=null){
                info+=temp;
                System.out.println("�ͻ��˽��շ���˷�����Ϣ��"+info);
            }
            
            p2pOnline.port=null;
            p2pOnline.port=Function.StrConvertInt(info);  // ������˷�����port�б����int�б���
            System.out.println("������˷�����port�б����int�б��в���ӡ");
            Arrays.toString(p2pOnline.port);
            
            
            //�ر����Ӧ����Դ
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


	
	

