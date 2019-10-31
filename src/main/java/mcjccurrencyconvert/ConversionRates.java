package mcjccurrencyconvert;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.xml.sax.SAXException;

public class ConversionRates {
	private Map<String, BigDecimal> conversionRates;
	private Document xmlConversion;
	
	public ConversionRates( ) {
		xmlConversion = makeConversionRates();
	}
	
	private Document makeConversionRates() {
		xmlConversion = null;
		try {
			URL xmlURL = new URL("http://wwww.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
			InputStream xml = xmlURL.openStream();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			org.w3c.dom.Document w3cDocument = db.parse(xml);
			xmlConversion = new DOMBuilder().build(w3cDocument);
		} catch (IOException | SAXException | ParserConfigurationException e) {
			e.printStackTrace();
		}
		return xmlConversion;
	}
	
	private void readConversionRates() {
		Element rootNode = xmlConversion.getRootElement();
		System.out.println("Root Element :: " + rootNode.getName());
		System.out.println("Root Child :: " + rootNode.getAttributeValue("cube rate"));
	}
	
	public BigDecimal getConversionRate(String currencyName) {
		return conversionRates.get(currencyName);
	}
}
