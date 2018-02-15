import java.io.*;
import java.util.StringTokenizer;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;



public class Program3 
	extends Frame 
	implements WindowListener, ActionListener {
    //lists
	List directoryList = new List(13);
	//labels
	Label srcLabel = new Label("Source");
	Label srcFileLabel = new Label();
	Label tgtDirLabel = new Label();
	Label sysMsgLabel = new Label();
	//buttons
	Button okBtn = new Button("Ok");
	Button tgtBtn = new Button("Target");
	//text fields
	TextField tgtFileName = new TextField("Text field........",60);
	
    public Program3 () {
        double colWeights[] = {1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
        double rowWeights[] = {1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
        int rowHeight[] = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        int colWidth[] = {1,1,1,1,1,1,1,1};
        GridBagConstraints c = new GridBagConstraints();
        GridBagLayout lm = new GridBagLayout();
        lm.columnWeights = colWeights;
        lm.rowWeights = rowWeights;
        lm.columnWidths = colWidth;
        lm.rowHeights = rowHeight;
        c.anchor = GridBagConstraints.EAST;
        this.setLayout(lm);
		
		//add directory listing
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 8;
        c.gridheight = 13;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
		lm.setConstraints(directoryList, c);
		directoryList.addActionListener(this);
        this.add(directoryList);
		
		//add src label
		c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 13;
		lm.setConstraints(srcLabel, c);
        this.add(srcLabel);
		
		//add source file label
		c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 7;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 13;
		lm.setConstraints(srcFileLabel, c);
        this.add(srcFileLabel);
		srcFileLabel.setText("srcFileLabel");
		
		//add target directory label
		c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 7;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 14;
		lm.setConstraints(tgtDirLabel, c);
        this.add(tgtDirLabel);
		tgtDirLabel.setText("tgtDirLabel");
		
		//add system message label
		c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 6;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 16;
		lm.setConstraints(sysMsgLabel, c);
        this.add(sysMsgLabel);
		sysMsgLabel.setText("sysMsgLabel");
		
		//add ok button
		c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 7;
        c.gridy = 15;
		lm.setConstraints(okBtn, c);
        this.add(okBtn);
		
		//add target button
		c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 14;
		lm.setConstraints(tgtBtn, c);
        this.add(tgtBtn);
		
		//add target filename field
		c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 6;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 12;
		lm.setConstraints(tgtFileName, c);
		Color clr = Color.black;
		tgtFileName.setBackground(clr);
        this.add(tgtFileName);
		
        this.setResizable(true);
        this.setVisible(true);
        this.addWindowListener(this);
		this.setTitle("My window");
        this.pack();
		
		display();

    } //end of constructor

    public static void main (String[] args) {
        Program3 window = new Program3();
        window.setBounds(20,20,600,600);
		File f = new File("");
		String s = f.getAbsolutePath();
		f = new File(s);
		System.out.println(f.getParent());
		
		
    }
	
	public void display() {
		
		this.setTitle("Hello");
	}

    public void stop () {
        this.removeWindowListener(this);
        this.dispose();
    }

    public void windowClosing (WindowEvent e) {
        System.out.println("Program exited");
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
        System.out.println("Window minimized");
    }

    public void windowDeiconified (WindowEvent e) {
        System.out.println("Window maximized");
    }
	
	public void actionPerformed (ActionEvent e) {
		/*Object s = e.getSource();
		if (s == list) {
			System.out.println("You clicked in the list!!!");
		}
		if (s == button) {
			System.out.println("You clicked the button!!!");
		}
		if (s == message) {
			System.out.println("You clicked in the list!!!");
		}
	*/}

}

