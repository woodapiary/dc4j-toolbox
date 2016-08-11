package info.dc4j.toolbox.connector;

import java.util.List;

import info.dc4j.toolbox.element.Element;

public interface Connectable extends Element {

  void setY(Connector y, int out);

  void setU(Connector u, int in);

  Connector getY(int out);

  Connector getU(int in);

  List<Connector> getListY();

  List<Connector> getListU();

}