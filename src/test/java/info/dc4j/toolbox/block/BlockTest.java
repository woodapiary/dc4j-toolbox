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
package info.dc4j.toolbox.block;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import info.dc4j.toolbox.connector.BoolConnector;
import info.dc4j.toolbox.connector.DoubleConnector;

public class BlockTest {

  double delta = 0.005;

  @Test
  public void test01() {
    EmptyBlock block1 = new EmptyBlock(1, "block1");
    DoubleConnector u1 = new DoubleConnector(1, "in1", null, block1);
    DoubleConnector y1 = new DoubleConnector(2, "out1", block1, null);
    block1.setConnector(u1, Block.Port.U, 0);
    block1.setConnector(y1, Block.Port.Y, 0);
    block1.run(0);
    assertEquals(0, u1.getValue(), delta);
    assertEquals(0, y1.getValue(), delta);
    u1.setValue(5.0);
    block1.run(0);
    assertEquals(5, u1.getValue(), delta);
    assertEquals(5, y1.getValue(), delta);
    block1.setD(10.0);
    block1.run(0);
    assertEquals(5, u1.getValue(), delta);
    assertEquals(50, y1.getValue(), delta);
  }

  @Test
  public void test02() {
    EmptyBlock block1 = new EmptyBlock(1, "block1");
    assertEquals(1, block1.getId());
    assertEquals("block1", block1.getName());
    assertEquals(0, block1.getOrder());
    assertEquals(0.001, block1.getScanTime(), delta);
    assertEquals("block1", block1.getCanonicalName());
    assertEquals("empty block", block1.getDesc());
    assertEquals("USER", block1.blockType().name());
    block1.run(0);
    assertEquals(1, block1.getStep());
    assertEquals(0.001, block1.getT(), delta);
  }

  @Test
  public void test03() {
    EmptyBlock block1 = new EmptyBlock(1, "block1");
    BoolConnector u1 = new BoolConnector(1, "in1", null, block1);
    BoolConnector y1 = new BoolConnector(2, "out1", block1, null);
    block1.setConnector(u1, Block.Port.U, 0);
    block1.setConnector(y1, Block.Port.Y, 0);
    block1.run(0);
    assertFalse(u1.getValue());
    assertFalse(y1.getValue());
    u1.setValue(true);
    block1.run(0);
    assertTrue(u1.getValue());
    assertFalse(y1.getValue());
    block1.setB(true);
    block1.run(0);
    assertTrue(u1.getValue());
    assertTrue(y1.getValue());
  }

}
