package parking.process;

public class Car {
  private String registrationNo;
  private String color;

  public Car(String registrationNo, String color) {
    this.registrationNo = registrationNo;
    this.color = color;
  }

  public String getRegistrationNo() {
    return registrationNo;
  }

  public void setRegistrationNo(String registrationNo) {
    this.registrationNo = registrationNo;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }
}