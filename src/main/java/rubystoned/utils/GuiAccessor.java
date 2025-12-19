package rubystoned.utils;

import net.minecraft.client.gui.Gui;

public class GuiAccessor extends Gui {
  public static float getZLevel(Gui var0) {
    return var0.zLevel;
  }

  public static void drawGradientRect(int var0, int var1, int var2, int var3, int var4, int var5) {
    Gui.drawGradientRect(var0, var1, var2, var3, var4, var5);
  }
}