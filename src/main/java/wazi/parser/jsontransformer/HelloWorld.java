package wazi.parser.jsontransformer;

import java.nio.charset.Charset;

public class HelloWorld {
	public static void main(String[] args) {

		String abc = "\u3A9f";
		
		System.out.println(abc);
		System.out.println(abc.length());
		System.out.println(abc.charAt(0) == '\u3a9f');
		System.out.println(abc.getBytes(Charset.forName("UTF8")).length);
		
		System.out.println(12%5);
	}
}
