package wazi.parser.jsontransformer;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HelloWorld {
	public static void main(String[] args) throws Throwable {
		
		System.out.println(new String("daf") instanceof Object);
		
		String className = "wazi.parser.jsontransformer.expression.function.I";
		Class<?> clazz = Class.forName(className);
		Object[] arguments = new Object[] {4, 11};
		
		String methodName = "dummy";

		Method[] methods = clazz.getMethods();

		for (Method method : methods) {
			if (methodName.equals(method.getName())) {

				if (arguments == null || arguments.length == 0) {

					System.out.println(method.invoke(null, (Object[]) null));
				} else {

					Parameter[] parameters = method.getParameters();
					LinkedList<Object> argList = new LinkedList<Object>();
					argList.addAll(Arrays.asList(arguments));
					Object[] applyingArgs = matchParameters(parameters, argList);
					if (applyingArgs == null) {
						continue;
					} else {
						Object[] argObjs = new Object[] {"xxx", new Integer(4), new Integer(11)};
						
						Class<? extends Object> varargClazz = argObjs[1].getClass();
						Object newInstance = Array.newInstance(varargClazz, 2);
						Class<? extends Object> varargArrayClazz = newInstance.getClass();
						Object[] newInst2 = (Object[]) newInstance;
						newInst2[0] = argObjs[1];
						newInst2[1] = argObjs[2];
						
						System.out.println(varargArrayClazz.isArray());
						
						System.out.println(method.invoke(null, new Object[] {argObjs[0], newInst2 }));//DKM mai cung dung
					}
				}
			}
		}

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
				Object[] varArgs = args.toArray();
				args.clear();
				applyingArgsList.add(varArgs);
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
