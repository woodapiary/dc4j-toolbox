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

import java.io.Serializable;

public class Data implements Serializable {

  private static final long serialVersionUID = -2428130720125168559L;
  private final int id;
  private final DataType type;
  private final Object value;

  public Data(final int id, final DataType type, final Object value) {
    if (type == null) {
      throw new IllegalArgumentException("type is null");
    }
    if (value == null) {
      throw new IllegalArgumentException("value is null");
    }
    if (DataType.getType(value.getClass()) != type) {
      throw new IllegalArgumentException("bad type of value");
    }
    this.value = value;
    this.type = type;
    this.id = id;
  }

  public Object getValue() {
    return value;
  }

  public DataType getType() {
    return type;
  }

  public int getId() {
    return id;
  }

  public Double getDouble() {
    if (type != DataType.DOUBLE) {
      throw new IllegalStateException("bad type of value");
    }
    return (Double) value;
  }

  public Integer getInteger() {
    if (type != DataType.INTEGER) {
      throw new IllegalStateException("bad type of value");
    }
    return (Integer) value;
  }

  public String getString() {
    return value.toString();
  }

  public Boolean getBoolean() {
    if (type != DataType.BOOLEAN) {
      throw new IllegalStateException("bad type of value");
    }
    return (Boolean) value;
  }

}
