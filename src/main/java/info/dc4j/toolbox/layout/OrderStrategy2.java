package info.dc4j.toolbox.layout;

import java.util.List;

import info.dc4j.toolbox.block.Block;

public class OrderStrategy2 {

  List<Block> layoutBlocks;


  public void execute(List<Block> blocks) {
    int level = 0;
    this.layoutBlocks = blocks;
    for (Block block : layoutBlocks) {
      level ++;
      block.setOrder(level);
    }
    check();
  }

  private void check() {
    for (Block block : layoutBlocks) {
      if (!block.isOrdered()) {
        throw new IllegalStateException("no order for block: " + block.getCanonicalName());
      }
    }
  }


}
