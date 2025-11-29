package dev.colbster937.eaglercraft.gui;

import dev.colbster937.eaglercraft.storage.SaveUtils;
import dev.colbster937.eaglercraft.utils.ScuffedUtils;
import net.lax1dude.eaglercraft.EagRuntime;
import net.lax1dude.eaglercraft.internal.vfs2.VFile2;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.peyton.java.awt.Color;

public class GuiScreenManageWorld extends GuiScreen {
  private final GuiScreenSelectManageWorld parent;
  private final int world;
  private final String worldName;
  private final boolean exists;

  public GuiScreenManageWorld(GuiScreenSelectManageWorld parent, int world, boolean exists) {
    this.parent = parent;
    this.world = world;
    this.worldName = "Level" + this.world;
    this.exists = exists;
  }

  @Override
  public void initGui() {
    if (exists) {
      controlList.add(new GuiButton(0, (width - 200) / 2, height / 3 + 29, "Delete Level"));
      controlList.add(new GuiButton(1, (width - 200) / 2, height / 3 + 5, "Export Level"));
    } else {
      controlList.add(new GuiButton(2, (width - 200) / 2, height / 3 + 5, "Generate New Level"));
      controlList.add(new GuiButton(3, (width - 200) / 2, height / 3 + 29, "Import Level"));
    }
    controlList.add(new GuiButton(4, (width - 200) / 2, height / 3 + 53, "Cancel"));
  }

  @Override
  public void drawScreen(int mx, int my, float f) {
    drawDefaultBackground();
    drawCenteredString(fontRenderer, "Manage Level " + world, width / 2, height / 4, Color.WHITE.getRGB());
    super.drawScreen(mx, my, f);
  }

  @Override
  public void updateScreen() {
    super.updateScreen();
    SaveUtils.tick();
  }

  @Override
  protected void actionPerformed(GuiButton btn) {
    if (btn.enabled) {
      GuiScreen parent = this.parent.getParentScreen();
      if (btn.id == 0) {
        (new VFile2(this.mc.mcDataDir, "level" + this.world + ".mclevel")).delete();
        this.mc.displayGuiScreen(parent);
      } else if (btn.id == 1) {
        EagRuntime.downloadFileWithName("level.mclevel", (new VFile2(this.mc.mcDataDir, "level" + this.world + ".mclevel")).getAllBytes());
        this.mc.displayGuiScreen(parent);
      } else if (btn.id == 2) {
        this.parent.createWorld(this.world);
      } else if (btn.id == 3) {
        SaveUtils.load(this.world, true);
      } else if (btn.id == 4) {
        this.mc.displayGuiScreen(this.parent);
      }
    }
  }
}
