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
 * Socket���̴߳����� �����������˽��յ��Ŀͻ������󣨴���Socket����
 */
public class SocketThread extends Thread {
	Function function=new Function();
    private Socket socket;
    static int i=0;
    public SocketThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
    	
        // ��������������Ϳͻ�������
        try { 
        	
       	
            InputStream inputStream = socket.getInputStream();
            // �õ�һ�������������տͻ��˴��ݵ���Ϣ
            InputStreamReader inputStreamReader = new InputStreamReader(
                    inputStream);// ���Ч�ʣ����Լ��ֽ���תΪ�ַ���
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);// ���뻺����
            String temp = null;
            String info = "";
            
            while ((temp = bufferedReader.readLine()) != null) {
                info += temp;
                p2pOnline.port[i]=Integer.parseInt(temp);
                
                System.out.println(Arrays.toString(p2pOnline.port));
                System.out.println("�ѽ��յ��ͻ�������");
                System.out.println("����˽��յ��ͻ�����Ϣ��" + p2pOnline.port[i] + ",��ǰ�ͻ���ipΪ��"
                        + socket.getInetAddress().getHostAddress());
                
                function.JlistUpdate(p2pOnline.port);
                
                i++;
            }

            OutputStream outputStream = socket.getOutputStream();// ��ȡһ��������������˷�����Ϣ
            PrintWriter printWriter = new PrintWriter(outputStream);// ���������װ�ɴ�ӡ��
            printWriter.print(Arrays.toString(p2pOnline.port));    //���������ӻ����Ķ˿�
            printWriter.flush();
            socket.shutdownOutput();// �ر������

            // �ر����Ӧ����Դ
            bufferedReader.close();
            inputStream.close();
            printWriter.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}