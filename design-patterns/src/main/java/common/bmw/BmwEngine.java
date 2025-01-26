package common.bmw;

import common.Engine;

public class BmwEngine implements Engine {

  @Override
  public Engine clone() {
    return new BmwEngine();
  }
}
