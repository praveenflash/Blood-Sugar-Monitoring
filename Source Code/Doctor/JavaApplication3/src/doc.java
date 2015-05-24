
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Toolkit;
import java.util.Timer;
import javax.swing.table.*;
import java.text.*;
import java.util.TimerTask;
import java.sql.*;

public class doc extends JApplet {

    JFrame auth, docinfo, bchart;
    JTextField user;
    JPanel im, patlist, patbio, bc, pan3, pan4;
    JPasswordField pass;
    JButton log;
    JLabel lpass, luser, pinfo;
    JButton back;
    JTextField pname, pname1, pid, pmid;
    JLabel lpname, lpid, lpmid, lread, lfrom, lto;
    int i, j;
    JTable list, sellist;
    int screenHeight, screenWidth;
    JScrollPane scrollPane;
    JButton tabdata, getchart;
    JButton btnDate;
    JButton btnClearDate;
    JButton btnDate1;
    JButton btnClearDate1;
    JCalendar jCalendar = null;
    JCalendar jCalendar1 = null;
    JDateTextField txtDate;
    JDateTextField txtDate2;
    int count, count1;
    Font fp = new Font("Times New Roman", Font.BOLD, 18);
    Connection con;
    Statement st;
    ResultSet rs;
    int m = 0, cnt;
    String[] columnNames = {"ID", "Name", "Address", "Phone"};
    String[] tableNames = {"Date", "Value"};
    String[][] data;
    String[][] sellistdata;
    int defaultval = 50;
    int vs;
    String dname = "Not Assigned";
    String[][] seldata;
    SimpleBarChart n;
    JButton getmail;

