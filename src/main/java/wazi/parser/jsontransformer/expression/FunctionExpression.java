package wazi.parser.jsontransformer.expression;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * Express the function call in jtex
 * @author wazi
 *
 */
public class FunctionExpression implements Expression {

	String className;
	String methodName;
	List<Expression> arguments;
	int position;

	public FunctionExpression(String className, String methodName, int position) {
		this.className = className;
		this.methodName = methodName;
		this.position = position;
		this.arguments = new LinkedList<>();
	}

	public void addArgument(Expression arg) {
		this.arguments.add(arg);
	}

	@Override
	public boolean isEvaluatable() {

		for (Expression expression : arguments) {
			if(!expression.isEvaluatable()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Object val() throws Exception {

		List<Object> args = new LinkedList<>();

		for (Expression expression : arguments) {
			args.add(expression.val());
		}
		return ReflectionUtil.invoke(className, methodName, args);
	}

	@Override
	public int getPostion() {

		return this.position;
	}
	public static class ReflectionUtil {

		public static Object invoke (String className, String methodName, Object... args) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			Class<?> clazz = Class.forName(className);

			Method[] methods = clazz.getMethods();

			for (Method method : methods) {
				if(methodName.equals(method.getName())) {
					Class<?>[] parameterTypes = method.getParameterTypes();

					if (isParameterTypeMatch(parameterTypes, args)) {
						return method.invoke(null, args);
					}
				}
			}

			throw new RuntimeException("Method not found: " + className + "." + methodName);
		}

		private static boolean isParameterTypeMatch(Class<?>[] parameterTypes, Object[] args) {

			if (parameterTypes.length != args.length) return false;

//			for (int i = 0; i < args.length; i++) {
				
//				if(!parameterTypes[i].isAssignableFrom(args[i].getClass())) return false;
				
//				try {
//					parameterTypes[i].cast(args[i]);
//				} catch (ClassCastException e) {
//					return false;
//				}
//			}
			return true;
		}
	}
}
