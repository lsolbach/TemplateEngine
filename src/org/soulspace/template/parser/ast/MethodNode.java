package org.soulspace.template.parser.ast;

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
	Value callSuperMethod();
	
	Value generateSymbol(AstNode returnNode, SymbolTable symbolTable);
}
