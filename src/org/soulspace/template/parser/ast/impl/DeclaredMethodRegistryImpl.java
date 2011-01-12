package org.soulspace.template.parser.ast.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.soulspace.template.exception.SyntaxException;
import org.soulspace.template.parser.ast.MethodNode;


public class DeclaredMethodRegistryImpl {

  private Map<String, MethodNode> methodTable;
  
  // TODO 2 method tables for lookup by name and by signature?!?
  
//  public final void initMethodTable() {
//    this.methodTable = new HashMap<String, MethodNode>();
//  }
//    
//  public MethodNode getMethodBySignature(String signature) {
//  	return methodTable.get(signature);
//  }
//  
//  public List<MethodNode> getMethodsByName() {
//  	List<MethodNode> methods = new ArrayList<MethodNode>();
//  	
//  	return methods;
//  }
  
//  public void addMethod(MethodNode node) {
//  	if(node != null && node.getType().getName().equals("METHOD")) {
//  		MethodNode methodNode = (MethodNode) node;
//  		MethodNode superNode = null;
//  		if((superNode = methodTable.get(methodNode.getSignatureString())) != null) {
//  			// System.out.println("Adding super method to " + methodNode.getSignatureString());
//  			methodNode.setSuperMethod(superNode);
//  		}
//  		// System.out.println("Adding method " + methodNode.getSignatureString());
//  		methodTable.put(methodNode.getSignatureString(), node);
//  	} else {
//  		throw new SyntaxException("Error while trying to add a method node: " + node);
//  	}
//  }
  
}
