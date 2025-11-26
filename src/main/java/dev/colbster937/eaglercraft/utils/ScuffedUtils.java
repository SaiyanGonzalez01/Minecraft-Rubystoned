package dev.colbster937.eaglercraft.utils;

import net.lax1dude.eaglercraft.HString;
import net.lax1dude.eaglercraft.Random;
import net.minecraft.client.Minecraft;

public class ScuffedUtils {
  private static final String[] darkGUIs = new String[] {
    "gui", "inventory", "furnace", "container", "crafting"
  };

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

  public static String getDarkGUI(String path) {
    if (path.startsWith("/gui/")) {
      boolean eagler = false;
      for (String gui : darkGUIs) {
        if (path.endsWith(gui + ".png")) eagler = true;
        else continue;
      }
      if (eagler && Minecraft.getMinecraft().options.darkGUI) path = "/gui_dark/" + path.substring(5);
    }
    return path;
  }
}
