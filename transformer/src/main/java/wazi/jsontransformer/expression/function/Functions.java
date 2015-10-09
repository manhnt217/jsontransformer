package wazi.jsontransformer.expression.function;

/**
 * General function
 * Created by wazi on 2015-10-02 002.
 */
public class Functions {

	public static String s(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString();
	}

}
