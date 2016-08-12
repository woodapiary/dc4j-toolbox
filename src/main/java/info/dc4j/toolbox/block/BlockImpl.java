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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.dc4j.toolbox.connector.Connector;
import info.dc4j.toolbox.connector.DoubleConnector;
import info.dc4j.toolbox.element.ElementImpl;
import info.dc4j.toolbox.layout.Composite;
import info.dc4j.toolbox.model.ModelConstants;

public abstract class BlockImpl extends ElementImpl implements Block {
  public static final String TYPE = "block";
  private final List<Connector> listY;
  private final List<Connector> listU;
  private final Map<String, Parameter> parameters = new HashMap<>();
  private long step;
  private Composite host;
  private double dt = ModelConstants.DT;
  private double t;
  private final int sizeU;
  private final int sizeY;

  //TODO get default parameters
  public BlockImpl(int id, String name, int sizeU, int sizeY) {
    super(id, name);
    this.sizeU = sizeU;
    this.sizeY = sizeY;
    listU = Arrays.asList(new Connector[sizeU]);
    listY = Arrays.asList(new Connector[sizeY]);
  }

  protected void setParameter(String key, Object value) {
    parameters.get(key).setValue(value);
  }

  protected void addParameter(String id, Object value, String type) {
    Parameter p = new Parameter(id, type, value);
    parameters.put(id, p);
  }

  protected Boolean getBooleanParameter(String key) {
    return parameters.get(key).getBoolean();
  }

  protected Double getDoubleParameter(String key) {
    return parameters.get(key).getDouble();
  }

  protected Integer getIntegerParameter(String key) {
    return parameters.get(key).getInteger();
  }

  protected String getStringParameter(String key) {
    return parameters.get(key).getString();
  }

  protected Double getDoubleY(int out) {
    DoubleConnector y = (DoubleConnector) getY(out);
    return y.getValue();
  }

  protected Double getDoubleU(int in) {
    DoubleConnector u = (DoubleConnector) getU(in);
    return u.getValue();
  }

  protected void setValueY(int out, Object value) {
    DoubleConnector y = (DoubleConnector) getY(out);
    y.setValue(value);
  }

  @Override
  public void setParameters(List<Parameter> params) {
    for (Parameter param : params) {
      setParameter(param.getId(), param.getValue());
    }
  }

  @Override
  public List<Parameter> getParameters() {
    return new ArrayList<Parameter>(parameters.values());
  }

  @Override
  public void setY(Connector y, int out) {
    listY.set(out, y);
  }

  @Override
  public void addY(Connector y) {
    listY.add(y);
  }

  @Override
  public void setU(Connector u, int in) {
    listU.set(in, u);
  }

  @Override
  public void addU(Connector u) {
    listU.add(u);
  }

  @Override
  public Connector getY(int out) {
    return listY.get(out);
  }

  @Override
  public Connector getU(int in) {
    return listU.get(in);
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
  public List<Connector> getListY() {
    return listY;
  }

  @Override
  public List<Connector> getListU() {
    return listU;
  }

  @Override
  public String getType() {
    return TYPE;
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

  public int getSizeU() {
    return sizeU;
  }

  public int getSizeY() {
    return sizeY;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Block [");
    if (listY != null) {
      builder.append("listY=");
      builder.append(listY);
      builder.append(", ");
    }
    if (listU != null) {
      builder.append("listU=");
      builder.append(listU);
      builder.append(", ");
    }
    builder.append("step=");
    builder.append(step);
    builder.append(", ");
    if (host != null) {
      builder.append("host=");
      builder.append(host.getCanonicalName());
      builder.append(", ");
    }
    builder.append(", dt=");
    builder.append(dt);
    builder.append(", t=");
    builder.append(t);
    builder.append(super.toString());
    builder.append("]");
    return builder.toString();
  }

}
