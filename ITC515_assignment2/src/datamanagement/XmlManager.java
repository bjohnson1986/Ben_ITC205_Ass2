package datamanagement;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.JDOMException;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.IOException;
import java.io.FileWriter;

public class XmlManager {
	private static XmlManager self__ = null;
	private Document xmlDocument__;
	
	public static XmlManager getXML() {
		if (self__ == null)
			self__ = new XmlManager();
		return self__;
	}

	private XmlManager() {
		init();

	}

	public void init() {
		String text = AppProperties.getInstance().getProperties()
				.getProperty("XMLFILE");
		try {
			SAXBuilder saxInstance = new SAXBuilder();
			saxInstance.setExpandEntities(true);
			xmlDocument__ = saxInstance.build(text);
		}

		catch (JDOMException exception) {
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
		String xmlfile = AppProperties.getInstance().getProperties()
				.getProperty("XMLFILE");
		try (FileWriter fout = new FileWriter(xmlfile)) {
			XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
			outputter.output(xmlDocument__, fout);
			fout.close();
		} catch (IOException ioException) {
			System.err.printf("%s\n",
					"DBMD : XMLManager : saveDocument : Error saving XML to " + xmlfile);
			throw new RuntimeException(
					"DBMD: XMLManager : saveDocument : error writing to file");
		}
	}
}
