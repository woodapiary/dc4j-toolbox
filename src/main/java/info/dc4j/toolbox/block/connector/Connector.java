/**
 * The MIT License
 * Copyright (c) 2002 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
/*
 * 
 */
package info.dc4j.toolbox.block.connector;

import info.dc4j.toolbox.block.Block;
import info.dc4j.toolbox.block.helper.SequenceId;

// TODO: Auto-generated Javadoc
/**
 * The Class Connector.
 */
public abstract class Connector {

  /** The id. */
  private final int id;
  
  /** The name. */
  private final String name;
  
  /** The source. */
  private final Block source;
  
  /** The chain. */
  protected Connector chain;

  /**
   * Instantiates a new connector.
   *
   * @param source the source
   * @param name the name
   */
  public Connector(Block source, String name) {
    this.id = SequenceId.getConnId();
    this.name = name;
    this.source = source;
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  public int getId() {
    if (chain != null) {
      return chain.getId();
    }
    return id;
  }

  /**
   * Gets the simple name.
   *
   * @return the simple name
   */
  public String getSimpleName() {
    if (chain != null) {
      return chain.getSimpleName();
    }
    return name;
  }

  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    if (chain != null) {
      return chain.getName();
    }
    if (source != null) {
      return source.getName() + name;
    }
    return name;
  }

  /**
   * Sets the chain.
   *
   * @param chain the new chain
   */
  public void setChain(Connector chain) {
    this.chain = chain;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "\n" + this.getClass().getSimpleName() + " - " + getName() + ": " + " id=" + id;
  }

}
