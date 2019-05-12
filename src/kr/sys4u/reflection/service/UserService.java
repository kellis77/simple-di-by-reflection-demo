package kr.sys4u.reflection.service;

import java.util.Arrays;

import kr.sys4u.reflection.annotation.CustomAutowired;
import kr.sys4u.reflection.repo.UserRepository;

public class UserService {
	@CustomAutowired
	private UserRepository userRepository;
	
	public void printAll() {
		Arrays.stream(userRepository.getAll()).forEach(e->System.out.print(e+" "));
	}
}
