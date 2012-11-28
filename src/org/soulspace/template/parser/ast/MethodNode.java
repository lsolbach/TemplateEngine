package org.soulspace.template.parser.ast;

import java.util.List;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.value.SymbolTable;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.ValueType;

public interface MethodNode extends AstNode {

	String getSignatureString();
	Signature getSignature();
	String getMethodName();
	
	void setReturnType(String returnType);
	void setReturnType(ValueType returnType);

	MethodNode getSuperMethod();
	void setSuperMethod(MethodNode node);
	Value callSuperMethod(List<Value> valueList);
	
	SymbolTable createSymbolTable(List<Value> valueList);
	Value generateSymbol(Environment environment, AstNode returnNode, List<Value> valueList);
}
