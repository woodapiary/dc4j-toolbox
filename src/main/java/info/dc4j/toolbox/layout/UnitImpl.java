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
import info.dc4j.toolbox.block.BlockImpl;
import info.dc4j.toolbox.connector.Connector;

public class UnitImpl extends BlockImpl implements Unit {
  public static final String TYPE = "composite";
  private final List<Block> blocks = new ArrayList<>();
  private final List<Connector> connectors = new ArrayList<>();
  private final HashMap<Integer, Block> mapBlocks = new HashMap<>();
  private final HashMap<Integer, Connector> mapConnectors = new HashMap<>();

  public UnitImpl(int id, String name) {
    super(id, name,0,0);
  }

  @Override
  protected void eval() {

  }

  @Override
  public void addBlock(Block block) {
    blocks.add(block);
    mapBlocks.put(block.getId(), block);
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
  public List<Block> getBlocks() {
    return blocks;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("CompositeBlock [childBlocks=");
    builder.append(blocks);
    builder.append(super.toString());
    builder.append("]");
    return builder.toString();
  }

  @Override
  public String getType() {
    return TYPE;
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
  public void addConnector(Connector connector) {
    connectors.add(connector);
    mapConnectors.put(connector.getId(), connector);

  }

  @Override
  public List<Connector> getConnectors() {
    return connectors;
  }

}
