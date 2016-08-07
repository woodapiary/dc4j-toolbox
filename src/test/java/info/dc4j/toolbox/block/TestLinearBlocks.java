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
package info.dc4j.toolbox.block;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import info.dc4j.toolbox.block.connector.DoubleConnector;
import info.dc4j.toolbox.block.generator.Step;
import info.dc4j.toolbox.block.linear.Adder;
import info.dc4j.toolbox.block.linear.Gain;
import info.dc4j.toolbox.block.linear.Loopback;
import info.dc4j.toolbox.block.linear.PT1;

// TODO: Auto-generated Javadoc
/**
 * The Class TestLinearBlocks.
 */
public class TestLinearBlocks {

  /**
   * Test gain.
   */
  @Test
  public void testGain() {
    DoubleConnector in = new DoubleConnector("const");
    in.setValue(1.0);
    Gain gain = new Gain(in);
    gain.run();
    assertTrue(gain.out().getValue() == 1.0);
    for (int i = 1; i < 3000; i++) {
      gain.run();
      // System.out.println(gain.toString());
    }
    assertTrue(gain.out().getValue() == 1.0);
  }

  /**
   * Test PT 1.
   */
  @Test
  public void testPT1() {
    DoubleConnector in = new DoubleConnector("const");
    in.setValue(1.0);
    PT1 pt = new PT1(in);
    pt.run();
    assertTrue(pt.out().getValue() < 0.1);
    assertTrue(pt.out().getValue() > 0.0);
    for (int i = 1; i < 3000; i++) {
      pt.run();
      // System.out.println(pt.toString());
    }
    assertTrue(pt.out().getValue() < 1.0);
    assertTrue(pt.out().getValue() > 0.9);
  }

  /**
   * Test loop back.
   */
  @Test
  public void testLoopBack() {
    Step spUa = new Step("Ua");
    spUa.setT0(1.0);
    Step spUb = new Step("Ub");
    spUb.setT0(2.0);
    Loopback dU = new Loopback("dUa", spUa.out(), spUb.out());
    spUa.run();
    spUb.run();
    dU.run();
    assertTrue(dU.out().getValue() == 0.0);
    for (int i = 1; i < 1500; i++) {
      spUa.run();
      spUb.run();
      dU.run();
      /*
       * System.out.println("------");
       * System.out.println(spUa.out().getValue());
       * System.out.println(spUb.out().getValue());
       * System.out.println(dU.out().getValue());
       */
    }
    assertTrue(dU.out().getValue() == 1.0);
    for (int i = 1500; i < 3000; i++) {
      spUa.run();
      spUb.run();
      dU.run();
    }
    assertTrue(dU.out().getValue() == 0.0);
  }

  /**
   * Test adder.
   */
  @Test
  public void testAdder() {
    Step spUa = new Step("Ua");
    spUa.setT0(1.0);
    Step spUb = new Step("Ub");
    spUb.setT0(2.0);
    Adder sU = new Adder("sU", spUa.out(), spUb.out());
    spUa.run();
    spUb.run();
    sU.run();
    assertTrue(sU.out().getValue() == 0.0);
    for (int i = 1; i < 1500; i++) {
      spUa.run();
      spUb.run();
      sU.run();
      /*
       * System.out.println("------");
       * System.out.println(spUa.out().getValue());
       * System.out.println(spUb.out().getValue());
       * System.out.println(sU.out().getValue());
       */
    }
    assertTrue(sU.out().getValue() == 1.0);
    for (int i = 1500; i < 3000; i++) {
      spUa.run();
      spUb.run();
      sU.run();
    }
    assertTrue(sU.out().getValue() == 2.0);
  }

  /**
   * Test integrator.
   */
  @Test
  public void testIntegrator() {
    // TODO
  }

}
