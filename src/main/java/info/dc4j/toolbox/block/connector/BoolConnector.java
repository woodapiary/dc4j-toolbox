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
package info.dc4j.toolbox.block.connector;

import info.dc4j.toolbox.block.Block;
import info.dc4j.toolbox.model.rw.BoolData;

public class BoolConnector extends Connector {

  private boolean value;

  public BoolConnector(Block source, String name) {
    super(source, name);
  }

  public BoolConnector(String name) {
    super(null, name);
  }

  public BoolConnector() {
    super(null, null);
  }

  public boolean getValue() {
    if (chain != null) {
      return getChain().getValue();
    }
    return value;
  }

  public void setValue(boolean value) {
    this.value = value;
  }

  public BoolConnector getChain() {
    return (BoolConnector) chain;
  }

  public BoolData getData() {
    return new BoolData(getName(), value);
  }

  @Override
  public String toString() {
    return super.toString() + " " + getValue();
  }

}
