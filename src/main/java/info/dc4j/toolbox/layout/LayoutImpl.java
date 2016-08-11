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

import java.util.List;

import info.dc4j.toolbox.block.Block;
import info.dc4j.toolbox.block.Parameter;
import info.dc4j.toolbox.connector.Connector;
import info.dc4j.toolbox.model.ModelFactory;
import info.dc4j.toolbox.model.Runnable;

public class LayoutImpl extends UnitImpl implements Layout {
  private final ModelFactory factory;

  public LayoutImpl(int id, String name, ModelFactory factory) {
    super(id, name);
    this.factory = factory;
  }

  @Override
  public void build() {
    int order = 0;
    for (Block block : getBlocks()) {
      order++;
      block.setOrder(order);
    }
    // TODO sort list by order
  }

  @Override
  public void run(double maxTime) {
    super.run(maxTime);
    for (Runnable block : getBlocks()) {
      block.run(maxTime);
    }
  }

  @Override
  public void init() {
    super.init();
    for (Runnable block : getBlocks()) {
      block.init();
    }
  }

  @Override
  public void setScanTime(double dt) {
    super.setScanTime(dt);
    for (Runnable block : getBlocks()) {
      block.setScanTime(dt);
    }
  }

  @Override
  public int createBlock(Integer id, String name, String type) {
    Block block = factory.createBlock(id, name, type);
    addBlock(block);
    return block.getId();
  }

  @Override
  public int createConnection(Integer id, String name, int fromId, int toId, int out, int in, String type) {
    Block source = getBlock(fromId);
    Block target = getBlock(toId);
    Connector connector = factory.createConnector(id, name, source, target, type);
    addConnector(connector);
    source.setY(connector, out);
    target.setU(connector, in);
    return connector.getId();
  }

  @Override
  public List<Parameter> getParameters(int blockId) {
    Block block = getBlock(blockId);
    return block.getParameters();
  }

  @Override
  public void setParameters(int blockId, List<Parameter> parameters) {
    Block block = getBlock(blockId);
    block.setParameters(parameters);
  }

}
