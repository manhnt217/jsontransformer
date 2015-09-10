package wazi.parser.jsontransformer.expression.helper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtil {
	public static Object invokeStaticMethod (String className, String methodName, Object... args) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = Class.forName(className);
		
		Method[] methods = clazz.getMethods();
		
		for (Method method : methods) {
			if(methodName.equals(method.getName())) {
			
				Class<?>[] parameterTypes = method.getParameterTypes();
				
				return method.invoke(null, args);
			}
		}
		
		throw new RuntimeException("Method not found");
	}
}
