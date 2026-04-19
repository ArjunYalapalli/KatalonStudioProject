package common

import java.text.SimpleDateFormat


public class JavaUtils {



	// 🔹 Current Date
	static String getCurrentDate(String format = "yyyy-MM-dd") {
		return new SimpleDateFormat(format).format(new Date())
	}

	// 🔹 Future Date
	static String getFutureDate(int days, String format = "yyyy-MM-dd") {
		Calendar cal = Calendar.getInstance()
		cal.add(Calendar.DAY_OF_MONTH, days)
		return new SimpleDateFormat(format).format(cal.getTime())
	}

	// 🔹 Past Date
	static String getPastDate(int days, String format = "yyyy-MM-dd") {
		Calendar cal = Calendar.getInstance()
		cal.add(Calendar.DAY_OF_MONTH, -days)
		return new SimpleDateFormat(format).format(cal.getTime())
	}

	// 🔹 Timestamp
	static String getTimestamp() {
		return String.valueOf(System.currentTimeMillis())
	}

	// 🔹 Random String
	static String getRandomString(int length = 8) {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
		StringBuilder sb = new StringBuilder()
		Random rand = new Random()

		for (int i = 0; i < length; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())))
		}
		return sb.toString()
	}

	// 🔹 Random Number (useful for IDs)
	static String getRandomNumber(int length = 6) {
		String numbers = "0123456789"
		StringBuilder sb = new StringBuilder()
		Random rand = new Random()

		for (int i = 0; i < length; i++) {
			sb.append(numbers.charAt(rand.nextInt(numbers.length())))
		}
		return sb.toString()
	}

	// 🔹 Random Mobile Number (India format)
	static String getRandomMobileNumber() {
		return "9" + getRandomNumber(9)  // starts with 9 → realistic
	}

	// 🔹 Random Email
	static String getRandomEmail() {
		return "user_" + getTimestamp() + "@gmail.com"
	}

}
