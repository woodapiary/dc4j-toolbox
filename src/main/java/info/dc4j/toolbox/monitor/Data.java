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
package info.dc4j.toolbox.monitor;

import java.io.Serializable;

public class Data implements Serializable {

  private static final long serialVersionUID = -2428130720125168559L;
  private final Integer id;
  private final String type; //TODO enum
  private Object value;

  public Data(Integer id, String type, Object value) {
    this.value = value;
    this.type = type;
    this.id = id;
  }

  public Object getValue() {
    return value;
  }

  public String getType() {
    return type;
  }

  public Integer getId() {
    return id;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public Double getDouble() {
    return (Double) value;
  }

  public Integer getInteger() {
    return (Integer) value;
  }

  public String getString() {
    return (String) value;
  }

  public Boolean getBoolean() {
    return (Boolean) value;
  }

}
