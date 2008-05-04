/*
 * Created on Mar 10, 2005
 */
package org.soulspace.template.parser.ast;

import java.util.Collection;
import java.util.Map;

import org.soulspace.template.parser.GenerateException;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.ISymbolTable;

/**
 * @author soulman
 *
 */
public interface IAstNode {
  IAstNodeType getType();
  IAstNode getParent();
  void setParent(IAstNode parent);

  Collection<IAstNode> getChildNodes();
  int getChildCount();
  IAstNode getChild(int index);
  void addChildNode(IAstNode node);

  ISymbol lookupSymbol(String name);
  ISymbol getSymbol(IAstNode node);
  ISymbolTable getSymbolTable();
  void setSymbolTable(ISymbolTable symbolTable);

  String getData();
  void setData(String data);

  Map<String, IAstNode> getMethodTable();
  IAstNode getMethodNode(String methodName);
  void addMethodNode(IAstNode node);

//  String generate();
  ISymbol generateSymbol();
}
