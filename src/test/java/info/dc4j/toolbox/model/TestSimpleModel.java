/**
 * The MIT License
 * Copyright (c) 2002 Ilkka Seppälä
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

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import info.dc4j.toolbox.block.monitor.MemoryTracer;
import info.dc4j.toolbox.block.monitor.PrintfTracer;
import info.dc4j.toolbox.model.rw.Result;
import info.dc4j.toolbox.model.rw.Settings;


public class TestSimpleModel {

  @Test
  public void testSimpleModel() {
    SimpleModel model = new SimpleModel();
    model.setSettings(getSettings());
    model.addTracer(new PrintfTracer());
    MemoryTracer tracer = new MemoryTracer();
    model.addTracer(tracer);
    model.build();
    model.run();
    Result res = tracer.getResult();
    assertTrue(res.getLines().size() == 101);
    assertTrue(model.getLayout().getListBlocks().size() == 8);
    assertTrue(model.getLayout().getT() > 99.9);
    assertTrue(model.getLayout().getT() < 100.1);
  }

  private Settings getSettings() {
    Settings sett = new Settings();
    sett.addDs("st2_a", 10);
    sett.setDt(1);
    sett.setMaxTime(100);
    sett.setTraceLevel(1);
    return sett;
  }
}
