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
	/** Holds the valuse of the currency and rate */
	private Map<String, BigDecimal> conversionRates;
	
	/** Holds the information about the conversion rates from one currency to a Euro */
	private Document xmlConversion;
	
	/**
	 * A constructor for the conversion rates class that initializes a map and document.
	 */
	public ConversionRates() {
		xmlConversion = null;
		conversionRates = new HashMap<String, BigDecimal>();
	}

	/**
	 * A method that returns an Optional InputStream based on a given url or file.
	 * @param urlLocation
	 * file or url location that needs to be input
	 * @return
	 * FileInputStream, InputStream, or null if one cannot be found.
	 */
	public InputStream makeXMLDocument(String urlLocation) {
		InputStream input = null;
		if (urlLocation.equals("")) {
			return null;
		}
		URL xmlURL;
		try {
			xmlURL = new URL(urlLocation);
			InputStream xml = xmlURL.openStream();
			input = xml;
		} catch (IOException e) {
			// if the URL cannot be found then try to create a file.
			File file = new File(urlLocation);
			if (file.exists()) {
				try {
					InputStream newFile = new FileInputStream(file);
					input = newFile;
				} catch (FileNotFoundException fne) {
					fne.printStackTrace();
					// throw and invalid argument exception because the url or file given was invalid.
					throw new IllegalArgumentException("Invalid argument");
				}
			} else {
				// throw and invalid argument exception because the url or file given was invalid.
				throw new IllegalArgumentException("Invalid argument");
			}
		}
		return input;
	}

	/**
	 * A method that converts an inputStream into an XML document.
	 * @param in
	 * Input stream to be turned into an XML document.
	 * @return
	 * a Document or null if one cannot be created.
	 */
	public Document parseXMLDocument(InputStream in) {
		SAXBuilder builder = new SAXBuilder();
		try {
			xmlConversion = builder.build(in);
		} catch (IOException | JDOMException e) {
			e.printStackTrace();
		}
		return xmlConversion;
	}
	
	/**
	 * A method that reads a currency and rate from an XML document into a map.
	 */
	public void readConversionRates() {
		Element rootNode = xmlConversion.getRootElement();
		List<Element> children = rootNode.getChild("Cube", null).getChild("Cube", null).getChildren();
		for (Element element : children) {
			BigDecimal value = new BigDecimal(element.getAttributeValue("rate"));
			conversionRates.put(element.getAttributeValue("currency"), value);
		}
	}
	
	public Map<String, BigDecimal> getConversionRates() {
		return conversionRates;
		
	}
	
	public BigDecimal getConversionRate(String currencyName) {
		BigDecimal amount = conversionRates.get(currencyName);
		return amount;
	}
	
	public void setConversionRates(Map<String, BigDecimal> map) {
		this.conversionRates = map;
	}
}
