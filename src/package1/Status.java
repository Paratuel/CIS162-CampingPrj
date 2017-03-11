package package1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Status {

	private String name;

	private GregorianCalendar cIn;

	private int siteN;

	private int days;

	private int daysRemaining;

	private GregorianCalendar cOut;

	public Status(){

	}

	public Status(String name, GregorianCalendar cIn, int siteN, int days, int daysRemaining){
		this.name = name;
		this.cIn = cIn;
		this.siteN = siteN;
		this.days = days;
		this.daysRemaining = daysRemaining;
	}

	public void setName(String nameN){
		this.name = nameN;
	}

	public void setCIn(GregorianCalendar gc){
		this.cIn = gc;
	}

	public void setSiteN(int site){
		this.siteN = site;
	}

	public void setDays(int daysS){
		this.days = daysS;
	}

	public void setDaysRemaining(int daysR){
		this.daysRemaining = daysR;
	}

	public void setCheckOut(GregorianCalendar cOut) {
		this.cOut = cOut;
	}

	public String getName(){
		return name;
	}

	public GregorianCalendar getCIn(){
		return cIn;
	}

	public int getSiteN(){
		return siteN;
	}

	public int getDays(){
		return days;
	}

	public GregorianCalendar getCOut() {
		return cOut;
	}
	
	public int getDaysRemaining() {
		return daysRemaining;
	}

}
