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
package info.dc4j.toolbox.block.source;

import info.dc4j.toolbox.block.LinearBlock;
import info.dc4j.toolbox.block.connector.DoubleConnector;

public class Sin extends LinearBlock {

  private double a = 1.0;
  private double w = 1.0;

  public Sin() {
    this("Sin");
  }

  public Sin(String name) {
    super(name);
    DoubleConnector y = new DoubleConnector(this, "Out");
    setY(y);
  }

  public Sin(String name, double a, double w) {
    this(name);
    setA(a);
    setW(w);
  }

  @Override
  protected void eval() {
    double y = a * Math.sin(t * w);
    out().setValue(y);
  }

  public double getA() {
    return a;
  }

  public void setA(double a) {
    this.a = a;
  }

  public double getW() {
    return w;
  }

  public void setW(double w) {
    this.w = w;
  }

}
