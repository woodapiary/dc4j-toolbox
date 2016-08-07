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
// y = k*u/(1+Ts)

/**
 * The Class Integrator.
 */
public class Integrator extends LinearBlock {

  /** The ti. */
  private double ti = 1.0;

  /**
   * Instantiates a new integrator.
   *
   * @param name the name
   * @param u the u
   */
  public Integrator(String name, DoubleConnector u) {
    super(name);
    if (u == null) {
      throw new IllegalArgumentException("null connector");
    }
    DoubleConnector y = new DoubleConnector(this, "Out");
    setConnectorU0Y0(u, y);
  }

  /**
   * Instantiates a new integrator.
   *
   * @param u the u
   */
  public Integrator(DoubleConnector u) {
    this("Integrator", u);
  }

  /**
   * Instantiates a new integrator.
   *
   * @param name the name
   * @param ti the ti
   * @param u the u
   */
  public Integrator(String name, double ti, DoubleConnector u) {
    this(name, u);
    setT(ti);
  }

  /* (non-Javadoc)
   * @see info.dc4j.dc4j_toolbox.block.Block#eval()
   */
  @Override
  protected void eval() {
    double y1 = out().getValue();
    double u = getU0().getValue();
    double y = (u - y1) / ti + y1;
    out().setValue(y);
  }

  /* (non-Javadoc)
   * @see info.dc4j.dc4j_toolbox.block.Block#getT()
   */
  @Override
  public double getT() {
    return ti;
  }

  /**
   * Sets the t.
   *
   * @param ti the new t
   */
  public void setT(double ti) {
    this.ti = ti;
    if (ti <= 0) {
      throw new IllegalArgumentException("T must be over zero");
    }
  }
}
