package board.test;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RandomString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SecureRandom random = new SecureRandom();
		System.out.println(new BigInteger(50, random).toString(32));
	}

}
