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

import java.util.List;

import info.dc4j.toolbox.connector.BoolConnector;
import info.dc4j.toolbox.connector.Connector;
import info.dc4j.toolbox.connector.DoubleConnector;
import info.dc4j.toolbox.element.ElementImpl;
import info.dc4j.toolbox.layout.Composite;
import info.dc4j.toolbox.model.ModelConstants;

public abstract class BlockImpl extends ElementImpl implements Block {
  public static final String TYPE = "block";
  protected final BoolSlot[] bY;
  protected final BoolSlot[] bU;
  protected final BoolSlot[] bS;
  protected final DoubleSlot[] dY;
  protected final DoubleSlot[] dU;
  protected final DoubleSlot[] dS;
  protected long step;
  private Composite host;
  protected double dt = ModelConstants.DT;
  protected double t;

  public BlockImpl(int id, String name, int sizeUd, int sizeYd, int sizeUb, int sizeYb, int sizeSd, int sizeSb) {
    super(id, name);
    bY = new BoolSlot[sizeYb];
    bU = new BoolSlot[sizeUb];
    bS = new BoolSlot[sizeSb];
    dY = new DoubleSlot[sizeYd];
    dU = new DoubleSlot[sizeUd];
    dS = new DoubleSlot[sizeSd];
    for (int i = 0; i < sizeYb; i++) {
      bY[i] = new BoolSlot();
    }
    for (int i = 0; i < sizeUb; i++) {
      bU[i] = new BoolSlot();
    }
    for (int i = 0; i < sizeSb; i++) {
      bS[i] = new BoolSlot();
    }
    for (int i = 0; i < sizeUd; i++) {
      dU[i] = new DoubleSlot();
    }
    for (int i = 0; i < sizeYd; i++) {
      dY[i] = new DoubleSlot();
    }
    for (int i = 0; i < sizeSd; i++) {
      dS[i] = new DoubleSlot();
    }
    setType(TYPE);
  }

  @Override
  public void setConnector(Connector connector, Block.Port port, int pin) {
    switch (port) {
      case U:
        switch (connector.type()) {
          case BOOL:
            bU[pin].setConnector((BoolConnector) connector);
            break;
          case DOUBLE:
            dU[pin].setConnector((DoubleConnector) connector);
            break;
          default:
            break;
        }
        break;
      case Y:
        switch (connector.type()) {
          case BOOL:
            bY[pin].setConnector((BoolConnector) connector);
            break;
          case DOUBLE:
            dY[pin].setConnector((DoubleConnector) connector);
            break;
          default:
            break;
        }
        break;
      case S:
        switch (connector.type()) {
          case BOOL:
            bS[pin].setConnector((BoolConnector) connector);
            break;
          case DOUBLE:
            dS[pin].setConnector((DoubleConnector) connector);
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

  public class BoolSlot {

    private BoolConnector connector;
    private boolean val;

    public void setConnector(BoolConnector connector) {
      this.connector = connector;
    }

    public boolean get() {
      if (connector == null || connector.getValue() == null) {
        return val;
      }
      return connector.getValue();
    }

    public void set(boolean value) {
      if (connector != null) {
        connector.setValue(value);
      }
      val = value;
    }

  }

  public class DoubleSlot {

    private DoubleConnector connector;
    private double val;

    public void setConnector(DoubleConnector connector) {
      this.connector = connector;
    }

    public double get() {
      if (connector == null || connector.getValue() == null) {
        return val;
      }
      return connector.getValue();
    }

    public void set(double value) {
      if (connector != null) {
        connector.setValue(value);
      }
      val = value;
    }

  }

}
