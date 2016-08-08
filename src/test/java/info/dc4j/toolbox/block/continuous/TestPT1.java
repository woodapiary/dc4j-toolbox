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
package info.dc4j.toolbox.block.continuous;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import info.dc4j.toolbox.block.connector.DoubleConnector;

public class TestPT1 {

  @Test
  public void testPT1() {
    DoubleConnector in = new DoubleConnector("const");
    in.setValue(1.0);
    PT1 pt = new PT1(in);
    pt.run();
    assertTrue(pt.out().getValue() < 0.1);
    assertTrue(pt.out().getValue() > 0.0);
    for (int i = 1; i < 3000; i++) {
      pt.run();

    }
    assertTrue(pt.out().getValue() < 1.0);
    assertTrue(pt.out().getValue() > 0.9);
  }
}