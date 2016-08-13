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
import java.util.HashMap;
import java.util.List;

import info.dc4j.toolbox.block.Block;
import info.dc4j.toolbox.connector.Connector;
import info.dc4j.toolbox.element.Element;
import info.dc4j.toolbox.element.ElementImpl;
import info.dc4j.toolbox.model.ModelConstants;
import info.dc4j.toolbox.model.ModelFactory;
import info.dc4j.toolbox.model.Runnable;

//TODO implements units

public class LayoutImpl extends ElementImpl implements Layout {
  private final ModelFactory factory;
  private final List<Block> blocks = new ArrayList<>();
  private final List<Connector> connectors = new ArrayList<>();
  private final HashMap<Integer, Block> mapBlocks = new HashMap<>();
  private final HashMap<Integer, Connector> mapConnectors = new HashMap<>();
  protected long step;
  protected double dt = ModelConstants.DT;
  protected double t;

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
    step++;
    t = t + dt;
    for (Runnable block : getBlocks()) {
      block.run(maxTime);
    }
  }

  @Override
  public void init() {
    t = 0;
    step = 0;
    for (Runnable block : getBlocks()) {
      block.init();
    }
  }

  @Override
  public void setScanTime(double dt) {
    this.dt = dt;
    for (Runnable block : getBlocks()) {
      block.setScanTime(dt);
    }
  }

  @Override
  public int createBlock(Integer id, String name, Block.Type type, Object param) {
    Block block = factory.createBlock(id, name, type, param);
    blocks.add(block);
    mapBlocks.put(block.getId(), block);
    return block.getId();
  }

  @Override
  public int createConnection(Integer id, String name, int fromId, int toId, int out, int in, Connector.Type type) {
    Block source = getBlock(fromId);
    Block target = getBlock(toId);
    Connector connector = factory.createConnector(id, name, source, target, type);
    connectors.add(connector);
    mapConnectors.put(connector.getId(), connector);
    source.setConnector(connector, Block.Port.Y, out);
    target.setConnector(connector, Block.Port.U, in);
    return connector.getId();
  }

  @Override
  public Object getBlockParameters(int blockId) {
    Block block = getBlock(blockId);
    return block.getParameters();
  }

  @Override
  public void setBlockParameters(int blockId, Object parameters) {
    Block block = getBlock(blockId);
    block.setParameters(parameters);
  }

  @Override
  public Connector getConnector(int id) {
    return mapConnectors.get(id);
  }

  @Override
  public Block getBlock(int id) {
    return mapBlocks.get(id);
  }

  @Override
  public List<Block> getBlocks() {
    return blocks;
  }

  @Override
  public List<Connector> getConnectors() {
    return connectors;
  }

  @Override
  public Element.Type elementType() {
    return Element.Type.LAYOUT;
  }

  @Override
  public double getScanTime() {
    return dt;
  }

  @Override
  public double getT() {
    return t;
  }

  @Override
  public long getStep() {
    return step;
  }


}
