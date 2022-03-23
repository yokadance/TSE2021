package enumerations;

public enum PackageState {
  Pending(0),
  Delivered(1),
  InTransit(2);

  private final int code;

  PackageState(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
