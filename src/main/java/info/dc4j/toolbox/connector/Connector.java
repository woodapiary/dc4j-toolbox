package info.dc4j.toolbox.connector;

import info.dc4j.toolbox.element.Element;
import info.dc4j.toolbox.monitor.Data;

public interface Connector extends Element {

  Object getValue();

  void setValue(Object value);

  Connectable getSource();

  Connectable getTarget();

  Data getData();

}