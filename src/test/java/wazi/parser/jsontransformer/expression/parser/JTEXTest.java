package wazi.parser.jsontransformer.expression.parser;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

public class JTEXTest {

	@Test
	public void test() {
		JTEX jtex = new JTEX("ありがとう");
		
		if(jtex.hasNext()) assertTrue('あ' == jtex.readNext());
		if(jtex.hasNext()) assertTrue('り' == jtex.readNext());
		if(jtex.hasNext()) assertTrue('が' == jtex.readNext());
		if(jtex.hasNext()) assertTrue('と' == jtex.readNext());
		if(jtex.hasNext()) assertTrue('う' == jtex.readNext());
		assertNull(jtex.readNext());
	}
}
