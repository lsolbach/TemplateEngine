package org.soulspace.template.parser.ast;

import org.soulspace.template.value.ISymbolTable;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.ValueType;

public interface IMethodNode extends IAstNode {

	String getSignatureString();
	ISignature getSignature();
	
	void setReturnType(String returnType);
	void setReturnType(ValueType returnType);

	IMethodNode getSuperMethod();
	void setSuperMethod(IMethodNode node);
	IValue callSuperMethod();
	
	IValue generateSymbol(IAstNode returnNode, ISymbolTable symbolTable);
}
