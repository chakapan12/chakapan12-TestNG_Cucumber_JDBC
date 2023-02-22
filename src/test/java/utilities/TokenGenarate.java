package utilities;

import java.util.HashMap;
import java.util.Map;

public class TokenGenarate {
	
	String username;
	String password;
	String token;
	
	public TokenGenarate(String username, String password) {
		this.username = username;
		this.password = password;
		this.token= tokenGenerate(username, password);
		
	}

	public static void main(String[] args) {

		System.out.println(tokenGenerate("harry.potter@gmail.com", "Hog*warts*2023"));
		
		System.out.println(getKey("harry.potter@gmail.com"));
		
		System.out.println(getPassword("harry.potter@gmail.com","Xw:qB:@BC"));
		
		login("harry.potter@gmail.com", "Hog*warts*2023");

	}
	
	public static void login(String user, String password) {
		
		Map<String, String> loginInfo = new HashMap<>();
		loginInfo.put("harry.potter@gmail.com", "Xw:qB:@BC");
		if(tokenGenerate(user, password).equals(loginInfo.get(user))){
			System.out.println("Login successful");
		} else {
			System.out.println("Invalid Credential");
		}
		
	}

	public static int getKey(String user) {

		int num = 0;
		for (int i = 0; i < 3; i++) {
			num += user.charAt(i);
		}

		return num % 25 + 1;
	}

	public static String tokenGenerate(String user, String password) {

		String token = encryptedString(reverseStringPreserveSpecialChars(password), getKey(user));

		return token;

	}

	public static String getPassword(String user, String token) {

		String password = reverseStringPreserveSpecialChars(decryptedString(token, getKey(user)));

		return password;

	}

	public static String encryptedString(String str, int key) {

		String encryptedString = "";

		for (int i = str.length() - 1; i >= 0; i--) {
			encryptedString += (char) (str.charAt(i) - key);
		}

		return encryptedString;

	}

	public static String decryptedString(String str, int key) {

		String encryptedString = "";

		for (int i = str.length() - 1; i >= 0; i--) {
			encryptedString += (char) (str.charAt(i) + key);
		}

		return encryptedString;

	}

	public static String reverseStringPreserveSpecialChars(String input) {
		char[] inputChars = input.toCharArray();
		int left = 0, right = inputChars.length - 1;
		while (left < right) {
			if (!Character.isLetterOrDigit(inputChars[left])) {
				left++;
			} else if (!Character.isLetterOrDigit(inputChars[right])) {
				right--;
			} else {
				char temp = inputChars[left];
				inputChars[left] = inputChars[right];
				inputChars[right] = temp;
				left++;
				right--;
			}
		}
		return new String(inputChars);
	}

}
