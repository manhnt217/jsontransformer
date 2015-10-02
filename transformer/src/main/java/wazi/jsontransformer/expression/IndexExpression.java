package wazi.jsontransformer.expression;

import java.util.List;
import java.util.Map;

/**
 * Created by wazi on 9/23/15.
 */
public class IndexExpression extends BaseExpression {

	int index;

	public IndexExpression(int start, int end) {
		super(start, end);
	}

	@Override
	public Object eval(Map<String, Object> symbolMap) {
		return this;
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
