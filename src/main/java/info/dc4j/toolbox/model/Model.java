package info.dc4j.toolbox.model;

import info.dc4j.toolbox.layout.LayoutService;
import info.dc4j.toolbox.monitor.MonitorService;

public interface Model extends Runnable, LayoutService, MonitorService {
  public static final double DT = 0.001;

}