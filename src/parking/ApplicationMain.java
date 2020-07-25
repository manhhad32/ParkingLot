package parking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import parking.process.LotParkingProcess;
import parking.process.Util;

public class ApplicationMain {

  static void processMessage(String[] args) throws URISyntaxException {
    CodeSource codeSource = ApplicationMain.class.getProtectionDomain().getCodeSource();
    File jarFile = new File(codeSource.getLocation().toURI().getPath());
    String jarDir = jarFile.getParentFile().getPath();
    String pathFileInput = null;
    if ((args == null) || (args.length == 0)) {
      pathFileInput = jarDir + "/file_inputs.txt";

    } else if (args[0].equalsIgnoreCase("file_inputs.txt")) {
      pathFileInput = jarDir + "/" + args[0];
    } else if (args[0].equalsIgnoreCase("exit")) {
      System.exit(0);
    } else {
      List<String> params = Arrays.stream(args).collect(Collectors.toList());
      LotParkingProcess.getInstance().mappingCallFunction(params);
    }

    if (pathFileInput != null) {
      BufferedReader bufferedReader;
      try {
        bufferedReader = new BufferedReader(new FileReader(pathFileInput));
        String line = bufferedReader.readLine();
        while (line != null) {
          List<String> params = Util.getParam(line);
          LotParkingProcess.getInstance().mappingCallFunction(params);
          line = bufferedReader.readLine();
        }
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }


  public static void main(String[] args) throws URISyntaxException {
    Scanner sc = new Scanner(System.in);
    boolean flag = true;
    String choice;
    do {
      processMessage(args);
      choice = sc.nextLine();
      args = Util.convertFrom(Util.getParam(choice));
      if(choice.equalsIgnoreCase("exit")){
        flag = false;
      }
    } while (flag);
    sc.close();
  }

}
