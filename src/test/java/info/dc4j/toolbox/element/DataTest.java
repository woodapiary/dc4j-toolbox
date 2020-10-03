/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2002-2016 dc4j.info
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package info.dc4j.toolbox.element;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DataTest {

  double delta = 0.005;
  Data data = null;

  @Test(expected = IllegalArgumentException.class)
  public void test01() {
    data = new Data(1, DataType.BOOLEAN, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void test02() {
    data = new Data(1, null, 1.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void test03() {
    data = new Data(1, DataType.BOOLEAN, 1.0);
  }

  @Test
  public void test04() {
    data = new Data(1, DataType.DOUBLE, 1.0);
    assertEquals(1.0, data.getDouble(), delta);
  }

  @Test(expected = IllegalStateException.class)
  public void test05() {
    data = new Data(1, DataType.DOUBLE, 1.0);
    data.getBoolean();
  }

  @Test
  public void test06() {
    data = new Data(1, DataType.BOOLEAN, false);
    assertEquals(false, data.getBoolean());
  }

  @Test(expected = IllegalStateException.class)
  public void test07() {
    data = new Data(1, DataType.BOOLEAN, false);
    data.getInteger();
  }

  @Test
  public void test08() {
    data = new Data(1, DataType.INTEGER, 1);
    long val = 1;
    assertEquals(val, (long) data.getInteger());
  }

  @Test(expected = IllegalStateException.class)
  public void test09() {
    data = new Data(1, DataType.INTEGER, 1);
    data.getDouble();
  }

  @Test
  public void test10() {
    data = new Data(1, DataType.STRING, "test");
    long val = 1;
    assertEquals(val, data.getId());
    assertEquals(DataType.STRING, data.getType());
    assertEquals("test", data.getValue());
    assertEquals("test", data.getString());
  }

}
