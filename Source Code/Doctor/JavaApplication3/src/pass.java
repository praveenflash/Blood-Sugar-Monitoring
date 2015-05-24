import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.lang.String;

public class pass extends JApplet
{
	JFrame pa;
	JTextField t1;
	JPasswordField t2,t3;
	JLabel l1,l2,l3;
	JButton confirm;
	Connection con;
	Statement st;
	ResultSet rs;
	String u,p,cp;
	public pass()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:patient","","");
			st=con.createStatement();

			pa=new JFrame("CREATE ACCOUNT");
			pa.setLayout(null);
			pa.setVisible(true);
			pa.setBounds(200,200,390,390);
			pa.setResizable(false);
			pa.setDefaultCloseOperation(pa.EXIT_ON_CLOSE);

			l1=new JLabel("User Name ");
			l1.setVisible(true);
			l1.setBounds(50,50,100,40);
			pa.add(l1);
	
			l2=new JLabel("Password ");
			l2.setVisible(true);
			l2.setBounds(50,125,100,40);
			pa.add(l2);
							
			l3=new JLabel("Confirm Password ");
			l3.setVisible(true);
			l3.setBounds(50,200,150,40);
			pa.add(l3);

			t1=new JTextField();
			t1.setVisible(true);
			t1.setBounds(200,60,150,25);
			pa.add(t1);

			
			t2=new JPasswordField();
			t2.setVisible(true);
			t2.setBounds(200,135,150,25);			
			pa.add(t2);

	
			t3=new JPasswordField();
			t3.setVisible(true);
			t3.setBounds(200,210,150,25);
			pa.add(t3);
	
			confirm=new JButton("Confirm");
			confirm.setVisible(true);
			confirm.setBounds(150,280,100,30);
			pa.add(confirm);
		
			confirm.addActionListener(new ActionListener()
			{
				
				public void actionPerformed(ActionEvent e)
				{
					try{
					u=t1.getText();
					p=t2.getText();
					cp=t3.getText();
					if((!u.equals("")) && (!p.equals("")) && (!cp.equals("")))
					{
						if(p.equals(cp))
						{
							st.executeUpdate("insert into admin values('"+u+"','"+p+"')");
							JOptionPane.showMessageDialog(null,"Authentications and Tokens Validated");
							pa.dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Please Confirm password correctly");
							t1.setText("");
							t2.setText("");
							t3.setText("");	
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Please enter all the fields ");	
						t1.setText("");
						t2.setText("");
						t3.setText("");
					}
					}
					catch(Exception e2)
					{
						JOptionPane.showMessageDialog(null,e2);	
					}
				}
			});
				
			pa.setBounds(200,200,400,400);

		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e);
		}
	}
	public static void main(String ar[])
	{
		pass p=new pass();
	}
}