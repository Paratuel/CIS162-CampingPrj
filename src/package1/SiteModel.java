/********************************************************************
 *This class controls the methods used by the GUICampingReg 
 * 
 * @author Patrick Dishaw and Grant Miller
 * @version 10/30/2013
 *********************************************************************/
package package1;

import javax.swing.table.AbstractTableModel;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SiteModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	
	/** Array that holds the sites */
	private ArrayList<Site> listSites;

	/** Array that holds the columnNames */
	private String[] columnNames = {"Name Reserving", "Checked in", "Days Staying", "Site #", "Tent/RV info"};
	
	/** Array that is called to determin if a stop is empty or taken */
	private static int[] siteArray = {-1,-1,-1,-1,-1,-1};	

	/****************************************************************
	 * The following method is a constructor that makes a SiteModel
	 * and establishes listSites 
	 ****************************************************************/
	public SiteModel(){
		super();
		listSites = new ArrayList<Site>();
	}

	/****************************************************************
	 * The following method removes a site
	 * 
	 * @param int i: location of the site
	 ****************************************************************/
	public void removeSite(int i){
		Site p = listSites.remove(i);
		fireTableRowsDeleted(0,listSites.size());
		siteArray[p.getSiteNumber()] = -1;
	}

	/****************************************************************
	 * The following method is adds a site
	 * 
	 * @param Site a: the Site being added
	 ****************************************************************/
	public void addSite(Site a){
		listSites.add(a);
		fireTableRowsInserted(0,listSites.size());
		siteArray[a.getSiteNumber()] = a.getSiteNumber();
		
	}

	/****************************************************************
	 * The following method gets a site at location i
	 * 
	 * @param int i: location of the site
	 * @return listSites at location i
	 ****************************************************************/
	public Site getSite(int i){
		return listSites.get(i);
	}

	/****************************************************************
	 * The following method gets the size of listSites
	 * 
	 * @return the size of listSites
	 ****************************************************************/
	public int getSize(){
		return listSites.size();
	}

	/****************************************************************
	 * The following method saves listSites as a database
	 * 
	 * @param String filename: the name of the file
	 * @exception IOExeption ex
	 ****************************************************************/
	public void saveDatabase(String filename){
		try{
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(listSites);
			oos.close();
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}

	/****************************************************************
	 * The following method loads the listSites database
	 * 
	 * @param String filename: the name of the file
	 * @exception IOExeption ex
	 ****************************************************************/
	@SuppressWarnings("unchecked")
	public void loadDatabase(String filename){
		try{
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);

			listSites = (ArrayList<Site>) ois.readObject();
			fireTableRowsInserted(0,listSites.size() - 1);
			ois.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/****************************************************************
	 * The following method saves listSites as a text
	 * 
	 * @param String filename: the name of the file
	 * @exception IOExeption ex
	 ****************************************************************/
	public boolean saveAsText(String filename){
		if(filename.equals("")){
			return false;
		}	

		try{
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			out.println(listSites.size());
			for(int i = 0; i < listSites.size(); i++){
				Site site = listSites.get(i);
				out.println(site.getClass().getName());
				out.println(DateFormat.getDateInstance(DateFormat.SHORT).format(site.getCheckIn().getTime()));
				out.println(site.getDaysStaying());
				out.println(site.getSiteNumber());
				out.println(site.getNameReserving());
				if(site instanceof RV)
					out.println(((RV) site) .getPower());
				else
					out.println(((Tent) site) .getTenters());
			}
			out.close();
			return true;
		}
		catch(IOException ex){
			return false;
		}
	}

	/****************************************************************
	 * The following method loads the listSites texts
	 * 
	 * @param String filename: the name of the file
	 * @exception IOExeption ex
	 ****************************************************************/
	public void loadFromText(String filename){
		listSites.clear();
		fireTableRowsDeleted(0,listSites.size());

		try{
			Scanner scanner = new Scanner(new File(filename));
			// should clear the arraylist and screen ....
			int count = Integer.parseInt(scanner.nextLine().trim());
			for (int i = 0; i < count; i++) {
				String type = scanner.nextLine().trim();
				GregorianCalendar dateIn = null;
				
				try {
					DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
					Date date = formatter.parse(scanner.nextLine().trim());
					dateIn = new GregorianCalendar();
					dateIn.setTime(date);
				} catch (ParseException ex) {
					ex.printStackTrace();				
				}
				int staying = Integer.parseInt(scanner.nextLine().trim());
				
				
				int sNumber = Integer.parseInt(scanner.nextLine().trim());
				
				String owner = scanner.nextLine().trim();
				int powerOrTenters = Integer.parseInt(scanner.nextLine().trim());
				GregorianCalendar dateOut = null;

				if(type.contains("RV")){
					RV rv = new RV(dateIn, staying, owner, sNumber, powerOrTenters, dateOut);
					listSites.add(rv);
					fireTableRowsInserted(listSites.size() - 1, listSites.size() - 1);
				}
				else{
					Tent tent = new Tent(dateIn, staying, owner, sNumber, powerOrTenters, dateOut);
					listSites.add(tent);
					fireTableRowsInserted(listSites.size() - 1, listSites.size() - 1);
				}
			}
			scanner.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/****************************************************************
	 * The following method gets the columnNames
	 * 
	 * @param int col: location of the col
	 * @return the names of the column at location col
	 ****************************************************************/
	public String getColumnName(int col) {
		return columnNames[col];
	}

	/****************************************************************
	 * The following method gets the length of columnNames
	 * 
	 * @return the names of the column at location col
	 ****************************************************************/
	public int getColumnCount() {
		return columnNames.length;
	}

	/****************************************************************
	 * The following method gets the length of listsSites
	 * 
	 * @return the length of listSites
	 ****************************************************************/
	public int getRowCount() {
		return listSites.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch (col){
		case 0:
			return (listSites.get(row).getNameReserving());
		case 1:
			return (DateFormat.getDateInstance(DateFormat.SHORT)
					.format(listSites.get(row).getCheckIn().getTime()));
		case 2:
			return (listSites.get(row).getDaysStaying());
		case 3:
			return (listSites.get(row).getSiteNumber());
		case 4:
			if (listSites.get(row) instanceof RV)
				return ((RV)listSites.get(row)).getPower() + " Amps";
			else
				return ((Tent)listSites.get(row)).getTenters() + " Tenters";

		default:
			return null;
		}

	}

	public int isOccupied(int siteNumber){
		int count = 0;
		for(int i = 1; i < siteArray.length; i++){
			if(siteArray[i] != -1){
				count++;
			}
			if(count == 5){
				return 2;
			}
		}
		if(siteNumber == siteArray[siteNumber]){
			return 1;
		}
		else 
			return 0;
	}
}
