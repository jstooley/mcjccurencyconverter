package mcjccurrencyconvert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.DOMBuilder;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.SAXException;

public class ConversionRates {
	private Map<String, BigDecimal> conversionRates;
	private Document xmlConversion;

	public ConversionRates() {
		xmlConversion = null;
		conversionRates = new HashMap<String, BigDecimal>();
	}

	public Optional<InputStream> makeXMLDocument(String urlLocation) {
		InputStream input = null;
		if (urlLocation.equals("")) {
			return Optional.ofNullable(input);
		}
		URL xmlURL;
		try {
			xmlURL = new URL(urlLocation);
			InputStream xml = xmlURL.openStream();
			input = xml;
		} catch (IOException e) {
			File file = new File(urlLocation);
			if (file.exists()) {
				try {
					InputStream newFile = new FileInputStream(file);
					input = newFile;
				} catch (FileNotFoundException fne) {
					fne.printStackTrace();
					throw new IllegalArgumentException("Invalid argument");
				}
			} else {
				throw new IllegalArgumentException("Invalid argument");
			}
		}
		return Optional.ofNullable(input);
	}

	public Optional<Document> parseXMLDocument(InputStream in) {
		SAXBuilder builder = new SAXBuilder();
		try {
			xmlConversion = builder.build(in);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(xmlConversion);
	}

	public void readConversionRates() {
		Element rootNode = xmlConversion.getRootElement();
		List<Element> children = rootNode.getChild("Cube", null).getChild("Cube", null).getChildren();
		for (Element element : children) {
			BigDecimal value = new BigDecimal(element.getAttributeValue("rate"));
			conversionRates.put(element.getAttributeValue("currency"), value);
		}
	}

	public BigDecimal getConversionRate(String currencyName) {
		BigDecimal amount = conversionRates.get(currencyName);
		return amount;
	}
	
	public void setConversionRates(Map<String, BigDecimal> map) {
		this.conversionRates = map;
	}
}
