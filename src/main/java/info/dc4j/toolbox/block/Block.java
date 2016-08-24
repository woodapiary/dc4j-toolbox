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
package info.dc4j.toolbox.block;

import java.util.List;

import info.dc4j.toolbox.connector.Connector;
import info.dc4j.toolbox.element.Element;
import info.dc4j.toolbox.element.Parametrizable;
import info.dc4j.toolbox.layout.Composite;
import info.dc4j.toolbox.model.Runnable;

public interface Block extends Element, Composite, Runnable, Parametrizable {

  public enum PortType {
    Y, U, S
  };

  interface Port {
    void setConnector(Connector connector, int pin);
    List<Socket> getSockets();
    BoolSocket getB(int i);
    DoubleSocket getD(int i);
  }

  public enum Type {
    USER, INTEGRATOR, PT1, GAIN, SUBSTRACT, SUM, SIN, STEP, SPLITTER_D, SPLITTER_B
  }

  Type blockType();

  int getOrder();

  void setOrder(int order);

  List<Block> getSourceBlock();

  List<Block> getTargetBlock();

  boolean isOrdered();

  void setConnector(Connector connector, Block.PortType port, int pin);

  BlockInfo getBlockInfo();

}
