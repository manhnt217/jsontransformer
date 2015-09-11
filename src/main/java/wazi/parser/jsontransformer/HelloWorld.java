package wazi.parser.jsontransformer;

public class HelloWorld {
	public static void main(String[] args) throws Throwable {

//		String abc = "\u3A9f";
//
//		System.out.println(abc);
//		System.out.println(abc.length());
//		System.out.println(abc.charAt(0) == '\u3a9f');
//		System.out.println(abc.getBytes(Charset.forName("UTF8")).length);

		System.out.println("\\u3A9f");

		System.out.println((char) Integer.parseInt("3A9f", 16));

		System.out.println("abc\u3A9f123");
	}
}
