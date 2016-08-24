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
package info.dc4j.toolbox.monitor;

import java.util.ArrayList;
import java.util.List;

import info.dc4j.toolbox.connector.Connector;
import info.dc4j.toolbox.element.Parameter;
import info.dc4j.toolbox.layout.Layout;
import info.dc4j.toolbox.model.Model;

public class MonitorImpl implements Monitor {
  private int traceLevel = 100;
  private final List<Tracer> tracers = new ArrayList<>();
  private final List<Connector> connectors = new ArrayList<>();
  private final Layout layout;
  protected long step;
  protected double dt = Model.DT;
  protected double t;

  public MonitorImpl(Layout layout) {
    this.layout = layout;
  }

  @Override
  public void setMonitoredConnector(int connectorId) {
    Connector connector = layout.getConnector(connectorId);
    connectors.add(connector);
  }

  protected void setMonitoredConnector(Connector connector) {
    connectors.add(connector);
  }

  @Override
  public List<Integer> getMonitoredConnectors() {
    // TODO
    return null;
  }

  @Override
  public void setTracer(Tracer.Type type) {
    tracers.add(Model.getFactory().createTracer(type));
  }

  @Override
  public List<String> getTracers() {
    // TODO
    return null;
  }

  private void trace(long step, double t, List<Connector> conn) {
    if (step % traceLevel == 0 || step == 0) {
      for (Tracer tracer : tracers) {
        tracer.trace(step, t, conn != null ? conn : connectors);
      }
    }
  }

  @Override
  public void init() {
    t = 0;
    step = 0;
    for (Tracer tracer : tracers) {
      tracer.clear();
    }
  }

  @Override
  public List<TraceData> getTraceData() {
    List<TraceData> res = null;
    for (Tracer tracer : tracers) {
      res = tracer.getTraceData();
      if (res != null) {
        break;
      }
    }
    return res;
  }

  @Override
  public void run(double maxTime) {
    t = t + dt;
    step++;
    trace(getStep(), getT(), null);
  }

  @Override
  public void setScanTime(double dt) {
    this.dt = dt;
  }

  @Override
  public double getScanTime() {
    return dt;
  }

  @Override
  public double getT() {
    return t;
  }

  @Override
  public long getStep() {
    return step;
  }

  protected void setTraceLevel(int traceLevel) {
    this.traceLevel = traceLevel;
  }

  @Override
  public List<Parameter> getParameters(boolean defaults) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setParameters(List<Parameter> params) {
    // TODO Auto-generated method stub

  }

}
