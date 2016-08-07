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
package info.dc4j.toolbox.block.generator;

import info.dc4j.toolbox.block.LinearBlock;
import info.dc4j.toolbox.block.connector.DoubleConnector;

// TODO: Auto-generated Javadoc
//y = A, t > t0

/**
 * The Class Step.
 */
public class Step extends LinearBlock {

  /** The a. */
  private double a = 1.0;

  /** The t 0. */
  private double t0 = 1.0;

  /**
   * Instantiates a new step.
   */
  public Step() {
    this("Step");
  }

  /**
   * Instantiates a new step.
   *
   * @param name the name
   */
  public Step(String name) {
    super(name);
    DoubleConnector y = new DoubleConnector(this, "Out");
    setY(y);
  }

  /**
   * Instantiates a new step.
   *
   * @param name the name
   * @param a the a
   * @param t0 the t 0
   */
  public Step(String name, double a, double t0) {
    this(name);
    setA(a);
    setT0(t0);
  }

  /* (non-Javadoc)
   * @see info.dc4j.dc4j_toolbox.block.Block#eval()
   */
  @Override
  protected void eval() {
    double y = 0;
    if (t >= t0) {
      y = a;
    }
    out().setValue(y);
  }

  /**
   * Gets the a.
   *
   * @return the a
   */
  public double getA() {
    return a;
  }

  /**
   * Sets the a.
   *
   * @param a the new a
   */
  public void setA(double a) {
    this.a = a;
  }

  /**
   * Gets the t0.
   *
   * @return the t0
   */
  public double getT0() {
    return t0;
  }

  /**
   * Sets the t0.
   *
   * @param t0 the new t0
   */
  public void setT0(double t0) {
    this.t0 = t0;
  }

}
