package wazi.parser.jsontransformer.expression.helper;

import java.lang.reflect.Method;

public class ReflectionUtil {
	public static Object invokeStaticMethod (String className, String methodName, Object... args) throws ClassNotFoundException {
		Class<?> clazz = Class.forName(className);
		
		Method[] methods = clazz.getMethods();
		
		for (Method method : methods) {
			
		}
		
		return null;
	}
}
