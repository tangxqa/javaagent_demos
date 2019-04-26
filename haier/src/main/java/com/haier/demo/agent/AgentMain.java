package com.haier.demo.agent;
 
import java.lang.instrument.Instrumentation;
 
/**
 * Hello world!
 */
public class AgentMain {


    /**
     * javaagent 生效时，打印，发生在main函数之前
     * @param agentOps
     * @param inst
     */
    public static void premain(String agentOps, Instrumentation inst) {
        System.out.println("========= premain 方法执行========");
        System.out.println(agentOps);
        // 添加Transformer
        inst.addTransformer(new FirstAgent());
    }


    /**
     * agent main 生效时，打印，运行态加载
     * @param agentOps
     * @param inst
     */
    public static void agentmain(String agentOps, Instrumentation inst) {
        System.out.println("========= agentmain 方法执行========");
        System.out.println(agentOps);
        // 添加Transformer
        inst.addTransformer(new FirstAgent());
    }



}
