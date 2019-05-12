package kr.sys4u.reflection.controller;

import kr.sys4u.reflection.annotation.CustomAutowired;
import kr.sys4u.reflection.service.UserService;

public class UserController {
	@CustomAutowired
	private UserService userServiceInjectedInField;
	
	public void print() {
		userServiceInjectedInField.printAll();
	}
}
