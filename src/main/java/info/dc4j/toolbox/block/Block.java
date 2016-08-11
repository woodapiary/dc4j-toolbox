package info.dc4j.toolbox.block;

import java.util.List;

import info.dc4j.toolbox.connector.Connectable;
import info.dc4j.toolbox.element.Element;
import info.dc4j.toolbox.layout.Composite;
import info.dc4j.toolbox.model.Runnable;

public interface Block extends Element, Composite, Connectable, Runnable {

  List<Parameter> getParameters();

  void setParameters(List<Parameter> parameters);

}
