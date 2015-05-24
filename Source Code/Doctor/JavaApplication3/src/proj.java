import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

public class proj extends JApplet
{
	public JFrame jf;
	Timer timer;
	int i,j;
	int screenWidth;
	JTextField pname,pid,pmid;
	JLabel lpname,lpid,lpmid;
	int screenHeight;
	JPanel patinfo;
	int w1,h1,x1,y1;
	JButton submit;
	public proj()
	{
		try{
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		DisplayMode dm = gs[0].getDisplayMode();
		screenWidth = dm.getWidth();
	        screenHeight = dm.getHeight();
		i=screenWidth;
		j=screenHeight;

		x1=screenWidth/4;
		y1=screenHeight/4;

		
		jf=new JFrame("Blood Sugar Monitoring Using Pervasive Computing Technologies");
		jf.setLayout(null);
		jf.setResizable(false);
		jf.setBounds(x1,y1,457,450);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);

	
				
		patinfo=new JPanel(true);
		patinfo.setVisible(false);
		patinfo.setLayout(null);
		patinfo.setBounds(0,0,450,400);
		patinfo.setBackground(Color.pink);
		Border raise=BorderFactory.createRaisedBevelBorder();
		Border titled=BorderFactory.createTitledBorder(raise,"Register");
		patinfo.setBorder(titled);
		jf.add(patinfo);

		lpname=new JLabel("Patient Name :");
		lpname.setVisible(true);
		lpname.setBounds(50,50,100,40);
		patinfo.add(lpname);

		lpid=new JLabel("Medical ID :");
		lpid.setVisible(true);
		lpid.setBounds(50,125,100,40);
		patinfo.add(lpid);
	
		lpname=new JLabel("E-Mail ID :");
		lpname.setVisible(true);
		lpname.setBounds(50,200,100,40);
		patinfo.add(lpname);
	
		pname=new JTextField();
		pname.setVisible(true);
		pname.setBounds(200,60,150,25);
		patinfo.add(pname);
	
		pid=new JTextField();
		pid.setVisible(true);
		pid.setBounds(200,135,150,25);
		patinfo.add(pid);
	
		pname=new JTextField();
		pname.setVisible(true);
		pname.setBounds(200,210,150,25);
		patinfo.add(pname);

		submit=new JButton("Register");
		submit.setVisible(true);
		submit.setBounds(150,300,100,30);
		patinfo.add(submit);

		submit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null,patinfo.getWidth()+""+patinfo.getHeight());
			}
		});
	
		int i1=450;
		int j1=400;
		for(;i1>10 && j1>10;i1-=10,j1-=8)
		{
			Thread.sleep(4);
			jf.setBounds(x1,y1,i1,j1);	

		}
		
		
		for(i=0,j=0;i<450;i+=10,j+=8)
		{
			if(j>400)
				j=400;
			Thread.sleep(4);
			jf.setBounds(x1,y1,i,j);	
		}
		jf.setBounds(x1,y1,457,433);

		


		File f=new File("patinfo");
		if(!f.exists())
		{
			f.createNewFile();
			patinfo.setVisible(true);		
		}
		boolean s=true;
		x1=screenWidth/2;
		y1=screenHeight/2;
		
		}
		catch (Exception e)
		{}
	}
	class RemindTask extends TimerTask
	{
		int i,j;
		public RemindTask(int i,int j)
		{
			this.i=i;
			this.j=j;
		}
		public void run()
		{
			jf.setBounds(x1,y1,457,433);
		}
	}	
	public static void main(String ar[])
	{
		proj p=new proj();
	}
}