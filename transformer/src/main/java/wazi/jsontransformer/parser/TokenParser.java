package wazi.jsontransformer.parser;

import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.Token;
import wazi.jsontransformer.expression.jtex.JTEX;

/**
 * Interface for all parser class
 *
 * @author wazi
 */
public interface TokenParser<T extends Token> {

	String packagePrefixString = "wazi.jsontransformer.expression.function.";

	T read(JTEX jtex);
}