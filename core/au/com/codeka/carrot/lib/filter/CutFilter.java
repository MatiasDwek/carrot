package au.com.codeka.carrot.lib.filter;

import au.com.codeka.carrot.base.CarrotException;
import au.com.codeka.carrot.interpret.InterpretException;
import au.com.codeka.carrot.interpret.CarrotInterpreter;
import au.com.codeka.carrot.lib.Filter;
import au.com.codeka.carrot.util.ObjectValue;

public class CutFilter implements Filter {

  @Override
  public Object filter(Object object, CarrotInterpreter interpreter, String... arg)
      throws CarrotException {
    if (arg.length != 1) {
      throw new InterpretException("filter cut expects 1 arg >>> " + arg.length);
    }
    String cutee = interpreter.resolveString(arg[0]);
    String origin = ObjectValue.printable(object);
    return origin.replace(cutee, "");
  }

  @Override
  public String getName() {
    return "cut";
  }

}