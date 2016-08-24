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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import info.dc4j.toolbox.block.Block;
import info.dc4j.toolbox.block.EmptyBlock;
import info.dc4j.toolbox.connector.BoolConnector;
import info.dc4j.toolbox.connector.DoubleConnector;

public class MonitorTest {

  double delta = 0.005;

  @Test
  public void test01() {
    MonitorImpl monitor = new MonitorImpl(null);
    EmptyBlock block1 = new EmptyBlock(1, "block1");
    DoubleConnector u1 = new DoubleConnector(1, "in1", null, block1);
    DoubleConnector y1 = new DoubleConnector(2, "out1", block1, null);
    block1.setConnector(u1, Block.PortType.U, 0);
    block1.setConnector(y1, Block.PortType.Y, 0);
    BoolConnector u2 = new BoolConnector(1, "in2", null, block1);
    BoolConnector y2 = new BoolConnector(2, "out2", block1, null);
    block1.setConnector(u2, Block.PortType.U, 0);
    block1.setConnector(y2, Block.PortType.Y, 0);
    monitor.setMonitoredConnector(u1);
    monitor.setMonitoredConnector(u2);
    monitor.setMonitoredConnector(y1);
    monitor.setMonitoredConnector(y2);
    monitor.setTracer(Tracer.Type.CONSOLE);
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
    MonitorImpl monitor = new MonitorImpl(null);
    EmptyBlock block1 = new EmptyBlock(1, "block1");
    DoubleConnector u1 = new DoubleConnector(1, "in1", null, block1);
    DoubleConnector y1 = new DoubleConnector(2, "out1", block1, null);
    block1.setConnector(u1, Block.PortType.U, 0);
    block1.setConnector(y1, Block.PortType.Y, 0);
    BoolConnector u2 = new BoolConnector(1, "in2", null, block1);
    BoolConnector y2 = new BoolConnector(2, "out2", block1, null);
    block1.setConnector(u2, Block.PortType.U, 0);
    block1.setConnector(y2, Block.PortType.Y, 0);
    monitor.setMonitoredConnector(u1);
    monitor.setMonitoredConnector(u2);
    monitor.setMonitoredConnector(y1);
    monitor.setMonitoredConnector(y2);
    monitor.setTracer(Tracer.Type.MEMORY);
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
