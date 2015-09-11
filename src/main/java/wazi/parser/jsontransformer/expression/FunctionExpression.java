package wazi.parser.jsontransformer.expression;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * Express the function call in jtex
 * 
 * @author wazi
 *
 */
public class FunctionExpression extends BaseExpression {

	String className;
	String methodName;
	List<Expression> arguments;

	public FunctionExpression(String className, String methodName, int position) {
		super(null, position);
		this.className = className;
		this.methodName = methodName;
		this.arguments = new LinkedList<>();
	}

	public void addArgument(Expression arg) {

		this.arguments.add(arg);
	}

	@Override
	public Object val() throws Exception {

		List<Object> args = null;
		if (this.arguments != null && this.arguments.size() > 0) {

			args = new LinkedList<>();

			for (Expression expression : arguments) {
				args.add(expression.val());
			}
		}
		
		return ReflectionUtil.invoke(className, methodName, args);
	}

	public String getClassName() {

		return className;
	}

	public String getMethodName() {

		return methodName;
	}

	public List<Expression> getArguments() {

		return arguments;
	}

	public void setClassName(String className) {

		this.className = className;
	}

	public void setMethodName(String methodName) {

		this.methodName = methodName;
	}

	public void setArguments(List<Expression> arguments) {

		this.arguments = arguments;
	}

	public static class ReflectionUtil {

		public static Object invoke(String className, String methodName, Object... args) throws ClassNotFoundException,
				IllegalAccessException, IllegalArgumentException, InvocationTargetException {

			Class<?> clazz = Class.forName(className);

			Method[] methods = clazz.getMethods();

			for (Method method : methods) {
				if (methodName.equals(method.getName())) {
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
