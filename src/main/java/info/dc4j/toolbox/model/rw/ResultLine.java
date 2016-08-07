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
package info.dc4j.toolbox.model.rw;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ResultLine.
 */
public class ResultLine implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -4527241881441543366L;
  
  /** The step. */
  private long step;
  
  /** The t. */
  private double t;
  
  /** The doubles. */
  private List<DoubleData> doubles;
  
  /** The bools. */
  private List<BoolData> bools;

  /**
   * Gets the step.
   *
   * @return the step
   */
  public long getStep() {
    return step;
  }

  /**
   * Gets the t.
   *
   * @return the t
   */
  public double getT() {
    return t;
  }

  /**
   * Gets the doubles.
   *
   * @return the doubles
   */
  public List<DoubleData> getDoubles() {
    return doubles;
  }

  /**
   * Gets the bools.
   *
   * @return the bools
   */
  public List<BoolData> getBools() {
    return bools;
  }

  /**
   * Sets the step.
   *
   * @param step the new step
   */
  public void setStep(long step) {
    this.step = step;
  }

  /**
   * Sets the t.
   *
   * @param t the new t
   */
  public void setT(double t) {
    this.t = t;
  }

  /**
   * Sets the doubles.
   *
   * @param doubles the new doubles
   */
  public void setDoubles(List<DoubleData> doubles) {
    this.doubles = doubles;
  }

  /**
   * Sets the bools.
   *
   * @param bools the new bools
   */
  public void setBools(List<BoolData> bools) {
    this.bools = bools;
  }
}
