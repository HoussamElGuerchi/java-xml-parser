package io.houssam.xmlparser.sax;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import io.houssam.xmlparser.actions.listeners.ReflectionActionListener;

public class MenuBarHandler extends DefaultHandler {
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem = null;
	
	private String iconsFolder;
	private String basePackage;
	private String actionClass;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals("menu-bar")) {
			menuBar = new JMenuBar();
			iconsFolder = attributes.getValue("icons-folder");
			basePackage = attributes.getValue("base-package");
			
		} else if (qName.equals("menu")) {
			menu = new JMenu(attributes.getValue("name"));
			menuBar.add(menu);
			actionClass = attributes.getValue("action-class");
			
		} else if (qName.equals("item")) {
			ImageIcon icon = new ImageIcon(iconsFolder + attributes.getValue("icon"));
			menuItem = new JMenuItem(attributes.getValue("name"), icon);
			menuItem.addActionListener(new ReflectionActionListener(basePackage + "." + actionClass, attributes.getValue("action")));
			menu.add(menuItem);
			
		} else if (qName.equals("separator")) {
			menu.addSeparator();
			
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (menuItem != null) {
			menuItem.setToolTipText(new String(ch, start, length));
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("item")) {
			menuItem = null;
		}
	}
	
	public JMenuBar getMenuBar() {
		return menuBar;
	}
	
}
