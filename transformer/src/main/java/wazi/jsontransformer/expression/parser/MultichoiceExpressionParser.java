package wazi.jsontransformer.expression.parser;

import wazi.jsontransformer.expression.Expression;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.parser.exception.ParserException;
import wazi.jsontransformer.expression.parser.exception.UnexpectedCharacterException;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wazi on 2015-09-24 024.
 */
public class MultichoiceExpressionParser implements ExpressionParser {

	List<ExpressionParser> parsers;

	public MultichoiceExpressionParser() {
		this((ExpressionParser[])null);
	}

	public MultichoiceExpressionParser(ExpressionParser... parsers) {
		this.parsers = new LinkedList<>();
		if (parsers != null) {
			for (ExpressionParser parser : parsers) {
				this.parsers.add(parser);
			}
		}
	}

	@Override
	public Expression readExpression(JTEX jtex) {

		ParserException ex = new UnexpectedCharacterException(jtex.getNextPosition(), jtex.retrieveNext(), "");

		for (ExpressionParser parser : parsers) {
			try {
				return parser.readExpression(new JTEX(jtex.getString(), jtex.getNextPosition()));
			} catch (ParserException e) {
				if (e.getPosition() > ex.getPosition()) {
					ex = e;
				}
			}
		}

		throw ex;
	}
}
