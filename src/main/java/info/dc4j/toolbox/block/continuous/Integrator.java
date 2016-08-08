/**
 * The MIT License
 * Copyright (c) 2002-2016 dc4j.info
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
package info.dc4j.toolbox.block.continuous;

import info.dc4j.toolbox.block.LinearBlock;
import info.dc4j.toolbox.block.connector.DoubleConnector;

public class Integrator extends LinearBlock {

  private double ti = 1.0;

  public Integrator(String name, DoubleConnector u) {
    super(name);
    if (u == null) {
      throw new IllegalArgumentException("null connector");
    }
    DoubleConnector y = new DoubleConnector(this, "Out");
    setConnectorU0Y0(u, y);
  }

  public Integrator(DoubleConnector u) {
    this("Integrator", u);
  }

  public Integrator(String name, double ti, DoubleConnector u) {
    this(name, u);
    setT(ti);
  }

  @Override
  protected void eval() {
    double y1 = out().getValue();
    double u = getU0().getValue();
    double y = u * dt / ti + y1;
    out().setValue(y);
  }

  @Override
  public double getT() {
    return ti;
  }

  public void setT(double ti) {
    this.ti = ti;
    if (ti <= 0) {
      throw new IllegalArgumentException("T must be over zero");
    }
  }
}
