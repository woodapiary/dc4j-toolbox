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

package info.dc4j.toolbox.block.continuous;

import info.dc4j.toolbox.block.LinearBlock;
import info.dc4j.toolbox.block.connector.DoubleConnector;

public class PT1 extends LinearBlock {

  private double k = 1.0;
  private double tf = 1.0;

  public PT1(String name, DoubleConnector u) {
    super(name);
    if (u == null) {
      throw new IllegalArgumentException("null connector");
    }
    DoubleConnector y = new DoubleConnector(this, "Out");
    setConnectorU0Y0(u, y);
  }

  public PT1(DoubleConnector u) {
    this("PT1", u);
  }

  public PT1(String name, double k, double tf, DoubleConnector u) {
    this(name, u);
    setK(k);
    setTf(tf);
  }

  @Override
  protected void eval() {
    double ku = k / (1 + tf / dt);
    double ky1 = 1 / (1 + dt / tf);
    double y1 = out().getValue();
    double u = getU0().getValue();
    double y = ky1 * y1 + ku * u;
    out().setValue(y);
  }

  public double getK() {
    return k;
  }

  public void setK(double k) {
    this.k = k;
  }

  @Override
  public double getT() {
    return tf;
  }

  public void setTf(double tf) {
    this.tf = tf;
    if (tf <= 0) {
      throw new IllegalArgumentException("T must be over zero");
    }
  }
}
