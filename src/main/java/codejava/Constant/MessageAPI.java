package codejava.Constant;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MessageAPI {
	public static Map<String, Object> message(String status, String Message, Object items) {
		Map<String, Object> map = new HashMap<>();
		map.put("Status", status);
		map.put("Message", Message);
		if (Objects.isNull(items)) {
			map.put("Items", "");
		} else {
			map.put("Items", items);
		}
		return map;
	}
	public static String FAIL = "Failed";
	public static String SUBMIT = "Submitted";
}
