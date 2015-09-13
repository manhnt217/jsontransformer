package wazi.parser.jsontransformer.expression;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
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

		if (this.arguments != null && this.arguments.size() > 0) {

			List<Object> args = new LinkedList<>();
			for (Expression expression : arguments) {
				args.add(expression.val());
			}
			return ReflectionUtil.invokeStatic(className, methodName, args.toArray());
		} else {
			return ReflectionUtil.invokeStatic(className, methodName, (Object[])null);
		}

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

		public static Object invokeStatic(String className, String methodName, Object... args) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException,
				InvocationTargetException {

			Class<?> clazz = Class.forName(className);

			Method[] methods = clazz.getMethods();

			for (Method method : methods) {
				if (methodName.equals(method.getName())) {

					if (args == null || args.length == 0) {

						return method.invoke(null, (Object[]) null);
					} else {

						Parameter[] parameters = method.getParameters();
						LinkedList<Object> argList = new LinkedList<Object>();
						argList.addAll(Arrays.asList(args));
						Object[] applyingArgs = matchParameters(parameters, argList);
						if (applyingArgs == null) {
							continue;
						} else {
							return method.invoke(null, applyingArgs);
						}
					}
				}
			}

			throw new RuntimeException("Method not found: " + className + "." + methodName);
		}

		private static Object[] matchParameters(Parameter[] parameters, LinkedList<Object> args) {

			if (parameters.length > args.size()) { // not enough argument

			}
			List<Object> applyingArgsList = new LinkedList<>();

			int i = 0;
			while (!args.isEmpty()) {

				if (i > parameters.length - 1) {
					return null;// not match
				}

				if (!parameters[i].isVarArgs()) {
					applyingArgsList.add(args.pop());
				} else {
					
					Class<?> varArgClazz = parameters[i].getType().getComponentType();
					
					Object[] varArgArrayObject = (Object[]) Array.newInstance(varArgClazz, args.size());
					System.arraycopy(args.toArray(), 0, varArgArrayObject, 0, varArgArrayObject.length);
					args.clear();
					applyingArgsList.add(varArgArrayObject);
				}
				i++;
			}

			return applyingArgsList.toArray();

			// for (int i = 0; i < args.length; i++) {

			// if(!parameterTypes[i].isAssignableFrom(args[i].getClass()))
			// return false;

			// try {
			// parameterTypes[i].cast(args[i]);
			// } catch (ClassCastException e) {
			// return false;
			// }
			// }
		}
	}

}