    public doc() {
        try {

            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con = DriverManager.getConnection("jdbc:odbc:patient", "", "");
            st = con.createStatement();




            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] gs = ge.getScreenDevices();
            DisplayMode dm = gs[0].getDisplayMode();
            screenWidth = dm.getWidth();
            screenHeight = dm.getHeight();
            i = screenWidth;
            j = screenHeight;

            auth = new JFrame("Log In");
            auth.setLayout(null);
            auth.setVisible(true);
            auth.setBounds((i / 3), (j / 3), 351, 351);
            auth.setResizable(false);
            auth.setDefaultCloseOperation(auth.EXIT_ON_CLOSE);


            docinfo = new JFrame("Welcome Doctor");
            docinfo.setLayout(null);
            docinfo.setVisible(false);
            docinfo.setBounds(0, 0, i - 1, (j / 2) + 50);
            docinfo.setResizable(true);
            docinfo.setDefaultCloseOperation(auth.EXIT_ON_CLOSE);

            getmail = new JButton("Get Mail");
            getmail.setVisible(true);
            getmail.setBounds((i / 2) - 50, (j / 2), 100, 30);
            docinfo.add(getmail);


            patbio = new JPanel(true);
            patbio.setVisible(false);
            patbio.setLayout(null);
            patbio.setBounds((i / 2), 0, (i), (j / 2));
            patbio.setBackground(Color.pink);
            Border raise6 = BorderFactory.createRaisedBevelBorder();
            Border titled6 = BorderFactory.createTitledBorder(raise6, "PATIENT BIODATA");
            patbio.setBorder(titled6);
            docinfo.add(patbio);

            patlist = new JPanel(true);
            patlist.setVisible(false);
            patlist.setLayout(new BorderLayout());
            patlist.setBounds(0, 0, (i / 2), (j / 2));
            patlist.setBackground(Color.pink);
            Border raise1 = BorderFactory.createRaisedBevelBorder();
            Border titled1 = BorderFactory.createTitledBorder(raise1, "PATIENT LIST");
            patlist.setBorder(titled1);
            docinfo.add(patlist);



            // Panel 11111111111111111

            lpname = new JLabel("Patient Name :");
            lpname.setVisible(true);
            lpname.setBounds(50, 50, 100, 40);
            patbio.add(lpname);

            lpid = new JLabel("Medical ID :");
            lpid.setVisible(true);
            lpid.setBounds(50, 125, 100, 40);
            patbio.add(lpid);

            lpname = new JLabel("Phone No :");
            lpname.setVisible(true);
            lpname.setBounds(50, 200, 100, 40);
            patbio.add(lpname);

            pname = new JTextField();
            pname.setVisible(true);
            pname.setBounds(200, 60, 150, 25);
            pname.setEditable(false);
            patbio.add(pname);

            pid = new JTextField();
            pid.setVisible(true);
            pid.setBounds(200, 135, 150, 25);
            pid.setEditable(false);
            patbio.add(pid);

            pname1 = new JTextField();
            pname1.setVisible(true);
            pname1.setBounds(200, 210, 150, 25);
            pname1.setEditable(false);
            patbio.add(pname1);


            lread = new JLabel("READINGS");
            lread.setVisible(true);
            lread.setBounds(200, 225, 100, 40);
            patbio.add(lread);


            lfrom = new JLabel("From :");
            lfrom.setVisible(true);
            lfrom.setBounds(50, 250, 100, 40);
            patbio.add(lfrom);

            pinfo = new JLabel("PATIENT INFORMATION");
            pinfo.setVisible(true);
            pinfo.setBounds(200, 10, 200, 40);
            patbio.add(pinfo);



            lto = new JLabel("To  :");
            lto.setVisible(true);
            lto.setBounds(50, 300, 100, 40);
            patbio.add(lto);

            txtDate = new JDateTextField(JDateTextField.LONG_DATE);
            txtDate.setBounds(200, 260, 105, 20);
            txtDate.setEditable(false);
            txtDate.setVisible(true);
            txtDate.setFont(fp);
            patbio.add(txtDate);

            btnDate = new JButton(new ImageIcon("date.gif"));
            btnDate.setBounds(310, 260, 20, 20);
            btnDate.setVisible(true);
            patbio.add(btnDate);

            btnClearDate = new JButton(new ImageIcon("delete.gif"));
            btnClearDate.setBounds(335, 260, 20, 20);
            btnClearDate.setVisible(true);
            patbio.add(btnClearDate);

            txtDate2 = new JDateTextField(JDateTextField.LONG_DATE);
            txtDate2.setBounds(200, 310, 105, 20);
            txtDate2.setEditable(false);
            txtDate2.setVisible(true);
            txtDate2.setFont(fp);
            patbio.add(txtDate2);

            btnDate1 = new JButton(new ImageIcon("date.gif"));
            btnDate1.setBounds(310, 310, 20, 20);
            btnDate1.setVisible(true);
            patbio.add(btnDate1);

            btnClearDate1 = new JButton(new ImageIcon("delete.gif"));
            btnClearDate1.setBounds(335, 310, 20, 20);
            btnClearDate1.setVisible(true);
            patbio.add(btnClearDate1);

            getchart = new JButton("Get Chart");
            getchart.setVisible(true);
            getchart.setBounds(200, 345, 100, 30);
            patbio.add(getchart);

            getchart.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    try {
                        docinfo.setVisible(false);

                        bchart = new JFrame("Patient Status");
                        bchart.setLayout(null);
                        bchart.setVisible(true);
                        bchart.setBounds(200, 200, 800, 800);
                        bchart.setResizable(false);
                        bchart.setDefaultCloseOperation(auth.EXIT_ON_CLOSE);

                        bc = new JPanel(true);
                        bc.setVisible(true);
                        bc.setLayout(new BorderLayout());
                        bc.setBounds(400, 0, 400, 400);
                        bc.setBackground(Color.pink);
                        Border raise2 = BorderFactory.createRaisedBevelBorder();
                        Border titled2 = BorderFactory.createTitledBorder(raise2, "PATIENT READINGS");
                        bc.setBorder(titled2);
                        bchart.add(bc);

                        back = new JButton("Done");
                        back.setVisible(true);
                        back.setBounds(10, 420, 100, 30);
                        bchart.add(back);


                        int count = 0;
                        int k = 0;
                        String id = pid.getText();
                        seldata = new String[10][2];

                        rs = st.executeQuery("select rdate,value from glucoreading where ID='" + id + "' and rdate between #" + txtDate.getText(13) + "# and #" + txtDate2.getText(13) + "# order by rdate desc");
                        if (k >= 5) {
                            while (rs.next()) {
                                seldata[count][0] = "" + rs.getDate(1);
                                seldata[count][1] = rs.getString(2);
                                count++;
                            }
                        } else {
                            while (rs.next()) {
                                seldata[count][0] = "" + rs.getDate(1);
                                seldata[count][1] = rs.getString(2);
                                count++;
                            }
                            while (count <= 5) {
                                seldata[count][0] = dname;
                                seldata[count][1] = "" + defaultval;
                                count++;
                            }
                        }
                        rs.close();

                        double[] values = new double[5];
                        String[] names = new String[5];
                        for (vs = 0; vs < 5; vs++) {
                            names[vs] = seldata[vs][0];
                            values[vs] = Double.parseDouble(seldata[vs][1]);

                        }

                        SimpleBarChart n = new SimpleBarChart(values, names, "Names");
                        n.setBounds(0, 0, 400, 400);
                        n.setBackground(Color.pink);
                        bchart.add(n);
                        bchart.setBounds(200, 200, 810, 501);


                        m = 0;
                        rs = st.executeQuery("select count(*) from glucoreading where ID='" + id + "'");
                        if (rs.next()) {
                            cnt = rs.getInt(1);
                        }
                        rs.close();
                        rs = st.executeQuery("select rdate,value from glucoreading where ID='" + id + "'");
                        sellistdata = new String[cnt][2];
                        while (rs.next()) {
                            sellistdata[m][0] = "" + rs.getDate(1);
                            sellistdata[m][1] = rs.getString(2);
                            m++;
                        }
                        m = 0;
                        rs.close();


                        sellist = new JTable(sellistdata, tableNames);
                        bc.add(sellist, BorderLayout.CENTER);
                        bc.add(sellist.getTableHeader(), BorderLayout.NORTH);
                        sellist.setVisible(true);
                        sellist.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                        bchart.add(bc);


                        back.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e) {
                                bchart.setVisible(false);
                                docinfo.setVisible(true);
                            }
                        });

                    } catch (Exception e2) {
                        bchart.dispose();
                        docinfo.show();
                        JOptionPane.showMessageDialog(null, "Please select the date fields");
                    }
                }
            });

            count = 0;

            jCalendar = new JCalendar(docinfo, "Calendar", true);
            btnDate.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    Calendar cDate = jCalendar.getCalendar();
                    if (cDate == null) {
                        cDate = Calendar.getInstance();
                    }
                    jCalendar.setCalendar(cDate);
                    jCalendar.setVisible(true);

                    if (jCalendar.isOkPressed()) {
                        txtDate.setDate(jCalendar.getDate());
                        count = 1;
                    }

                    jCalendar.setVisible(false);
                }
            });

            btnClearDate.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    txtDate.setDate((java.util.Date) null);
                    jCalendar.setDate(null);
                    count = 0;
                }
            });


            count1 = 0;

            jCalendar1 = new JCalendar(docinfo, "Calendar", true);
            btnDate1.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    Calendar cDate = jCalendar.getCalendar();
                    if (cDate == null) {
                        cDate = Calendar.getInstance();
                    }
                    jCalendar.setCalendar(cDate);
                    jCalendar.setVisible(true);

                    if (jCalendar.isOkPressed()) {
                        txtDate2.setDate(jCalendar.getDate());
                        count = 1;
                    }

                    jCalendar.setVisible(false);
                }
            });

            btnClearDate1.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    txtDate2.setDate((java.util.Date) null);
                    jCalendar.setDate(null);
                    count = 0;
                }
            });


            // Panel 22222222222222





            rs = st.executeQuery("select count(*) from patient");
            if (rs.next()) {
                cnt = rs.getInt(1);
            }
            rs.close();
            rs = st.executeQuery("select * from patient");
            data = new String[cnt][4];
            while (rs.next()) {
                data[m][0] = rs.getString(1);
                data[m][1] = rs.getString(2);
                data[m][2] = rs.getString(3);
                data[m][3] = rs.getString(4);
                m++;
            }
            m = 0;
            rs.close();

            list = new JTable(data, columnNames);
            patlist.add(list, BorderLayout.CENTER);
            patlist.add(list.getTableHeader(), BorderLayout.NORTH);
            list.setVisible(true);
            list.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            patlist.add(list);



            tabdata = new JButton("Get Info");
            tabdata.setVisible(false);
            patlist.add(tabdata, BorderLayout.SOUTH);


            tabdata.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    try {
                        int sel = list.getSelectedRow();
                        String[] da = new String[4];
                        da[0] = data[sel][0];
                        rs = st.executeQuery("select * from patient where ID='" + data[sel][0] + "'");
                        if (rs.next()) {
                            pid.setText(rs.getString("ID"));
                            pname.setText(rs.getString("Name"));
                            pname1.setText(rs.getString("Phone"));
                        }
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "Please select a Patient Name and get his information");
                    }
                }
            });

            luser = new JLabel("UserName :");
            luser.setVisible(true);
            luser.setBounds(50, 50, 100, 40);
            auth.add(luser);

            lpass = new JLabel("Password :");
            lpass.setVisible(true);
            lpass.setBounds(50, 125, 100, 40);
            auth.add(lpass);

            user = new JTextField();
            user.setVisible(true);
            user.setBounds(150, 60, 150, 25);
            auth.add(user);

            pass = new JPasswordField();
            pass.setVisible(true);
            pass.setBounds(150, 135, 150, 25);
            auth.add(pass);

            log = new JButton("Log In");
            log.setVisible(true);
            log.setBounds(125, 225, 100, 30);
            auth.add(log);



            log.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    String u = user.getText();
                    String p = pass.getText();
                    String pa = null;

                    try {
                        if ((!u.equals("")) && (!p.equals(""))) {
                            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                            con = DriverManager.getConnection("jdbc:odbc:patient", "", "");
                            st = con.createStatement();
                            rs = st.executeQuery("select pass from admin where user='" + u + "'");
                            if (rs.next()) {
                                pa = rs.getString(1);
                            }
                            if (p.equals(pa)) {
                                auth.dispose();
                                docinfo.setVisible(true);
                                patbio.setVisible(true);
                                patlist.setVisible(true);
                                docinfo.setBounds(0, 0, i, (j / 2) + 70);
                                list.setVisible(true);
                                tabdata.setVisible(true);
                                //	pan4.setVisible(true);
                                //	pan3.setVisible(true);
                            } else {
                                JOptionPane.showMessageDialog(null, "Invalid Authentication");
                                auth.dispose();
                                System.exit(0);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Please Enter all the fields");
                            user.setText("");
                            pass.setText("");
                        }
                    } catch (Exception u1) {
                        JOptionPane.showMessageDialog(null, u1);
                    }

                }
            });

            auth.setBounds((i / 3), (j / 3), 350, 350);


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        getmail.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new Main();
            }
        });
    }

    public static void main(String ar[]) {
        doc d = new doc();

    }
}
