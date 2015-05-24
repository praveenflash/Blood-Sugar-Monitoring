
import java.awt.*;
import java.io.*;
import gnu.io.*;

public class CommPortOpen {

    public static final int TIMEOUTSECONDS = 30;
    public static final int BAUD = 2400;
    protected Frame parent;
    protected DataInputStream is;
    protected PrintStream os;
    protected String response;
    protected boolean debug = true;
    CommPortIdentifier thePortID;
    CommPort thePort;

    public static void main(String[] argv)
            throws IOException, NoSuchPortException, PortInUseException,
            UnsupportedCommOperationException {
        new CommPortOpen(null).converse();
        System.exit(0);
    }

    public CommPortOpen(Frame f)
            throws IOException, NoSuchPortException, PortInUseException,
            UnsupportedCommOperationException {

        PortChooser chooser = new PortChooser(null);
        String portName = null;
        do {
            chooser.setVisible(true);
            portName = chooser.getSelectedName();
            if (portName == null) {
                System.out.println("No port selected. Try again.\n");
            }
        } while (portName == null);
        thePortID = chooser.getSelectedIdentifier();
        System.out.println("Trying to open " + thePortID.getName() + "...");
        switch (thePortID.getPortType()) {
            case CommPortIdentifier.PORT_SERIAL:
                thePort = thePortID.open("DarwinSys DataComm",
                        TIMEOUTSECONDS * 1000);
                SerialPort myPort = (SerialPort) thePort;
                myPort.setSerialPortParams(BAUD, SerialPort.DATABITS_8,
                        SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                System.out.println("Port Opened");
                break;
            case CommPortIdentifier.PORT_PARALLEL:
                break;
            default:
                throw new IllegalStateException("Unknown port type " + thePortID);
        }
        try {
            is = new DataInputStream(thePort.getInputStream());
            byte[] b = new byte[2];
            System.out.println("Reading Data....");
            Thread.sleep(5000);
            while (true) {
                is.read(b);
                int val = (b[0] * 0x1000) | b[1];
                val=val*-1;
               // if (val!=0)
                //{
                    javax.swing.JOptionPane.showMessageDialog(null,"Your Blood Sugar Value is 1000");
                    new Mailer(""+1000);
                //}
                System.out.println(val);
            }
        } catch (Exception e) {
            System.err.println("Can't open input stream: write-only" + e);
            is = null;
        }
        os = new PrintStream(thePort.getOutputStream(), true);
    }

    protected void converse() throws IOException {
        System.out.println("Ready to read and write port.");
        if (is != null) {
            is.close();
        }
        os.close();
    }
}
