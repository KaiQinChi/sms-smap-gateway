package com.android.smap.samuel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser for inbound SMS's.
 * 
 * @author Matt Witherow
 * 
 */
public class Samuel {

	private final static String	NEW_LINE					= System.getProperty("line.separator");
	private static final String	SMAP_IDENTIFIER				= "#";
	private static final String	GOAL_REFERENCE_STRING_REGEX	= "(?<=#)(.*)(?=!)";
	private static final String	GOAL_COMMAND_STRING_REGEX	= "(?<=!)(.*)(?=.)";
	private static final String	GOAL_PAYLOAD_STRING_REGEX	= "(?<={)[^}]*(?=})";

	public static boolean isSmapRelatedSMS(String message) {
		message = message.trim();
		return message.startsWith(SMAP_IDENTIFIER);
	}

//	public static ??? parse(String message) {
//	}

	private static String getModel(String message) {
		String regex = GOAL_PAYLOAD_STRING_REGEX;
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(message);
		if (matcher.find()) {
			return matcher.group(1);
		}
		return null;
	}

	private static boolean isMalformedMessage(String message) {
		// TODO parse against regex.
		return false;
	}

	private static String getGoalReference(String message) {

		String regex = GOAL_REFERENCE_STRING_REGEX;
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(message);
		if (matcher.find()) {
			return matcher.group(1);
		}
		return null;
	}

	private static String getGoalCommand(String message) {

		if (message.contains("!")) {
			String regex = GOAL_COMMAND_STRING_REGEX;
			Pattern p = Pattern.compile(regex);
			Matcher matcher = p.matcher(message);
			if (matcher.find()) {
				return matcher.group(1);
			}
		}
		return null;
	}
}
