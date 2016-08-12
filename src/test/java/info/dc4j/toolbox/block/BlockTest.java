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

import java.util.Arrays;

import org.junit.Test;

import info.dc4j.toolbox.connector.DoubleConnector;
import info.dc4j.toolbox.element.TypeEnum;

public class BlockTest {

  @Test
  public void test01() {
    EmptyBlock block1 = new EmptyBlock(1, "block1");
    DoubleConnector u1 = new DoubleConnector(1, "in1", null, block1);
    DoubleConnector y1 = new DoubleConnector(2, "out1", block1, null);
    block1.setU(u1, 0);
    block1.setY(y1, 0);
    block1.run(0);
    System.out.println(u1.getValue());
    System.out.println(y1.getValue());
    u1.setValue(5.0);
    block1.run(0);
    System.out.println(u1.getValue());
    System.out.println(y1.getValue());
    Parameter p = new Parameter("d", TypeEnum.DOUBLE, 10.0);
    block1.setParameters(Arrays.asList(p));
    block1.run(0);
    System.out.println(u1.getValue());
    System.out.println(y1.getValue());

    System.out.println(block1.getId());
    System.out.println(block1.getName());
    System.out.println(block1.getOrder());
    System.out.println(block1.getScanTime());
    System.out.println(block1.getStep());
    System.out.println(block1.getCanonicalName());
    System.out.println(block1.getDesc());
    System.out.println(block1.getT());
    System.out.println(block1.getType());
  }

}
