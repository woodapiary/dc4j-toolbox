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
package info.dc4j.toolbox.layout;

import java.util.ArrayList;
import java.util.List;

import info.dc4j.toolbox.element.Element;
import info.dc4j.toolbox.element.ElementImpl;

public class UnitImpl extends ElementImpl implements Unit {

  private final List<Composite> blocks = new ArrayList<>();
  private Composite host;

  public UnitImpl(int id, String name) {
    super(id, name);
  }

  public void addBlock(Composite block) {
    blocks.add(block);
    block.setHost(this);
  }

  @Override
  public List<Composite> getTreeBlocks() {
    List<Composite> res = new ArrayList<>();
    for (Composite block : blocks) {
      List<Composite> childList = block.getTreeBlocks();
      if (childList == null) {
        res.add(block);
      } else {
        res.addAll(childList);
        res.add(block);
      }
    }
    return res;
  }

  @Override
  public Type typeElement() {
    return Element.Type.UNIT;
  }

  @Override
  public Composite getHost() {
    return host;
  }

  @Override
  public void setHost(Composite host) {
    this.host = host;
  }

}
