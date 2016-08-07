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
import info.dc4j.toolbox.block.helper.SequenceId;

// TODO: Auto-generated Javadoc
/**
 * The Class Block.
 */
public abstract class Block {

  /** The id. */
  private final int id;
  
  /** The name. */
  private final String name;
  
  /** The list Y. */
  protected List<DoubleConnector> listY;
  
  /** The list U. */
  protected List<DoubleConnector> listU;
  
  /** The list yb. */
  protected List<BoolConnector> listYb;
  
  /** The list ub. */
  protected List<BoolConnector> listUb;
  
  /** The step. */
  private long step;
  
  /** The host. */
  private Block host;
  
  /** The order. */
  private int order;
  
  /** The dt. */
  protected double dt = 0.001;
  
  /** The t. */
  protected double t;

  /**
   * Instantiates a new block.
   *
   * @param name the name
   */
  public Block(String name) {
    this.id = SequenceId.getBlockId();
    this.name = name;
    listY = new ArrayList<DoubleConnector>();
    listU = new ArrayList<DoubleConnector>();
    listYb = new ArrayList<BoolConnector>();
    listUb = new ArrayList<BoolConnector>();
  }

  /**
   * Sets the y.
   *
   * @param y the new y
   */
  public void setY(DoubleConnector y) {
    listY.add(y);
  }

  /**
   * Sets the y.
   *
   * @param y the new y
   */
  public void setY(BoolConnector y) {
    listYb.add(y);
  }

  /**
   * Sets the u.
   *
   * @param u the new u
   */
  public void setU(DoubleConnector u) {
    listU.add(u);
  }

  /**
   * Sets the u.
   *
   * @param u the new u
   */
  public void setU(BoolConnector u) {
    listUb.add(u);
  }

  /**
   * Gets the y.
   *
   * @param i the i
   * @return the y
   */
  public DoubleConnector getY(int i) {
    return listY.get(i);
  }

  /**
   * Gets the u.
   *
   * @param i the i
   * @return the u
   */
  public DoubleConnector getU(int i) {
    return listU.get(i);
  }

  /**
   * Gets the yb.
   *
   * @param i the i
   * @return the yb
   */
  public BoolConnector getYb(int i) {
    return listYb.get(i);
  }

  /**
   * Gets the ub.
   *
   * @param i the i
   * @return the ub
   */
  public BoolConnector getUb(int i) {
    return listUb.get(i);
  }

  /**
   * Eval.
   */
  protected abstract void eval();

  /**
   * Run.
   */
  public void run() {
    step++;
    t = t + dt;
    eval();
  }

  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    if (host != null) {
      return host.getName() + name;
    }
    return name;
  }

  /**
   * Gets the t.
   *
   * @return the t
   */
  public double getT() {
    return t;
  }

  /**
   * Sets the dt.
   *
   * @param dt the new dt
   */
  public void setDt(double dt) {
    if (dt <= 0) {
      throw new IllegalArgumentException("dt must be over zero");
    }
    this.dt = dt;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\n" + this.getClass().getSimpleName() + " - " + getName() + ": " + " id=" + id + ", order=" + getOrder()
        + ", step=" + getStep());
    sb.append("{");
    for (DoubleConnector conn : listU) {
      sb.append(conn.toString());
    }
    for (DoubleConnector conn : listY) {
      sb.append(conn.toString());
    }
    for (BoolConnector conn : listUb) {
      sb.append(conn.toString());
    }
    for (BoolConnector conn : listUb) {
      sb.append(conn.toString());
    }
    sb.append("}");
    return sb.toString();
  }

  /**
   * Gets the step.
   *
   * @return the step
   */
  public long getStep() {
    return step;
  }

  /**
   * Gets the host.
   *
   * @return the host
   */
  public Block getHost() {
    return host;
  }

  /**
   * Sets the host.
   *
   * @param host the new host
   */
  public void setHost(Block host) {
    this.host = host;
  }

  /**
   * Gets the order.
   *
   * @return the order
   */
  public int getOrder() {
    return order;
  }

  /**
   * Sets the order.
   *
   * @param order the new order
   */
  public void setOrder(int order) {
    this.order = order;
  }

  /**
   * Gets the descendants.
   *
   * @return the descendants
   */
  public List<Block> getDescendants() {
    return null;
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * Gets the list Y.
   *
   * @return the list Y
   */
  public List<DoubleConnector> getListY() {
    return listY;
  }

  /**
   * Gets the list U.
   *
   * @return the list U
   */
  public List<DoubleConnector> getListU() {
    return listU;
  }

  /**
   * Gets the list yb.
   *
   * @return the list yb
   */
  public List<BoolConnector> getListYb() {
    return listYb;
  }

  /**
   * Gets the list ub.
   *
   * @return the list ub
   */
  public List<BoolConnector> getListUb() {
    return listUb;
  }
}
