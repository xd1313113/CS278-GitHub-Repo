package com.example.sodacloudsmsexampleclient;

import java.util.HashMap;

public class ModuleImpl implements Module{

	private HashMap<Class<?>, Object> components = new HashMap<Class<?>, Object>();
	
	public <T> T getComponent(Class<T> type) {
		return (T) components.get(type);
	}

	@Override
	public <T> void setComponent(Class<T> type, T component) {
		components.put(type, component);	
	}

}
