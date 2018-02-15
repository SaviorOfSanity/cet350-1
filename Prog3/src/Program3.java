import java.io.*;
import java.util.StringTokenizer;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;


public class Program3 extends Frame implements WindowListener, ActionListener{
    Label message = new Label("It worked!");
	List list = new List(13);
	
	Label scrFileLabel = new Label();
	int mode = 0;
	File curdir;
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
        this.setLayout(lm);

        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 3;
        c.gridy = 3;

        lm.setConstraints(message, c);
        this.add(message);
		
		c.weightx = 1.0;
		c.weighty = 1.0;
        c.gridwidth = 7;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 3;
		lm.setConstraints(scrFileLabel, c);
		this.add(scrFileLabel);
		
		this.setLayout(lm);
		this.add(list);
  		
        this.setResizable(true);
        this.setVisible(true);
        this.addWindowListener(this);
		this.setTitle("My window");
		list.addActionListener(this);
		
		
        this.pack();
		
		File g = new File("");
		String s = g.getAbsolutePath();
		curdir = new File(s); 
		display(null);

    } //end of constructor
	
	
    public static void main (String[] args) {
        Program3 window = new Program3();
        window.setBounds(20,20,600,600);
		File curdir = new File("."); 
		
		//System.out.println("Parent= " + curdir.getParent());

		
		//window.add(list);
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
	
	public void display(String name){  //name is the item click in the list
	
		switch(mode)
		{
		
			case 0:  //Source Mode
			/*
			Click in List
			Set Source
			Adds +
			Target and Copy/OK buttons are disabled
			File name text field disabled
			When Target button pressed go to mode 1			
			*/
			
			//File g = new File("");
			//String s = g.getAbsolutePath();
			//curdir = new File(s); 
		
			//System.out.println("Parent= " + curdir.getParent());
			//window.setTitle(curdir.getAbsolutePath());
			//List list = new List(13);
			if(name == null)
			{
				
				
			}
			else 
			{
				if( name.equals(".."))
				{
					curdir = new File(curdir.getParent());
					System.out.println("Parent= " + curdir.getParent());
				}
				else
				{
					File f = new File(curdir, name);
					if(f.isDirectory())
					{
						curdir = new File(curdir, name);
						System.out.println("Directory: " + curdir.getAbsolutePath());
						
					}
					else
					{
						//check mode
						//set appropirate file name if mode == 0 set srcfilelabel to curdir
						scrFileLabel.setText(curdir.getAbsolutePath() + "\\" + name);
					}
				}
			}
			
			
			String[] filenames;
			filenames = curdir.list();
			
			if(filenames == null)
			{
				filenames = new String[]{};
				System.out.println("Empty Array");
			}
			else
			{
				this.setTitle(curdir.getAbsolutePath());
			}
			list.removeAll();
			if(curdir.getParent() != null)
			{
				list.add("..");
				
			
			}
			for(int i=0; i< filenames.length; i++)
			{
				list.add(filenames[i]);
			
			
			}
			break;
			
			case 1:  //Target/Copy Mode
			/*
			Click on list
			Set Target
			Adds +
			Target button pressed add path to target label
			Can input into file name field
			Ok button or Enter moves to mode 2
			
			
			*/
			break;
			
			
			case 2: //Copy mode
			/*
			Needs valid inout and output
			copies files
			may overwrite current file
			if same filename is moved to different directory it creates a new file with that name in the new directory
			*/
			break;
		
			
		}
	}
		public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if(s == list)
		{
			String item = list.getSelectedItem();
			//might have to remove the +
			System.out.println(item + " is selected");
			display(item);
			
		}
	}
}
	

