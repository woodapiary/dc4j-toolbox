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
import info.dc4j.toolbox.block.helper.SequenceId;

public abstract class Connector {

  private final int id;
  private final String name;
  private final Block source;
  protected Connector chain;

  public Connector(Block source, String name) {
    id = SequenceId.getConnId();
    this.name = name;
    this.source = source;
  }

  public int getId() {
    if (chain != null) {
      return chain.getId();
    }
    return id;
  }

  public String getSimpleName() {
    if (chain != null) {
      return chain.getSimpleName();
    }
    return name;
  }

  public String getName() {
    if (chain != null) {
      return chain.getName();
    }
    if (source != null) {
      return source.getName() + name;
    }
    return name;
  }

  public void setChain(Connector chain) {
    this.chain = chain;
  }

  @Override
  public String toString() {
    return "\n" + this.getClass().getSimpleName() + " - " + getName() + ": " + " id=" + id;
  }

}
