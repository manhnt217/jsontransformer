package wazi.parser.jsontransformer;

import java.lang.reflect.Array;
import java.lang.reflect.Parameter;
import java.util.LinkedList;
import java.util.List;

public class HelloWorld {
	public static void main(String[] args) throws Throwable {
		
		Object abc = new int[] {123};
		Class<? extends Object> clazz1 = abc.getClass();
		Class<?> clazz2 = clazz1.getComponentType();
		System.out.println(clazz2);
		
		Object[] newInstance2 = (Object[]) Array.newInstance(Integer.class, 3);
		
		newInstance2[0] = 4;
		
//		
//		String className = "wazi.parser.jsontransformer.expression.function.I";
//		String methodName = "dummy";
//		
//		Class<?> clazz = Class.forName(className);
//		Object[] arguments = new Object[] {"abc", new Integer(4), 11, "xyz"};
//		Method[] methods = clazz.getMethods();
//
//		for (Method method : methods) {
//			if (methodName.equals(method.getName())) {
//
//				if (arguments == null || arguments.length == 0) {
//
//					System.out.println(method.invoke(null, (Object[]) null));
//				} else {
//
//					Parameter[] parameters = method.getParameters();
//					LinkedList<Object> argList = new LinkedList<Object>();
//					argList.addAll(Arrays.asList(arguments));
//					Object[] applyingArgs = matchParameters(parameters, argList);
//					if (applyingArgs == null) {
//						continue;
//					} else {
//						Object[] argObjs = new Object[] {"xxx", 4, 11, "yyy"};
//						
//						Object newInstance = Array.newInstance(Object.class, 4);
//						Object[] newInst2 = (Object[]) newInstance;
//						newInst2[0] = argObjs[0];
//						newInst2[1] = argObjs[1];
//						newInst2[2] = argObjs[2];
//						newInst2[3] = argObjs[3];
//						
//						System.out.println(method.invoke(null, new Object[] { newInst2 }));//DKM mai cung dung
//					}
//				}
//			}
//		}

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
