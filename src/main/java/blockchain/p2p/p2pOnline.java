package blockchain.p2p;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.Button;
import javax.swing.text.Document;








public class p2pOnline {
	
	static DefaultTableModel tm1 = null;
	static	int i,j;
	static int[] port=new int[15];
	private static JFrame frame;
	static DefaultListModel mo;
	
	public static   void createAndShowGUI() {
		
		Function function=new Function();
			
		frame = new JFrame ("毕设币交易系统");
		frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane jsp1,jsp2,jsp3;
        JPanel panel=new JPanel(null);
        
        
        JLabel yh = new JLabel("   地址 ：");
        JLabel dz = new JLabel("端口号   ：");
        JButton Jb = new JButton("登录");
   
        JTextField yh1= new JTextField("");
        JTextField dz1 = new JTextField();
        
        JList list1 = new JList();
        JList list_item = new JList();
        list_item.setModel(new DefaultListModel());
        mo = (DefaultListModel)list_item.getModel();
        mo.addElement("item1");
        mo.addElement("item2");
        mo.addElement("item3");
        
        jsp1 = new JScrollPane(list_item); 
        jsp2 = new JScrollPane(list_item); 
        jsp3 = new JScrollPane(list1); 
        
        yh.setBounds(50,60,80,20);
        dz.setBounds(50,80,80,20);
        Jb.setBounds(280, 60, 100,40);
        yh1.setBounds(120,60,140,20);
        dz1.setBounds(120,80,140,20);
        
        jsp1.setBounds(50, 100, 300, 400); 
        jsp2.setBounds(500,100,250,170);
        jsp3.setBounds(500,300,250,200); 
        
        
        panel.add(jsp1);
        panel.add(jsp2);
        panel.add(jsp3);
        panel.add(yh);
        panel.add(dz);
        panel.add(Jb);
        panel.add(yh1);
        panel.add(dz1);
        frame.add(panel);
        
        
        j=0;
        Jb.addActionListener(new ActionListener() {    //登录按钮实现  打开服务端   发送数据
            public void actionPerformed(ActionEvent e) {
            	
            	if(j==0){	
            	i = Integer.parseInt(dz1.getText());            
            	Runnable ls = new ListenServer();
            	Thread t1 = new Thread(ls);
            	t1.start();
            	 System.out.print("线程启动");
            	 j++;
            	}
            	 
            	

            	
            	Runnable con = new Connect();
             	Thread t2 = new Thread(con);
             	t2.start();
             	try {
					t2.join();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
             	System.out.print("线程启动");
            	 
             	function.JlistUpdate(port);
             	
            }
        });

        
        
	}
	

	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       
	     javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public  void run() {
	            	createAndShowGUI(); 
	            	
	            	
	            }
	        });
			        
			     
	}
}


