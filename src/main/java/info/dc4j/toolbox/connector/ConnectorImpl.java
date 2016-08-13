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
package info.dc4j.toolbox.connector;

import info.dc4j.toolbox.element.Element;
import info.dc4j.toolbox.element.ElementImpl;
import info.dc4j.toolbox.monitor.Data;

public abstract class ConnectorImpl extends ElementImpl implements Connector {

  private final Connectable source;
  private final Connectable target;
  private Object value;

  public ConnectorImpl(int id, String name, Connectable source, Connectable target) {
    super(id, name);
    this.source = source;
    this.target = target;
  }

  @Override
  public Object getValue() {
    return value;
  }

  @Override
  public void setValue(Object value) {
    this.value = value;
  }

  @Override
  public String getCanonicalName() {
    String sourceName = (source != null) ? source.getCanonicalName() : "source";
    String targetName = (target != null) ? target.getCanonicalName() : "target";
    return sourceName + "/" + getName() + "/" + targetName;
  }

  @Override
  public Connectable getSource() {
    return source;
  }

  @Override
  public Connectable getTarget() {
    return target;
  }

  @Override
  public Data getData() {
    return new Data(getId(), connectorType().name(), value);
  }

  @Override
  public Element.Type elementType() {
    return Element.Type.CONNECTOR;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Connector [");
    if (source != null) {
      builder.append("source=");
      builder.append(source.getCanonicalName());
      builder.append(", ");
    }
    if (target != null) {
      builder.append("target=");
      builder.append(target.getCanonicalName());
      builder.append(", ");
    }
    builder.append(super.toString());
    builder.append("]");
    return builder.toString();
  }

}
