package info.dc4j.toolbox.model;

public interface Runnable {

  void run(double maxTime);

  void setScanTime(double dt);

  double getScanTime();

  void init();

  double getT();

  long getStep();

}