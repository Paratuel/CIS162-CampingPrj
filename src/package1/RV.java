package package1;

import java.util.*;

public class RV extends Site {

	private static final long serialVersionUID = 1L;

	/** Represents the power supplied to the site */
	private int power;   // 30, 40, 50 amps of service.

	public RV(){
		super();
	}

	public RV(GregorianCalendar dateCheckedIn, int daysStaying, String 
			nameReserving, int siteNumber, int power, 
			GregorianCalendar dateCheckedOut){
		super(dateCheckedIn, daysStaying, nameReserving, siteNumber, 
				dateCheckedOut);
		this.power = power;
	}

	public int getPower(){
		return power;
	}

	public void setPower(int power){
		this.power = power;
	}

	public double getCost(Site unit){ 
		int days = 0;
		if(unit.getDateCheckedOut() == null){
			return daysStaying * 30;
		}
		else if(unit.getDateCheckedOut().compareTo(unit.getCheckIn()) 
				== 0){
			return 30;
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
			return days * 30;
		}
	}
}
