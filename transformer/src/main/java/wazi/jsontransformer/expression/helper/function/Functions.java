package wazi.jsontransformer.expression.helper.function;

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

	public static String concat(Object... objs) {
		StringBuilder builder = new StringBuilder();
		for (Object obj : objs) {

			if (obj == null) {
				continue;
			}
			builder.append(obj.toString());
		}
		return builder.toString();
	}
}
