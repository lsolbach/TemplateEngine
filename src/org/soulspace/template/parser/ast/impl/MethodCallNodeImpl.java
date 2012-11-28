package org.soulspace.template.parser.ast.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.MethodNode;
import org.soulspace.template.value.MethodValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.ValueType;
import org.soulspace.template.value.impl.StringValueImpl;

public class MethodCallNodeImpl extends AbstractAstNode {

	public MethodCallNodeImpl() {
		super();
		setType(AstNodeType.METHOD_CALL);
	}

	public String getMethodName() {
		return getData();
	}

	public Value generateValue(Environment environment) {
		setEnvironment(environment);
		try {
			if (getMethodName() != null && getMethodName().equals("super")) {
				return generateSuperCall();
			} else {
				return generateMethodCall();
			}
		} catch (NullPointerException e) {
			throw new GenerateException(
					"Error in method call to "
							+ getMethodName() + "()! Template " + getTemplate() + ", line " + getLine(), e);
		} catch (GenerateException ge) {
			throw new GenerateException(ge.getMessage() + "\nCalled from " + getMethodName() + "()! Template " + getTemplate() + ", line " + getLine(), ge.getCause());
		}
	}

	public List<Value> evaluateArgList(ArgListNodeImpl argList) {
		List<Value> valueList = new ArrayList<Value>();
		for(int i = 0; i < argList.getChildCount(); i++) {
			AstNode argNode = argList.getChild(i);
			Value value = argNode.generateValue(getEnvironment());
			if(value == null) {
				throw new GenerateException(
						"Null value in argument " + argNode + " with index " + i + " of method call to "
						+ getMethodName() + "()! Template " + getTemplate() + ", line " + getLine());
			}
			valueList.add(value);
		}
		return valueList;
	}
	
	public Value generateMethodCall() {
		MethodNode methodNode;
		ArgListNodeImpl argList = (ArgListNodeImpl) getChild(0);
		List<Value> valueList = evaluateArgList(argList);
		
		Value value = lookupSymbol(getMethodName());
		Environment environment = getEnvironment();
		
		if(value != null && value.getType().equals(ValueType.METHOD)) {
			MethodValue methodValue = (MethodValue) value;
			// lookup method by the data of the value
			if(methodValue.getMethodNode() != null) {
				methodNode = methodValue.getMethodNode();
				environment = methodValue.getEnvironment();
//				System.out.println("Call to method " + getMethodName() + " by method node in method value " + methodValue.toString());
//				System.out.println("Environment for call to method " + getMethodName());
//				System.out.println(environment.printEnvironment());
			} else {
				methodNode = getMethodNode(this, methodValue.getData(), valueList);
				environment = getEnvironment();
			}
		} else {
			methodNode = getMethodNode(this, getMethodName(), valueList);
			environment = getEnvironment();
		}

		if(methodNode != null) {
			// create symbol table for arguments
//			SymbolTable argTable = methodNode.createSymbolTable(valueList);

			// generate from the method
			return ((MethodNodeImpl) methodNode).generateSymbol(
					environment, methodNode, valueList);		
		} else {
			throw new GenerateException(
					"No method node found for signature "
							+ getSignatureString(valueList) + "! Template " + getTemplate() + ", line " + getLine());
		}
	}
	
	public Value generateSuperCall() {
		AstNode node = getParent();
		boolean found = false;
		while (!found) {
			if (node != null) {
				if (node instanceof MethodNodeImpl) {
					MethodNodeImpl mNode = (MethodNodeImpl) node;
					if (mNode.getSuperMethod() == null) {
						throw new GenerateException(
								"super() call, but there is no super method! Template " + getTemplate() + ", line " + getLine());
					}
					// TODO call method outside of loop to make the exits from this method more clear
					ArgListNodeImpl argList = (ArgListNodeImpl) getChild(0);
					List<Value> valueList = evaluateArgList(argList);
					return mNode.callSuperMethod(valueList);
				}
				node = node.getParent();
			} else {
				throw new GenerateException(
						"super() call outside a method body! Template " + getTemplate() + ", line " + getLine());
			}
		}
		return new StringValueImpl("");
	}
	
	String getSignatureString(List<Value> valueList) {
		StringBuilder sb = new StringBuilder();
		sb.append(getData());
		sb.append("(");
		String sep = "";
		for(Value v : valueList) {
			if(v == null) {
				throw new GenerateException(
						"Null value in argument list of method call to "
						+ getMethodName() + "()! Template " + getTemplate() + ", line " + getLine());
			}
			sb.append(sep);
			if(v.getType() != null) {
				sb.append(v.getType());
			} else {
				sb.append("UNKNOWN");
			}
			sep = ", ";
		}
		sb.append(")");
		return sb.toString();
	}

}
