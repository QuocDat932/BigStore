package codejava.Constant;

import java.sql.Date;
import java.time.LocalDateTime;

public class publicConst {
	
	public enum Orderprocess {
		NEW("NEW"),				//1 Lên Đơn Hàng
		CONFIRM("CONFIRM"),		//2 Xác Nhận Đơn Hàng
		SHIP("SHIP"),			//3 Vận Chuyển Hàng
		TAKED("TAKED"),			//4 Đã Nhận Hàng
		REJECT("REJECT"),		//5 Trả Đơn
		CANCEL("CANCEL");		//6 Hủy hàng
		private String code;
		Orderprocess(String newCode) { this.code = newCode;}
		public String getCode() {return this.code;}
	};
	
	public final static String Top4Prod = "Top4Prod";
	public final static String ListCart = "listCart";
	
	public final static Date CurrentDtSQL() {
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);
		return date;
	};
	public static LocalDateTime CurrentTimestampDtSQL() {
		// TODO Auto-generated method stub
		return java.time.LocalDateTime.now();
	};
	
}
