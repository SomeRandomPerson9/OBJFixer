package com.walker.objfixer;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ObjFixerWindow extends JFrame
{
	private JLabel OldFileLabel;
	private JLabel NewFileLabel;
	private JTextField OldFileField;
	private JTextField NewFileField;
	private JButton OpenOldButton;
	private JButton OpenNewButton;
	private JButton FixButton;
	private JPanel contentPane;
	private JFileChooser fcOpen;
	private JFileChooser fcSave;

	private File openFile;
	private File saveFile;


	public ObjFixerWindow()
	{
		super();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		initializeComponent();
		//
		// TODO: Add any constructor code after initializeComponent call
		//

		this.setVisible(true);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always regenerated
	 * by the Windows Form Designer. Otherwise, retrieving design might not work properly.
	 * Tip: If you must revise this method, please backup this GUI file for JFrameBuilder
	 * to retrieve your design properly in future, before revising this method.
	 */
	private void initializeComponent()
	{
		this.setTitle("Obj Fixer");
		this.setSize(new Dimension(485, 200));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(new Point(screenSize.width/2 - this.getWidth()/2, screenSize.height/2 - this.getHeight()/2));

		OldFileLabel = new JLabel();
		NewFileLabel = new JLabel();
		OldFileField = new JTextField();
		NewFileField = new JTextField();
		OpenOldButton = new JButton();
		OpenNewButton = new JButton();
		FixButton = new JButton();
		fcOpen = new JFileChooser();
		fcSave = new JFileChooser();
		contentPane = (JPanel)this.getContentPane();

		//
		// OldFileLabel
		//
		OldFileLabel.setText("Old File");
		//
		// NewFileLabel
		//
		NewFileLabel.setText("New File");
		//
		// OldFileField
		//
		OldFileField.setText("");
		OldFileField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jTextField1_actionPerformed(e);
			}

		});
		//
		// NewFileField
		//
		NewFileField.setText("");
		NewFileField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jTextField2_actionPerformed(e);
			}

		});
		//
		// OpenOldButton
		//
		OpenOldButton.setText("Open");
		OpenOldButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				OpenOldButton_actionPerformed(e);
			}

		});
		//
		// OpenNewButton
		//
		OpenNewButton.setText("Open");
		OpenNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				OpenNewButton_actionPerformed(e);
			}

		});
		//
		// FixButton
		//
		FixButton.setText("Fix");
		FixButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				FixButton_actionPerformed(e);
			}

		});

		fcOpen.setFileFilter(new FileNameExtensionFilter("3D Models", "obj"));

		fcSave.setFileFilter(new FileNameExtensionFilter("3D Models", "obj"));
		fcSave.setDialogType(JFileChooser.SAVE_DIALOG);
		//
		// contentPane
		//
		contentPane.setLayout(null);
		addComponent(contentPane, OldFileLabel, 27,14,60,18);
		addComponent(contentPane, NewFileLabel, 27,66,60,18);
		addComponent(contentPane, OldFileField, 26,30,321,26);
		addComponent(contentPane, NewFileField, 26,84,321,26);
		addComponent(contentPane, OpenOldButton, 361,30,83,28);
		addComponent(contentPane, OpenNewButton, 361,84,83,28);
		addComponent(contentPane, FixButton, this.getWidth()/2 - 83/2,122,83,28);
		//
		// ObjFixerWindow
		//

	}

	/** Add Component Without a Layout Manager (Absolute Positioning) */
	private void addComponent(Container container,Component c,int x,int y,int width,int height)
	{
		c.setBounds(x,y,width,height);
		container.add(c);
	}

	//
	// TODO: Add any appropriate code in the following Event Handling Methods
	//
	private void jTextField1_actionPerformed(ActionEvent e)
	{
		System.out.println("\njTextField1_actionPerformed(ActionEvent e) called.");
		// TODO: Add any handling code here

	}

	private void jTextField2_actionPerformed(ActionEvent e)
	{
		System.out.println("\njTextField2_actionPerformed(ActionEvent e) called.");
		// TODO: Add any handling code here

	}

	private void OpenOldButton_actionPerformed(ActionEvent e)
	{
		System.out.println("\nOpenOldButton_actionPerformed(ActionEvent e) called.");
		fcOpen.showDialog(contentPane, "Open");
		openFile = fcOpen.getSelectedFile();
		OldFileField.setText(openFile.getAbsolutePath());
		// TODO: Add any handling code here

	}

	private void OpenNewButton_actionPerformed(ActionEvent e)
	{
		System.out.println("\nOpenNewButton_actionPerformed(ActionEvent e) called.");
		fcSave.showDialog(contentPane, "Save");
		saveFile = fcSave.getSelectedFile();
		NewFileField.setText(saveFile.getAbsolutePath());
		// TODO: Add any handling code here

	}

	private void FixButton_actionPerformed(ActionEvent e)
	{
		System.out.println("\nFixButton_actionPerformed(ActionEvent e) called.");
		try{
			ArrayList<String> writeLines = new ArrayList<String>();

			String line = null;

			FileReader fileReader = new FileReader(openFile);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null){
				String[] segments = null;
				segments = line.split(" ");

				if(segments[0].equalsIgnoreCase("f")){
					if(segments.length > 4){
						writeLines.add("f " + segments[1] + " " + segments[2] + " " + segments[3]);
						writeLines.add("f " + segments[2] + " " + segments[3] + " " + segments[4]);
					}
					else{
						writeLines.add(line);
					}
				}
				else{
					writeLines.add(line);
				}
			}

			bufferedReader.close();

			FileWriter fileWriter = new FileWriter(saveFile);

			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for(String writeLine : writeLines){
				bufferedWriter.write(writeLine);
				bufferedWriter.newLine();
			}

			bufferedWriter.close();
		}
		catch(Exception e1){
			e1.printStackTrace();
		}

	}

	//
	// TODO: Add any method code to meet your needs in the following area
	//

}
