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
/*
 *
 */
package info.dc4j.toolbox.model.rw;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class Settings.
 */
public class Settings implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -1101844193863102298L;

  /** The double list. */
  private final List<DoubleData> doubleList;

  /** The bool list. */
  private final List<BoolData> boolList;

  /** The dt. */
  private double dt = 0.001;

  /** The max time. */
  private double maxTime = 10;

  /** The trace level. */
  private int traceLevel = 100;

  /** The double map. */
  private transient Map<String, DoubleData> doubleMap;

  /** The bool map. */
  private transient Map<String, BoolData> boolMap;

  /**
   * Instantiates a new settings.
   */
  public Settings() {
    doubleList = new ArrayList<DoubleData>();
    boolList = new ArrayList<BoolData>();
    doubleMap = new HashMap<String, DoubleData>();
    boolMap = new HashMap<String, BoolData>();
  }

  /**
   * Adds the ds.
   *
   * @param key the key
   * @param val the val
   */
  public void addDs(String key, double val) {
    DoubleData data = new DoubleData(key, val);
    doubleMap.put(key, data);
    doubleList.add(data);
  }

  /**
   * Adds the bs.
   *
   * @param key the key
   * @param val the val
   */
  public void addBs(String key, boolean val) {
    BoolData data = new BoolData(key, val);
    boolMap.put(key, data);
    boolList.add(data);
  }

  /**
   * Inits the.
   */
  public void init() {
    doubleMap = new HashMap<String, DoubleData>();
    boolMap = new HashMap<String, BoolData>();

    for (DoubleData val : doubleList) {
      doubleMap.put(val.getKey(), val);
    }
    for (BoolData val : boolList) {
      boolMap.put(val.getKey(), val);
    }
  }

  /**
   * Gets the dt.
   *
   * @return the dt
   */
  public double getDt() {
    return dt;
  }

  /**
   * Gets the max time.
   *
   * @return the max time
   */
  public double getMaxTime() {
    return maxTime;
  }

  /**
   * Gets the trace level.
   *
   * @return the trace level
   */
  public int getTraceLevel() {
    return traceLevel;
  }

  /**
   * Gets the ds.
   *
   * @param key the key
   * @return the ds
   */
  public double getDs(String key) {
    return doubleMap.get(key).getValue();
  }

  /**
   * Gets the bs.
   *
   * @param key the key
   * @return the bs
   */
  public boolean getBs(String key) {
    return boolMap.get(key).getValue();
  }

  /**
   * Sets the dt.
   *
   * @param dt the new dt
   */
  public void setDt(double dt) {
    this.dt = dt;
  }

  /**
   * Sets the max time.
   *
   * @param maxTime the new max time
   */
  public void setMaxTime(double maxTime) {
    this.maxTime = maxTime;
  }

  /**
   * Sets the trace level.
   *
   * @param traceLevel the new trace level
   */
  public void setTraceLevel(int traceLevel) {
    this.traceLevel = traceLevel;
  }

}
