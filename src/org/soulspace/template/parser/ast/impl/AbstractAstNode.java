/*
 * Created on Mar 10, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.exception.SyntaxException;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.IAstNodeType;
import org.soulspace.template.parser.ast.IMethodNode;
import org.soulspace.template.value.IListValue;
import org.soulspace.template.value.IMapValue;
import org.soulspace.template.value.INumericValue;
import org.soulspace.template.value.IStringValue;
import org.soulspace.template.value.ISymbolTable;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.NumericValue;
import org.soulspace.template.value.impl.StringValue;
import org.soulspace.template.value.impl.ValueType;

/**
 * @author soulman
 *
 */
public abstract class AbstractAstNode implements IAstNode {

  private IAstNode parent;
  private IAstNodeType type;
  private String template = "";
  private int line = 0;
  private ISymbolTable symbolTable;
  private List<IAstNode> childNodes = new ArrayList<IAstNode>();
  private String data;
  private Map<String, IMethodNode> methodTable;
  
  /**
   * Constuctor
   */
  public AbstractAstNode() {
    super();
  }

  /**
   * Constructor
   * @param type
   * @param parent
   */
  public AbstractAstNode(IAstNode parent) {
    super();
    this.parent = parent;
  }
  
  /**
   * 
   * @param type
   */
  public AbstractAstNode(AstNodeType type) {
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
	 * @return the template
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}

	/**
	 * @return the line
	 */
	public int getLine() {
		return line;
	}

	/**
	 * @param line the line to set
	 */
	public void setLine(int line) {
		this.line = line;
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
  public IValue lookupSymbol(String name) {
    IValue symbol = null;
    if(symbolTable != null) {
      symbol = symbolTable.getSymbol(name);      
    }
    if(symbol == null && parent != null) {
      symbol = parent.lookupSymbol(name);
    }
    return symbol;
  }
  
  public IValue lookupSymbolInBlock(String name) {
    IValue symbol = null;
    if(symbolTable != null) {
      symbol = symbolTable.getSymbol(name);      
    }
    return symbol;
  }
  
  public IValue getSymbol(IAstNode node) {
    IValue symbol = null;
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
  	StringBuilder sb = new StringBuilder(32);
  	sb.append("AstNode[");
  	sb.append("Type=" + getType().getName() + ",");
  	sb.append("Template=" + getTemplate() + ",");
  	sb.append("Line=" + getLine());
  	if (getData() != null) {
    	sb.append(",Data=" + getData());			
		}
  	sb.append("]");
  	return sb.toString();
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

	public NumericValue asNumeric(IValue symbol) {
		if(symbol == null) {
			return new NumericValue(0);
		}
		if(symbol instanceof INumericValue) {
			return (NumericValue) symbol;
		} else if(symbol instanceof IStringValue) {
			return new NumericValue(((IStringValue) symbol).getData());			
		} else if(symbol instanceof IListValue) {
			return new NumericValue(((IListValue) symbol).getData().size());
		} else if(symbol instanceof IMapValue) {
			return new NumericValue(((IMapValue) symbol).getData().getSymbolCount());
		} else {
			throw new GenerateException("Unknown type: " + symbol.getClass().getSimpleName());
		}
	}

	public StringValue asString(IValue symbol) {
		if(symbol == null) {
			return new StringValue("");
		}
		if(symbol instanceof IStringValue) {
			return (StringValue) symbol;
		} else if(symbol instanceof INumericValue) {
			return new StringValue(((IStringValue) symbol).getData());			
		} else if(symbol instanceof IListValue) {
			return new StringValue(String.valueOf(((IListValue) symbol).getData().size()));
		} else if(symbol instanceof IMapValue) {
			return new StringValue(String.valueOf(((IMapValue) symbol).getData().getSymbolCount()));
		} else {
			throw new GenerateException("Unknown type: " + symbol.getClass().getSimpleName());
		}
	}

	/**
   * 
   * @param symbol
   * @return
   */
  String evaluate(IValue symbol) {
    String result = "";
    if(symbol == null) {
      //throw new GenerateException("Symbol not found: " + getData());
      System.out.println("Symbol not found: " + getData());
      return "";
    }
    
    if (symbol.getType().equals(ValueType.STRING)) {
      result = ((IStringValue) symbol).getData();
    } else if (symbol.getType().equals(ValueType.NUMERIC)) {
      result = ((INumericValue) symbol).getData();
    } else if (symbol.getType().equals(ValueType.LIST)) {
      // return number of elements in scalar context
      // TODO is this necessary, now we have ":_SIZE"?
      result = String.valueOf(((IListValue) symbol).getData().size());
    } else if (symbol.getType().equals(ValueType.MAP)) {
      // return number of elements in scalar context
      // TODO is this necessary, now we have ":_SIZE"?
      result = String.valueOf(((IMapValue) symbol).getData().getSymbolCount());
    }
    return result;    
  }
  
  public final void initMethodTable() {
    this.methodTable = new HashMap<String, IMethodNode>();
  }
  
  public Map<String, IMethodNode> getMethodTable() {
  	if(methodTable == null && parent != null) {
  		return parent.getMethodTable();
  	} else {
  		return methodTable;
  	}
  }
  
  public IMethodNode getMethodNode(String signature) {
  	// TODO implement a lookup with signature compatibility and best match strategy
  	return getMethodTable().get(signature);
  }
  
  public void addMethodNode(IMethodNode node) {
  	if(node != null && node.getType().equals(AstNodeType.METHOD)) {
  		IMethodNode superNode = null;
  		// FIXME use getSignature() instead of getData()
  		if((superNode = getMethodTable().get(node.getSignatureString())) != null) {
  			node.setSuperMethod(superNode);
  		}
  		getMethodTable().put(node.getSignatureString(), node);  		
  	} else {
  		throw new SyntaxException("Error while trying to add a method node: " + node);
  	}
  }
}
