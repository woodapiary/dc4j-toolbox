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

package info.dc4j.toolbox.model.rw;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Settings implements Serializable {

  private static final long serialVersionUID = -1101844193863102298L;
  private final List<DoubleData> doubleList;
  private final List<BoolData> boolList;
  private double dt = 0.001;
  private double maxTime = 10;
  private int traceLevel = 100;
  private transient Map<String, DoubleData> doubleMap;
  private transient Map<String, BoolData> boolMap;

  public Settings() {
    doubleList = new ArrayList<>();
    boolList = new ArrayList<>();
    doubleMap = new HashMap<>();
    boolMap = new HashMap<>();
  }

  public void addDs(String key, double val) {
    DoubleData data = new DoubleData(key, val);
    doubleMap.put(key, data);
    doubleList.add(data);
  }

  public void addBs(String key, boolean val) {
    BoolData data = new BoolData(key, val);
    boolMap.put(key, data);
    boolList.add(data);
  }

  public void init() {
    doubleMap = new HashMap<>();
    boolMap = new HashMap<>();

    for (DoubleData val : doubleList) {
      doubleMap.put(val.getKey(), val);
    }
    for (BoolData val : boolList) {
      boolMap.put(val.getKey(), val);
    }
  }

  public double getDt() {
    return dt;
  }

  public double getMaxTime() {
    return maxTime;
  }

  public int getTraceLevel() {
    return traceLevel;
  }

  public double getDs(String key) {
    return doubleMap.get(key).getValue();
  }

  public boolean getBs(String key) {
    return boolMap.get(key).getValue();
  }

  public void setDt(double dt) {
    this.dt = dt;
  }

  public void setMaxTime(double maxTime) {
    this.maxTime = maxTime;
  }

  public void setTraceLevel(int traceLevel) {
    this.traceLevel = traceLevel;
  }

}
