package info.dc4j.toolbox.element;

public interface Element extends Typable {

  int getId();

  String getName();

  void setName(String name);

  String getDesc();

  void setDesc(String desc);

  String getCanonicalName();

  int getOrder();

  void setOrder(int order);

}