package codejava.Constant;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class publicFuncs {
	
	public static String FormatDate(LocalDateTime Date, String FormatType) {
		String strDate = null ;
		if(FormatType.equalsIgnoreCase("hh:mm:ss")) {
			SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
	        strDate = formatter.format(Date);
		}
		return strDate;
	};
	public static LocalDateTime formatDateLocalDateTime(Date dateToConvert) {
		return Instant.ofEpochMilli(dateToConvert.getTime())
			      .atZone(ZoneId.systemDefault())
			      .toLocalDateTime();
	}
}
