package wazi.jsontransformer.webdemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import wazi.jsontransformer.JSONTransformer;

/**
 * Handles requests for the application home page.
 */
@RestController
public class TransformController {

	@RequestMapping(value = "/transform", method = RequestMethod.GET)
	public Object transform(@RequestParam String input, @RequestParam String jtex) throws Exception {
		return JSONTransformer.transform(input, jtex);
	}
}
