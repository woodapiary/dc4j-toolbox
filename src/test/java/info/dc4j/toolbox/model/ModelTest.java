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

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import info.dc4j.toolbox.block.Block;
import info.dc4j.toolbox.connector.Connector;
import info.dc4j.toolbox.element.Data;
import info.dc4j.toolbox.monitor.TraceData;
import info.dc4j.toolbox.monitor.Tracer;

public class ModelTest {

  double delta = 0.005;

  @Test
  public void test01() {
    Model model = ModelFactoryImpl.createModel();
    int b1 = model.createBlock("block1", Block.Type.STEP);
    int b2 = model.createBlock("block2", Block.Type.PT1);
    int c1 = model.createConnection("c1", b1, b2, 0, 0, Connector.Type.DOUBLE);
    int c2 = model.createConnection("c2", b2, null, 0, 0, Connector.Type.DOUBLE);
    model.setMonitoredConnector(c1);
    model.setMonitoredConnector(c2);
    model.setTracer(Tracer.Type.CONSOLE);
    model.setTracer(Tracer.Type.MEMORY);
    model.build();
    model.run(3.0);
    List<TraceData> res = model.getTraceData();
    assertEquals(30, res.size());
    Data data = res.get(res.size() - 1).getIndicators().get(1);
    assertEquals(0.865, data.getDouble(),delta);

  }

}
