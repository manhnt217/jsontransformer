package wazi.jsontransformer.expression;

import java.util.List;
import java.util.Map;

/**
 * Created by wazi on 9/23/15.
 */
public class RangeOperatorExpression implements Expression {

	int startIndex;
	int endIndex;
	int start;
	int end;

	public RangeOperatorExpression(int start, int end) {
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

	@Override
	public List<String> symbolList() {
		return null;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
}
