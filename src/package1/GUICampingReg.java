package package1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;

public class GUICampingReg extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	/** ?????????????????????????????????????????????????????? */
	private SiteModel guiTableModel;

	/** The displayed table that holds reservation information */
	private JTable guiTable;

	/** Scroll bar at the right side of the window */
	private JScrollPane scrollPane;

	/** Menu bar at the top of the window */
	private JMenuBar menus;

	/** The file drop down menu on the menu bar */
	private JMenu fileMenu;

	/** Open Serial menu item in the fileMenu drop down menu */
	private JMenuItem openSerial;

	/** Save Serial menu item in the fileMenu drop down menu */
	private JMenuItem saveSerial;

	/** Open Text menu item in the fileMenu drop down Menu */
	private JMenuItem openText;

	/** Save Text menu item in the fileMenu drop down menu */
	private JMenuItem saveText;

	/** Exit menu item in the fileMenu drop down menu */
	private JMenuItem exit;

	/** Checking In drop down menu on the menu bar */
	private JMenu checkingInMenu;

	/** Checking Out menu button on the menu bar */
	private JMenu checkingOut;

	private JMenuItem checkingOutOn;

	/** CheckingInRV menu item in the checkingIn drop down menu */
	private JMenuItem checkingInRV;

	/** CheckingInTent menu item in the checkingIn drop down menu */
	private JMenuItem checkingInTent;

	private Site unit;
	
	private JMenuItem status;

	public GUICampingReg(){

		fileMenu = new JMenu("File:");
		checkingInMenu = new JMenu("Checking In:");
		exit = new JMenuItem("Exit");
		openSerial = new JMenuItem("Open Serial");
		saveSerial = new JMenuItem("Save Serial");
		openText = new JMenuItem("Open Text");
		saveText = new JMenuItem("Save Text");
		checkingInRV = new JMenuItem("Check In RV");
		checkingInTent = new JMenuItem("Check In Tent");
		checkingOut = new JMenu("Check Out:");
		checkingOutOn = new JMenuItem("Check Out On ...");
		status = new JMenuItem("Status");



		createGUI();

	}

	private void createGUI(){
		fileMenu.add(openSerial);
		fileMenu.add(saveSerial);
		fileMenu.add(openText);
		fileMenu.add(saveText);
		fileMenu.add(status);
		fileMenu.add(exit);

		checkingInMenu.add(checkingInRV);
		checkingInMenu.add(checkingInTent);
		checkingOut.add(checkingOutOn);

		status.addActionListener(this);
		exit.addActionListener(this);
		openSerial.addActionListener(this);
		saveSerial.addActionListener(this);
		openText.addActionListener(this);
		saveText.addActionListener(this);
		checkingInRV.addActionListener(this);
		checkingInTent.addActionListener(this);
		checkingOutOn.addActionListener(this);



		menus = new JMenuBar();

		menus.add(fileMenu);
		menus.add(checkingInMenu);
		menus.add(checkingOut);

		setJMenuBar(menus);

		guiTableModel = new SiteModel();
		guiTable = new JTable(guiTableModel);
		scrollPane = new JScrollPane(guiTable);
		add(scrollPane);

		setVisible(true);
		setSize(600,400);
	}

	public static void main(String[] args){
		new GUICampingReg();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (openSerial == e.getSource()) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.
					FILES_AND_DIRECTORIES); 

			if(chooser.showOpenDialog(null) == JFileChooser.
					APPROVE_OPTION){
				String filename = chooser.getSelectedFile().getAbsolutePath();
				guiTableModel.loadDatabase(filename);
			}
		}

		if (openText == e.getSource()) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(
					JFileChooser.FILES_AND_DIRECTORIES); 

			if(chooser.showOpenDialog(null) == 
					JFileChooser.APPROVE_OPTION){
				String filename = chooser.
						getSelectedFile().getAbsolutePath();
				guiTableModel.loadFromText(filename);
			}
		}

		if (exit == e.getSource()) {
			System.exit(0);
		}

		if (saveSerial == e.getSource()) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(
					JFileChooser.FILES_AND_DIRECTORIES);  

			if(chooser.showSaveDialog(null) == 
					JFileChooser.APPROVE_OPTION){
				String filename = chooser.
						getSelectedFile().getAbsolutePath();
				guiTableModel.saveDatabase(filename);
			}
		}

		if (saveText == e.getSource()) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser
					.FILES_AND_DIRECTORIES);  

			if(chooser.showSaveDialog(null) == JFileChooser
					.APPROVE_OPTION){
				String filename = chooser.getSelectedFile().getAbsolutePath();
				guiTableModel.saveAsText(filename);
			}
		}

		if (checkingInRV == e.getSource()) {
			RV s = new RV();
			DialogCheckInRV x = new DialogCheckInRV(this, s);
			if (x.getCloseStatus() == true) {
				guiTableModel.addSite(s);
			}
			
		}

		if (checkingInTent == e.getSource()) {
			Tent s = new Tent();
			DialogCheckInTent x = new DialogCheckInTent(this, s);
			if(x.getCloseStatus() == true){
				guiTableModel.addSite(s);
			}
		}

		if (checkingOutOn == e.getSource()) {
			int index = guiTable.getSelectedRow();
			//System.out.println (index);
			if (index == -1)
				JOptionPane.showMessageDialog(this,"No. Not even close, ya Dingus.");
			else{
				String t = JOptionPane.showInputDialog("Date checking out on:","10/20/2013");
				unit = guiTableModel.getSite(index);
				unit.stringToCalendar(t);
				try{
					double cost = unit.getCost(unit);
					JOptionPane.showMessageDialog(null, "You must pay " + cost + " dollars.");
					guiTableModel.removeSite(index);
				}
				catch(Exception c){
					JOptionPane.showMessageDialog(null, "You can't do that you Dingus.");
				}
			}
		}
		
		if(status == e.getSource()){
			DialogCampFullStatus z = new DialogCampFullStatus(this, guiTableModel);
		}

	}
}
