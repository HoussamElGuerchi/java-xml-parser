package io.houssam.xmlparser.actions.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionActionListener implements ActionListener {
	private Object actionObject;
	private Method method;
	
	public ReflectionActionListener(String className, String methodName) {
		try {
			Class<?> cls = Class.forName(className);
			actionObject = cls.getConstructor().newInstance();
			method = actionObject.getClass().getMethod(methodName);
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			method.invoke(actionObject);
		} catch (Exception e1) {
			System.out.println("Error: " + e1.getMessage());
		}
	}

}
