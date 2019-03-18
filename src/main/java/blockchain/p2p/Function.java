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

public class Function {
	
	
	public static int[] StrConvertInt(String str){
		str=str.replace("[","");
		str=str.replace("]","");
		String[] arr = str.split(", "); // ��,�ָ�
	   
	     int[] ints = new int[arr.length];
	     
	     for(int k=0;k<arr.length;k++){
	         ints[k] = Integer.parseInt(arr[k]);
	     }
	     return ints;
		
	}
	
	
	public static void SentPort(int port,int[] portlist){     //���ͱ�����ͨ���б��ָ���Ŀͻ���port��
		
	    try {
            //����Socket����
            Socket socket=new Socket("localhost",port);
            
            //��������������ͷ��������
            OutputStream outputStream=socket.getOutputStream();//��ȡһ��������������˷�����Ϣ
            PrintWriter printWriter=new PrintWriter(outputStream);//���������װ�ɴ�ӡ��                      
            printWriter.print(Arrays.toString(portlist)); //���ͱ�����
            printWriter.flush();
            socket.shutdownOutput();//�ر������                                                         
            //�ر����Ӧ����Դ

            printWriter.close();
            outputStream.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
	}
	
	
	public void JlistUpdate(int[] portlist){    //������������ӡ��JList��
				
		p2pOnline.mo.clear();
		
		for(int i=0;i<portlist.length;i++){
			
			
			if(portlist[i]!=0) {
			p2pOnline.mo.addElement(portlist[i]);
			}
			
		}
	
	}
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
   
	/*	
     int [] i = {4444,5555,6666,7777,8888};
     int[]	j;
     String info =Arrays.toString(i);
     info=info.replace("[","");
     info=info.replace("]","");
     String[] arr = info.split(", "); // ��,�ָ�
     System.out.println(Arrays.toString(arr));
     
     int[] ints = new int[arr.length];
     
     for(int k=0;k<arr.length;k++){
         ints[k] = Integer.parseInt(arr[k]);
     }

     System.out.println(Arrays.toString(ints));
		
	*/	

		
		
	}
			        
			     
	

}
