package info.dc4j.toolbox.layout;

import java.util.ArrayList;
import java.util.List;

import info.dc4j.toolbox.block.Block;

public class OrderStrategy1 {

  private static final boolean DEBUG = false;

  List<Block> layoutBlocks;
  List<Block> pointers;
  List<Block> snapPointers;
  int level = 0;

  public void execute(List<Block> layoutBlocks) {
    int countOp = 0;
    int flagOut = 0;
    level = 0;
    this.layoutBlocks = layoutBlocks;
    pointers = new ArrayList<>();
    snapPointers(layoutBlocks);
    addStartedBlocksToPointer(countOp); // no source
    level++;
    setLevelOnNewBlocksInPointer(level);
    delAllTargetOrderedBlocksFromPointer(countOp);
    do {
      do {
        countOp = 0;
        snapPointers(pointers);
        // all source done
        countOp = addTargetWhichAllSourceOrderedToPointer(countOp);
        level++;
        setLevelOnNewBlocksInPointer(level);
        countOp = delAllTargetOrderedBlocksFromPointer(countOp);
        //System.out.println(countOp);
      } while (countOp > 0);
      snapPointers(pointers);
      flagOut = addAllNoOrderedTargetsToPointer(); // dead point, add all
      setLevelOnNewBlocksInPointer(level);
    } while (flagOut > 0);
    check();
    sort();
  }

  private int addStartedBlocksToPointer(int count) {
    for (Block pointer : snapPointers) {
      if (getCountSource(pointer) == 0) {
        count = addBlockToPointer(count, pointer);
      }
    }
    printf("add started");
    return count;
  }

  private int addTargetWhichAllSourceOrderedToPointer(int count) {
    for (Block pointer : snapPointers) {
      List<Block> targets = pointer.getTargetBlock();
      for (Block target : targets) {
        if (isAllSourceOrdered(target)) {
          count = addBlockToPointer(count, target);
        }
      }
    }
    printf("add by source");
    return count;
  }

  private int addAllNoOrderedTargetsToPointer() {
    int count = 0;
    for (Block pointer : snapPointers) {
      List<Block> targets = pointer.getTargetBlock();
      for (Block target : targets) {
        if (isNoNullSourceOrdered(target)) {
          count = addBlockToPointer(count, target);
        }
      }
    }
    printf("add dead");
    return count;
  }

  private int delAllTargetOrderedBlocksFromPointer(int count) {
    for (Block block : snapPointers) {
      if (isAllTargetOrdered(block)) {
        pointers.remove(block);
        count++;
      }
    }
    printf("del");
    return count;
  }

  private boolean isAllSourceOrdered(Block b) {
    List<Block> blocks = b.getSourceBlock();
    if (blocks.size() == 0) {
      return false;
    }
    boolean res = true;
    for (Block block : blocks) {
      res = res && block.isOrdered();
    }
    return res;
  }

  private int getCountSource(Block b) {
    return b.getSourceBlock().size();
  }

  private boolean isNoNullSourceOrdered(Block b) {
    List<Block> blocks = b.getSourceBlock();
    if (blocks.size() == 0) {
      return false;
    }
    boolean res = false;
    for (Block block : blocks) {
      res = res || block.isOrdered();
    }
    return res;
  }

  private boolean isAllTargetOrdered(Block b) {
    List<Block> blocks = b.getTargetBlock();
    if (blocks.size() == 0) {
      return true;
    }
    boolean res = true;
    for (Block block : blocks) {
      res = res && block.isOrdered();
    }
    return res;
  }

  private int addBlockToPointer(int count, Block block) {
    if (!block.isOrdered() && !pointers.contains(block)) {
      pointers.add(block);
      count++;
    }
    return count;
  }

  private void setLevelOnNewBlocksInPointer(int level) {
    for (Block pointer : pointers) {
      if (!pointer.isOrdered()) {
        pointer.setOrder(level);
      }
    }

  }

  private void snapPointers(List<Block> blocks) {
    snapPointers = new ArrayList<>(blocks);
  }

  private void sort() {
    // TODO Auto-generated method stub

  }

  private void check() {
    for (Block block : layoutBlocks) {
      if (!block.isOrdered()) {
        throw new IllegalStateException("no order for block: " + block.getCanonicalName());
      }
      if (DEBUG) {
        System.out.print(block.getName() + " - ");
        System.out.print(block.getOrder());
        System.out.print(", ");
      }
    }
    if (DEBUG) {
      System.out.println();
    }

  }

  private void printf(String str) {
    if (DEBUG) {
      System.out.print(str + ": pointer=  ");
      System.out.print(level);
      System.out.print(" -> ");
      for (Block pointer : pointers) {
        System.out.print(pointer.getName() + ", ");
      }
      System.out.println();
    }
  }

  @SuppressWarnings("unused")
  private void printBlocks(List<Block> bs) {
    for (Block b : bs) {
      System.out.print(b.toString() + ",   ");
    }
    System.out.println();
  }

}
