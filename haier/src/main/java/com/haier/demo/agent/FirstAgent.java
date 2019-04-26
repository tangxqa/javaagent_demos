package com.haier.demo.agent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * 发生"loading"事件时的回调，用于字节码注入
 * @author tangxqa
 */
public class FirstAgent implements ClassFileTransformer {

    /**
     * 将要被注入的类
     */
    public final String[] injectedClasses = {"com.haier.demo.app.AppMain", "com.rrs.appplat.SingletonClass"};

    /**
     * 将要被注入字节码的方法
     */
    public final String methodName = "sayHelloWorld";

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        //控制台打印当前正在加载的类名
        System.out.println("Class loading... : " + className);
        className = className.replace("/", ".");


        //如果当前正在被加载的类，是想要被注入的类，则进行注入动作
        boolean shouldInject = false;
        for (String injectedClass : injectedClasses) {
            if (injectedClass.equals(className)) {
                shouldInject = true;
            }
        }


        //修改当前正在加载的类的字节码
        //修改动作为：
        // 在一个名字为"sayHello"的方法体的开头，注入一句代码：Step into: sayHelloWorld, Amazing...!
        // 在一个名字为"sayHello"的方法体的记为，注入一句代码：Step out: sayHelloWorld, Amazing...!
        if (shouldInject) {
            CtClass ctclass = null;
            try {
                System.out.println(loader);
                ctclass = ClassPool.getDefault().makeClass(new ByteArrayInputStream(classfileBuffer));
                CtMethod ctmethod = ctclass.getDeclaredMethod(methodName);
                ctmethod.insertBefore("System.out.println(\"Step into: " + methodName + ", Amazing...!\");");
                ctmethod.insertAfter("System.out.println(\"Step out: " + methodName + ", Amazing...!\");");

                //将修改后的字节码，重新保存为class文件到本地
                ctclass.writeFile("/Users/tangxqa/develop/code/java/demos/appplat/generated_classes_after_inject");

                //返回修改后的字节码给JVM
                return ctclass.toBytecode();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }

        return null;
    }
}
