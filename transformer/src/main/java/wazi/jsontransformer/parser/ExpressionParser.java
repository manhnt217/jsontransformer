package wazi.jsontransformer.parser;

import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.logical.relational.RelationalExpression;
import wazi.jsontransformer.parser.arithmetic.ArithmeticExpressionParser;
import wazi.jsontransformer.parser.helper.MultiChoiceParser;
import wazi.jsontransformer.parser.literal.*;
import wazi.jsontransformer.parser.logical.LogicalExpressionParser;
import wazi.jsontransformer.parser.logical.relational.RelationalExpressionParser;

/**
 * Created by wazi on 10/9/15.
 */
public class ExpressionParser {

	//simple parsers
	public final NullLiteralParser nullLiteralParser;
	public final BooleanLiteralParser booleanLiteralParser;
	public final StringLiteralParser stringLiteralParser;
	public final SymbolParser symbolParser;
	public final NumberLiteralParser numberLiteralParser;
	//complex parsers
	public final ArithmeticExpressionParser arithmeticExpressionParser;
	public final FunctionExpressionParser functionExpressionParser;
	public final LogicalExpressionParser logicalExpressionParser;
	public final RelationalExpressionParser relationalExpressionParser;
	public final IfExpressionParser ifExpressionParser;

	//top level expression parser
	public final MultiChoiceParser<BaseExpression> parser;

//	//top level expression parser
//	public final MultiChoiceParser<BaseExpression> expressionParser;

	public ExpressionParser() {
		super();

		//create
		nullLiteralParser = new NullLiteralParser();
		booleanLiteralParser = new BooleanLiteralParser();
		stringLiteralParser = new StringLiteralParser();
		symbolParser = new SymbolParser();
		numberLiteralParser = new NumberLiteralParser();

		arithmeticExpressionParser = new ArithmeticExpressionParser();
		logicalExpressionParser = new LogicalExpressionParser();
		relationalExpressionParser = new RelationalExpressionParser();
		functionExpressionParser = new FunctionExpressionParser();
		ifExpressionParser = new IfExpressionParser();

		LogicalExpressionParser nestedLogicalExpressionParser = new LogicalExpressionParser();
		RelationalExpressionParser nestedRelationalExpressionParser = new RelationalExpressionParser();
		IfExpressionParser nestedIfExpressionParser = new IfExpressionParser();
		ArithmeticExpressionParser nestedArithmeticExpressionParser = new ArithmeticExpressionParser();

		parser = new MultiChoiceParser<>();

		//initialize parsers
		arithmeticExpressionParser.addSubParsers(
				numberLiteralParser,
				symbolParser,
				nestedIfExpressionParser,
				functionExpressionParser,
				nestedArithmeticExpressionParser
		);
		functionExpressionParser.addSubParsers(
				nullLiteralParser,
				numberLiteralParser,
				stringLiteralParser,
				booleanLiteralParser,
				symbolParser,
				functionExpressionParser,
				ifExpressionParser,
				relationalExpressionParser,
				logicalExpressionParser,
				arithmeticExpressionParser
				);

		logicalExpressionParser.addSubParsers(
				nullLiteralParser,
				numberLiteralParser,
				stringLiteralParser,
				booleanLiteralParser,
				symbolParser,
				functionExpressionParser,
				ifExpressionParser,
				relationalExpressionParser,
				nestedLogicalExpressionParser,
				arithmeticExpressionParser
				);

		relationalExpressionParser.addSubParsers(
				nullLiteralParser,
				numberLiteralParser,
				stringLiteralParser,
				booleanLiteralParser,
				symbolParser,
				functionExpressionParser,
				ifExpressionParser,
				nestedLogicalExpressionParser,
				nestedRelationalExpressionParser,
				arithmeticExpressionParser
				);

		ifExpressionParser.addIfClauseParsers(
				nullLiteralParser,
				numberLiteralParser,
				stringLiteralParser,
				booleanLiteralParser,
				symbolParser,
				functionExpressionParser,
				ifExpressionParser,
				logicalExpressionParser,
				relationalExpressionParser,
				arithmeticExpressionParser
				);
		ifExpressionParser.addSubParsers(
				nullLiteralParser,
				numberLiteralParser,
				stringLiteralParser,
				booleanLiteralParser,
				symbolParser,
				functionExpressionParser,
				ifExpressionParser,
				logicalExpressionParser,
				relationalExpressionParser,
				arithmeticExpressionParser
				);

		parser.addParsers(
				nullLiteralParser,
				numberLiteralParser,
				stringLiteralParser,
				booleanLiteralParser,
				symbolParser,
				functionExpressionParser,
				ifExpressionParser,
				logicalExpressionParser,
				relationalExpressionParser,
				arithmeticExpressionParser
				);

		nestedLogicalExpressionParser.addSubParsers(logicalExpressionParser.getSubParsers());
		nestedLogicalExpressionParser.setNestedLevel(1);
		nestedRelationalExpressionParser.addSubParsers(relationalExpressionParser.getSubParsers());
		nestedRelationalExpressionParser.setNestedLevel(1);
		nestedIfExpressionParser.addSubParsers(ifExpressionParser.getSubParsers());
		nestedIfExpressionParser.setNestedLevel(1);
		nestedArithmeticExpressionParser.addSubParsers(arithmeticExpressionParser.getSubParsers());
		nestedArithmeticExpressionParser.setNestedLevel(1);
	}
}
