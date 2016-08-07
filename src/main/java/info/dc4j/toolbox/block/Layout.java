/**
 * The MIT License
 * Copyright (c) 2002 Ilkka Seppälä
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
/*
 *
 */
package info.dc4j.toolbox.block;

import java.util.HashMap;
import java.util.List;

import info.dc4j.toolbox.block.connector.BoolConnector;
import info.dc4j.toolbox.block.connector.DoubleConnector;

public class Layout extends CompositeBlock {

  private final HashMap<Integer, Block> mapBlocks = new HashMap<Integer, Block>();
  private final HashMap<Integer, DoubleConnector> mapDoubleConnectors = new HashMap<Integer, DoubleConnector>();
  private final HashMap<Integer, BoolConnector> mapBoolConnectors = new HashMap<Integer, BoolConnector>();
  private List<Block> listBlocks;

  /**
   * Instantiates a new layout.
   */
  public Layout() {
    super("");
  }

  /**
   * Builds the.
   */
  public void build() {
    listBlocks = getDescendants();
    int order = 0;
    for (Block block : listBlocks) {
      order++;
      mapBlocks.put(block.getId(), block);
      block.setOrder(order);
    }
  }

  /**
   * Gets the map blocks.
   *
   * @return the map blocks
   */
  public HashMap<Integer, Block> getMapBlocks() {
    return mapBlocks;
  }

  /**
   * Gets the map double connectors.
   *
   * @return the map double connectors
   */
  public HashMap<Integer, DoubleConnector> getMapDoubleConnectors() {
    return mapDoubleConnectors;
  }

  /**
   * Gets the map bool connectors.
   *
   * @return the map bool connectors
   */
  public HashMap<Integer, BoolConnector> getMapBoolConnectors() {
    return mapBoolConnectors;
  }

  /**
   * Gets the list blocks.
   *
   * @return the list blocks
   */
  public List<Block> getListBlocks() {
    return listBlocks;
  }

}
