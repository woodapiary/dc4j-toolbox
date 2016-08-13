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
package info.dc4j.toolbox.monitor;

import java.util.Date;
import java.util.List;

import info.dc4j.toolbox.connector.Connector;

public class ConsoleTracer implements Tracer {

  @Override
  public void trace(long step, double t, List<Connector> connectors) {
    System.out.printf("%tT  ", new Date());
    System.out.printf("%07d  ", step);
    System.out.printf("t=");
    System.out.printf("%-5.3f  ", t);
    for (Connector conn : connectors) {
      System.out.printf("%5.4s", conn.getName());
      System.out.printf("=");
      switch (conn.connectorType()) {
        case BOOL:
          System.out.printf("%-6.5b", conn.getValue());
          break;
        case DOUBLE:
          System.out.printf("%-8.3f  ", conn.getValue());
          break;
        default:
          throw new IllegalStateException("bad type of connector");
      }

    }
    System.out.printf("%n");
  }

  @Override
  public List<TraceData> getTraceData() {
    return null;
  }

  @Override
  public void clear() {

  }

  @Override
  public TracerType type() {
    return TracerType.CONSOLE;
  }

}
