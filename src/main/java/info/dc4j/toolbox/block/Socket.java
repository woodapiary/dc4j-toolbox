package info.dc4j.toolbox.block;

import info.dc4j.toolbox.connector.Connector;
import info.dc4j.toolbox.element.DataType;

public interface Socket {
  void setConnector(Connector connector);
  Object get();
  void set(Object value);
  Connector getConnector();
  abstract DataType socketType();
}
