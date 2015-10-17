package wazi.jsontransformer.parser;

import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.parser.arithmetic.ArithmeticExpressionParser;
import wazi.jsontransformer.parser.helper.MultiChoiceParser;
import wazi.jsontransformer.parser.literal.*;
import wazi.jsontransformer.parser.logical.LogicalExpressionParser;
import wazi.jsontransformer.parser.logical.relational.RelationalExpressionParser;
import wazi.jsontransformer.parser.logical.relational.RelationalExpressionParser.NestedRelationalExpressionParser;

import static wazi.jsontransformer.parser.IfExpressionParser.*;
import static wazi.jsontransformer.parser.logical.LogicalExpressionParser.*;

/**
 * Created by wazi on 10/9/15.
 */
public class ExpressionParser extends MultiChoiceParser<BaseExpression> {

	//simple parsers
	public final NullLiteralParser nullLiteralParser;
	public final BooleanLiteralParser booleanLiteralParser;
	public final StringLiteralParser stringLiteralParser;
	public final SymbolParser symbolParser;
	//complex parsers
	public final ArithmeticExpressionParser arithmeticExpressionParser;
	public final FunctionExpressionParser functionExpressionParser;
	public final LogicalExpressionParser logicalExpressionParser;
	public final RelationalExpressionParser relationalExpressionParser;
	public final IfExpressionParser ifExpressionParser;

	//full expression parser
	public final MultiChoiceParser<BaseExpression> expressionParser;

	public ExpressionParser() {
		super();

		nullLiteralParser = new NullLiteralParser();
		booleanLiteralParser = new BooleanLiteralParser();
		stringLiteralParser = new StringLiteralParser();
		symbolParser = new SymbolParser();

		arithmeticExpressionParser = new ArithmeticExpressionParser();

		logicalExpressionParser = new LogicalExpressionParser();
		NestedLogicalExpressionParser nestedLogicalExpressionParser = new NestedLogicalExpressionParser(logicalExpressionParser);
		relationalExpressionParser = new RelationalExpressionParser();

		functionExpressionParser = new FunctionExpressionParser();

		ifExpressionParser = new IfExpressionParser(logicalExpressionParser);
		NestedIfExpressionParser nestedIfExpressionParser = new NestedIfExpressionParser(ifExpressionParser);

		expressionParser = new MultiChoiceParser<>();

		//initialize parsers
		functionExpressionParser.addSubParsers(
				ifExpressionParser,
				nestedIfExpressionParser,
				nullLiteralParser,
				booleanLiteralParser,
				stringLiteralParser,
				symbolParser,
				arithmeticExpressionParser,
				functionExpressionParser,
				logicalExpressionParser
		);

		logicalExpressionParser.addSubParsers(
				ifExpressionParser,
				nestedIfExpressionParser,
				nullLiteralParser,
				booleanLiteralParser,
				stringLiteralParser,
				symbolParser,
				arithmeticExpressionParser,
				nestedLogicalExpressionParser,
				relationalExpressionParser,
				functionExpressionParser
		);

		relationalExpressionParser.addSubParsers(
				ifExpressionParser,
				nestedIfExpressionParser,
				nullLiteralParser,
				booleanLiteralParser,
				nestedLogicalExpressionParser,
				stringLiteralParser,
				symbolParser,
				arithmeticExpressionParser,
				functionExpressionParser,
				new NestedRelationalExpressionParser(relationalExpressionParser)
		);

		ifExpressionParser.addSubParsers(
				ifExpressionParser,
				nestedIfExpressionParser,
				nullLiteralParser,
				booleanLiteralParser,
				stringLiteralParser,
				symbolParser,
				arithmeticExpressionParser,
				functionExpressionParser,
				logicalExpressionParser
		);

		expressionParser.addAllParsers(
				ifExpressionParser,
				nestedIfExpressionParser,
				nullLiteralParser,
				booleanLiteralParser,
				stringLiteralParser,
				symbolParser,
				arithmeticExpressionParser,
				functionExpressionParser,
				logicalExpressionParser
		);
//		this.addParser(nullLiteralParser);
//		this.addParser(booleanLiteralParser);
//		this.addParser(stringLiteralParser);
//		this.addParser(symbolParser);
//		this.addParser(arithmeticExpressionParser);
//		this.addParser(functionExpressionParser);
//		this.addParser(logicalExpressionParser);
	}
}
