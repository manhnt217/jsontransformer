package wazi.parser.jsontransformer.expression.parser;

import wazi.parser.jsontransformer.expression.FunctionExpression;
import wazi.parser.jsontransformer.expression.parser.exception.UnexpectedCharacterException;
import wazi.parser.jsontransformer.expression.parser.jtex.JTEX;

public class FunctionParser {
	public FunctionExpression readFunction(JTEX jtex) {
		StringBuilder className = new StringBuilder();
		StringBuilder methodName = new StringBuilder();
		int position = jtex.getPosition();
		if('A' <= jtex.retrieveNext() && jtex.retrieveNext() <= 'Z') className.append(jtex.next());
		
		while(isValidJavaNameCharacter(jtex.retrieveNext())) {
			className.append(jtex.next());
		}
		if(jtex.next() != '.') throw new UnexpectedCharacterException(jtex.getPosition(), jtex.retrieveNext(), "Missing dot sign after class name.");
		while(isValidJavaNameCharacter(jtex.retrieveNext())) {
			methodName.append(jtex.next());
		}
		FunctionExpression functionExpression = new FunctionExpression(className.toString(), methodName.toString(), position);
		
		if(jtex.next() != '(') throw new UnexpectedCharacterException(jtex.getPosition(), jtex.retrieveNext(), "Missing parentheses after method name.");
		//...
		if(jtex.retrieveNext() != ')') throw new UnexpectedCharacterException(jtex.getPosition(), jtex.retrieveNext(), "Missing parentheses after method name.");
		//...
		return functionExpression;
	}
	
	private boolean isValidJavaNameCharacter(char c) {
		return ('a' <= c && c <= 'z') ||  ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9') ||  (c == '_');
	}
}
