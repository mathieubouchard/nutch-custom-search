package ir.co.bayan.simorq.zal.nutch.extractor.engine;

import static org.junit.Assert.assertEquals;
import ir.co.bayan.simorq.zal.nutch.extractor.ExtractedDoc;
import ir.co.bayan.simorq.zal.nutch.extractor.config.Document;
import ir.co.bayan.simorq.zal.nutch.extractor.config.SelectorConfiguration;

import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Taha Ghasemi <taha.ghasemi@gmail.com>
 * 
 */
public class XPathEngineTest {

	private static XPathEngine engine;
	private static byte[] content;
	private static SelectorConfiguration configuration;
	private static String encoding = "UTF-8";

	@BeforeClass
	public static void init() throws Exception {
		engine = new XPathEngine();
		content = IOUtils.toByteArray(XPathEngine.class.getResourceAsStream("/test.xml"));
		configuration = SelectorConfiguration.readConfig(new InputStreamReader(XPathEngine.class
				.getResourceAsStream("/extractors-xpath-test.xml")));
	}

	/**
	 * Test method for
	 * {@link ir.co.bayan.simorq.zal.nutch.extractor.engine.XPathEngine#extractDocuments(ir.co.bayan.simorq.zal.nutch.extractor.config.Document, java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public void testExtractDocuments() throws Exception {
		Document document = configuration.getDocuments().get(0);
		List<ExtractedDoc> docs = engine.extractDocuments(document, "", content, encoding, "");
		ExtractedDoc doc = docs.get(0);
		assertEquals("content1", doc.getFields().get("f1"));
		assertEquals("b1 b3", doc.getFields().get("f2"));
	}
}