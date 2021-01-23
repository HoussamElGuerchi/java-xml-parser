package io.houssam.xmlparser.sax;

import javax.swing.JMenuBar;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class MenuBarParser {
	private JMenuBar menuBar;
	
	public MenuBarParser(String source) {
		SAXParserFactory factory = SAXParserFactory.newDefaultInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			MenuBarHandler menuBarHandler = new MenuBarHandler();
			parser.parse(source, menuBarHandler);
			
			menuBar = menuBarHandler.getMenuBar();
		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
		}
	}
	
	public JMenuBar getMenuBar() {
		return menuBar;
	}

}
