package info.dc4j.toolbox.layout;

import java.util.List;

import info.dc4j.toolbox.block.Parameter;

public interface LayoutService {
  void build();

  int createBlock(Integer id, String name, String type);

  int createConnection(Integer id, String name, int fromId, int toId, int out, int in, String type);

  List<Parameter> getParameters(int blockId);

  void setParameters(int blockId, List<Parameter> parameters);

  /*
   * 
   * List<BlockSettings> getBlocksSettings();
   */

}
