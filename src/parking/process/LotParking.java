package parking.process;

public class LotParking {

  private int numOrder;
  private boolean isFree;
  private Car car;

  public LotParking(int numOrder, Car car) {
    this.numOrder = numOrder;
    this.isFree = (car == null);
    this.car = car;
  }

  public int getNumOrder() {
    return numOrder;
  }

  public void setNumOrder(int numOrder) {
    this.numOrder = numOrder;
  }

  public boolean isFree() {
    return isFree;
  }

  public void setFree(boolean free) {
    isFree = free;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.isFree = (car == null);
    this.car = car;
  }

}
