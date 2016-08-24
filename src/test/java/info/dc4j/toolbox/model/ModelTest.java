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
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import info.dc4j.toolbox.block.Block;
import info.dc4j.toolbox.element.Data;
import info.dc4j.toolbox.element.DataType;
import info.dc4j.toolbox.monitor.TraceData;
import info.dc4j.toolbox.monitor.Tracer;

public class ModelTest {

  double delta = 0.005;

  @Test
  public void test01() {
    ModelFactory factory = new ModelFactoryImpl();
    Model model = factory.createModel();
    int b1 = model.getLayoutService().createBlock("block1", Block.Type.STEP);
    int b2 = model.getLayoutService().createBlock("block2", Block.Type.PT1);
    int c1 = model.getLayoutService().createConnection("c1", b1, b2, 0, 0, DataType.DOUBLE);
    int c2 = model.getLayoutService().createConnection("c2", b2, null, 0, 0, DataType.DOUBLE);
    model.getMonitorService().setMonitoredConnector(c1);
    model.getMonitorService().setMonitoredConnector(c2);
    model.getMonitorService().setTracer(Tracer.Type.CONSOLE);
    model.getMonitorService().setTracer(Tracer.Type.MEMORY);
    model.build();
    model.run(3.0);
    List<TraceData> res = model.getMonitorService().getTraceData();
    assertEquals(30, res.size());
    Data data = res.get(res.size() - 1).getIndicators().get(1);
    assertEquals(0.865, data.getDouble(), delta);
  }

  @SuppressWarnings("unused")
  @Test
  public void test02() {
    // 1->5->6->7->10->8
    // 2->4->5
    // 3->9->11->7
    // 8->9
    // 6->8
    ModelFactory factory = new ModelFactoryImpl();
    Model model = factory.createModel();
    int[] b = new int[12];
    b[6] = model.getLayoutService().createBlock("block6", Block.Type.SPLITTER_D);
    b[7] = model.getLayoutService().createBlock("block7", Block.Type.SUM);
    b[8] = model.getLayoutService().createBlock("block8", Block.Type.SUM);
    b[9] = model.getLayoutService().createBlock("block9", Block.Type.SUM);
    b[10] = model.getLayoutService().createBlock("block10", Block.Type.PT1);
    b[11] = model.getLayoutService().createBlock("block11", Block.Type.PT1);
    b[1] = model.getLayoutService().createBlock("block1", Block.Type.STEP);
    b[2] = model.getLayoutService().createBlock("block2", Block.Type.STEP);
    b[3] = model.getLayoutService().createBlock("block3", Block.Type.STEP);
    b[4] = model.getLayoutService().createBlock("block4", Block.Type.PT1);
    b[5] = model.getLayoutService().createBlock("block5", Block.Type.SUM);

    int c1 = model.getLayoutService().createConnection("c1", b[1], b[5], 0, 0, DataType.DOUBLE);
    int c2 = model.getLayoutService().createConnection("c2", b[2], b[4], 0, 0, DataType.DOUBLE);
    int c3 = model.getLayoutService().createConnection("c3", b[3], b[9], 0, 0, DataType.DOUBLE);
    int c4 = model.getLayoutService().createConnection("c4", b[4], b[5], 0, 1, DataType.DOUBLE);
    int c5 = model.getLayoutService().createConnection("c5", b[5], b[6], 0, 0, DataType.DOUBLE);
    int c6 = model.getLayoutService().createConnection("c6", b[6], b[7], 0, 0, DataType.DOUBLE);
    int c7 = model.getLayoutService().createConnection("c7", b[6], b[8], 1, 0, DataType.DOUBLE);
    int c8 = model.getLayoutService().createConnection("c8", b[7], b[10], 0, 0, DataType.DOUBLE);
    int c9 = model.getLayoutService().createConnection("c9", b[8], b[9], 0, 1, DataType.DOUBLE);
    int c10 = model.getLayoutService().createConnection("c10", b[9], b[11], 0, 0, DataType.DOUBLE);
    int c11 = model.getLayoutService().createConnection("c11", b[11], b[7], 0, 1, DataType.DOUBLE);
    int c12 = model.getLayoutService().createConnection("c12", b[10], b[8], 0, 1, DataType.DOUBLE);
    model.build();
    assertTrue(model.getLayoutService().getBlockInfo(b[1]).getOrder() < model.getLayoutService().getBlockInfo(b[11])
        .getOrder());
  }

}
