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
package info.dc4j.toolbox.block;

import info.dc4j.toolbox.block.connector.DoubleConnector;

public abstract class LinearBlock extends Block {

  /**
   * Instantiates a new linear block.
   *
   * @param name the name
   */
  public LinearBlock(String name) {
    super(name);
    // TODO Auto-generated constructor stub
  }

  /**
   * Gets the u0.
   *
   * @return the u0
   */
  public DoubleConnector getU0() {
    return getU(0);
  }

  /**
   * Gets the u1.
   *
   * @return the u1
   */
  public DoubleConnector getU1() {
    return getU(1);
  }

  /**
   * Out.
   *
   * @return the double connector
   */
  public DoubleConnector out() {
    return getY(0);
  }

  /**
   * Sets the connector U 0 Y 0.
   *
   * @param u the u
   * @param y the y
   */
  public void setConnectorU0Y0(DoubleConnector u, DoubleConnector y) {
    listU.add(u);
    listY.add(y);
  }

  /**
   * Sets the connector U 0 U 1 Y 0.
   *
   * @param u1 the u 1
   * @param u2 the u 2
   * @param y the y
   */
  public void setConnectorU0U1Y0(DoubleConnector u1, DoubleConnector u2, DoubleConnector y) {
    listU.add(u1);
    listU.add(u2);
    listY.add(y);
  }

}
