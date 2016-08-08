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
package info.dc4j.toolbox.block.monitor;

import java.util.ArrayList;
import java.util.List;

import info.dc4j.toolbox.block.connector.BoolConnector;
import info.dc4j.toolbox.block.connector.DoubleConnector;
import info.dc4j.toolbox.model.rw.BoolData;
import info.dc4j.toolbox.model.rw.DoubleData;
import info.dc4j.toolbox.model.rw.Response;
import info.dc4j.toolbox.model.rw.ResultLine;

public class MemoryTracer implements ITracable {

  private final Response result;

  public MemoryTracer() {
    super();
    result = new Response();
  }

  @Override
  public void trace(long step, double t, List<DoubleConnector> dConn, List<BoolConnector> bConn) {
    ResultLine line = new ResultLine();
    result.addLine(line);
    line.setStep(step);
    line.setT(t);
    List<DoubleData> doubles = new ArrayList<>();
    List<BoolData> bools = new ArrayList<>();
    line.setDoubles(doubles);
    line.setBools(bools);
    for (DoubleConnector conn : dConn) {
      doubles.add(conn.getData());
    }
    for (BoolConnector conn : bConn) {
      bools.add(conn.getData());
    }
  }

  public Response getResult() {
    return result;
  }

}
