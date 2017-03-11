package package1;

import java.util.GregorianCalendar;

public class Tent extends Site {

	private static final long serialVersionUID = 1L;

	/** Represents the number of tenters on this site */
	private int numOfTenters;

	public Tent(){
		super();
	}

	public Tent(GregorianCalendar dateCheckedIn, int daysStaying, String 
			nameReserving, int siteNumber, int numOfTenters, 
			GregorianCalendar dateCheckedOut){
		super(dateCheckedIn, daysStaying, nameReserving, siteNumber, 
				dateCheckedOut);
		this.numOfTenters = numOfTenters;
	}

	public int getTenters(){
		return numOfTenters;
	}

	public void setTenters(int tenters){
		this.numOfTenters = tenters;
	}

	public double getCost(Site unit){ 
		int days = 0;
		if(unit.getDateCheckedOut() == null){
			return (3 * this.getTenters()) * daysStaying;
		}
		else if(unit.getDateCheckedOut().compareTo(unit.getCheckIn()) 
				== 0){
			return 3 * this.getTenters();
		}
		else if(unit.getDateCheckedOut().compareTo(unit.getCheckIn()) 
				< 0){
			throw new IllegalArgumentException();
		}
		else{
			while(unit.getDateCheckedOut().compareTo(unit.getCheckIn()) 
					> 0){
				unit.getCheckIn().add(GregorianCalendar.DAY_OF_MONTH, 1);
				days++;
			}
			return (3 * this.getTenters()) * days;
		}
	}
}
