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
import info.dc4j.toolbox.model.rw.BoolData;

// TODO: Auto-generated Javadoc
/**
 * The Class BoolConnector.
 */
public class BoolConnector extends Connector {

  /** The value. */
  private boolean value;

  /**
   * Instantiates a new bool connector.
   *
   * @param source the source
   * @param name the name
   */
  public BoolConnector(Block source, String name) {
    super(source, name);
  }

  /**
   * Instantiates a new bool connector.
   *
   * @param name the name
   */
  public BoolConnector(String name) {
    super(null, name);
  }

  /**
   * Instantiates a new bool connector.
   */
  public BoolConnector() {
    super(null, null);
  }

  /**
   * Gets the value.
   *
   * @return the value
   */
  public boolean getValue() {
    if (chain != null) {
      return getChain().getValue();
    }
    return value;
  }

  /**
   * Sets the value.
   *
   * @param value the new value
   */
  public void setValue(boolean value) {
    this.value = value;
  }

  /**
   * Gets the chain.
   *
   * @return the chain
   */
  public BoolConnector getChain() {
    return (BoolConnector) chain;
  }

  /**
   * Gets the data.
   *
   * @return the data
   */
  public BoolData getData() {
    return new BoolData(getName(), value);
  }

  /* (non-Javadoc)
   * @see info.dc4j.dc4j_toolbox.block.connector.Connector#toString()
   */
  @Override
  public String toString() {
    return super.toString() + " " + getValue();
  }

}
