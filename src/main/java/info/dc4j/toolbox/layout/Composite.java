package info.dc4j.toolbox.layout;

import java.util.List;

import info.dc4j.toolbox.element.Element;

public interface Composite extends Element {

  Composite getHost();

  void setHost(Composite host);

  List<Composite> getTreeBlocks();

}