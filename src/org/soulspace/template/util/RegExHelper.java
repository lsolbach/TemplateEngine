/**
 * File: RegExHelper.java 
 *
 * Created on Feb 5, 2003
 */
package org.soulspace.template.util;

import java.util.WeakHashMap;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;

/**
 * @author soulman
 * 
 *         Helper class for ORO regular expression
 */
public class RegExHelper {

	private static WeakHashMap<String, Pattern> patternMap = new WeakHashMap<String, Pattern>();

	/**
	 * Matches the input string against the pattern.
	 * 
	 * @param input
	 * @param patternString
	 * @return MatchResult
	 */
	public static MatchResult match(String input, String patternString) {
		PatternCompiler compiler = new Perl5Compiler();
		PatternMatcher matcher = new Perl5Matcher();
		Pattern pattern = null;

		pattern = (Pattern) patternMap.get(patternString);
		if (pattern == null) {
			try {
				pattern = compiler.compile(patternString,
						Perl5Compiler.SINGLELINE_MASK);
				patternMap.put(patternString, pattern);
			} catch (MalformedPatternException e) {
				System.out.println("Bad pattern: " + patternString + ".");
				System.out.println(e.getMessage());
				e.printStackTrace();
				return null;
			}
		}
		if (matcher.matches(input, pattern)) {
			return matcher.getMatch();
		}
		return null;
	}

}
