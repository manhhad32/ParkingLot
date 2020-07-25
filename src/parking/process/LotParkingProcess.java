package parking.process;

import java.util.ArrayList;
import java.util.List;

public class LotParkingProcess {

  private List<LotParking> lotParkings;
  private int capacity;

  private static LotParkingProcess instance;

  private LotParkingProcess() {
  }


  public static LotParkingProcess getInstance() {
    if (instance == null) {
      instance = new LotParkingProcess();
    }
    return instance;
  }

  public List<LotParking> initialization(int numSlot) {
    this.capacity = numSlot;
    this.lotParkings = new ArrayList<>(numSlot);
    for (int i = 0; i < numSlot; i++) {
      LotParking lotParking = new LotParking(i + 1, null);
      lotParkings.add(lotParking);
    }
    System.out.println("Created a parking lot with " + numSlot + " slot");
    return lotParkings;
  }

  public List<LotParking> getMappingLotParking() {
    return this.lotParkings;
  }

  public LotParking leave(int slot) {
    if (this.capacity == 0) {
      System.out.println("please init slot packing first!");
      return null;
    }
    if (slot < 0 || slot > this.capacity) {
      System.out.println("out of scope!");
      return null;
    }
    this.lotParkings.get(slot - 1).setCar(null);
    System.out.println("Slot number " + lotParkings.get(slot - 1).getNumOrder() + " is free");
    return this.lotParkings.get(slot - 1);
  }

  public List<LotParking> status() {
    if (!validateCapacity()) {
      return new ArrayList<>();
    }
    System.out.println("Slot No    Registration No      Color");
    for (int i = 0; i < this.capacity; i++) {
      String registrationNo = "empty";
      String color = "empty";
      if (this.lotParkings.get(i).getCar() != null) {
        registrationNo = this.lotParkings.get(i).getCar().getRegistrationNo();
        color = this.lotParkings.get(i).getCar().getColor();
      }
      System.out.println(
          this.lotParkings.get(i).getNumOrder() + "           " + registrationNo + "    " + color);
    }
    return this.lotParkings;
  }

  public List<LotParking> listLotParkingCarColorWithRegistrationNo(String color) {
    if (!validateCapacity()) {
      return new ArrayList<>();
    }
    List<LotParking> listReturn = new ArrayList<>();
    String registrationNo = null;
    List<String> joinStr = new ArrayList<>();
    for (int i = 0; i < this.capacity; i++) {
      if ((this.lotParkings.get(i).getCar() != null) && this.lotParkings.get(i).getCar().getColor()
          .equalsIgnoreCase(color)) {
        listReturn.add(lotParkings.get(i));
        joinStr.add(this.lotParkings.get(i).getCar().getRegistrationNo());
      }
    }

    if (joinStr.isEmpty()) {
      registrationNo = "not found";
    } else {
      registrationNo = String.join(",", joinStr);
    }
    System.out.println(registrationNo);
    return listReturn;
  }

  public List<LotParking> listLotParkingCarColorWithSlot(String color) {
    if (!validateCapacity()) {
      return new ArrayList<>();
    }
    List<LotParking> listReturn = new ArrayList<>();
    String slot = null;
    List<String> joinSlot = new ArrayList<>();
    for (int i = 0; i < this.capacity; i++) {
      if ((this.lotParkings.get(i).getCar() != null) && this.lotParkings.get(i).getCar().getColor()
          .equalsIgnoreCase(color)) {
        listReturn.add(this.lotParkings.get(i));
        joinSlot.add(Integer.toString(this.lotParkings.get(i).getNumOrder()));
      }
    }
    if (joinSlot.isEmpty()) {
      slot = "not found";
    } else {
      slot = String.join(",", joinSlot);
    }
    System.out.println(slot);
    return listReturn;
  }

  public LotParking listLotParkingCarRegistrationNo(String registrationNo) {
    if (!validateCapacity()) {
      return null;
    }
    int slot = 0;
    for (int i = 0; i < this.capacity; i++) {
      if ((this.lotParkings.get(i).getCar() != null) && this.lotParkings.get(i).getCar()
          .getRegistrationNo().equalsIgnoreCase(registrationNo)) {
        slot = i;
        break;
      }
    }
    if (slot > 0) {
      System.out.println(lotParkings.get(slot).getNumOrder());
      return this.lotParkings.get(slot);
    } else {
      System.out.println("not found");
      return null;
    }
  }

  public LotParking parkCar(String registrationNo, String color) {
    if (!validateCapacity()) {
      return null;
    }
    if (!validateRegistrationNo(registrationNo)) {
      return null;
    }
    int slot = 0;
    for (int i = 0; i < this.capacity; i++) {
      if (this.lotParkings.get(i).isFree()) {
        Car car = new Car(registrationNo, color);
        this.lotParkings.get(i).setCar(car);
        slot = this.lotParkings.get(i).getNumOrder();
        break;
      }
    }
    if (slot == 0) {
      System.out.println("Sorry, paring lot is full");
      return null;
    } else {
      System.out.println("Allocated slot number: " + slot);
      return this.lotParkings.get(slot - 1);
    }

  }

  public void mappingCallFunction(List<String> params) {
    if (params.get(0).equalsIgnoreCase("create_parking_lot")) {
      int iCapacity = Integer.parseInt(params.get(1));
      initialization(iCapacity);

    } else if (params.get(0).equalsIgnoreCase("park")) {
      if (params.size() < 3) {
        System.out.println("Wrong param when call park, plz check again");
        return;
      }
      parkCar(params.get(1), params.get(2));
    } else if (params.get(0).equalsIgnoreCase("leave")) {
      if (params.size() < 2) {
        System.out.println("Wrong param when call leave, plz check again");
        return;
      }
      int slot = Integer.parseInt(params.get(1));
      leave(slot);
    } else if (params.get(0).equalsIgnoreCase("status")) {
      status();
    } else if (params.get(0).equalsIgnoreCase("registration_numbers_for_cars_with_colour")) {
      if (params.size() < 2) {
        System.out.println("Wrong param, plz check again");
        return;
      }
      listLotParkingCarColorWithRegistrationNo(params.get(1));
    } else if (params.get(0).equalsIgnoreCase("slot_numbers_for_cars_with_colour")) {
      if (params.size() < 2) {
        System.out.println("Wrong param, plz check again");
        return;
      }
      listLotParkingCarColorWithSlot(params.get(1));
    } else if (params.get(0).equalsIgnoreCase("slot_number_for_registration_number")) {
      if (params.size() < 2) {
        System.out.println("Wrong param, plz check again");
        return;
      }
      listLotParkingCarRegistrationNo(params.get(1));
    }
  }



  private boolean validateCapacity() {
    if (this.capacity == 0) {
      System.out.println("slot parking not yet init");
      return false;
    }
    return true;
  }

  private boolean validateRegistrationNo(String registrationNo) {
    if (!validateCapacity()) {
      return false;
    }
    for (int i = 0; i < this.capacity; i++) {
      if (!this.lotParkings.get(i).isFree() && this.lotParkings.get(i).getCar().getRegistrationNo()
          .equalsIgnoreCase(registrationNo)) {
        System.out.println("car have registration exist: " + registrationNo);
        return false;
      }
    }
    return true;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

}
