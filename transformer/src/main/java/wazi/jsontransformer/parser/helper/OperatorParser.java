package wazi.jsontransformer.parser.helper;

import wazi.jsontransformer.expression.Token;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.operator.Operator;
import wazi.jsontransformer.parser.TokenParser;
import wazi.jsontransformer.exception.parser.ParserException;

/**
 * Created by wazi on 2015-10-09 009.
 */
public class OperatorParser implements TokenParser<Operator> {
	static FixTokenParser[] opParsers;

	static {
		Operator.Op[] ops = Operator.Op.values();
		opParsers = new FixTokenParser[ops.length];
		for (int i = 0; i < ops.length; i++) {
			opParsers[i] = new FixTokenParser(ops[i].toString());
		}
	}

	private MultiChoiceParser<Token> operatorTokenParser;

	public OperatorParser() {
		operatorTokenParser = new MultiChoiceParser<>(opParsers);
	}

	@Override
	public Operator read(JTEX jtex) throws ParserException {
		Token token = operatorTokenParser.read(jtex);
		String tokenString = jtex.getString().substring(token.getStart(), token.getEnd() + 1);
		return new Operator(tokenString, token.getStart(), token.getEnd());
	}
}
