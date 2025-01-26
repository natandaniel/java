package common.bmw;

import common.Door;

public class BmwDoor implements Door {

  @Override
  public Door clone() {
    return new BmwDoor();
  }
}
