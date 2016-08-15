/**
 * The MIT License
 * Copyright (c) 2002-2016 dc4j.info
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
package info.dc4j.toolbox.layout;

import org.junit.Test;

import info.dc4j.toolbox.block.Block;
import info.dc4j.toolbox.element.DataType;
import info.dc4j.toolbox.model.Model;
import info.dc4j.toolbox.model.ModelFactoryImpl;

public class LayoutTest {

  double delta = 0.005;

  @SuppressWarnings("unused")
  @Test
  public void test01() {
    //1->5->6->7->10->8
    //2->4->5
    //3->9->11->7
    //8->9
    //6->8
    Model model = ModelFactoryImpl.createModel();
    int b1 = model.createBlock("block1", Block.Type.STEP);
    int b2 = model.createBlock("block2", Block.Type.STEP);
    int b3 = model.createBlock("block3", Block.Type.STEP);
    int b4 = model.createBlock("block4", Block.Type.PT1);
    int b5 = model.createBlock("block5", Block.Type.SUM);
    int b6 = model.createBlock("block6", Block.Type.SPLITTER);
    int b7 = model.createBlock("block7", Block.Type.SUM);
    int b8 = model.createBlock("block8",   Block.Type.SUM);
    int b9 = model.createBlock("block9", Block.Type.SUM);
    int b10 = model.createBlock("block10", Block.Type.PT1);
    int b11 = model.createBlock("block11", Block.Type.PT1);

    int c1 = model.createConnection("c1", b1, b5, 0, 0, DataType.DOUBLE);
    int c2 = model.createConnection("c2", b2, b4, 0, 0, DataType.DOUBLE);
    int c3 = model.createConnection("c3", b3, b9, 0, 0, DataType.DOUBLE);
    int c4 = model.createConnection("c4", b4, b5, 0, 1, DataType.DOUBLE);
    int c5 = model.createConnection("c5", b5, b6, 0, 0, DataType.DOUBLE);
    int c6 = model.createConnection("c6", b6, b7, 0, 0, DataType.DOUBLE);
    int c7 = model.createConnection("c7", b6, b8, 1, 0, DataType.DOUBLE);
    int c8 = model.createConnection("c8", b7, b10, 0, 0, DataType.DOUBLE);
    int c9 = model.createConnection("c9", b8, b9, 0, 1, DataType.DOUBLE);
    int c10 = model.createConnection("c10", b9, b11, 0, 0, DataType.DOUBLE);
    int c11 = model.createConnection("c11", b11, b7, 0, 1, DataType.DOUBLE);
    int c12 = model.createConnection("c12", b10, b8, 0, 1, DataType.DOUBLE);
    model.build();

  }

}
