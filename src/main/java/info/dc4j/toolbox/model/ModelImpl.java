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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import info.dc4j.toolbox.block.Block;
import info.dc4j.toolbox.layout.Layout;
import info.dc4j.toolbox.layout.LayoutService;
import info.dc4j.toolbox.monitor.Monitor;
import info.dc4j.toolbox.monitor.MonitorService;

public class ModelImpl extends Model {

  private final Layout layout;
  private final Monitor monitor;
  private double dt = Model.DT;
  private double t;
  private long step;

  protected ModelImpl(final Layout layout, final Monitor monitor) {
    this.layout = layout;
    this.monitor = monitor;
  }

  @Override
  public void run(final double maxTime) {
    while (t < maxTime) {
      step++;
      t = t + dt;
      for (final Runnable block : layout.getBlocks()) {
        block.run(maxTime);
      }
      monitor.run(maxTime);
    }
  }

  @Override
  public void setScanTime(final double dt) {
    if (dt <= 0) {
      throw new IllegalArgumentException("dt must be over zero");
    }
    this.dt = dt;
    for (final Runnable block : layout.getBlocks()) {
      block.setScanTime(dt);
    }
    monitor.setScanTime(dt);
  }

  @Override
  public double getScanTime() {
    return dt;
  }

  @Override
  public void init() {
    t = 0;
    step = 0;
    for (final Runnable block : layout.getBlocks()) {
      block.init();
    }
    monitor.init();
  }

  @Override
  public double getT() {
    return t;
  }

  @Override
  public long getStep() {
    return step;
  }

  @Override
  public void build() {
    final List<Block> list = layout.getBlocks();
    final OrderStrategy strategy = Model.getFactory().createOrderStrategy(Model.ORDER_STRAREGY_TYPE);
    strategy.execute(list);
    check();
    Collections.sort(list, new Comparator<Block>() {
      @Override
      public int compare(final Block o1, final Block o2) {
        return ((Integer) o1.getOrder()).compareTo(o2.getOrder());
      }
    });
    // System.out.println(layout.toString());
  }

  private void check() {
    for (final Block block : layout.getBlocks()) {
      if (!block.isOrdered()) {
        throw new IllegalStateException("no order for block: " + block.getCanonicalName());
      }
    }
  }

  @Override
  public LayoutService getLayoutService() {
    return layout;
  }

  @Override
  public MonitorService getMonitorService() {
    return monitor;
  }
}
