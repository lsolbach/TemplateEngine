package org.soulspace.template.parser.ast.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.parser.ast.ISignature;
import org.soulspace.template.value.impl.ValueType;

public class SignatureImpl implements ISignature {

	private String methodName;
	private ValueType returnType;
	private List<ValueType> parameterTypes = new ArrayList<ValueType>();
	
	public SignatureImpl(String methodName, ValueType returnType, List<ValueType> parameterTypes) {
		this.methodName = methodName;
		this.returnType = returnType;
		if(parameterTypes != null) {
			this.parameterTypes = parameterTypes;			
		}
	}
	
	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}
	
	/**
	 * @return the returnType
	 */
	public ValueType getReturnType() {
		return returnType;
	}
	
	/**
	 * @return the parameterTypes
	 */
	public List<ValueType> getParameterTypes() {
		return parameterTypes;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getMethodName());
		sb.append("(");
		// TODO add param type string, if type inference works in method call
		sb.append(parameterTypes.size());
		sb.append(")");
		return sb.toString();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((methodName == null) ? 0 : methodName.hashCode());
		result = prime * result
				+ ((parameterTypes == null) ? 0 : parameterTypes.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final SignatureImpl other = (SignatureImpl) obj;
		if (methodName == null) {
			if (other.methodName != null)
				return false;
		} else if (!methodName.equals(other.methodName))
			return false;
		if (parameterTypes == null) {
			if (other.parameterTypes != null)
				return false;
		} else if (!parameterTypes.equals(other.parameterTypes))
			return false;
		return true;
	}
	
}
