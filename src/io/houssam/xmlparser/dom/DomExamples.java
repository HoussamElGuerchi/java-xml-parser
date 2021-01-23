package io.houssam.xmlparser.dom;

import io.houssam.xmlparser.sax.MenuBarFrame;

public class DomExamples {
	
	public DomExamples() {
		example2();
	}
	
	private void example1() {
		XMLNode root = new XMLNode("ressources/documents.xml");
		XMLNode[] childs = root.getChildrens();
		for (int i = 0; i < childs.length; i++) {
			String docTitle = childs[i].getChild("title").textValue();
			double docPrice = childs[i].getChild("price").doubleValue();
			System.out.println(docPrice);
		}
	}
	
	private void example2() {
		new MenuBarFrame();
	}
	
	public static void main(String[] args) {
		DomExamples domExamples = new DomExamples();
	}

}
