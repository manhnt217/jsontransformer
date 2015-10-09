package wazi.jsontransformer.expression.arithmetic;

import wazi.jsontransformer.exception.EvaluationException;
import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.helper.Num;
import wazi.jsontransformer.expression.operator.Operator;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by wazi on 2015-09-20 020.
 */
public class ArithmeticExpression extends BaseExpression {

	public List<TermExpression> terms;

	public ArithmeticExpression(int start, int end) {
		super(start, end);
		terms = new LinkedList<>();
	}

	public ArithmeticExpression() {
		this(-1, -1);
	}

	@Override
	public Object eval(Map<String, Object> symbolMap) {

		Num sum = new Num(0);
		for (TermExpression term : terms) {
			Object val = term.eval(symbolMap);
			try {
				sum = sum.exe(Operator.Op.PLUS, new Num(val));
			} catch (IllegalArgumentException e) {
				throw new EvaluationException("Value must be integer or double. Got " + val.getClass().getName(), term.getStart(), term.getEnd());
			}
		}

		return sum.get();
	}

//	@Override
//	public List<String> symbolList() {
//		return null;
//	}

	public void addTerm(TermExpression termExpression) {
		this.terms.add(termExpression);
	}

}

