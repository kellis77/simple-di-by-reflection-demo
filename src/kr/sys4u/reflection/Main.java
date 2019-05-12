package kr.sys4u.reflection;

import java.util.Arrays;
import java.util.List;

import kr.sys4u.reflection.container.CustomAnnotationContainer;
import kr.sys4u.reflection.controller.UserController;

public class Main {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<String> requiredBeans = Arrays.asList("kr.sys4u.reflection.controller.UserController","kr.sys4u.reflection.repo.UserRepository","kr.sys4u.reflection.service.UserService");
		
		CustomAnnotationContainer container = new CustomAnnotationContainer(requiredBeans) ;
		container.printAllBeans();
		
		System.out.println();
		UserController userController = (UserController) container.get("kr.sys4u.reflection.controller.UserController");
		userController.print();
	}
}
