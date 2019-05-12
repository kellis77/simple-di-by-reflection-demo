package kr.sys4u.reflection.container;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kr.sys4u.reflection.annotation.CustomAutowired;
import kr.sys4u.reflection.exception.BeanCreationException;

public class DependencyListGenerator {
	private List<String> requiredClasses;
	private List<String> dependencyList;
	
	public DependencyListGenerator(List<String> requiredClasses) {
		this.requiredClasses = requiredClasses;
		this.dependencyList = new ArrayList<>();
	}
	
	public List<String> generate() {
		requiredClasses.forEach(this::generateDependency);
		return dependencyList;
	}
	
	private void generateDependency(String beanName) {
		if(dependencyList.contains(beanName)) {
			return;
		}
		
		dependencyList.add(beanName);
		try {
			Arrays.asList(Class.forName(beanName).getDeclaredFields())
				.stream().sequential()
				.filter(f -> f.isAnnotationPresent(CustomAutowired.class))
				.forEach(field->generateDependency(field.getType().getName()));
		}catch (Exception e) {
			throw new BeanCreationException(e);
		}
	}
}
