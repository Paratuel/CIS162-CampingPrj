package package1;

import java.text.DateFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class StatusModel extends AbstractTableModel {

	private ArrayList<Status> listStatus;

	private String[] columnNames = { "Name:", "Checked In:", "Site #:",
			"Days Staying:", "Days Remaining:" };

	public StatusModel() {
		super();
		listStatus = new ArrayList<Status>();
	}

	public void addStatus(Status s) {
		listStatus.add(s);
		fireTableRowsInserted(0, listStatus.size());
	}

	public void removeStatus(int i) {
		listStatus.remove(i);
		fireTableRowsDeleted(0, listStatus.size());
	}

	public Status getStatus(int i) {
		return listStatus.get(i);
	}

	public int getSize() {
		return listStatus.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listStatus.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		switch (col) {
		case 0:
			return (listStatus.get(row).getName());
		case 1:
			return (DateFormat.getDateInstance(DateFormat.SHORT)
					.format(listStatus.get(row).getCIn().getTime()));
		case 2:
			return (listStatus.get(row).getSiteN());
		case 3:
			return (listStatus.get(row).getDays());
		case 4:
			return (listStatus.get(row).getDaysRemaining());

		default:
			return null;
		}
	}

}
