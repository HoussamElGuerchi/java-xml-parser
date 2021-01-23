package io.houssam.xmlparser.sax;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class MenuBarFrame extends JFrame {

	public MenuBarFrame() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
		}
		
		MenuBarParser menuBarParser = new MenuBarParser("ressources/menu-bar.xml");
		this.setJMenuBar(menuBarParser.getMenuBar());
		
		this.setVisible(true);
		this.setTitle("SAX Parser");
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
