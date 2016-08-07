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
package info.dc4j.toolbox.block.linear;

import info.dc4j.toolbox.block.LinearBlock;
import info.dc4j.toolbox.block.connector.DoubleConnector;

// TODO: Auto-generated Javadoc
//y = u1 - u2

/**
 * The Class Loopback.
 */
public class Loopback extends LinearBlock {

  /**
   * Instantiates a new loopback.
   *
   * @param name the name
   * @param u1 the u 1
   * @param u2 the u 2
   */
  public Loopback(String name, DoubleConnector u1, DoubleConnector u2) {
    super(name);
    if (u1 == null) {
      throw new IllegalArgumentException("null connector");
    }
    if (u2 == null) {
      throw new IllegalArgumentException("null connector");
    }
    DoubleConnector y = new DoubleConnector(this, "Out");
    setConnectorU0U1Y0(u1, u2, y);
  }

  /**
   * Instantiates a new loopback.
   *
   * @param u1 the u 1
   * @param u2 the u 2
   */
  public Loopback(DoubleConnector u1, DoubleConnector u2) {
    this("Loopback", u1, u2);
  }

  /* (non-Javadoc)
   * @see info.dc4j.dc4j_toolbox.block.Block#eval()
   */
  @Override
  protected void eval() {
    double u1 = getU0().getValue();
    double u2 = getU1().getValue();
    double y = u1 - u2;
    out().setValue(y);
  }

}
