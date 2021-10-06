package codejava.Constant;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TesthashPassword {
	public static void main( String[] args) {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		String Pasword1 = "abcdef";
		String Pasword2 = "1111";
		String PassWordToDB1 = bcrypt.encode(Pasword1);
		String PassWordToDB2 = bcrypt.encode(Pasword2);
		System.out.println("Pass1: abcdef "+PassWordToDB1);
		System.out.println("Pass2: 1111 "+PassWordToDB2);
		
		boolean login = bcrypt.matches(Pasword2, PassWordToDB2);
		System.out.println(login);
	}
	
}
