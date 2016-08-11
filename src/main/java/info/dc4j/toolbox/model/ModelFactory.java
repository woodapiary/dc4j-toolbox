package info.dc4j.toolbox.model;

import info.dc4j.toolbox.block.Block;
import info.dc4j.toolbox.connector.Connector;
import info.dc4j.toolbox.monitor.Tracer;

public interface ModelFactory {

  Block createBlock(Integer id, String name, String type);

  Model createModel();

  Connector createConnector(Integer id, String name, Block source, Block target, String type);

  Tracer createTracer(String type);

}