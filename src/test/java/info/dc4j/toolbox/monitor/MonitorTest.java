package info.dc4j.toolbox.monitor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import info.dc4j.toolbox.block.Block;
import info.dc4j.toolbox.block.EmptyBlock;
import info.dc4j.toolbox.connector.BoolConnector;
import info.dc4j.toolbox.connector.DoubleConnector;
import info.dc4j.toolbox.model.ModelFactoryImpl;

public class MonitorTest {

  double delta = 0.005;

  @Test
  public void test01() {
    MonitorImpl monitor = new MonitorImpl(0, "", ModelFactoryImpl.getInstanse(), null);
    EmptyBlock block1 = new EmptyBlock(1, "block1");
    DoubleConnector u1 = new DoubleConnector(1, "in1", null, block1);
    DoubleConnector y1 = new DoubleConnector(2, "out1", block1, null);
    block1.setConnector(u1, Block.Port.U, 0);
    block1.setConnector(y1, Block.Port.Y, 0);
    BoolConnector u2 = new BoolConnector(1, "in2", null, block1);
    BoolConnector y2 = new BoolConnector(2, "out2", block1, null);
    block1.setConnector(u2, Block.Port.U, 0);
    block1.setConnector(y2, Block.Port.Y, 0);
    monitor.setMonitoredConnector(u1);
    monitor.setMonitoredConnector(u2);
    monitor.setMonitoredConnector(y1);
    monitor.setMonitoredConnector(y2);
    monitor.setTracer(TracerType.CONSOLE);
    monitor.setTraceLevel(1);
    monitor.run(0);
    block1.run(0);
    monitor.run(0);
    block1.setB(true);
    u1.setValue(5.0);
    u2.setValue(true);
    block1.run(0);
    monitor.run(0);
  }

  @Test
  public void test02() {
    MonitorImpl monitor = new MonitorImpl(0, "", ModelFactoryImpl.getInstanse(), null);
    EmptyBlock block1 = new EmptyBlock(1, "block1");
    DoubleConnector u1 = new DoubleConnector(1, "in1", null, block1);
    DoubleConnector y1 = new DoubleConnector(2, "out1", block1, null);
    block1.setConnector(u1, Block.Port.U, 0);
    block1.setConnector(y1, Block.Port.Y, 0);
    BoolConnector u2 = new BoolConnector(1, "in2", null, block1);
    BoolConnector y2 = new BoolConnector(2, "out2", block1, null);
    block1.setConnector(u2, Block.Port.U, 0);
    block1.setConnector(y2, Block.Port.Y, 0);
    monitor.setMonitoredConnector(u1);
    monitor.setMonitoredConnector(u2);
    monitor.setMonitoredConnector(y1);
    monitor.setMonitoredConnector(y2);
    monitor.setTracer(TracerType.MEMORY);
    monitor.setTraceLevel(1);
    monitor.run(0);
    block1.run(0);
    monitor.run(0);
    block1.setB(true);
    u1.setValue(5.0);
    u2.setValue(true);
    block1.run(0);
    monitor.run(0);
    assertEquals(3, monitor.getTraceData().size());
  }

}
