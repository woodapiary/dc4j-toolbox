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
package info.dc4j.toolbox.model;

import info.dc4j.toolbox.block.Block;
import info.dc4j.toolbox.block.Layout;
import info.dc4j.toolbox.block.connector.BoolConnector;
import info.dc4j.toolbox.block.connector.DoubleConnector;
import info.dc4j.toolbox.block.monitor.ITracable;
import info.dc4j.toolbox.block.monitor.Monitor;
import info.dc4j.toolbox.model.rw.Settings;

// TODO: Auto-generated Javadoc
/**
 * The Class Model.
 */
public abstract class Model {

  /** The layout. */
  private final Layout layout;

  /** The settings. */
  private Settings settings;

  /** The monitor. */
  private final Monitor monitor;

  /**
   * Instantiates a new model.
   */
  public Model() {
    this.layout = new Layout();
    this.monitor = new Monitor();
    setSettings(new Settings());
  }

  /**
   * Sets the settings.
   *
   * @param settings the new settings
   */
  public void setSettings(Settings settings) {
    this.settings = settings;
    this.settings.init();
  }

  /**
   * Run.
   *
   * @param maxStep the max step
   */
  protected void run(int maxStep) {
    for (int i = 0; i < maxStep; i++) {
      layout.run();
    }
  }

  /**
   * Run.
   *
   * @param maxTime the max time
   */
  protected void run(double maxTime) {
    int maxStep = (int) (maxTime / settings.getDt());
    run(maxStep);
  }

  /**
   * Run.
   */
  public void run() {
    run(settings.getMaxTime());
  }

  /**
   * Sets the dt.
   *
   * @param dt the new dt
   */
  protected void setDt(double dt) {
    for (Block block : layout.getListBlocks()) {
      block.setDt(dt);
    }
  }

  /**
   * Builds the.
   */
  public void build() {
    create();
    layout.add(monitor);
    layout.build();
    setDt(settings.getDt());
    monitor.setTraceLevel(settings.getTraceLevel());
    monitor.trace();
  }

  /**
   * Adds the.
   *
   * @param block the block
   */
  protected void add(Block block) {
    layout.add(block);
  }

  /**
   * Gets the layout.
   *
   * @return the layout
   */
  public Layout getLayout() {
    return layout;
  }

  /**
   * Gets the ds.
   *
   * @param key the key
   * @return the ds
   */
  protected double getDs(String key) {
    return settings.getDs(key);
  }

  /**
   * Gets the bs.
   *
   * @param key the key
   * @return the bs
   */
  protected boolean getBs(String key) {
    return settings.getBs(key);
  }

  /**
   * Adds the tracer.
   *
   * @param tracer the tracer
   */
  public void addTracer(ITracable tracer) {
    monitor.addTracer(tracer);
  }

  /**
   * Creates the.
   */
  public abstract void create();

  /**
   * Watch.
   *
   * @param u the u
   */
  protected void watch(DoubleConnector u) {
    monitor.setU(u);
  }

  /**
   * Watch.
   *
   * @param u the u
   */
  protected void watch(BoolConnector u) {
    monitor.setU(u);
  }

}
