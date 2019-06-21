package discreteeventsystems.utils;

public class StringUtils {

	/**
	 * Replaces all occurrences of [ with {, and ] with }
	 * @param s string to alter
	 * @return altered string
	 */
	public static String squareToBraces(String s) {
		return s.replaceAll("[\\[]","{").replaceAll("[]]", "}");
	}

	public static String centerWithCharacter(String s, int length, char c) {
		int remaining = length - s.length();

		if (remaining < 0) {
			return s;
		}

		int           numChars = remaining / 2;
		StringBuilder builder  = new StringBuilder();
		for (int i = 0; i < numChars; i++) {
			builder.append(c);
		}

		builder.append(s);

		numChars = remaining - numChars;
		for (int i = 0; i < numChars; i++) {
			builder.append(c);
		}

		return builder.toString();
	}

	public static String centerWithCharacter(Object o, int length, char c) {
		return centerWithCharacter(o.toString(), length, c);
	}

	public static String center(Object o, int length) {
		return center(o.toString(), length);
	}

	public static String center(String s, int length) {
		return centerWithCharacter(s, length, ' ');
	}

}
