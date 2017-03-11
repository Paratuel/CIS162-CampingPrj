package package1;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;


public class DialogCampFullStatus extends JDialog {
	private static final long serialVersionUID = 1L;

	private String statusDate;

	private JTable statusTable;

	private StatusModel statusTableModel;

	private JScrollPane scrollPane;

	private Site s;

	private Status status;

	public DialogCampFullStatus(JFrame paOccupy,
			SiteModel guiTableModel){
		super(paOccupy, true);


		statusTableModel = new StatusModel();
		statusTable = new JTable(statusTableModel);
		scrollPane = new JScrollPane(statusTable);
		add(scrollPane);

		statusDate = JOptionPane.showInputDialog("Input a date:",
				"10/20/2013");

		for(int i = 0; i < guiTableModel.getRowCount(); i++){
			s = guiTableModel.getSite(i);
			status = new Status();
			status.setCIn(s.getCheckIn());
			status.setDays(s.getDaysStaying());
			status.setName(s.getNameReserving());
			status.setSiteN(s.getSiteNumber());
			status.setDaysRemaining(s.getDaysRemaining(s.getCheckIn(),
					s.returnStringToCalendar(statusDate),
					s.getDaysStaying()));
			statusTableModel.addStatus(status);
		}

		setVisible(true);
		setSize(1000,400);
	}
}
