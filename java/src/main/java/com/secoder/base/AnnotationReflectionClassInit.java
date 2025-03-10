package com.secoder.base;

/**
 * @file com.secoder.base.AnnotationReflectionClassInit
 * @author sf
 * @date 2020/9/1 11:56 上午
 * @description 什么时候h会发生类的初始化
 */

public class AnnotationReflectionClassInit {

public static void main(String[] args) throws ClassNotFoundException {
	// main 方法
	
	// 1、类的主动引用
//		com.secoder.base.SonClass sonClass = new com.secoder.base.SonClass();
	
	// 2、反射也会产生主动引用
//		Class.forName("com.crisimple.com.secoder.base.com.secoder.base.SonClass");
	
	// 3、不会产生类的调用方法
//		System.out.println(com.secoder.base.SonClass.b);
	
	// 4、数组不会产生类的调用方法
//		com.secoder.base.SonClass[] array = new com.secoder.base.SonClass[5];
	
	// 5、静态变量不会产生类的调用方法
	System.out.println(SonClass.M);
}
}

class FatherClass {
static int b = 2;

static {
	System.out.println("父类静态代码块被加载");
}
}

class SonClass extends FatherClass {
static {
	System.out.println("子类静态代码块被加载");
	int m = 100;
}

static int m = 300;
static final int M = 1;
}