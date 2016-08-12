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

import info.dc4j.toolbox.block.BlockImpl;
import info.dc4j.toolbox.element.TypeEnum;

public class PT1 extends BlockImpl {
  public static final String TYPE = "pt1";
  private static final double K = 1.0;
  private static final double TF = 1.0;

  public PT1(int id, String name) {
    super(id, name,1,1);
    addParameter("tf", TF, TypeEnum.DOUBLE);
    addParameter("k", K, TypeEnum.DOUBLE);
  }

  @Override
  protected void eval() {
    // TODO
    /*
     * double ku = k / (1 + tf / dt); double ky1 = 1 / (1 + dt / tf); double y1
     * = out().getValue(); double u = getU0().getValue(); double y = ky1 * y1 +
     * ku * u; out().setValue(y);
     */
  }

  @Override
  public String getType() {
    return TYPE;
  }
}
