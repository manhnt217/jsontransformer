package wazi.jsontransformer.parser;

import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.logical.relational.RelationalExpression;
import wazi.jsontransformer.parser.arithmetic.ArithmeticExpressionParser;
import wazi.jsontransformer.parser.helper.MultiChoiceParser;
import wazi.jsontransformer.parser.literal.BooleanLiteralParser;
import wazi.jsontransformer.parser.literal.NullLiteralParser;
import wazi.jsontransformer.parser.literal.StringLiteralParser;
import wazi.jsontransformer.parser.literal.SymbolParser;
import wazi.jsontransformer.parser.logical.LogicalExpressionParser;
import wazi.jsontransformer.parser.logical.relational.RelationalExpressionParser;

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

//	//top level expression parser
//	public final MultiChoiceParser<BaseExpression> expressionParser;

	public ExpressionParser() {
		super();

		//create
		nullLiteralParser = new NullLiteralParser();
		booleanLiteralParser = new BooleanLiteralParser();
		stringLiteralParser = new StringLiteralParser();
		symbolParser = new SymbolParser();
		arithmeticExpressionParser = new ArithmeticExpressionParser();
		logicalExpressionParser = new LogicalExpressionParser();
		relationalExpressionParser = new RelationalExpressionParser();
		functionExpressionParser = new FunctionExpressionParser();
		ifExpressionParser = new IfExpressionParser(logicalExpressionParser);

		LogicalExpressionParser nestedLogicalExpressionParser = new LogicalExpressionParser();
		RelationalExpressionParser nestedRelationalExpressionParser = new RelationalExpressionParser();
		IfExpressionParser nestedIfExpressionParser = new IfExpressionParser(logicalExpressionParser);

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
				nestedLogicalExpressionParser
		);

		logicalExpressionParser.addSubParsers(
				ifExpressionParser,
				nestedIfExpressionParser,
				nullLiteralParser,
				booleanLiteralParser,
				stringLiteralParser,
				symbolParser,
				arithmeticExpressionParser,
//				nestedRelationalExpressionParser,
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
				nestedRelationalExpressionParser,
				functionExpressionParser
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
				nestedLogicalExpressionParser
		);

		nestedLogicalExpressionParser.addSubParsers(logicalExpressionParser.getSubParsers());
		nestedLogicalExpressionParser.setNestedLevel(1);
		nestedRelationalExpressionParser.addSubParsers(relationalExpressionParser.getSubParsers());
		nestedRelationalExpressionParser.setNestedLevel(1);
		nestedIfExpressionParser.addSubParsers(ifExpressionParser.getSubParsers());
		nestedIfExpressionParser.setNestedLevel(1);
	}
}
