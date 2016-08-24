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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import info.dc4j.toolbox.block.Block;
import info.dc4j.toolbox.block.BlockInfo;
import info.dc4j.toolbox.element.DataType;
import info.dc4j.toolbox.element.Parameter;
import info.dc4j.toolbox.layout.Layout;
import info.dc4j.toolbox.monitor.Monitor;
import info.dc4j.toolbox.monitor.TraceData;
import info.dc4j.toolbox.monitor.Tracer;

public class ModelImpl implements Model {

  private final Layout layout;
  private final Monitor monitor;
  private double dt = Model.DT;
  private double t;
  private long step;
  private final ModelFactory factory;

  protected ModelImpl(ModelFactory factory, Layout layout, Monitor monitor) {
    this.layout = layout;
    this.monitor = monitor;
    this.factory = factory;
  }

  @Override
  public void run(double maxTime) {
    while (t < maxTime) {
      step++;
      t = t + dt;
      for (Runnable block : layout.getBlocks()) {
        block.run(maxTime);
      }
      monitor.run(maxTime);
    }
  }

  @Override
  public void setScanTime(double dt) {
    if (dt <= 0) {
      throw new IllegalArgumentException("dt must be over zero");
    }
    this.dt = dt;
    for (Runnable block : layout.getBlocks()) {
      block.setScanTime(dt);
    }
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
    for (Runnable block : layout.getBlocks()) {
      block.init();
    }
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
    List<Block> list = layout.getBlocks();
    OrderStrategy strategy = factory.createOrderStrategy(Model.ORDER_STRAREGY_TYPE);
    strategy.execute(list);
    check();
    Collections.sort(list, new Comparator<Block>() {
      @Override
      public int compare(Block o1, Block o2) {
        return ((Integer) o1.getOrder()).compareTo(o2.getOrder());
      }
    });
    // System.out.println(layout.toString());
  }

  private void check() {
    for (Block block : layout.getBlocks()) {
      if (!block.isOrdered()) {
        throw new IllegalStateException("no order for block: " + block.getCanonicalName());
      }
    }
  }

  @Override
  public int createBlock(Integer id, String name, Block.Type type, Object param) {
    return layout.createBlock(id, name, type, param);
  }

  @Override
  public int createBlock(String name, Block.Type type) {
    return createBlock(null, name, type, null);
  }

  @Override
  public int createConnection(Integer id, String name, Integer fromId, Integer toId, Integer out, Integer in,
      DataType type) {
    return layout.createConnection(id, name, fromId, toId, out, in, type);
  }

  @Override
  public int createConnection(String name, Integer fromId, Integer toId, int out, int in, DataType type) {
    return createConnection(null, name, fromId, toId, out, in, type);
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
  public void setTracer(Tracer.Type type) {
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
  public List<Parameter> getBlockParameters(int blockId, boolean defaults) {
    return layout.getBlockParameters(blockId, defaults);
  }

  @Override
  public void setBlockParameters(int blockId, List<Parameter> parameters) {
    layout.setBlockParameters(blockId, parameters);
  }

  @Override
  public BlockInfo getBlockInfo(int blockId) {
    return layout.getBlockInfo(blockId);
  }
}
