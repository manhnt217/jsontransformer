package wazi.jsontransformer.expression;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Express literal values in jtex, include String, Number, Boolean, Null
 *
 * @author wazi
 */
public abstract class BaseExpression {

	protected int start;
	protected int end;
	protected List<String> symbolList;

	public BaseExpression(int start, int end) {
		this.start = start;
		this.end = end;
		symbolList = new LinkedList<>();
	}

	public int getStart() {
		return this.start;
	}

	public int getEnd() {
		return this.end;
	}

	/**
	 * @return the value of expression while applying values to symbols in this expression.
	 * @throws RuntimeException if evaluation fails
	 * @param symbolMap
	 */
	public abstract Object eval(Map<String, Object> symbolMap);

	/**
	 * List all symbols in expression that can pass value to.
	 * Every symbol in list must be unique.
	 * @return
	 */
	public List<String> symbolList() {
		return symbolList;
	}

	public void addSymbol(String symbol) {
		this.symbolList.add(symbol);
	}
}
