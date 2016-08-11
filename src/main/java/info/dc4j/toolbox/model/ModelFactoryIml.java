package info.dc4j.toolbox.model;

import info.dc4j.toolbox.block.Block;
import info.dc4j.toolbox.block.BlockImpl;
import info.dc4j.toolbox.block.continuous.Integrator;
import info.dc4j.toolbox.block.continuous.PT1;
import info.dc4j.toolbox.connector.BoolConnector;
import info.dc4j.toolbox.connector.Connector;
import info.dc4j.toolbox.connector.DoubleConnector;
import info.dc4j.toolbox.layout.UnitImpl;
import info.dc4j.toolbox.layout.Layout;
import info.dc4j.toolbox.layout.LayoutImpl;
import info.dc4j.toolbox.monitor.ConsoleTracer;
import info.dc4j.toolbox.monitor.MemoryTracer;
import info.dc4j.toolbox.monitor.Monitor;
import info.dc4j.toolbox.monitor.Tracer;

public class ModelFactoryIml implements ModelFactory {

  private final SequenceId seq = new SequenceId();

  @Override
  public Block createBlock(Integer id, String name, String type) {
    if (id == null) {
      id = seq.getBlockId();
    }
    BlockImpl block = null;
    switch (type) {
      case UnitImpl.TYPE:
        block = new UnitImpl(id, name);
        break;
      case Integrator.TYPE:
        block = new Integrator(id, name);
        break;
      case PT1.TYPE:
        block = new PT1(id, name);
        break;
      // TODO add elements
      default:
        throw new IllegalArgumentException("bad type of block");
    }
    return block;
  }

  @Override
  public Model createModel() {
    Layout layout = new LayoutImpl(0, "", this);
    Monitor monitor = new Monitor(1, "monitor", this, layout);
    return new ModelImpl(this, layout, monitor);
  }

  @Override
  public Connector createConnector(Integer id, String name, Block source, Block target, String type) {
    if (id == null) {
      id = seq.getConnectorId();
    }
    Connector connector = null;
    switch (type) {
      case BoolConnector.TYPE:
        connector = new BoolConnector(id, name, source, target);
        break;
      case DoubleConnector.TYPE:
        connector = new DoubleConnector(id, name, source, target);
        break;
      default:
        throw new IllegalArgumentException("bad type of connector");
    }
    return connector;
  }

  @Override
  public Tracer createTracer(String type) {
    Tracer tracer = null;
    switch (type) {
      case ConsoleTracer.TYPE:
        tracer = new ConsoleTracer();
        break;
      case MemoryTracer.TYPE:
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
