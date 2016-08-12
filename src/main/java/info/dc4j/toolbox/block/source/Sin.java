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

import info.dc4j.toolbox.block.BlockImpl;
import info.dc4j.toolbox.element.TypeEnum;

public class Sin extends BlockImpl {
  public static final String TYPE = "sin";
  private static final double A = 1.0;
  private static final double W = 1.0;

  public Sin(int id, String name) {
    super(id, name,0,1);
    addParameter("a", A, TypeEnum.DOUBLE);
    addParameter("w", W, TypeEnum.DOUBLE);
  }

  @Override
  protected void eval() {
    // TODO
    /*
     * double y = a * Math.sin(t * w); out().setValue(y);
     */
  }

  @Override
  public String getType() {
    return TYPE;
  }

}
