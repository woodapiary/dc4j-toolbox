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
import info.dc4j.toolbox.model.rw.DoubleData;

// TODO: Auto-generated Javadoc
/**
 * The Class DoubleConnector.
 */
public class DoubleConnector extends Connector {

  /** The value. */
  private double value;

  /**
   * Instantiates a new double connector.
   *
   * @param source the source
   * @param name the name
   */
  public DoubleConnector(Block source, String name) {
    super(source, name);
  }

  /**
   * Instantiates a new double connector.
   *
   * @param name the name
   */
  public DoubleConnector(String name) {
    super(null, name);
  }

  /**
   * Instantiates a new double connector.
   */
  public DoubleConnector() {
    super(null, null);
  }

  /**
   * Gets the value.
   *
   * @return the value
   */
  public double getValue() {
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
  public void setValue(double value) {
    this.value = value;
  }

  /**
   * Gets the chain.
   *
   * @return the chain
   */
  public DoubleConnector getChain() {
    return (DoubleConnector) chain;
  }

  /**
   * Gets the data.
   *
   * @return the data
   */
  public DoubleData getData() {
    return new DoubleData(getName(), value);
  }

  /* (non-Javadoc)
   * @see info.dc4j.dc4j_toolbox.block.connector.Connector#toString()
   */
  @Override
  public String toString() {
    return super.toString() + ", value=" + getValue();
  }

}
