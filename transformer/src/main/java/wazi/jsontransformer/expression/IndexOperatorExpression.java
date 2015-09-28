package wazi.jsontransformer.expression;

import java.util.List;
import java.util.Map;

/**
 * Created by wazi on 9/23/15.
 */
public class IndexOperatorExpression implements Expression {

	int index;
	int start;
	int end;

	public IndexOperatorExpression(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public Object eval(Map<String, Object> symbolMap) {
		return this;
	}

	@Override
	public int getStart() {
		return start;
	}

	@Override
	public int getEnd() {
		return end;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public List<String> symbolList() {
		return null;
	}
}
