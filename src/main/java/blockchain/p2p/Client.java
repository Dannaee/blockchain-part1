package blockchain.p2p;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;


public class Client {

    /**
     * Socket�ͻ���
     */
    public static void main(String[] args) {
        try {
            //����Socket����
            Socket socket=new Socket("localhost",8888);
            
            //��������������ͷ��������
            OutputStream outputStream=socket.getOutputStream();//��ȡһ��������������˷�����Ϣ
            PrintWriter printWriter=new PrintWriter(outputStream);//���������װ�ɴ�ӡ��
            printWriter.print(p2pOnline.i);//���ͱ����˿ں�
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
            
            int[] j=Function.StrConvertInt(info);
            System.out.println(Arrays.toString(j));
            
            
            
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