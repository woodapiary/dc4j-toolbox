package info.dc4j.toolbox.element;

public abstract class ElementImpl implements Element {
  public static final String DESC = "no description";
  private final int id;
  private String name;
  private String desc;
  private int order;

  public ElementImpl(int id, String name) {
    this.id = id;
    if (name == null) {
      this.name = getType() + "-" + ((Integer) id).toString();
    } else {
      this.name = name;
    }
    desc = DESC;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("name must be not null");
    }
    this.name = name;
  }

  @Override
  public abstract String getType();

  @Override
  public String getDesc() {
    return desc;
  }

  @Override
  public void setDesc(String desc) {
    this.desc = desc;
  }

  @Override
  public int getOrder() {
    return order;
  }

  @Override
  public void setOrder(int order) {
    this.order = order;
  }

  @Override
  public abstract String getCanonicalName();

}
