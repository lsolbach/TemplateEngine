/*
 * Created on Nov 14, 2005
 */
package fittest.tokenizer;
 
import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.tokenizer.IToken;
import org.soulspace.template.tokenizer.ITokenList;
import org.soulspace.template.tokenizer.Tokenizer;
import org.soulspace.template.tokenizer.impl.TokenizerImpl;

import fit.RowFixture;

public class Tokenlist extends RowFixture {

  public Tokenlist() {
    super();
  }

  @Override
  public Object[] query() throws Exception {
    Tokenizer tokenizer = new TokenizerImpl();
    List<TokenFit> list = new ArrayList<TokenFit>();
    ITokenList tl = tokenizer.tokenize(args[0]);
    int i = 0;
    while(tl.hasToken()) {
      IToken t = tl.getToken();
      TokenFit tf = new TokenFit(i, t.getType().toString(), t.getData());
      list.add(tf);
      tl.getNextToken();
      i++;
    }
    return list.toArray();
  }
  
  @Override
  public Class getTargetClass() {
    return TokenFit.class;
  }
  
}
