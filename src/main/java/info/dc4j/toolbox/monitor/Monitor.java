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

import info.dc4j.toolbox.block.BlockImpl;
import info.dc4j.toolbox.connector.Connector;
import info.dc4j.toolbox.element.TypeEnum;
import info.dc4j.toolbox.layout.Layout;
import info.dc4j.toolbox.model.ModelFactory;

public class Monitor extends BlockImpl implements Tracer, MonitorService {
  public static final int TRACE_LEVEL = 100;
  private final List<Tracer> tracers = new ArrayList<>();
  private final Layout layout;
  private final ModelFactory factory;
  private int index;

  public Monitor(int id, String name, ModelFactory factory, Layout layout) {
    super(id, name);
    this.layout = layout;
    this.factory = factory;
    addParameter("traceLevel", TRACE_LEVEL, TypeEnum.STRING);
  }

  @Override
  protected void eval() {
    trace(getStep(), getT(), null);
  }

  @Override
  public void setMonitoredConnector(int connectorId) {
    Connector connector = layout.getConnector(connectorId);
    setU(connector, index);
  }

  @Override
  public List<Integer> getMonitoredConnectors() {
    // TODO
    return null;
  }

  @Override
  public void setTracer(String type) {
    tracers.add(factory.createTracer(type));
  }

  @Override
  public List<String> getTracers() {
    // TODO
    return null;
  }

  @Override
  public void trace(long step, double t, List<Connector> connectors) {
    int traceLevel = getIntegerParameter("traceLevel");
    if (getStep() % traceLevel == 0 || getStep() == 0) {
      for (Tracer tracer : tracers) {
        tracer.trace(step, t, connectors != null ? connectors : getListU());
      }
    }
  }

  @Override
  public void init() {
    super.init();
    clear();
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
  public void clear() {
    for (Tracer tracer : tracers) {
      tracer.clear();
    }
  }

}
