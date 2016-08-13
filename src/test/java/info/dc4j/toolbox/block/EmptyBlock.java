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
package info.dc4j.toolbox.block;

import java.util.EnumMap;

public class EmptyBlock extends BlockImpl {

  public static final String TYPE = "Empty";
  public static final String DESC = "empty block";

  public enum P {
    D, B
  };

  private double d = 1.0;
  private boolean b = false;

  public EmptyBlock(int id, String name) {
    super(id, name, 1, 1,1,1,0,0);
    setDesc(DESC);
    setType(TYPE);
  }

  @Override
  protected void eval() {
    double u0 = dU[0].get();
    boolean u1 = bU[0].get();
    double y0 = u0 * d;
    boolean y1 = u1 && b;
    dY[0].set(y0);
    bY[0].set(y1);
  }

  @Override
  public Object getParameters() {
    // TODO Auto-generated method stub
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void setParameters(Object map) {
    EnumMap<P, Object> params = (EnumMap<P, Object>) map;
    d = (Double)params.get(P.D);
    b = (Boolean)params.get(P.B);
  }

  @Override
  public Object getDefaultParameters() {
    // TODO Auto-generated method stub
    return null;
  }

  protected void setD(double d) {
    this.d = d;
  }

  protected void setB(boolean b) {
    this.b = b;
  }



}
