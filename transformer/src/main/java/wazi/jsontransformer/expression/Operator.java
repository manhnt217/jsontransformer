package wazi.jsontransformer.expression;

/**
 * Created by wazi on 2015-09-22 022.
 */
public enum Operator {

	//Numeric operators
	PLUS, MINUS, MULTIPLY, DIVIDE, DIV, MOD, POWER,

	//Relational operators
	LT, GT, LTE, GTE, EQ,

	//String operators
	CONCAT,

	//JsonPath
	JSONPATH_SELECTOR,

	//Bool operators
	AND, OR, NOT

	//Bit operators
	;

	@Override
	public String toString() {
		switch (this) {
			case PLUS:
				return "+";
			case MINUS:
				return "-";
			case MULTIPLY:
				return "*";
			case DIVIDE:
				return "/";
			case DIV:
				return "div";
			case MOD:
				return "%";
			case POWER:
				return "pow";

			case GT:
				return ">";
			case LT:
				return "<";
			case GTE:
				return ">=";
			case LTE:
				return "<=";
			case EQ:
				return "==";

			case CONCAT:
				return ".";

			case JSONPATH_SELECTOR:
				return "s";

			case AND:
				return "and";
			case OR:
				return "or";
			case NOT:
				return "not";

			default:
				return null;
		}
	}
}
