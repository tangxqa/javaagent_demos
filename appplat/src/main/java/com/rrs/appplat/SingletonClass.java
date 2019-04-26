package com.rrs.appplat;

public class SingletonClass {
	
	private SingletonClass() {}
	
	
	public static SingletonClass getInstance() {
		return InnerClass.instance;
	}
	
	private static class InnerClass {
		private static SingletonClass instance = new SingletonClass();
	}
	
	
	public String sayHelloWorld() {
		System.out.println("================hello world=============");
		return "hello world";
	}

}
