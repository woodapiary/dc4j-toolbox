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

import java.util.ArrayList;
import java.util.List;

import info.dc4j.toolbox.block.connector.BoolConnector;
import info.dc4j.toolbox.block.connector.DoubleConnector;

public class CompositeBlock extends Block {

  protected List<Block> childBlocks;

  /**
   * Instantiates a new composite block.
   *
   * @param name the name
   * @param inDouble the in double
   * @param outDouble the out double
   * @param inBool the in bool
   * @param outBool the out bool
   */
  public CompositeBlock(String name, int inDouble, int outDouble, int inBool, int outBool) {
    super(name);
    childBlocks = new ArrayList<Block>();
    for (int i = 0; i < inDouble; i++) {
      setU(new DoubleConnector());
    }
    for (int i = 0; i < outDouble; i++) {
      setY(new DoubleConnector());
    }
    for (int i = 0; i < inBool; i++) {
      setU(new BoolConnector());
    }
    for (int i = 0; i < outBool; i++) {
      setY(new BoolConnector());
    }
  }

  /**
   * Instantiates a new composite block.
   *
   * @param name the name
   */
  public CompositeBlock(String name) {
    this(name, 0, 0, 0, 0);
  }

  /* (non-Javadoc)
   * @see info.dc4j.dc4j_toolbox.block.Block#run()
   */
  @Override
  public void run() {
    super.run();
    for (Block block : childBlocks) {
      block.run();
    }
  }

  @Override
  protected void eval() {

  }

  /**
   * Adds the.
   *
   * @param block the block
   */
  public void add(Block block) {
    childBlocks.add(block);
    block.setHost(this);
  }

  /* (non-Javadoc)
   * @see info.dc4j.dc4j_toolbox.block.Block#getDescendants()
   */
  @Override
  public List<Block> getDescendants() {
    List<Block> res = new ArrayList<Block>();
    for (Block block : childBlocks) {
      List<Block> childList = block.getDescendants();
      if (childList == null) {
        res.add(block);
      } else {
        res.addAll(childList);
        res.add(this);
      }
    }
    return res;
  }

  /* (non-Javadoc)
   * @see info.dc4j.dc4j_toolbox.block.Block#toString()
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    sb.append("[");
    for (Block block : childBlocks) {
      sb.append(block.toString());
    }
    sb.append("]");
    return sb.toString();
  }

}
