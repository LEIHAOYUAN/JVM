package com.test.proxy.cglibnew;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
	
	/**
	 * 生成Cglib代理对象
	 * @param cls
	 * @return
	 */
	public Object getProxy(Class cls) {
		//enhancer 增强类对象
		Enhancer enhancer = new Enhancer();
		//设置增强类型
		enhancer.setSuperclass(cls);
		//定义代理逻辑对象为当前对象，要求当前对象实现MethodInterceptor方法
		enhancer.setCallback(this);
		//生成并返回代理对象
		return enhancer.create();
	}
	/**
	 * 代理逻辑方法
	 * @param proxy 代理对象
	 * @param method 方法
	 * @param args 方法参数
	 * @param methodProxy 方法代理
	 * @return 代理逻辑返回
	 * @throws Throwable
	 */
	@Override
	public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println("调用真实对象前");
		Object result = methodProxy.invokeSuper(proxy, args);
		System.out.println("调用真实对象后");
		return result;
	}

}
