package info.dc4j.toolbox.monitor;

import java.util.List;

public interface MonitorService {

  void setMonitoredConnector(int connectorId);

  List<Integer> getMonitoredConnectors();

  void setTracer(String type);

  List<String> getTracers();

  List<TraceData> getTraceData();

}