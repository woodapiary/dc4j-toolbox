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
package info.dc4j.toolbox.connector;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import info.dc4j.toolbox.block.Block;

public class SplitterTest {

  double delta = 0.005;

  @Test
  public void test01() {
    Splitter block1 = new Splitter(1, "block1");
    DoubleConnector u1 = new DoubleConnector(1, "in1", null, block1);
    DoubleConnector y1 = new DoubleConnector(2, "out1", block1, null);
    DoubleConnector y2 = new DoubleConnector(3, "out2", block1, null);
    block1.setConnector(u1, Block.Port.U, 0);
    block1.setConnector(y1, Block.Port.Y, 0);
    block1.setConnector(y2, Block.Port.Y, 1);
    u1.setValue(2.0);
    for (int i = 1; i < 3000; i++) {
      block1.run(0);
      //System.out.println(y.getValue());
    }
    assertEquals(2, y1.getValue(), delta);
    assertEquals(2, y2.getValue(), delta);
  }
}
