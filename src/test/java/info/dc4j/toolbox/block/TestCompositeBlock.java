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
package info.dc4j.toolbox.block;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import info.dc4j.toolbox.block.continuous.PT1;
import info.dc4j.toolbox.block.source.Step;

public class TestCompositeBlock {

  @Test
  public void testComposite() {
    CompositeBlock comp = new CompositeBlock("model", 0, 1, 0, 0);
    Step step = new Step();
    comp.add(step);
    PT1 pt = new PT1(step.out());
    comp.add(pt);
    comp.getY(0).setChain(pt.out());
    for (int i = 0; i < 3000; i++) {
      comp.run();

    }
    assertTrue(comp.getY(0).getValue() < 1.0);
    assertTrue(comp.getY(0).getValue() > 0.8);
  }
}
