package wazi.jsontransformer.expression.function;

/**
 * Boolean functions
 */
public class B {
	public Boolean and(Boolean... args) {
		for (Boolean b : args) {
			if (!b) return false;
		}
		return true;
	}

	public Boolean or(Boolean... args) {
		for (Boolean b : args) {
			if (b) return true;
		}
		return false;
	}

	public Boolean not(Boolean b) {
		return !b;
	}
}
