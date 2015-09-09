package wazi.parser.jsontransformer.expression;

import java.util.regex.Pattern;

public class ExpressionParser {

	/** 
	 * Regex: /\w+\.\w+\s*\(\s*\S+\s*(,\s*\S+\s*)*\)/
	 * Match example: "f.s ( 1 ,  3   , 4 )"
	 * */
	private static final Pattern FUNCTION_PATTERN = Pattern.compile("\\w+\\.\\w+\\s*\\(\\s*\\S+\\s*(,\\s*\\S+\\s*)*\\)");
	
	private J jsonPath;
	
	public ExpressionParser (String originalJson) {
		
		jsonPath = new J(originalJson);
	}
	
	/**
	 * Evaluate transforming expression
	 * @param exp JSON transforming expression. Ex: D.add(J.s($.temp), 15) to extract temp value from {temp: 11} then add 15
	 * @return result after evaluation
	 */
	public Object eval(String jtex) {
		
		return null;
	}
	
	private Object evalFunction(String jtexFunction) {
		return null;
	}
	
	private Object evalSelectorFunction(String selectorFunction) {
		
	}
}
