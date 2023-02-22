package dataFaker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.github.javafaker.Faker;

public class CreateFakeData {

	static Faker fake = new Faker();
	
	
	public static void main(String[] args) {

		System.out.println(createCustomerInfo(5));
		System.out.println(createFakeEmailAndPassword(5));
	}

	/*
	 * Use for generating email and password and storing in list of map for testing
	 * number --> number of data you need to generate
	 * 
	 */
	public static List<Map<Object, Object>> createFakeEmailAndPassword(int numberOfData) {

		Faker fake = new Faker();
		List<Map<Object, Object>> customerData = new ArrayList<>();

		for (int i = 1; i <= numberOfData; i++) {
			Map<Object, Object> userPassword = new LinkedHashMap<>();
			customerData.add(userPassword);
			customerData.get(i - 1).put(fake.internet().emailAddress(), fake.internet().password());
		}
		
		return customerData;

	}
	
	/*
	 * use for creating fake customer info and storing in list of map
	 */
	public static List<Map<Object, Object>> createCustomerInfo(int numberOfData){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		List<Map<Object, Object>> customerInfo= new ArrayList<>();

		for (int i = 1; i <= numberOfData; i++) {
			Map<Object, Object> data = new LinkedHashMap<>();
			customerInfo.add(data);
			customerInfo.get(i - 1).put("first_name", fake.name().firstName());
			customerInfo.get(i - 1).put("last_name", fake.name().lastName());
			customerInfo.get(i - 1).put("birth_date", sdf.format(fake.date().birthday()));
			customerInfo.get(i - 1).put("address", fake.address().streetAddress());
			customerInfo.get(i - 1).put("city", fake.address().city());
			customerInfo.get(i - 1).put("state", fake.address().state());
		}
		
		return customerInfo;
		
	}

}
