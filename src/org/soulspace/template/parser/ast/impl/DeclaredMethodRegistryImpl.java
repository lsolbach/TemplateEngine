package org.soulspace.template.parser.ast.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.soulspace.template.exception.SyntaxException;
import org.soulspace.template.parser.ast.IMethodNode;


public class DeclaredMethodRegistryImpl {

  private Map<String, IMethodNode> methodTable;
  
  // TODO 2 method tables for lookup by name and by signature?!?
  
  public final void initMethodTable() {
    this.methodTable = new HashMap<String, IMethodNode>();
  }
    
  public IMethodNode getMethodBySignature(String signature) {
  	return methodTable.get(signature);
  }
  
  public List<IMethodNode> getMethodsByName() {
  	List<IMethodNode> methods = new ArrayList<IMethodNode>();
  	
  	return methods;
  }
  
  public void addMethod(IMethodNode node) {
  	if(node != null && node.getType().getName().equals("METHOD")) {
  		IMethodNode methodNode = (IMethodNode) node;
  		IMethodNode superNode = null;
  		if((superNode = methodTable.get(methodNode.getSignatureString())) != null) {
  			// System.out.println("Adding super method to " + methodNode.getSignatureString());
  			methodNode.setSuperMethod(superNode);
  		}
  		// System.out.println("Adding method " + methodNode.getSignatureString());
  		methodTable.put(methodNode.getSignatureString(), node);
  	} else {
  		throw new SyntaxException("Error while trying to add a method node: " + node);
  	}
  }
  
}
