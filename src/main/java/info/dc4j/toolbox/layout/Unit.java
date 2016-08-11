package info.dc4j.toolbox.layout;

import java.util.List;

import info.dc4j.toolbox.block.Block;
import info.dc4j.toolbox.connector.Connector;

public interface Unit extends Block {

  void addBlock(Block block);

  void addConnector(Connector connector);

  List<Block> getBlocks();

  List<Connector> getConnectors();

  Connector getConnector(int id);

  Block getBlock(int id);

}