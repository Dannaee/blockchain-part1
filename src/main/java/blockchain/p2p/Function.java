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
		String[] arr = str.split(", "); // 用,分割
	   
	     int[] ints = new int[arr.length];
	     
	     for(int k=0;k<arr.length;k++){
	         ints[k] = Integer.parseInt(arr[k]);
	     }
	     return ints;
		
	}
	
	
	public static void SentPort(int port,int[] portlist){     //发送本机中通信列表给指定的客户（port）
		
	    try {
            //创建Socket对象
            Socket socket=new Socket("localhost",port);
            
            //根据输入输出流和服务端连接
            OutputStream outputStream=socket.getOutputStream();//获取一个输出流，向服务端发送信息
            PrintWriter printWriter=new PrintWriter(outputStream);//将输出流包装成打印流                      
            printWriter.print(Arrays.toString(portlist)); //发送本机的
            printWriter.flush();
            socket.shutdownOutput();//关闭输出流                                                         
            //关闭相对应的资源

            printWriter.close();
            outputStream.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
	}
	
	
	public void JlistUpdate(int[] portlist){    //将传入的数组打印在JList中
				
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
     String[] arr = info.split(", "); // 用,分割
     System.out.println(Arrays.toString(arr));
     
     int[] ints = new int[arr.length];
     
     for(int k=0;k<arr.length;k++){
         ints[k] = Integer.parseInt(arr[k]);
     }

     System.out.println(Arrays.toString(ints));
		
	*/	

		
		
	}
			        
			     
	

}
