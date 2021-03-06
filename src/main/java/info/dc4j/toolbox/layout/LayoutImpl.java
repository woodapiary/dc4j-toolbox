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
package info.dc4j.toolbox.layout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import info.dc4j.toolbox.block.Block;
import info.dc4j.toolbox.block.Block.Type;
import info.dc4j.toolbox.block.BlockInfo;
import info.dc4j.toolbox.connector.Connector;
import info.dc4j.toolbox.element.DataType;
import info.dc4j.toolbox.element.Parameter;
import info.dc4j.toolbox.model.Model;

//TODO implements units

public class LayoutImpl implements Layout {
  private final List<Block> blocks = new ArrayList<>();
  private final List<Connector> connectors = new ArrayList<>();
  private final HashMap<Integer, Block> mapBlocks = new HashMap<>();
  private final HashMap<Integer, Connector> mapConnectors = new HashMap<>();

  public LayoutImpl() {

  }

  @Override
  public int createBlock(final int id, final String name, final Block.Type type) {
    return createBlock(id, name, type, null);
  }

  private int createBlock(final Integer id, final String name, final Block.Type type, final Object param) {
    final Block block = Model.getFactory().createBlock(id, name, type, param);
    blocks.add(block);
    mapBlocks.put(block.getId(), block);
    return block.getId();
  }

  @Override
  public int createBlock(final String name, final Type type) {
    return createBlock(null, name, type, null);
  }

  @Override
  public int createConnection(final int id, final String name, final Integer fromId, final Integer toId,
      final Integer out, final Integer in, final DataType type) {
    return createConnection1(id, name, fromId, toId, out, in, type);
  }

  @Override
  public int createConnection(final String name, final Integer fromId, final Integer toId, final Integer out,
      final Integer in, final DataType type) {
    return createConnection1(null, name, fromId, toId, out, in, type);
  }

  private int createConnection1(final Integer id, final String name, final Integer fromId, final Integer toId,
      final Integer out, final Integer in, final DataType type) {
    final Block source = fromId != null ? getBlock(fromId) : null;
    final Block target = toId != null ? getBlock(toId) : null;
    final Connector connector = Model.getFactory().createConnector(id, name, source, target, type);
    connectors.add(connector);
    mapConnectors.put(connector.getId(), connector);
    if ((source != null) && (out != null)) {
      source.setConnector(connector, Block.PortType.Y, out);
    }
    if ((target != null) && (in != null)) {
      target.setConnector(connector, Block.PortType.U, in);
    }
    return connector.getId();
  }

  @Override
  public List<Parameter> getBlockParameters(final int blockId, final boolean defaults) {
    final Block block = getBlock(blockId);
    return block.getParameters(defaults);
  }

  @Override
  public void setBlockParameters(final int blockId, final List<Parameter> parameters) {
    final Block block = getBlock(blockId);
    block.setParameters(parameters);
  }

  @Override
  public Connector getConnector(final int id) {
    return mapConnectors.get(id);
  }

  @Override
  public Block getBlock(final int id) {
    return mapBlocks.get(id);
  }

  @Override
  public BlockInfo getBlockInfo(final int id) {
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
    final StringBuilder builder = new StringBuilder();
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
