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

import info.dc4j.toolbox.block.Block;
import info.dc4j.toolbox.block.continuous.Integrator;
import info.dc4j.toolbox.block.continuous.PT1;
import info.dc4j.toolbox.block.math.Gain;
import info.dc4j.toolbox.block.math.Subtract;
import info.dc4j.toolbox.block.math.Sum;
import info.dc4j.toolbox.block.source.Sin;
import info.dc4j.toolbox.block.source.Step;
import info.dc4j.toolbox.connector.BoolConnector;
import info.dc4j.toolbox.connector.Connector;
import info.dc4j.toolbox.connector.DoubleConnector;
import info.dc4j.toolbox.layout.Layout;
import info.dc4j.toolbox.layout.LayoutImpl;
import info.dc4j.toolbox.monitor.ConsoleTracer;
import info.dc4j.toolbox.monitor.MemoryTracer;
import info.dc4j.toolbox.monitor.Monitor;
import info.dc4j.toolbox.monitor.MonitorImpl;
import info.dc4j.toolbox.monitor.Tracer;
import info.dc4j.toolbox.monitor.TracerType;

public class ModelFactoryIml implements ModelFactory {

  private final SequenceId seq = new SequenceId();

  @Override
  public Block createBlock(Integer id, String name, Block.Type type, Object param) {
    if (id == null) {
      id = seq.getBlockId();
    }
    Block block = null;
    switch (type) {
      case INTEGRATOR:
        block = new Integrator(id, name);
        break;
      case PT1:
        block = new PT1(id, name);
        break;
      case GAIN:
        block = new Gain(id, name);
        break;
      case SUM:
        block = new Sum(id, name);
        break;
      case SIN:
        block = new Sin(id, name);
        break;
      case STEP:
        block = new Step(id, name);
        break;
      case SUBSTRACT:
        block = new Subtract(id, name);
        break;
      case USER:
        createUserBlock(id, name, param);
        break;
      default:
        throw new IllegalArgumentException("wrong type block");
    }
    return block;
  }

  @Override
  public Block createUserBlock(Integer id, String name, Object param) {
    throw new IllegalArgumentException("no user blocks");
  }

  @Override
  public Model createModel() {
    Layout layout = new LayoutImpl(0, "", this);
    Monitor monitor = new MonitorImpl(ModelConstants.MONITOR_ID, "monitor", this, layout);
    return new ModelImpl(layout, monitor);
  }

  @Override
  public Connector createConnector(Integer id, String name, Block source, Block target, Connector.Type type) {
    if (id == null) {
      id = seq.getConnectorId();
    }
    Connector connector = null;
    switch (type) {
      case BOOL:
        connector = new BoolConnector(id, name, source, target);
        break;
      case DOUBLE:
        connector = new DoubleConnector(id, name, source, target);
        break;
      default:
        throw new IllegalArgumentException("bad type of connector");
    }
    return connector;
  }

  @Override
  public Tracer createTracer(TracerType type) {
    Tracer tracer = null;
    switch (type) {
      case CONSOLE:
        tracer = new ConsoleTracer();
        break;
      case MEMORY:
        tracer = new MemoryTracer();
        break;
      default:
        throw new IllegalArgumentException("bad type of tracer");
    }
    return tracer;
  }

  class SequenceId {

    private int seqBlock = 1; // 0 is layout, 1 is monitor
    private int seqConnector = 0;

    public int getBlockId() {
      seqBlock++;
      return seqBlock;
    }

    public int getConnectorId() {
      seqConnector++;
      return seqConnector;
    }
  }
}
