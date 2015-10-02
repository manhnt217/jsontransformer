package wazi.jsontransformer.expression.function;

import com.jayway.jsonpath.JsonPath;

import java.util.Arrays;
import java.util.List;

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
