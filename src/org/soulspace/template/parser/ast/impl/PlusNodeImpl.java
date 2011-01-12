/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.Iterator;

import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.value.ListValue;
import org.soulspace.template.value.MapValue;
import org.soulspace.template.value.NumericValue;
import org.soulspace.template.value.StringValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.ListValueImpl;
import org.soulspace.template.value.impl.MapValueImpl;
import org.soulspace.template.value.impl.NumericValueImpl;
import org.soulspace.template.value.impl.StringValueImpl;

public class PlusNodeImpl extends AbstractAstNode {

	/**
   * 
   */
	public PlusNodeImpl() {
		this(null);
	}

	/**
	 * @param parent
	 */
	public PlusNodeImpl(AstNode parent) {
		super(parent);
		setType(AstNodeType.PLUS);
	}

	public Value generateValue() {
		Value symbol = getChild(0).generateValue();
		if (symbol instanceof StringValue) {
			StringValueImpl string = new StringValueImpl("");
			Iterator<AstNode> it = getChildNodes().iterator();
			while (it.hasNext()) {
				StringValue next = asString(it.next().generateValue());
				string = string.add(next);
			}
			return string;
		} else if (symbol instanceof NumericValue) {
			NumericValueImpl numeric = new NumericValueImpl(0.0);
			Iterator<AstNode> it = getChildNodes().iterator();
			while (it.hasNext()) {
				numeric = numeric.add(asNumeric(it.next().generateValue()));
			}
			return numeric;
		} else if (symbol instanceof ListValue) {
			ListValueImpl list = new ListValueImpl();
			Iterator<AstNode> it = getChildNodes().iterator();
			while (it.hasNext()) {
				Value s = it.next().generateValue();
				if (s instanceof ListValue) {
					list.getData().addAll(((ListValue) s).getData());
				}
			}
			return list;
		} else if (symbol instanceof MapValue) {
			MapValueImpl map = new MapValueImpl();
			Iterator<AstNode> it = getChildNodes().iterator();
			while (it.hasNext()) {
				Value s = it.next().generateValue();
				if (s instanceof MapValue) {
					map.getData().addSymbolTable(((MapValue) s).getData());
				}
			}
			return map;
		} else {
			throw new GenerateException("Unknown type! Template " + getTemplate() + ", line " + getLine());
		}
	}
}