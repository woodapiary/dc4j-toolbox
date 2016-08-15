package info.dc4j.toolbox.block;

import info.dc4j.toolbox.connector.Connector;
import info.dc4j.toolbox.element.Element;
import info.dc4j.toolbox.element.ElementImpl;

public abstract class SocketImpl extends ElementImpl implements Socket {

  private Connector connector;
  private Object value;

  public SocketImpl(int id, String name) {
    super(id, name);
  }

  @Override
  public void setConnector(Connector connector) {
    //TODO check type
    if (this.connector == null) {
      this.connector = connector;
    } else {
      throw new IllegalStateException("socket connected already");
    }
  }

  @Override
  public Object get() {
    if (connector == null || connector.getValue() == null) {
      return value;
    }
    return connector.getValue();
  }

  @Override
  public void set(Object value) {
    //TODO check type
    if (connector != null) {
      connector.setValue(value);
    }
    this.value = value;
  }

  @Override
  public Connector getConnector() {
    return connector;
  }

  @Override
  public Element.Type elementType() {
    return Element.Type.SOCKET;
  }

}
