package wazi.jsontransformer.expression;

import java.util.List;
import java.util.Map;

/**
 * Express literal values in jtex, include String, Number, Boolean, Null
 *
 * @author wazi
 */
public abstract class BaseExpression implements Expression {

	protected int start;
	protected int end;
	protected List<String> symbolList;


	public BaseExpression(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public int getStart() {

		return this.start;
	}

	@Override
	public int getEnd() {
		
		return this.end;
	}

	public List<String> getSymbolList() {
		return symbolList;
	}

	public void addSymbol(String symbol) {
		this.symbolList.add(symbol);
	}
}
