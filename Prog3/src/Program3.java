import java.io.*;
import java.util.StringTokenizer;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;

public class Program3
    extends Frame
    implements WindowListener, ActionListener{


    //lists
    private List directoryList = new List(13);
    //labels
    private Label srcLabel = new Label("Source:");
    private Label fileLabel = new Label("File Name:");
    private Label srcFileLabel = new Label();
    private Label tgtDirLabel = new Label();
    private Label sysMsgLabel = new Label();
    //buttons
    private Button okBtn = new Button("Ok");
    private Button tgtBtn = new Button("Target");
    //text fields
    private TextField tgtFileName = new TextField("Text field........",60);

    //for different modes in "display" switch case
    private int mode = 0;   //0 - source, 1 - target, 2 - copy

    //to keep our current directory
    private File curDir;

    //strings to store our files when selected
    private String sourceFile;
    private String targetFile;
	
	//General Variables
	String Filename;

	public Program3 (File f) {

	    //set the current directory to the one passed by main function
	    curDir = f;

	    //grid bag layout settings
        double colWeights[] = {1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
        double rowWeights[] = {1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
        int rowHeight[] = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        int colWidth[] = {1,1,1,1,1,1,1,1};

        //create and set layout manager settings
        GridBagLayout lm = new GridBagLayout();
        lm.columnWeights = colWeights;
        lm.rowWeights = rowWeights;
        lm.columnWidths = colWidth;
        lm.rowHeights = rowHeight;
        this.setLayout(lm);

        //create the grid bag constraints object
        GridBagConstraints c = new GridBagConstraints();

        //set anchoring
        c.anchor = GridBagConstraints.EAST;

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

        //add file name label

        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 15;
        lm.setConstraints(fileLabel, c);
        this.add(fileLabel);

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
        okBtn.addActionListener(this);
        okBtn.setEnabled(false);        //enabled for mode 2

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
        tgtBtn.addActionListener(this);
        tgtBtn.setEnabled(false);       //enabled in mode 1

        //add target filename field
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 6;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 15;
        lm.setConstraints(tgtFileName, c);
        Color clr = Color.white;
        tgtFileName.setBackground(clr);
        tgtFileName.addActionListener(this);
        this.add(tgtFileName);
        tgtFileName.setEnabled(false);  //enabled in mode 1
		
		this.setLayout(lm);
  		
        this.setResizable(true);
        this.setVisible(true);
        this.addWindowListener(this);
		
		
        this.pack();
		
		try{
		display(null);
		} catch( IOException e){
			System.out.println(e);
		}
		

    } //end of constructor


    public static void main (String[] args) {
        //check if there was a command line argument
        File f;
        if (args.length > 0) {
            //if yes, use the first arg as the directory
            f = new File(args[0]);
        }
        else { //otherwise use the current directory
            f = new File("");
            String s = f.getAbsolutePath();
            f = new File(s);
        }

        Program3 window = new Program3(f);
        window.setBounds(20,20,600,350);
    }

    private void display(String name) throws FileNotFoundException, IOException {  //name is the item in the directoryList that is "picked"
        switch (mode) {
            case 0:     //source mode
                if (name != null) {
                    if (name.equals("..")) {    //go to parent directory if it exists
                        curDir = new File(curDir.getParent());
                        System.out.println("curDir.getParent() = " + curDir.getParent());
                    } else {      //otherwise it is a file or child directory
                        File f = new File(curDir, name);
                        if (f.isDirectory()) {  //check if it is a directory
                            curDir = new File(curDir, name);    //set the current directory to the directory clicked
                        } else if (f.isFile()) {  //check if it is a file
                            sourceFile = f.getAbsolutePath();       //store the path
                            sysMsgLabel.setText("Source file selected: " + sourceFile);
                            srcFileLabel.setText(curDir.getAbsolutePath() + "\\" + name);   //set the srcFileLabel to the filename selected
                            tgtBtn.setEnabled(true);        //turn target button on, because valid file was selected
                        }
                    }
                }

                //update the fileDirectoryList
                String[] fileNames;
                fileNames = curDir.list();


                //set the title of the window
                if (fileNames == null) {
                    fileNames = new String[]{};
                } else {
                    this.setTitle(curDir.getAbsolutePath());
                }

                //clear the list
                directoryList.removeAll();

                //check if a parent directory exists
                if (curDir.getParent() != null) {
                    directoryList.add("..");    //add the change to parent directory indicator
                }

                //populate the list with files/directories
                for (int i = 0; i < fileNames.length; i++) {
                    directoryList.add(fileNames[i]);
                }
                break;

            case 1: //target mode
                /*click on items in list
                * set the target field if valid file dir/file is selected
                * can input in to file name field to create a "new" file
                * ok button or enter enters mode 2*/
                System.out.println("Target mode has been entered.");
				
				if (name != null) {
                    if (name.equals("..")) {    //go to parent directory if it exists
                        curDir = new File(curDir.getParent());
                        System.out.println("curDir.getParent() = " + curDir.getParent());
                    } else {      //otherwise it is a file or child directory
                        File f = new File(curDir, name);
                        if (f.isDirectory()) {  //check if it is a directory
                            curDir = new File(curDir, name);    //set the current directory to the directory clicked
                        } else if (f.isFile()) {  //check if it is a file
                            targetFile = f.getAbsolutePath();       //store the path
                            sysMsgLabel.setText("Target file selected: " + targetFile);
                            tgtDirLabel.setText(curDir.getAbsolutePath());   //set the tgtDirLabel to the filename selected  **NEEDS TO CHANGE WHEN ENTERING PARENT
							tgtFileName.setEnabled(true);  //***NEEDS TO DISABLE WHEN FILE NAME IS DELETED
							tgtFileName.setText(name);
                            okBtn.setEnabled(true);        //turn target button on, because valid file was selected
                        }
                    }
                }

                //update the fileDirectoryList
               // String[] fileNames;
                fileNames = curDir.list();


                //set the title of the window
                if (fileNames == null) {
                    fileNames = new String[]{};
                } else {
                    this.setTitle(curDir.getAbsolutePath());
                }

                //clear the list
                directoryList.removeAll();

                //check if a parent directory exists
                if (curDir.getParent() != null) {
                    directoryList.add("..");    //add the change to parent directory indicator
                }

                //populate the list with files/directories
                for (int i = 0; i < fileNames.length; i++) {
                    directoryList.add(fileNames[i]);
                }
                break;
            case 2: //copy mode
                /*needs valid input/output files
                * may overwrite existing file
                * if dir is changed, creates file with
                * previously selected name in the new dir*/
				
				BufferedReader inFile = new BufferedReader(new FileReader(sourceFile));  //bufferedreader of the file that will be backed up
				String backupFilename = targetFile;  //adds -backup.txt to the old file
				PrintWriter outFile = new PrintWriter(new FileWriter(backupFilename));		//open the "backup" to write to.
				String inBuffer = inFile.readLine();  //Read first line
				while(inBuffer != null){  //backs up file by writing it to other file
					outFile.println(inBuffer); //print current line to file
					inBuffer = inFile.readLine(); //get next line
				}
				inFile.close();
				outFile.close();

				
                break;


        }



    }

    private void stop () {
        this.removeWindowListener(this);
        this.dispose();
    }

    //overload windowListener Functions

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

    //overload actionListener functions
	public void actionPerformed(ActionEvent e) {
	    Object s = e.getSource();
		if(s == directoryList)
		{
			String item = directoryList.getSelectedItem();
			//might have to remove the +
			System.out.println(item + " is selected");
			//display(item);
			try{
				display(item);
			} catch( IOException er){
				System.out.println(er);
			}
		}
		else if (s == tgtBtn) {
            System.out.println("Target Mode entered!");
            mode = 1;
        }
		else if (s == okBtn) 
		{
			//filename = curDir.getAbsolutePath() + "\\" + name
			mode = 2;
		}
	}
}