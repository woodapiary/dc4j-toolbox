/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2002-2016 dc4j.info
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package info.dc4j.toolbox.block.math;

import java.util.ArrayList;
import java.util.List;

import info.dc4j.toolbox.block.Block;
import info.dc4j.toolbox.block.BlockImpl;
import info.dc4j.toolbox.element.DataType;
import info.dc4j.toolbox.element.Parameter;

public class Gain extends BlockImpl {

  public static final String DESC = "multiply input by constant";

  public enum P {
    K
  }

  public interface Size {
    int U_D = 1;
    int U_B = 0;
    int Y_D = 1;
    int Y_B = 0;
    int S_D = 0;
    int S_B = 0;
  }

  private static double kDefault = 1.0;

  private double k = kDefault;

  public Gain(final int id, final String name) {
    super(id, name, Size.U_D, Size.Y_D, Size.U_B, Size.Y_B, Size.S_D, Size.S_B);
    setDesc(DESC);
  }

  @Override
  protected void eval() {
    final double u0 = u.getD(0).get();
    final double y0 = k * u0;
    y.getD(0).set(y0);
  }

  @Override
  public Block.Type blockType() {
    return Block.Type.GAIN;
  }

  @Override
  public List<Parameter> getParameters(final boolean defaults) {
    final List<Parameter> data = new ArrayList<>();
    if (!defaults) {
      data.add(new Parameter(getId(), P.K.name(), DataType.DOUBLE, k));
    }
    return data;
  }

  @Override
  public void setParameters(final List<Parameter> params) {
    for (final Parameter param : params) {
      switch (P.valueOf(param.getName())) {
        case K:
          k = param.getDouble();
          break;
        default:
          throw new IllegalArgumentException("wrong parameter");
      }
    }
  }

  protected void setK(final double k) {
    this.k = k;
  }

}
