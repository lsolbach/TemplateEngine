/*
 * Created on Mar 10, 2005
 */
package org.soulspace.template.parser.ast;


/**
 * @author soulman
 *
 */
public class AstNodeType implements IAstNodeType {

  public static final AstNodeType ROOT          = new AstNodeType("ROOT");
  public static final AstNodeType TERM          = new AstNodeType("TERM");
  public static final AstNodeType IF            = new AstNodeType("IF");
  public static final AstNodeType XML_DECL      = new AstNodeType("XML_DECL");
  public static final AstNodeType WHILE         = new AstNodeType("WHILE");
  public static final AstNodeType FOREACH       = new AstNodeType("FOREACH");
  public static final AstNodeType DECLARATION   = new AstNodeType("DECLARATION");
  public static final AstNodeType IDENTIFIER    = new AstNodeType("IDENTIFIER");
  public static final AstNodeType UNARY_EXPR    = new AstNodeType("UNARY");
  public static final AstNodeType DEREF_EXPR    = new AstNodeType("DEREF_EXPR");
  public static final AstNodeType MULT          = new AstNodeType("MULT");
  public static final AstNodeType DIV           = new AstNodeType("DIV");
  public static final AstNodeType IDIV          = new AstNodeType("IDIV");
  public static final AstNodeType MODULO        = new AstNodeType("MODULO");
  public static final AstNodeType PLUS          = new AstNodeType("PLUS");
  public static final AstNodeType MINUS         = new AstNodeType("MINUS");
  public static final AstNodeType GREATER       = new AstNodeType("GREATER");
  public static final AstNodeType GREATER_EQUAL = new AstNodeType("GREATER_EQUAL");
  public static final AstNodeType LESS          = new AstNodeType("LESS");
  public static final AstNodeType LESS_EQUAL    = new AstNodeType("LESS_EQUAL");
  public static final AstNodeType EQUAL         = new AstNodeType("EQUAL");
  public static final AstNodeType NOT_EQUAL     = new AstNodeType("NOT_EQUAL");
  public static final AstNodeType STRING_EQUAL  = new AstNodeType("STRING_EQUAL");
  public static final AstNodeType STRING_NOT_EQUAL = new AstNodeType("STRING_NOT_EQUAL");
  public static final AstNodeType LOGICAL_AND   = new AstNodeType("LOGICAL_AND");
  public static final AstNodeType LOGICAL_OR    = new AstNodeType("LOGICAL_OR");
  public static final AstNodeType LOGICAL_NOT   = new AstNodeType("LOGICAL_NOT");
  public static final AstNodeType ASSIGN        = new AstNodeType("ASSIGN");
  public static final AstNodeType NUMERIC_CONST = new AstNodeType("NUMERIC_CONST");
  public static final AstNodeType STRING_CONST  = new AstNodeType("STRING_CONST");
  public static final AstNodeType LOOKUP_EXPR   = new AstNodeType("LOOKUP_EXPR");
  public static final AstNodeType METHOD        = new AstNodeType("METHOD");
  public static final AstNodeType METHOD_CALL   = new AstNodeType("METHOD_CALL");
  public static final AstNodeType TYPE_METHOD_CALL = new AstNodeType("TYPE_METHOD_CALL");
  public static final AstNodeType ARG_LIST      = new AstNodeType("ARG_LIST");
  public static final AstNodeType PARAM_LIST    = new AstNodeType("PARAM_LIST");
  
  public static final AstNodeType TEXT    = new AstNodeType("TEXT");

  private final String name;
  
  /**
   * 
   */
  private AstNodeType(String name) {
    this.name = name;
  }

  /**
   * @return Returns the type.
   */
  public String getName() {
    return name;
  }
  
  public String toString() {
    return "AstNodeType: " + name;
  }
}
