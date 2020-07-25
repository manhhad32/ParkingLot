package parking.process;

import java.util.ArrayList;
import java.util.List;

public class Util {

  private Util() {
  }

  public static List<String> getParam(String arg) {
    List<String> params = new ArrayList<>();
    if (arg == null) {
      return params;
    }
    int lastInd = 0;
    for (int i = 0; i < arg.length(); i++) {
      String param1;
      if (arg.charAt(i) == ' ') {
        if (lastInd == 0) {
          param1 = arg.substring(0, i);
        } else {
          param1 = arg.substring(lastInd + 1, i);
        }
        lastInd = i;
        params.add(param1);
      }
    }
    if (lastInd == 0) {
      params.add(arg);
    } else {
      params.add(arg.substring(lastInd + 1, arg.length()));
    }

    return params;
  }

  public static String[] convertFrom(List<String> args){
    String[] response = new String[args.size()];
    int i = 0;
    for(String arg: args){
      response[i] = arg;
      i++;
    }
    return response;
  }


}
