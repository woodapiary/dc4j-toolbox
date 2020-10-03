/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2002-2016 dc4j.info
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package info.dc4j.toolbox.model;

import java.util.ArrayList;
import java.util.List;

import info.dc4j.toolbox.block.Block;

public class OrderStrategyByWave implements OrderStrategy {

  private static final boolean DEBUG = false;

  private List<Block> pointers;
  private List<Block> snapPointers;
  private int level = 0;

  @Override
  public void execute(final List<Block> layoutBlocks) {
    int countOp = 0;
    int flagOut = 0;
    level = 0;
    pointers = new ArrayList<>();
    snapPointers(layoutBlocks);
    addStartedBlocksToPointer(countOp); // no source
    level++;
    setLevelOnNewBlocksInPointer(level);
    delAllTargetOrderedBlocksFromPointer(countOp);

    do {
      countOp = 0;
    } while (countOp > 0);

    do {
      do {
        countOp = 0;
        snapPointers(pointers);
        // all source done
        countOp = addTargetWhichAllSourceOrderedToPointer(countOp);
        level++;
        setLevelOnNewBlocksInPointer(level);
        countOp = delAllTargetOrderedBlocksFromPointer(countOp);
        // System.out.println(countOp);
      } while (countOp > 0);
      snapPointers(pointers);
      flagOut = addAllNoOrderedTargetsToPointer(); // dead point, add all
      setLevelOnNewBlocksInPointer(level);
    } while (flagOut > 0);
  }

  private int addStartedBlocksToPointer(int count) {
    for (final Block pointer : snapPointers) {
      if (getCountSource(pointer) == 0) {
        count = addBlockToPointer(count, pointer);
      }
    }
    printf("add started");
    return count;
  }

  private int addTargetWhichAllSourceOrderedToPointer(int count) {
    for (final Block pointer : snapPointers) {
      final List<Block> targets = pointer.getTargetBlock();
      for (final Block target : targets) {
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
    for (final Block pointer : snapPointers) {
      final List<Block> targets = pointer.getTargetBlock();
      for (final Block target : targets) {
        if (isNoNullSourceOrdered(target)) {
          count = addBlockToPointer(count, target);
        }
      }
    }
    printf("add dead");
    return count;
  }

  private int delAllTargetOrderedBlocksFromPointer(int count) {
    for (final Block block : snapPointers) {
      if (isAllTargetOrdered(block)) {
        pointers.remove(block);
        count++;
      }
    }
    printf("del");
    return count;
  }

  private boolean isAllSourceOrdered(final Block b) {
    final List<Block> blocks = b.getSourceBlock();
    if (blocks.size() == 0) {
      return false;
    }
    boolean res = true;
    for (final Block block : blocks) {
      res = res && block.isOrdered();
    }
    return res;
  }

  private int getCountSource(final Block b) {
    return b.getSourceBlock().size();
  }

  private boolean isNoNullSourceOrdered(final Block b) {
    final List<Block> blocks = b.getSourceBlock();
    if (blocks.size() == 0) {
      return false;
    }
    boolean res = false;
    for (final Block block : blocks) {
      res = res || block.isOrdered();
    }
    return res;
  }

  private boolean isAllTargetOrdered(final Block b) {
    final List<Block> blocks = b.getTargetBlock();
    if (blocks.size() == 0) {
      return true;
    }
    boolean res = true;
    for (final Block block : blocks) {
      res = res && block.isOrdered();
    }
    return res;
  }

  private int addBlockToPointer(int count, final Block block) {
    if (!block.isOrdered() && !pointers.contains(block)) {
      pointers.add(block);
      count++;
    }
    return count;
  }

  private void setLevelOnNewBlocksInPointer(final int level) {
    for (final Block pointer : pointers) {
      if (!pointer.isOrdered()) {
        pointer.setOrder(level);
      }
    }
  }

  private void snapPointers(final List<Block> blocks) {
    snapPointers = new ArrayList<>(blocks);
  }

  private void printf(final String str) {
    if (DEBUG) {
      System.out.print(str + ": pointer=  ");
      System.out.print(level);
      System.out.print(" -> ");
      for (final Block pointer : pointers) {
        System.out.print(pointer.getName() + ", ");
      }
      System.out.println();
    }
  }

}
