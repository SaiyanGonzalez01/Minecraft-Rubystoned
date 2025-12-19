package dev.colbster937.eaglercraft.gui;

import dev.colbster937.eaglercraft.rp.TexturePack;
import net.lax1dude.eaglercraft.HString;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class GuiScreenTexturePackOptions extends GuiYesNo {
  private boolean allowClick = false;

  public GuiScreenTexturePackOptions(GuiScreen parentScreen, TexturePack pack) {
    super(new Callback(parentScreen, pack), HString.format("What do you want to do with '%s'?", pack.getName()), "Tip: Hold Shift to skip this screen when selecting a texture pack!", "Delete this texture pack", "Select this texture pack", -1);
  }

  @Override
  protected void drawSlotInventory(int x, int y, int b) {
    if (allowClick)
      super.drawSlotInventory(x, y, b);
  }

  @Override
  public void updateScreen() {
    if (!allowClick)
      allowClick = true;
  }

  private static class Callback extends GuiScreen {
    private final GuiScreen parentScreen;
    private final TexturePack pack;

    private Callback(GuiScreen parentScreen, TexturePack pack) {
      this.parentScreen = parentScreen;
      this.pack = pack;
    }

    @Override
    public void confirmClicked(boolean val, int id) {
      Minecraft mc = Minecraft.getMinecraft();
      if (val) {
        pack.delete(mc.loadingScreen);
        TexturePack.setDefaultPack();
      } else {
        TexturePack.setSelectedPack(pack);
      }
      mc.displayGuiScreen(parentScreen);
    }
  }
}