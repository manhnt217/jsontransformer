package wazi.parser.jsontransformer.expression;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
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
			return ReflectionUtil.invokeStatic(className, methodName, (Object[]) null);
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

		public static Object invokeStatic(String className, String methodName, Object... args)
				throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

			Class<?> clazz = Class.forName(className);

			Method[] methods = clazz.getMethods();

			for (Method method : methods) {
				if (methodName.equals(method.getName())) {

					if (args == null || args.length == 0) {

						return method.invoke(null, (Object[]) null);
					} else {

						Parameter[] parameters = method.getParameters();
						//						LinkedList<Object> argList = new LinkedList<Object>();
						//						argList.addAll(Arrays.asList(args));
						Object[] applyingArgs = matchParameters(parameters, args);
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

		private static Object[] matchParameters(Parameter[] parameters, Object[] args) {

			if (parameters.length > args.length) { // not enough argument

			}
			List<Object> applyingArgsList = new LinkedList<>();

			int i = 0;
			while (i < args.length) {

				if (i > parameters.length - 1) {
					return null;// not match
				}

				if (!parameters[i].isVarArgs()) {
					//if(!parameters[i].getType().isAssignableFrom(args.peek().getClass())) return null;
					applyingArgsList.add(args[i]);
					i++;
				} else {
					Class<?> varArgClazz = parameters[i].getType().getComponentType();

					if (varArgClazz.isPrimitive()) {
						//						Class<?> arrayType = primitiveArrayTypeMap.get(varArgClazz);
						//						Object varArgArrayObject = primitiveArrayTypeMap.get(varArgClazz).cast(Array.newInstance(varArgClazz, args.size()));
						//						args.clear();

						//						Object varArgArrayObject = Array.newInstance(varArgClazz, args.length);
						//						
						//						System.arraycopy(args, i, varArgArrayObject, 0, args.length - i);
						applyingArgsList.add(copyPrimitiveArray(args, i, args.length - i, varArgClazz));
					} else {
						Object[] varArgArrayObject = (Object[]) Array.newInstance(varArgClazz, args.length - i);
						System.arraycopy(args, i, varArgArrayObject, 0, args.length - i);
						applyingArgsList.add(varArgArrayObject);
					}
					break;//vararg is always the last argument
				}
			}

			return applyingArgsList.toArray();
		}

		private static Object copyPrimitiveArray(Object[] args, int from, int length, Class<?> primitiveClazz) {

			if (int.class.equals(primitiveClazz)) {
				int[] rs = new int[length];
				for (int k = 0; k < length; k++) {
					rs[k] = (int) args[from + k];
				}
				return rs;
			}
			if (double.class.equals(primitiveClazz)) {
				double[] rs = new double[length];
				for (int k = 0; k < length; k++) {
					rs[k] = (double) args[from + k];
				}
				return rs;
			}
			if (boolean.class.equals(primitiveClazz)) {
				boolean[] rs = new boolean[length];
				for (int k = 0; k < length; k++) {
					rs[k] = (boolean) args[from + k];
				}
				return rs;
			}
			if (byte.class.equals(primitiveClazz)) {
				byte[] rs = new byte[length];
				for (int k = 0; k < length; k++) {
					rs[k] = (byte) args[from + k];
				}
				return rs;
			}
			if (short.class.equals(primitiveClazz)) {
				short[] rs = new short[length];
				for (int k = 0; k < length; k++) {
					rs[k] = (short) args[from + k];
				}
				return rs;
			}
			if (char.class.equals(primitiveClazz)) {
				char[] rs = new char[length];
				for (int k = 0; k < length; k++) {
					rs[k] = (char) args[from + k];
				}
				return rs;
			}
			if (long.class.equals(primitiveClazz)) {
				long[] rs = new long[length];
				for (int k = 0; k < length; k++) {
					rs[k] = (long) args[from + k];
				}
				return rs;
			}
			if (float.class.equals(primitiveClazz)) {
				float[] rs = new float[length];
				for (int k = 0; k < length; k++) {
					rs[k] = (float) args[from + k];
				}
				return rs;
			}
			throw new RuntimeException("Primitive type not found");
		}
	}

}
