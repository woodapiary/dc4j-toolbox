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
package info.dc4j.toolbox.monitor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import info.dc4j.toolbox.element.Data;

public class TraceData implements Serializable {

  private static final long serialVersionUID = -4527241881441543366L;
  private final long step;
  private final double t;
  private final List<Data> indicators = new ArrayList<>();

  public TraceData(long step, double t) {
    this.step = step;
    this.t = t;
  }

  public long getStep() {
    return step;
  }

  public double getT() {
    return t;
  }

  public List<Data> getIndicators() {
    return indicators;
  }

  public void addIndicator(Data indicator) {
    indicators.add(indicator);
  }

}
