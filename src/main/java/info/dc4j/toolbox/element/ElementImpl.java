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
package info.dc4j.toolbox.element;

public abstract class ElementImpl implements Element {
  public static final String DESC = "no description";
  private final int id;
  private String name;
  private String desc;
  private int order;

  public ElementImpl(int id, String name) {
    this.id = id;
    if (name == null) {
      this.name = getType() + "-" + ((Integer) id).toString();
    } else {
      this.name = name;
    }
    desc = DESC;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("name must be not null");
    }
    this.name = name;
  }

  @Override
  public abstract String getType();

  @Override
  public String getDesc() {
    return desc;
  }

  @Override
  public void setDesc(String desc) {
    this.desc = desc;
  }

  @Override
  public int getOrder() {
    return order;
  }

  @Override
  public void setOrder(int order) {
    this.order = order;
  }

  @Override
  public abstract String getCanonicalName();

}
