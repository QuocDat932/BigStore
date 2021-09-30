package codejava.Constant;

public class publicConst {
	
	public enum Orderprocess {
		NEW("NEW"),
		CONFIRM("CONFIRM"),
		APPROVE("APPROVE"),
		REJECT("REJECT"),
		CANCEL("CANCEL");
		private String code;
		Orderprocess(String newCode) { this.code = newCode;}
		public String getCode() {return this.code;}
	};
	
	public final static String Top4Prod = "Top4Prod";
	public final static String ListCart = "listCart";
}
