package package1;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class Site implements Serializable {
	private static final long serialVersionUID = 1L;

	/** The name of the person who is occupying the Site */
	protected String nameReserving;

	/** The date the Site was checked-in (occupied) */
	protected GregorianCalendar dateCheckedIn;

	/** The estimated number of days the person is reserving */
	/** This is just an estimate when the camper is  */
	/** is checking in  */
	protected int daysStaying; 

	/** The date the Site was checked out */
	/** This is the exact day they checked-out */
	protected GregorianCalendar checkOutOn;

	/** The Site number */
	protected int siteNumber;  

	/** cost of staying*/
	protected int cost;

	/** The date for changing a string into a
	 * GregorianCalendar  */
	protected GregorianCalendar dateCheckedOut;

	/****************************************************************
	 * The following method is a constructor that makes an empty 
	 * Site 
	 ****************************************************************/
	public Site(){

	}

	/****************************************************************
	 * The following method is a constructor that makes a Site
	 * 
	 *  @param GregorianCalendar dateCheckedIn: The date the Site was 
	 *  checked-in (occupied)
	 *  @param int daysStaying: The estimated number of days the 
	 *  person is reserving this is just an estimate when the camper 
	 *  is checking in 
	 *  @param String nameReserving: The name of the person who is
	 *  occupying the Site
	 *  @param int siteNumber: The Site number 
	 *  @param GregorianCalendar dateCheckedOut: the date the Site 
	 *  was checked out. This is the exact day they checked-out 
	 ****************************************************************/
	public Site(GregorianCalendar dateCheckedIn, int daysStaying, 
			String nameReserving, int siteNumber, 
			GregorianCalendar dateCheckedOut){
		this.dateCheckedIn = dateCheckedIn;
		this.daysStaying = daysStaying;
		this.nameReserving = nameReserving;
		this.siteNumber = siteNumber;
		this.dateCheckedOut = dateCheckedOut;
	}

	/****************************************************************
	 * The following method gets dateCheckedOut
	 * 
	 * @return GregorianCalendar dateCheckedOut: the date the Site 
	 *  was checked out. This is the exact day they checked-out 
	 ****************************************************************/
	public GregorianCalendar getDateCheckedOut(){
		return dateCheckedOut;
	}

	/****************************************************************
	 * The following method sets dateCheckedOut
	 * 
	 * @param GregorianCalendar date: the date the Site 
	 *  was checked out. This is the exact day they checked-out 
	 ****************************************************************/
	public void setDateCheckedOut(GregorianCalendar date){
		this.dateCheckedOut = date;
	}

	/****************************************************************
	 * The following method gets dateCheckedIn
	 * 
	 * @return GregorianCalendar dateCheckedIn: The date the Site was 
	 *  checked-in (occupied)
	 ****************************************************************/
	public GregorianCalendar getCheckIn(){
		return dateCheckedIn;
	}

	/****************************************************************
	 * The following method sets dateCheckedIn
	 * 
	 * @param GregorianCalendar date: The date the Site was 
	 *  checked-in (occupied)
	 ****************************************************************/
	public void setCheckIn(GregorianCalendar date){
		this.dateCheckedIn = date;
	}

	/****************************************************************
	 * The following method gets checkOutOn
	 * 
	 * @return GregorianCalendar checkOutOn: The date the Site was 
	 * checked out this is the exact day they checked-out
	 ****************************************************************/
	public GregorianCalendar getCheckOutOn(){
		return checkOutOn;
	}

	/****************************************************************
	 * The following method sets checkOutOn
	 * 
	 * @param GregorianCalendar date: The date the Site was 
	 * checked out this is the exact day they checked-out
	 ****************************************************************/
	public void setCheckOutOn(GregorianCalendar date){
		this.checkOutOn = date;
	}

	/****************************************************************
	 * The following method gets daysStaying
	 * 
	 * @return int daysStaying: The estimated number of days the 
	 *  person is reserving this is just an estimate when the camper 
	 *  is checking in 
	 ****************************************************************/
	public int getDaysStaying(){
		return daysStaying;
	}

	/****************************************************************
	 * The following method sets daysStaying
	 * 
	 * @param int days: The estimated number of days the 
	 *  person is reserving this is just an estimate when the camper 
	 *  is checking in 
	 ****************************************************************/
	public void setDaysStaying(int days){
		this.daysStaying = days;
	}

	/****************************************************************
	 * The following method gets nameReserving
	 * 
	 * @return String nameReserving: The name of the person who is
	 *  occupying the Site
	 ****************************************************************/
	public String getNameReserving(){
		return nameReserving;
	}

	/****************************************************************
	 * The following method sets nameReserving
	 * 
	 * @return String name: The name of the person who is
	 *  occupying the Site
	 ****************************************************************/
	public void setNameReserving(String name){
		this.nameReserving = name;
	}

	/****************************************************************
	 * The following method gets siteNumber
	 * 
	 * @return int siteNumber: The Site number 
	 ****************************************************************/
	public int getSiteNumber(){
		return siteNumber;
	}

	/****************************************************************
	 * The following method sets siteNumber
	 * 
	 * @param int number: The Site number 
	 ****************************************************************/
	public void setSiteNumber(int number){
		this.siteNumber = number;
	}

	/****************************************************************
	 * The following method changes string t into a 
	 * GregorianCalendar
	 * 
	 * @param String t: input date
	 ****************************************************************/
	public void stringToCalendar(String t){
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			dateCheckedOut = new GregorianCalendar();
			Date d = sdf.parse(t);
			dateCheckedOut.setTime(d); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/****************************************************************
	 * The following method changes string t into a 
	 * GregorianCalendar and returns it
	 * 
	 * @param String t: input date
	 * @return dateCheckedOut: The date the Site was 
	 * checked out this is the exact day they checked-out
	 ****************************************************************/
	public GregorianCalendar returnStringToCalendar(String t){
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			dateCheckedOut = new GregorianCalendar();
			Date d = sdf.parse(t);
			dateCheckedOut.setTime(d); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateCheckedOut;
	}

	/****************************************************************
	 * The following method determines the days remaining on a site
	 * when given a date
	 * 
	 * @param GregorianCalendar cIn: checkedIn date
	 * @param GregorianCalendar cOut: checkedOut date
	 * @param int days: estimated days staying
	 * @return int count: final days remaining
	 ****************************************************************/
	public int getDaysRemaining(GregorianCalendar cIn, 
			GregorianCalendar cOut, int days) {
		int count = 0;
		//adds estimated days to cIn
		cIn.add(GregorianCalendar.DAY_OF_MONTH, days);
		//checks if dates are equal and returns 0
		if(cIn.compareTo(cOut) == 0) 
			return 0;
		//checks if CIn is less than COut and finds the difference
		else if(cIn.compareTo(cOut) < 0) {
			while(cIn.compareTo(cOut) < 0){
				cIn.add(GregorianCalendar.DAY_OF_MONTH, 1);
				count--;
			}
			return count;
		}
		//checks if CIn is greater than COut and finds the difference
		else {
			while(cIn.compareTo(cOut) > 0){
				cOut.add(GregorianCalendar.DAY_OF_MONTH, 1);
				count++;
			}
			//takes estimated days away from cIn
			cIn.add(GregorianCalendar.DAY_OF_MONTH, -days);
			return count;
		}
	}

	/****************************************************************
	 * The following method is overriden by Tent and RV to get costs
	 *
	 * @param Site unit: the given site
	 ****************************************************************/
	public abstract double getCost(Site unit);

}
