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

package info.dc4j.toolbox.model;

import info.dc4j.toolbox.block.CompositeBlock;
import info.dc4j.toolbox.block.continuous.PT1;
import info.dc4j.toolbox.block.math.Sum;
import info.dc4j.toolbox.block.source.Step;

public class SimpleModel extends Model {


  @Override
  public void create() {
    CompositeBlock comp1 = new CompositeBlock("b1", 0, 1, 0, 0);
    add(comp1);
    Step step1 = new Step("St1");
    comp1.add(step1);
    PT1 pt1 = new PT1("Pt1", step1.out());
    comp1.add(pt1);
    comp1.getY(0).setChain(pt1.out());

    CompositeBlock comp2 = new CompositeBlock("b2", 1, 1, 0, 0);
    add(comp2);
    Step step2 = new Step("St2", getDs("st2_a"), 1);
    comp2.add(step2);
    Sum adder = new Sum("Add2", step2.out(), comp2.getU(0));
    comp2.add(adder);
    PT1 pt2 = new PT1("Pt2", adder.out());
    comp2.add(pt2);
    comp2.getY(0).setChain(pt2.out());

    comp2.getU(0).setChain(comp1.getY(0));

    watch(comp1.getY(0));
    watch(comp2.getU(0));
    watch(comp2.getY(0));
    watch(adder.out());
    watch(pt2.out());
  }

}
