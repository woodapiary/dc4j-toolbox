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

package info.dc4j.toolbox.block;

import java.util.ArrayList;
import java.util.List;

import info.dc4j.toolbox.block.connector.BoolConnector;
import info.dc4j.toolbox.block.connector.DoubleConnector;
import info.dc4j.toolbox.block.helper.SequenceId;

public abstract class Block {

  private final int id;
  private final String name;
  protected List<DoubleConnector> listY;
  protected List<DoubleConnector> listU;
  protected List<BoolConnector> listYb;
  protected List<BoolConnector> listUb;
  private long step;
  private Block host;
  private int order;
  protected double dt = 0.001;
  protected double t;

  public Block(String name) {
    id = SequenceId.getBlockId();
    this.name = name;
    listY = new ArrayList<>();
    listU = new ArrayList<>();
    listYb = new ArrayList<>();
    listUb = new ArrayList<>();
  }

  public void setY(DoubleConnector y) {
    listY.add(y);
  }

  public void setY(BoolConnector y) {
    listYb.add(y);
  }

  public void setU(DoubleConnector u) {
    listU.add(u);
  }

  public void setU(BoolConnector u) {
    listUb.add(u);
  }

  public DoubleConnector getY(int i) {
    return listY.get(i);
  }

  public DoubleConnector getU(int i) {
    return listU.get(i);
  }

  public BoolConnector getYb(int i) {
    return listYb.get(i);
  }

  public BoolConnector getUb(int i) {
    return listUb.get(i);
  }

  protected abstract void eval();

  public void run() {
    step++;
    t = t + dt;
    eval();
  }

  public String getName() {
    if (host != null) {
      return host.getName() + name;
    }
    return name;
  }

  public double getT() {
    return t;
  }

  public void setDt(double dt) {
    if (dt <= 0) {
      throw new IllegalArgumentException("dt must be over zero");
    }
    this.dt = dt;
  }

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

  public long getStep() {
    return step;
  }

  public Block getHost() {
    return host;
  }

  public void setHost(Block host) {
    this.host = host;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public List<Block> getDescendants() {
    return null;
  }

  public int getId() {
    return id;
  }

  public List<DoubleConnector> getListY() {
    return listY;
  }

  public List<DoubleConnector> getListU() {
    return listU;
  }

  public List<BoolConnector> getListYb() {
    return listYb;
  }

  public List<BoolConnector> getListUb() {
    return listUb;
  }
}
