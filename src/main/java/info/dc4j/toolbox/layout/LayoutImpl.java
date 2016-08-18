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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import info.dc4j.toolbox.block.Block;
import info.dc4j.toolbox.block.BlockInfo;
import info.dc4j.toolbox.connector.Connector;
import info.dc4j.toolbox.element.DataType;
import info.dc4j.toolbox.element.Parameter;
import info.dc4j.toolbox.model.ModelFactory;

//TODO implements units

public class LayoutImpl implements Layout {
  private final ModelFactory factory;
  private final List<Block> blocks = new ArrayList<>();
  private final List<Connector> connectors = new ArrayList<>();
  private final HashMap<Integer, Block> mapBlocks = new HashMap<>();
  private final HashMap<Integer, Connector> mapConnectors = new HashMap<>();


  public LayoutImpl(ModelFactory factory) {
    this.factory = factory;
  }

  @Override
  public int createBlock(Integer id, String name, Block.Type type, Object param) {
    Block block = factory.createBlock(id, name, type, param);
    blocks.add(block);
    mapBlocks.put(block.getId(), block);
    return block.getId();
  }

  @Override
  public int createConnection(Integer id, String name, Integer fromId, Integer toId, Integer out, Integer in,
      DataType type) {
    Block source = fromId != null ? getBlock(fromId) : null;
    Block target = toId != null ? getBlock(toId) : null;
    Connector connector = factory.createConnector(id, name, source, target, type);
    connectors.add(connector);
    mapConnectors.put(connector.getId(), connector);
    if (source != null && out != null) {
      source.setConnector(connector, Block.Port.Y, out);
    }
    if (target != null && in != null) {
      target.setConnector(connector, Block.Port.U, in);
    }
    return connector.getId();
  }

  @Override
  public List<Parameter> getBlockParameters(Integer blockId, boolean defaults) {
    // TODO get all on null
    Block block = getBlock(blockId);
    return block.getParameters(defaults);
  }

  @Override
  public void setBlockParameters(Integer blockId, List<Parameter> parameters) {
    // TODO set all on null
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
  public BlockInfo getBlockInfo(int id) {
    return mapBlocks.get(id).getBlockInfo();
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
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("LayoutImpl [");
    if (blocks != null) {
      builder.append("blocks=");
      builder.append(Arrays.toString(blocks.toArray()));
      builder.append(", ");
    }
    if (connectors != null) {
      builder.append("connectors=");
      builder.append(Arrays.toString(connectors.toArray()));
      builder.append(", ");
    }
    builder.append("]");
    return builder.toString();
  }

}
