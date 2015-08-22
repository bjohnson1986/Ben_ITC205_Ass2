package datamanagement;

import java.io.IOException;
import java.io.FileWriter;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.JDOMException;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class XmlManager {
	private static XmlManager instance__ = null;
	private Document xmlDocument__;

	public static XmlManager getInstance() {
		if (instance__ == null)
		 instance__ = new XmlManager();
		return instance__;
	}

	private XmlManager() {
		String text = AppProperties.getInstance().getProperties()
				.getProperty("XMLFILE");
		try {
			SAXBuilder saxInstance = new SAXBuilder();
			saxInstance.setExpandEntities(true);
			xmlDocument__ = saxInstance.build(text);
		} catch (JDOMException exception) {
			System.err.printf("%s",
					"DBMD: XMLManager : init : caught JDOMException\n");
			throw new RuntimeException("DBMD: XMLManager : init : JDOMException");
		} catch (IOException exception) {
			System.err.printf("%s", "DBMD: XMLManager : init : caught IOException\n");

			throw new RuntimeException("DBMD: XMLManager : init : IOException");
		}
	}

	public Document getDocument() {
		return xmlDocument__;
	}

	public void saveDocument() {
		String xmlFile = AppProperties.getInstance().getProperties()
				.getProperty("XMLFILE");
		try (FileWriter fOut = new FileWriter(xmlFile)) {
			XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
			outputter.output(xmlDocument__, fOut);
			fOut.close();
		} catch (IOException ioException) {
			System.err.printf("%s\n",
					"DBMD : XMLManager : saveDocument : Error saving XML to " + xmlFile);
			throw new RuntimeException(
					"DBMD: XMLManager : saveDocument : error writing to file");
		}
	}
}
