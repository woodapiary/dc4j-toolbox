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
package info.dc4j.toolbox.block;

import java.util.ArrayList;
import java.util.List;

import info.dc4j.toolbox.connector.Connector;
import info.dc4j.toolbox.element.Element;
import info.dc4j.toolbox.element.ElementImpl;
import info.dc4j.toolbox.layout.Composite;
import info.dc4j.toolbox.model.ModelConstants;

public abstract class BlockImpl extends ElementImpl implements Block {

  protected final BoolSocket[] bY;
  protected final BoolSocket[] bU;
  protected final BoolSocket[] bS;
  protected final DoubleSocket[] dY;
  protected final DoubleSocket[] dU;
  protected final DoubleSocket[] dS;
  protected long step;
  private Composite host;
  protected double dt = ModelConstants.DT;
  protected double t;
  private int order;

  public BlockImpl(int id, String name, int sizeUd, int sizeYd, int sizeUb, int sizeYb, int sizeSd, int sizeSb) {
    super(id, name);
    bY = new BoolSocket[sizeYb];
    bU = new BoolSocket[sizeUb];
    bS = new BoolSocket[sizeSb];
    dY = new DoubleSocket[sizeYd];
    dU = new DoubleSocket[sizeUd];
    dS = new DoubleSocket[sizeSd];
    for (int i = 0; i < sizeYb; i++) {
      bY[i] = new BoolSocket(i, null);
    }
    for (int i = 0; i < sizeUb; i++) {
      bU[i] = new BoolSocket(i, null);
    }
    for (int i = 0; i < sizeSb; i++) {
      bS[i] = new BoolSocket(i, null);
    }

    for (int i = 0; i < sizeUd; i++) {
      dU[i] = new DoubleSocket(i, null);
    }
    for (int i = 0; i < sizeYd; i++) {
      dY[i] = new DoubleSocket(i, null);
    }
    for (int i = 0; i < sizeSd; i++) {
      dS[i] = new DoubleSocket(i, null);
    }
  }

  @Override
  public void setConnector(Connector connector, Block.Port port, int pin) {
    switch (port) {
      case U:
        switch (connector.connectorType()) {
          case BOOL:
            bU[pin].setConnector(connector);
            break;
          case DOUBLE:
            dU[pin].setConnector(connector);
            break;
          default:
            break;
        }
        break;
      case Y:
        switch (connector.connectorType()) {
          case BOOL:
            bY[pin].setConnector(connector);
            break;
          case DOUBLE:
            dY[pin].setConnector(connector);
            break;
          default:
            break;
        }
        break;
      case S:
        switch (connector.connectorType()) {
          case BOOL:
            bS[pin].setConnector(connector);
            break;
          case DOUBLE:
            dS[pin].setConnector(connector);
            break;
          default:
            break;
        }
        break;
      default:
        break;
    }
  }

  protected abstract void eval();

  @Override
  public void run(double maxTime) {
    step++;
    t = t + dt;
    eval();
  }

  @Override
  public String getCanonicalName() {
    if (host != null) {
      return host.getCanonicalName() + "/" + getName();
    }
    return getName();
  }

  @Override
  public double getT() {
    return t;
  }

  @Override
  public void setScanTime(double dt) {
    if (dt <= 0) {
      throw new IllegalArgumentException("dt must be over zero");
    }
    this.dt = dt;
  }

  @Override
  public long getStep() {
    return step;
  }

  @Override
  public Composite getHost() {
    return host;
  }

  @Override
  public void setHost(Composite host) {
    this.host = host;
  }

  @Override
  public List<Composite> getTreeBlocks() {
    return null;
  }

  @Override
  public double getScanTime() {
    return dt;
  }

  @Override
  public void init() {
    t = 0;
    step = 0;
  }

  @Override
  public int getOrder() {
    return order;
  }

  @Override
  public void setOrder(int order) {
    this.order = order;
  }

  @Override
  public Element.Type elementType() {
    return Element.Type.BLOCK;
  }

  @Override
  public boolean isOrdered() {
    return order > 0;
  }

  @Override
  public List<Block> getSourceBlock() {
    List<Block> blocks = new ArrayList<>();
    //TODO add bool
    for (Socket s : dU) {
      Connector c = s.getConnector();
      if (c != null) {
        Block b = c.getSource();
        if (b != null) {
          blocks.add(b);
        }
      }
    }
    return blocks;
  }

  @Override
  public List<Block> getTargetBlock() {
    List<Block> blocks = new ArrayList<>();
    //TODO add bool
    for (Socket s : dY) {
      Connector c = s.getConnector();
      if (c != null) {
        Block b = c.getTarget();
        if (b != null) {
          blocks.add(b);
        }
      }
    }
    return blocks;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("BlockImpl [order=");
    builder.append(order);
    builder.append(super.toString());
    builder.append("]");
    return builder.toString();
  }




}
