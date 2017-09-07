package azure_test;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.log4j.BasicConfigurator;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.Country;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.UidGenerator;

public class test_iCalendar {

	public static void main(String args[]) throws IOException, ValidationException {

		BasicConfigurator.configure();

		// Creating a Calendar

		Calendar calendar = new Calendar();
		calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
		calendar.getProperties().add(Version.VERSION_2_0);
		calendar.getProperties().add(CalScale.GREGORIAN);

		// Creating a new Calendar Event
		
		
		//Calculating Start Date
		java.util.Calendar startDate = java.util.Calendar.getInstance(java.util.TimeZone.getTimeZone("Etc/UTC"));
		startDate.set(java.util.Calendar.MONTH, java.util.Calendar.MAY);
		startDate.set(java.util.Calendar.DAY_OF_MONTH, 25);
		startDate.set(java.util.Calendar.YEAR, 2010);
		startDate.set(java.util.Calendar.HOUR_OF_DAY, 17);
		startDate.clear(java.util.Calendar.MINUTE);
		startDate.clear(java.util.Calendar.SECOND);
		
		// End Date is on: May 25, 2010, 6:00 Pm
		java.util.Calendar endDate = java.util.Calendar.getInstance(java.util.TimeZone.getTimeZone("Etc/UTC"));
		endDate.set(java.util.Calendar.MONTH, java.util.Calendar.MAY);
		endDate.set(java.util.Calendar.DAY_OF_MONTH, 25);
		endDate.set(java.util.Calendar.YEAR, 2010);
		endDate.set(java.util.Calendar.HOUR_OF_DAY, 18);
		endDate.clear(java.util.Calendar.MINUTE);
		endDate.clear(java.util.Calendar.SECOND);
		
		// Create the event
		DateTime start = new DateTime(startDate.getTime());
		start.setUtc(true);
		DateTime end = new DateTime(endDate.getTime());
		end.setUtc(true);
		
		
       /* java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(java.util.Calendar.MONTH, java.util.Calendar.DECEMBER);
		cal.set(java.util.Calendar.DAY_OF_MONTH, 25); */
        
	    //VEvent christmas = new VEvent(new Date(cal.getTime()), "Christmas Day");
		// VEvent cricket = new VEvent(null, "test");
		// Initialize as an all-day event..
		VEvent christmas = new VEvent(start, end, "Christmas Event of 2017");
     
		
		// Creating an event
		// christmas.getProperties().getProperty(Property.DTSTART).getParameters().add(Value.DATE);
	    
		UidGenerator uidGenerator = new UidGenerator("1");
		christmas.getProperties().add(uidGenerator.generateUid());
		christmas.getProperties().add(new Description("All info about the event"));
		christmas.getProperties().add(new Location("New York"));
		christmas.getProperties().add(new Country("USA"));
		
		//Adding the event to the Calendar
		//================================
		calendar.getComponents().add(christmas);

		// Printing Calendar on output File
		FileOutputStream fout = new FileOutputStream("/Users/sathya/Desktop/mycalendar.ics");
		CalendarOutputter outputter = new CalendarOutputter();
		outputter.output(calendar, fout);

	}
}
