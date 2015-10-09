package wazi.jsontransformer.expression;

import java.util.Map;

/**
 * Created by wazi on 9/23/15.
 */
public class RangeExpression extends BaseExpression {

	int startIndex;
	int endIndex;

	public RangeExpression(int start, int end) {
		super(start, end);
	}

	@Override
	public Object eval(Map<String, Object> symbolMap) {
		return this;
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
