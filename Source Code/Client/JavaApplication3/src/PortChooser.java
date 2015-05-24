import gnu.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class PortChooser extends JDialog implements ItemListener {

    protected HashMap map = new HashMap();
    protected String selectedPortName;
    protected CommPortIdentifier selectedPortIdentifier;
    protected JComboBox serialPortsChoice;
    protected JComboBox parallelPortsChoice;
    protected JComboBox other;
    protected SerialPort ttya;
    protected JLabel choice;
    protected final int PAD = 5;

    public void itemStateChanged(ItemEvent e) {
        selectedPortName = (String) ((JComboBox) e.getSource()).getSelectedItem();
        selectedPortIdentifier = (CommPortIdentifier) map.get(selectedPortName);
        choice.setText(selectedPortName);
    }

    public String getSelectedName() {
        return selectedPortName;
    }

    public CommPortIdentifier getSelectedIdentifier() {
        return selectedPortIdentifier;
    }

    public static void main(String[] ap) {
        PortChooser c = new PortChooser(null);
        c.setVisible(true);
        System.out.println("You chose " + c.getSelectedName()
                + " (known by " + c.getSelectedIdentifier() + ").");
        System.exit(0);
    }

    public PortChooser(JFrame parent) {
        super(parent, "Port Chooser", true);
        makeGUI();
        populate();
        finishGUI();

    }

    protected void makeGUI() {
        Container cp = getContentPane();
        JPanel centerPanel = new JPanel();
        cp.add(BorderLayout.CENTER, centerPanel);
        centerPanel.setLayout(new GridLayout(0, 2, PAD, PAD));
        centerPanel.add(new JLabel("Serial Ports", JLabel.RIGHT));
        serialPortsChoice = new JComboBox();
        centerPanel.add(serialPortsChoice);
        serialPortsChoice.setEnabled(false);
        centerPanel.add(new JLabel("Your choice:", JLabel.RIGHT));
        centerPanel.add(choice = new JLabel());
        JButton okButton;
        cp.add(BorderLayout.SOUTH, okButton = new JButton("OK"));
        okButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                PortChooser.this.dispose();
            }
        });
    }

    protected void populate() {
        Enumeration pList = CommPortIdentifier.getPortIdentifiers();
        while (pList.hasMoreElements()) {
            CommPortIdentifier cpi = (CommPortIdentifier) pList.nextElement();
            map.put(cpi.getName(), cpi);
            if (cpi.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                serialPortsChoice.setEnabled(true);
                serialPortsChoice.addItem(cpi.getName());
            }
        }
        serialPortsChoice.setSelectedIndex(-1);
    }

    protected void finishGUI() {
        serialPortsChoice.addItemListener(this);
        pack();
    }
}
