package com.haier.demo.attach;

import java.io.IOException;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

public class AttachMain {

	
	public static void main(String args[]) {
		try {
			attachTargetVm("5886", "/Users/tangxqa/develop/code/java/demos/haier/target/haier-1.0-SNAPSHOT.jar");
			
		} catch (AttachNotSupportedException | IOException | AgentLoadException | AgentInitializationException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void attachTargetVm(String pid, String agentPath) throws AttachNotSupportedException, IOException, AgentLoadException, AgentInitializationException {
		
		VirtualMachine vm = VirtualMachine.attach(pid);
		vm.loadAgent(agentPath, null);

	}
	
}




 

