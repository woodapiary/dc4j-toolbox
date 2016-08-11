package info.dc4j.toolbox.block;

import java.io.Serializable;

public class Parameter implements Serializable {

  private static final long serialVersionUID = -2428130820125168559L;
  private final String id;
  private final String type;
  private Object value;

  public Parameter(String id, String type, Object value) {
    this.value = value;
    this.type = type;
    this.id = id;
  }

  public Object getValue() {
    return value;
  }

  public String getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public Double getDouble() {
    return (Double) value;
  }

  public Integer getInteger() {
    return (Integer) value;
  }

  public String getString() {
    return (String) value;
  }

  public Boolean getBoolean() {
    return (Boolean) value;
  }

}
