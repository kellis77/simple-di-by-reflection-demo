package kr.sys4u.reflection.container;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.sys4u.reflection.annotation.CustomAutowired;
import kr.sys4u.reflection.exception.BeanCreationException;

public class BeanInstantiator {
	private List<String> requiredClasses;
	private List<String> dependencyList;
	private Map<String, Object> instances;
	
	public BeanInstantiator(List<String> requiredClasses, List<String> dependencyList) {
		this.requiredClasses = requiredClasses;
		this.dependencyList = dependencyList;
		instances = new HashMap<>();
	}
	
	public Map<String, Object> instantiate(){
		dependencyList.forEach(this::instantiate);
		return instances;
	}
	
	private Object instantiate(String beanName) {
		Object instance = instances.get(beanName);
		if (instance != null) {
			return instance;
		}
		
		try {
			Class<?> bean = Class.forName(beanName);
			Object newBean = bean.newInstance();
			
			Arrays.asList(bean.getDeclaredFields())
					.stream().sequential()
					.filter(f -> f.isAnnotationPresent(CustomAutowired.class))
					.map(field -> {
						field.setAccessible(true);
						return field;
					})
					.forEach(field -> {
						if(!requiredClasses.contains(field.getType().getName())) {
							throw new BeanCreationException();
						}
						Object autowiredField = instances.get(field.getType().getName());
						Object innerField = autowiredField == null ? instantiate(field.getType().getName()) : autowiredField;
						try {
							field.set(newBean, innerField);
						} catch (Exception e) {
							e.printStackTrace();
						}
					});

			instances.put(beanName, newBean);
			return newBean;
		} catch (Exception e) {
			throw new BeanCreationException(e);
		}
	}
}
