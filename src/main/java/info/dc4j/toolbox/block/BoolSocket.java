package info.dc4j.toolbox.block;

import info.dc4j.toolbox.connector.BoolConnector;

public class BoolSocket {

  private BoolConnector connector;
  private boolean val;

  public void setConnector(BoolConnector connector) {
    if (this.connector == null) {
      this.connector = connector;
    } else {
      throw new IllegalStateException("socket connected already");
    }
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

  public BoolConnector getConnector() {
    return connector;
  }

}