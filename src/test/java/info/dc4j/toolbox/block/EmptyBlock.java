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

import info.dc4j.toolbox.element.TypeEnum;

public class EmptyBlock extends BlockImpl {
  public static final String TYPE = "Empty";
  public static final String DESC = "empty block";

  public static final double D = 1.0;
  public static final String D_NAME = "d";
  public static final boolean B = true;
  public static final String B_NAME = "b";
  public static final int I = 2;
  public static final String I_NAME = "i";
  public static final String S = "3";
  public static final String S_NAME = "s";

  public EmptyBlock(int id, String name) {
    super(id, name,4,4);
    addParameter(D_NAME, D, TypeEnum.DOUBLE);
    addParameter(B_NAME, B, TypeEnum.BOOL);
    addParameter(I_NAME, I, TypeEnum.INTEGER);
    addParameter(S_NAME, S, TypeEnum.STRING);
  }

  @Override
  protected void eval() {
    double u0 = getDoubleU(0);
    double p0 = getDoubleParameter(D_NAME);
    double y0 = u0 * p0;
    setValueY(0, y0);
  }

  @Override
  public String getType() {
    return TYPE;
  }

  @Override
  public String getDesc() {
    return DESC;
  }
}
