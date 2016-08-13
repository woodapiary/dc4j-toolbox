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
package info.dc4j.toolbox.block.math;

import info.dc4j.toolbox.block.Block;
import info.dc4j.toolbox.block.BlockImpl;

public class Sum extends BlockImpl {

  public Sum(int id, String name) {
    super(id, name,2,1,0,0,0,0);
  }

  @Override
  protected void eval() {
    double u0 = dU[0].get();
    double u1 = dU[1].get();
    double y = u1 + u0;
    dY[0].set(y);
  }

  @Override
  public Object getParameters() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setParameters(Object map) {
    // TODO Auto-generated method stub

  }

  @Override
  public Object getDefaultParameters() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Block.Type blockType() {
    return Block.Type.SUM;
  }

}
