/**
 * The MIT License
 * Copyright (c) 2002 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
/*
 *
 */
package info.dc4j.toolbox.block.monitor;

import java.util.ArrayList;
import java.util.List;

import info.dc4j.toolbox.block.Block;

// TODO: Auto-generated Javadoc
/**
 * The Class Monitor.
 */
public class Monitor extends Block {

  /** The trace level. */
  private int traceLevel = 100;

  /** The tracers. */
  private final List<ITracable> tracers;

  /**
   * Instantiates a new monitor.
   */
  public Monitor() {
    super("monitor");
    this.tracers = new ArrayList<ITracable>();
  }

  /**
   * Sets the trace level.
   *
   * @param traceLevel the new trace level
   */
  public void setTraceLevel(int traceLevel) {
    this.traceLevel = traceLevel;
  }

  /**
   * Adds the tracer.
   *
   * @param tracer the tracer
   */
  public void addTracer(ITracable tracer) {
    tracers.add(tracer);
  }

  /**
   * Trace.
   */
  public void trace() {
    if (getStep() % traceLevel == 0 || getStep() == 0) {
      for (ITracable tracer : tracers) {
        tracer.trace(getStep(), t, getListU(), getListUb());
      }
    }
  }

  /* (non-Javadoc)
   * @see info.dc4j.dc4j_toolbox.block.Block#eval()
   */
  @Override
  protected void eval() {
    trace();
  }
}
