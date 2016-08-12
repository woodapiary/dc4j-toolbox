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
package info.dc4j.toolbox.block.continuous;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import info.dc4j.toolbox.block.Parameter;
import info.dc4j.toolbox.connector.DoubleConnector;
import info.dc4j.toolbox.element.TypeEnum;

public class IntegratorTest {

  @Test
  public void test01() {
    Integrator block1 = new Integrator(1, "block1");
    DoubleConnector u = new DoubleConnector(1, "in", null, block1);
    DoubleConnector y = new DoubleConnector(2, "out", block1, null);
    block1.setU(u, 0);
    block1.setY(y, 0);
    Parameter p = new Parameter(Integrator.TI_NAME, TypeEnum.DOUBLE, 2.0);
    block1.setParameters(Arrays.asList(p));
    u.setValue(1.0);
    for (int i = 1; i < 3000; i++) {
      block1.run(0);
      //System.out.println(y.getValue());
    }
    assertTrue(y.getValue() > 1.49);
    assertTrue(y.getValue() < 1.51);

  }
}
