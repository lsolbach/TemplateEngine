/*
 * Created on Mar 10, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.IAstNodeType;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.ISymbolTable;
import org.soulspace.template.symbols.impl.ListSymbol;
import org.soulspace.template.symbols.impl.MapSymbol;
import org.soulspace.template.symbols.impl.NumericSymbol;
import org.soulspace.template.symbols.impl.StringSymbol;
import org.soulspace.template.symbols.impl.SymbolType;

/**
 * @author soulman
 *
 */
public abstract class AstNode implements IAstNode {

  private IAstNode parent;
  private IAstNodeType type;
  private ISymbolTable symbolTable;
  private List<IAstNode> childNodes = new ArrayList<IAstNode>();
  private String data;
  private Map<String, IAstNode> methodTable;
  
  /**
   * Constuctor
   */
  public AstNode() {
    super();
  }

  /**
   * Constructor
   * @param type
   * @param parent
   */
  public AstNode(IAstNode parent) {
    super();
    this.parent = parent;
  }
  
  /**
   * 
   * @param type
   */
  public AstNode(AstNodeType type) {
    super();
    this.type = type;
  }

  
  /**
   * 
   * @return Returns the type.
   */
  public IAstNodeType getType() {
    return type;
  }
  
  /**
   * 
   * @param type The type to set.
   */
  public void setType(AstNodeType type) {
    this.type = type;
  }
  
  /**
   * 
   * @return Returns the subNodes.
   */
  public Collection<IAstNode> getChildNodes() {
    return childNodes;
  }
  
  public int getChildCount() {
  	return childNodes.size();
  }
  
  /**
   * 
   * @param subNodes The subNodes to set.
   */
  public void setSubNodes(List<IAstNode> subNodes) {
    this.childNodes = subNodes;
  }
  
  /**
   * 
   * @param astNode
   */
  public void addChildNode(IAstNode astNode) {
  	if(astNode != null) {
      childNodes.add(astNode);
      astNode.setParent(this);  		
  	}
  }
  
  /* (non-Javadoc)
   * @see org.soulspace.templates.ast.IAstNode#getParent()
   */
  public IAstNode getParent() {
    return parent;
  }
  
  /**
   * @param parent The parent to set.
   */
  public void setParent(IAstNode parent) {
    this.parent = parent;
  }
  
  /**
   * @return Returns the symbolTable.
   */
  public ISymbolTable getSymbolTable() {
    if(symbolTable == null && parent != null) {
      return parent.getSymbolTable();
    } else {
      return symbolTable;      
    }
  }
  
  /**
   * @param symbolTable The symbolTable to set.
   */
  public void setSymbolTable(ISymbolTable symbolTable) {
    this.symbolTable = symbolTable;
  }
  
  /* (non-Javadoc)
   * @see org.soulspace.templates.ast.IAstNode#lookupSymbol(java.lang.String)
   */
  public ISymbol lookupSymbol(String name) {
    ISymbol symbol = null;
    if(symbolTable != null) {
      symbol = symbolTable.getSymbol(name);      
    }
    if(symbol == null && parent != null) {
      symbol = parent.lookupSymbol(name);
    }
    return symbol;
  }
  
  public ISymbol lookupSymbolInBlock(String name) {
    ISymbol symbol = null;
    if(symbolTable != null) {
      symbol = symbolTable.getSymbol(name);      
    }
    return symbol;
  }
  
  public ISymbol getSymbol(IAstNode node) {
    ISymbol symbol = null;
    if (node instanceof DereferenceNode) {
      DereferenceNode deref = (DereferenceNode) node;
      symbol = deref.generateSymbol();
    } else if (node instanceof IdentifierNode) {
      symbol = ((IdentifierNode) node).derefSymbol(null, node.getData());
    }
    return symbol;
  }
  
  /**
   * @return Returns the data.
   */
  public String getData() {
    return data;
  }
  
  /**
   * @param data The data to set.
   */
  public void setData(String data) {
    this.data = data;
  }
  
  public String toString() {
    return "AstNode " + getType().getName() + (getData() != null ? ": " + getData() : "");
  }

  /* (non-Javadoc)
   * @see org.soulspace.templates.ast.IAstNode#getChild(int)
   */
  public IAstNode getChild(int index) {
    if(childNodes.size() > index) {
      return (IAstNode) childNodes.get(index);      
    } else {
      return null;
    }
  }

  protected boolean isTrue(String value) {
    if(!value.equals("") && !value.equals("0") && !value.equalsIgnoreCase("false")) {
      return true;
    }
    return false;
  }
  
  public boolean isNumeric(String result) {
    try {
      Double.parseDouble(result);
    } catch (NumberFormatException ex) {
      return false;
    }
    return true;
  }

  public String roundResult(String result) {
    if (!result.equals("") && isNumeric(result)) {
      double doubleResult = Double.parseDouble(result);
      long longResult = (new Double(doubleResult)).longValue();
      if ((doubleResult - longResult) == 0) {
        return String.valueOf(longResult);
      }
    }
    return result;
  }

  /**
   * 
   * @param symbol
   * @return
   */
  String evaluate(ISymbol symbol) {
    String result = "";
    if(symbol == null) {
      //throw new GenerateException("Symbol not found: " + getData());
      System.out.println("Symbol not found: " + getData());
      return "";
    }
    
    if (symbol.getType().equals(SymbolType.STRING)) {
      result = ((StringSymbol) symbol).getData();
    } else if (symbol.getType().equals(SymbolType.NUMERIC)) {
      result = ((NumericSymbol) symbol).getData();
    } else if (symbol.getType().equals(SymbolType.LIST)) {
      // return number of elements in scalar context
      // TODO is this necessary, now we have ":_SIZE"?
      result = String.valueOf(((ListSymbol) symbol).getData().size());
    } else if (symbol.getType().equals(SymbolType.MAP)) {
      // return number of elements in scalar context
      // TODO is this necessary, now we have ":_SIZE"?
      result = String.valueOf(((MapSymbol) symbol).getData().getSymbolCount());
    }

    return result;    
  }
  
  public final void initMethodTable() {
    this.methodTable = new HashMap<String, IAstNode>();
  }
  
  public Map<String, IAstNode> getMethodTable() {
  	if(methodTable == null && parent != null) {
  		return parent.getMethodTable();
  	} else {
  		return methodTable;
  	}
  }
  
  public IAstNode getMethodNode(String methodName) {
  	return getMethodTable().get(methodName);
  }
  
  public void addMethodNode(IAstNode node) {
  	if(node != null && node.getType().equals(AstNodeType.METHOD)) {
    	getMethodTable().put(node.getData(), node);  		
//  	} else {
//  		throw new SyntaxException("Error adding method node");
  	}
  }
}
