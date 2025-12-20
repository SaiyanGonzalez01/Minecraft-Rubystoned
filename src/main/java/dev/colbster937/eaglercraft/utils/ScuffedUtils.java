package dev.colbster937.eaglercraft.utils;

import org.lwjgl.input.Keyboard;

import net.lax1dude.eaglercraft.EagRuntime;
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

  public static boolean isStringEmpty(String str) {
    return str == null || str.isEmpty() || str.isBlank() || str.length() < 1;
  }

  public static boolean isShiftKeyDown() {
    return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
  }

  public static boolean isCtrlKeyDown() {
    return Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL);
  }

  public static void showZipFileChooser() {
    EagRuntime.displayFileChooser("application/zip", ".zip");
  }

  public static String getFormattedTime(long ticks) {
    long t = (((ticks % 24000) + 24000) % 24000 + 6000) % 24000;
    long h = t / 1000;
    long m = (t % 1000) * 60 / 1000;
    return HString.format("The day is %s at %s", ticks / 24000,
        HString.format("%02d:%02d", h, m));
  }
}
