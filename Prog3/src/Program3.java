import java.io.*;
import java.util.StringTokenizer;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;


public class Program3 extends Frame implements WindowListener{
    Label message = new Label("It worked!");
    public Program3 () {
        double colWeights[] = {1.0,1.0,1.0,1.0};
        double rowWeights[] = {1.0,1.0,1.0,1.0};
        int rowHeight[] = {1,1,1,1};
        int colWidth[] = {1,1,1,1};
        GridBagConstraints c = new GridBagConstraints();
        GridBagLayout lm = new GridBagLayout();
        lm.columnWeights = colWeights;
        lm.rowWeights = rowWeights;
        lm.columnWidths = colWidth;
        lm.rowHeights = rowHeight;
        c.anchor = GridBagConstraints.EAST;
        this.setBounds(20,20,600,600);
        this.setSize(600,500);
        this.setLayout(lm);

        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 1;

        lm.setConstraints(message, c);
        this.add(message);
        this.setResizable(true);
        this.setVisible(true);
        this.addWindowListener(this);
        this.pack();

    } //end of constructor

    public static void main (String[] args) {
        new Program3();
    }

    public void stop () {
        this.removeWindowListener(this);
        this.dispose();
    }

    public void windowClosing (WindowEvent e) {
        stop();
    }

    public void windowClosed (WindowEvent e) {

    }

    public void windowOpened (WindowEvent e) {

    }

    public void windowActivated (WindowEvent e) {

    }

    public void windowDeactivated (WindowEvent e) {

    }

    public void windowIconified (WindowEvent e) {

    }

    public void windowDeiconified (WindowEvent e) {

    }

}

