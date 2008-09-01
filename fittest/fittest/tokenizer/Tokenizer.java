/*
 * Created on Nov 14, 2005
 */
package fittest.tokenizer;
 
import org.soulspace.template.exception.UnknownTokenException;
import org.soulspace.template.tokenizer.ITokenList;
import org.soulspace.template.tokenizer.impl.TokenizerImpl;

import fit.ColumnFixture;

public class Tokenizer extends ColumnFixture {

  org.soulspace.template.tokenizer.Tokenizer TOKENIZER = new TokenizerImpl();
  ITokenList TOKEN_LIST = TOKENIZER.createTokenList();
  
  public String input;
  
  public Tokenizer() {
    super();
  }

  public boolean tokenizeInput() {
    try {
      TOKEN_LIST = TOKENIZER.tokenize(input);
    } catch (UnknownTokenException e) {
      e.printStackTrace();
      return false;
    }
    if(TOKEN_LIST.size() > 0) {
      return true;
    }
    return false;
  }
  
}
