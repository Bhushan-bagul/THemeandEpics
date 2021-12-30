package Utils;

/***************** Header Files ******************/
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/***************** Class DateUtils ******************/
public class DateUtils {

	/***************** Gets the Current Time in Specific Format ******************/
	public static String getTimeStamp() {

		Date date = new Date();
		return date.toString().replaceAll(":", "_").replaceAll(" ", "_");

	}
}
