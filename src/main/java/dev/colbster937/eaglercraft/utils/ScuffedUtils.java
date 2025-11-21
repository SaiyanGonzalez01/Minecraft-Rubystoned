package dev.colbster937.eaglercraft.utils;

import net.lax1dude.eaglercraft.HString;
import net.lax1dude.eaglercraft.Random;

public class ScuffedUtils {
  public static String getDefaultUsername() {
    String[] defaultNames = new String[] {
        "Yeeish", "Yeeish", "Yee", "Yee", "Yeer", "Yeeler", "Eagler", "Eagl",
        "Darver", "Darvler", "Vool", "Vigg", "Vigg", "Deev", "Yigg", "Yeeg"
    };

    Random rand = new Random();

    String name;

    do {
      name = HString.format("%s%s%04d", defaultNames[rand.nextInt(defaultNames.length)],
          defaultNames[rand.nextInt(defaultNames.length)], rand.nextInt(10000));
    } while (name.length() > 16);

    return name;
  }
}
