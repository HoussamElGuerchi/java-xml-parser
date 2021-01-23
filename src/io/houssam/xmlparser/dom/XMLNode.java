package io.houssam.xmlparser.dom;

import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLNode {
	private Node node;

	public XMLNode(Node node) {
		super();
		this.node = node;
	}

	public XMLNode(String source) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(source);
			node = document.getFirstChild();
			while(node.getNodeType() != Node.ELEMENT_NODE) {
				node = node.getNextSibling();
			}
		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	public XMLNode[] getChildrens() {
		NodeList nodeList = node.getChildNodes();
		Vector<XMLNode> nodes = new Vector<>();
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
				nodes.add(new XMLNode(nodeList.item(i)));
			}
			
		}
		XMLNode[] nodesTab = new XMLNode[nodes.size()];
		nodes.toArray(nodesTab);
		
		return nodesTab;
	}
	
	public XMLNode getChild(String elementName) {
		NodeList nodeList = node.getChildNodes();
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			if (elementName.equals(nodeList.item(i).getNodeName())) {
				return new XMLNode(nodeList.item(i));
			}
		}
		
		return null;
	}
	
	public String getAttribute(String attrName) {
		NamedNodeMap attributes = node.getAttributes();
		Node attr = attributes.getNamedItem(attrName);
		
		return attr.getNodeValue();
	}
	
	public String textValue() {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			return node.getFirstChild().getNodeValue();
		}
		return node.getNodeValue();
	}
	
	public double doubleValue() {
		String strPrice = textValue();
		try {
			return Double.parseDouble(strPrice);
		} catch (Exception e) {
			return -1;
		}
	}
	
	public Node getNode() {
		return node;
	}
	
}
