package wazi.parser.jsontransformer;


public class HelloWorld {
	public static void main(String[] args) throws Throwable {

//		String abc = "\u3A9f";
//		
//		System.out.println(abc);
//		System.out.println(abc.length());
//		System.out.println(abc.charAt(0) == '\u3a9f');
//		System.out.println(abc.getBytes(Charset.forName("UTF8")).length);
//		
//		System.out.println(12%5);
		
		double d = -.2e19;
		double d2 = -0.e19;
		System.out.println(Double.parseDouble("-.2e10"));
	}
}
