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
package info.dc4j.toolbox.model;

import java.util.List;

import info.dc4j.toolbox.block.Parameter;
import info.dc4j.toolbox.layout.Layout;
import info.dc4j.toolbox.monitor.Monitor;
import info.dc4j.toolbox.monitor.TraceData;

public class ModelImpl implements Model {

  private final Layout layout;
  private final Monitor monitor;
  private double dt = Model.DT;
  private double t;
  private long step;

  protected ModelImpl(ModelFactory factory, Layout layout, Monitor monitor) {
    this.layout = layout;
    this.monitor = monitor;
  }

  @Override
  public void run(double maxTime) {
    monitor.trace(step, t, null);
    while (t < maxTime) {
      // step++;
      t = t + dt;
      layout.run(maxTime);
      monitor.run(maxTime);
    }
  }

  @Override
  public void setScanTime(double dt) {
    if (dt <= 0) {
      throw new IllegalArgumentException("dt must be over zero");
    }
    this.dt = dt;
    layout.setScanTime(dt);
    monitor.setScanTime(dt);
  }

  @Override
  public double getScanTime() {
    return dt;
  }

  @Override
  public void init() {
    t = 0;
    step = 0;
    layout.init();
    monitor.init();
  }

  @Override
  public double getT() {
    return t;
  }

  @Override
  public long getStep() {
    return step;
  }

  @Override
  public void build() {
    layout.build();
  }

  @Override
  public int createBlock(Integer id, String name, String type) {
    return layout.createBlock(id, name, type);
  }

  @Override
  public int createConnection(Integer id, String name, int fromId, int toId, int out, int in, String type) {
    return layout.createConnection(id, name, fromId, toId, out, in, type);
  }

  @Override
  public void setMonitoredConnector(int connectorId) {
    monitor.setMonitoredConnector(connectorId);
  }

  @Override
  public List<Integer> getMonitoredConnectors() {
    return monitor.getMonitoredConnectors();
  }

  @Override
  public void setTracer(String type) {
    monitor.setTracer(type);
  }

  @Override
  public List<String> getTracers() {
    return monitor.getTracers();
  }

  @Override
  public List<TraceData> getTraceData() {
    return monitor.getTraceData();
  }

  @Override
  public List<Parameter> getParameters(int blockId) {
    return layout.getParameters(blockId);
  }

  @Override
  public void setParameters(int blockId, List<Parameter> parameters) {
    layout.setParameters(blockId, parameters);
  }

}
