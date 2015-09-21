package wazi.jsontransformer.expression.function;

import java.util.List;

/**
 * Control functions
 */
public class C {
	public static Object choice(IfThen... ifThens) {

		for (IfThen ifThen : ifThens) {
			if(ifThen.ifCondition) return ifThen.thenExpression;
		}
		return null;
	}
	
	public static Object ite(Boolean ifCond, Object thenEx, Object elseEx) {
		if(ifCond) return thenEx;
		else return elseEx;
	}

	public static Object foreach(List<?> avx) {

		return null;
	}
	
	public static IfThen ift(Boolean ifCondition, Object thenEx) {
		return new IfThen(ifCondition, thenEx);
	}

	public static class IfThen {
		
		boolean ifCondition;
		Object thenExpression;
		
		public IfThen(boolean ifCondition, Object thenExpression) {
			this.ifCondition = ifCondition;
			this.thenExpression = thenExpression;
		}
	}
}