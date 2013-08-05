/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.parser.ast.Signature;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.ValueType;

public class SignatureImpl implements Signature {

	private String methodName;
	private ValueType returnType;
	private List<ValueType> parameterTypes = new ArrayList<ValueType>();
	
	public enum MatchType {NONE, COMPATIBLE, EXACT};
	
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
	
	public MatchType matches(List<Value> paramList) {
		if(paramList.size() != parameterTypes.size()) {
			// can't be any other match type
			return MatchType.NONE;
		}
		MatchType match = MatchType.EXACT;
		for(int i = 0; i < parameterTypes.size(); i++) {
			ValueType t1 = paramList.get(i).getType();
			ValueType t2 = parameterTypes.get(i);
			if(t1 != t2 && (t2.equals(ValueType.STRING) || t2.equals(ValueType.NUMERIC))) {
				match = MatchType.COMPATIBLE;
			} else {
				// can't be any other match type
				return MatchType.NONE;
			}
		}
		return match;
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
