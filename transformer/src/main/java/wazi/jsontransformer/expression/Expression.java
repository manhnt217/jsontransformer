package wazi.jsontransformer.expression;

import java.util.List;
import java.util.Map;

public interface Expression {

	/**
	 * @return the value of expression while applying values to symbols in this expression.
	 * @throws Exception
	 * @param symbolMap
	 */
	Object eval(Map<String, Object> symbolMap);

	/**
	 * List all symbols in expression that can pass value to.
	 * Every symbol in list must be unique.
	 * @return
	 */
	List<String> symbolList();

	/**
	 * @return position of this expression in entire jtex expression for tracing purpose
	 */
	int getStart();

	int getEnd();
}
