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

package info.dc4j.toolbox.block.math;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import info.dc4j.toolbox.block.source.Step;

public class TestSum {

  @Test
  public void testAdder() {
    Step spUa = new Step("Ua");
    spUa.setT0(1.0);
    Step spUb = new Step("Ub");
    spUb.setT0(2.0);
    Sum sU = new Sum("sU", spUa.out(), spUb.out());
    spUa.run();
    spUb.run();
    sU.run();
    assertTrue(sU.out().getValue() == 0.0);
    for (int i = 1; i < 1500; i++) {
      spUa.run();
      spUb.run();
      sU.run();

    }
    assertTrue(sU.out().getValue() == 1.0);
    for (int i = 1500; i < 3000; i++) {
      spUa.run();
      spUb.run();
      sU.run();
    }
    assertTrue(sU.out().getValue() == 2.0);
  }
}
