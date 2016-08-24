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
import info.dc4j.toolbox.model.Model;

public abstract class BlockImpl extends ElementImpl implements Block {

  protected final Port y;
  protected final Port u;
  protected final Port s;
  protected long step;
  private Composite host;
  protected double dt = Model.DT;
  protected double t;
  private int order;

  public BlockImpl(int id, String name, int sizeUd, int sizeYd, int sizeUb, int sizeYb, int sizeSd, int sizeSb) {
    super(id, name);
    y = new PortImpl(sizeYd, sizeYb);
    u = new PortImpl(sizeUd, sizeUb);
    s = new PortImpl(sizeSd, sizeSb);

  }

  @Override
  public void setConnector(Connector connector, Block.PortType port, int pin) {
    switch (port) {
      case U:
        u.setConnector(connector, pin);
        break;
      case Y:
        y.setConnector(connector, pin);
        break;
      case S:
        s.setConnector(connector, pin);
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
    for (Socket s : u.getSockets()) {
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
    for (Socket s : y.getSockets()) {
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
  public BlockInfo getBlockInfo() {
    return new BlockInfo(this);
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

  class PortImpl implements Port {

    public final BoolSocket[] b;
    protected final DoubleSocket[] d;

    public PortImpl(int sizeD, int sizeB) {
      b = new BoolSocket[sizeB];
      d = new DoubleSocket[sizeD];
      for (int i = 0; i < sizeB; i++) {
        b[i] = new BoolSocket(i, null);
      }

      for (int i = 0; i < sizeD; i++) {
        d[i] = new DoubleSocket(i, null);
      }
    }

    @Override
    public BoolSocket[] getB() {
      return b;
    }

    @Override
    public DoubleSocket[] getD() {
      return d;
    }

    @Override
    public void setConnector(Connector connector, int pin) {
      switch (connector.connectorType()) {
        case BOOLEAN:
          b[pin].setConnector(connector);
          break;
        case DOUBLE:
          d[pin].setConnector(connector);
          break;
        default:
          break;
      }
    }

    @Override
    public List<Socket> getSockets() {
      List<Socket> res = new ArrayList<Socket>();
      for (Socket s : b) {
        res.add(s);
      }
      for (Socket s : d) {
        res.add(s);
      }
      return res;
    }
  }

}
