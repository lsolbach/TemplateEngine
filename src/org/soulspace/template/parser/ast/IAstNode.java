/*
 * Created on Mar 10, 2005
 */
package org.soulspace.template.parser.ast;

import java.util.Collection;
import java.util.Map;

import org.soulspace.template.value.INumericValue;
import org.soulspace.template.value.IStringValue;
import org.soulspace.template.value.ISymbolTable;
import org.soulspace.template.value.IValue;

/**
 * @author soulman
 *
 */
public interface IAstNode {
  IAstNodeType getType();
  String getTemplate();
  int getLine();
  
  IAstNode getParent();
  void setParent(IAstNode parent);

  Collection<IAstNode> getChildNodes();
  int getChildCount();
  IAstNode getChild(int index);
  void addChildNode(IAstNode node);

  IValue lookupSymbol(String name);
  IValue getSymbol(IAstNode node);
  ISymbolTable getSymbolTable();
  void setSymbolTable(ISymbolTable symbolTable);

  String getData();
  void setData(String data);

  Map<String, IMethodNode> getMethodTable();
  IMethodNode getMethodNode(String methodName);
  void addMethodNode(IMethodNode node);

//  String generate();
  IValue generateSymbol();
	INumericValue asNumeric(IValue symbol);
	IStringValue asString(IValue symbol);

}
