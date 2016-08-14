package info.dc4j.toolbox.block;

import info.dc4j.toolbox.connector.DoubleConnector;

public class DoubleSocket {

  private DoubleConnector connector;
  private double val;

  public void setConnector(DoubleConnector connector) {
    if (this.connector == null) {
      this.connector = connector;
    } else {
      throw new IllegalStateException("socket connected already");
    }
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

  public DoubleConnector getConnector() {
    return connector;
  }

}