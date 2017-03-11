package package1;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

public class DialogCheckInRV extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	/** Text field that accepts the user input for a name */
	private JTextField nameTxt;

	/** Text field that accepts the user input for a 
	 * date to occupy the site */
	private JTextField occupiedOnTxt;

	/** Text field that accepts the user input for 
	 * the time staying on site */
	private JTextField stayingTxt;

	/** Text field that accepts the user input for the site number */
	private JTextField siteNumberTxt;

	/** Text field that accepts the user input for the power required */
	private JTextField powerTxt;

	/** Button in the dialog box that finalizes the input */
	private JButton okButton;

	/** Button in the dialog box that cancels the input */
	private JButton cancelButton;

	/** ??????????????????????????????????????????????? */
	private boolean closeStatus;

	/** ??????????????????????????????????????????????? */
	private RV unit;  	

	private SiteModel model;

	public DialogCheckInRV(JFrame paOccupy, RV d) {	
		super(paOccupy, true);

		model = new SiteModel();
		setTitle("Add an RV");
		closeStatus = false;
		setSize(400,200);

		unit = d; 

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(6,2));

		textPanel.add(new JLabel("Name:"));
		nameTxt = new JTextField("John Doe", 30);
		textPanel.add(nameTxt);

		textPanel.add(new JLabel("Days Staying:"));
		stayingTxt = new JTextField("3", 30);
		textPanel.add(stayingTxt);

		textPanel.add(new JLabel("Date Checking In:"));
		occupiedOnTxt = new JTextField(30);
		occupiedOnTxt.setText("10/18/2013");
		textPanel.add(occupiedOnTxt); 	
		getContentPane().add(textPanel, BorderLayout.CENTER);

		textPanel.add(new JLabel("Site Number:"));
		siteNumberTxt = new JTextField("1", 30);
		textPanel.add(siteNumberTxt);

		textPanel.add(new JLabel("Power:"));
		powerTxt = new JTextField("30", 30);
		textPanel.add(powerTxt);

		getContentPane().add(textPanel, BorderLayout.CENTER);

		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		okButton.addActionListener((ActionListener) this);
		cancelButton.addActionListener((ActionListener) this);

		setSize(300,300);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		JButton comp = (JButton) e.getSource();

		if(comp == okButton){
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date d;
			try{
				d = sdf.parse(occupiedOnTxt.getText());

				GregorianCalendar opened = new GregorianCalendar();
				opened.setTime(d);

				int powerW = Integer.parseInt(powerTxt.getText());

				int stayingTxtW = Integer.parseInt(stayingTxt.getText());

				int siteTxtW = Integer.parseInt(siteNumberTxt.getText());

				unit.setCheckIn(opened);
				unit.setDaysStaying(stayingTxtW);
				unit.setNameReserving(nameTxt.getText());
				unit.setSiteNumber(siteTxtW);
				unit.setPower(powerW);
				unit.setDateCheckedOut(null);

				if(model.isOccupied(siteTxtW) == 2){
					closeStatus = false;
					JOptionPane.showMessageDialog(null, "All sites are full.");
				}
				else if(model.isOccupied(siteTxtW) == 1){
					closeStatus = false;
					JOptionPane.showMessageDialog(null, "Site is already taken.");
				}
				else{
					closeStatus = true;
					JOptionPane.showMessageDialog(null, "You will owe " + unit.getCost(unit) + " dolans.");

				}
			}
			catch(ParseException e1){
				System.out.println("RV PARSE ERROR");
				e1.printStackTrace();	
			}
		}
		dispose();
	}

	public boolean getCloseStatus(){
		return closeStatus;
	}

}
