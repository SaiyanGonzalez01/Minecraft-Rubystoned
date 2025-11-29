package dev.colbster937.eaglercraft.storage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import net.lax1dude.eaglercraft.EagRuntime;
import net.lax1dude.eaglercraft.internal.FileChooserResult;
import net.lax1dude.eaglercraft.internal.vfs2.VFile2;
import net.minecraft.client.Minecraft;
import net.minecraft.client.PlayerLoader;
import net.minecraft.client.gui.GuiNewLevel;
import net.minecraft.game.level.World;

public class SaveUtils {
  public static DirStorage i;
  public static int loadedLevel = -1;
  
  private static Minecraft mc;
  private static PlayerLoader pl;
  private static PlayerLoader pla;

  public static void init(Minecraft imc) {
    i = new DirStorage(imc, "saves");
    mc = imc;
    pl = new PlayerLoader(imc, imc.loadingScreen);
    pla = new PlayerLoader(imc, null);
  }

  public static boolean levelSaved = false;

  public static void save(OutputStream os, boolean show) {
    try {
      if (show)
        pl.save(mc.theWorld, os);
      else
        pla.save(mc.theWorld, os);
      levelSaved = true;
    } catch (Exception e) {
      e.printStackTrace();
      EagRuntime.showPopup(e.getMessage());
    }
  }

  public static void save(int i, boolean show) {
    save(getLF(i).getOutputStream(), show);
  }

  public static void save(int i) {
    save(i, true);
  }

  public static void export() {
    try {
      ByteArrayOutputStream baos = new ByteArrayOutputStream(1 << 20);
      save(baos, true);
      baos.close();
      EagRuntime.downloadFileWithName("level.mclevel", baos.toByteArray());
    } catch (Exception e) {
      e.printStackTrace();
      EagRuntime.showPopup(e.getMessage());
    }
  }

  public static void load(int i, boolean upload) {
    loadedLevel = i;
    if (!upload && savedLevel(i))
      loadLevel(getLF(i).getAllBytes());
    else if (upload)
      EagRuntime.displayFileChooser("minecraft/mclevel", ".mclevel");
    else mc.displayGuiScreen(new GuiNewLevel(mc.currentScreen));
  }

  private static void loadLevel(byte[] data) {
    try {
      ByteArrayInputStream bais = new ByteArrayInputStream(data);
      World world = pl.load(bais);
      bais.close();
      mc.setLevel(world);
      mc.displayGuiScreen(null);
    } catch (Exception e) {
      e.printStackTrace();
      EagRuntime.showPopup(e.getMessage());
    }
  }

  public static void tick(int i) {
    if (i == 1)
      levelSaved = false;
    else if (i == 20)
      save(loadedLevel, false);
    if (EagRuntime.fileChooserHasResult()) {
      FileChooserResult result = EagRuntime.getFileChooserResult();
      if (result.fileName.endsWith(".mclevel")) {
        loadLevel(result.fileData);
      } else {
        EagRuntime.clearFileChooserResult();
        EagRuntime.showPopup("Please choose a valid indev level!");
      }
    }
  }

  public static void tick() {
    tick(-1);
  }

  public static boolean savedLevel(int i) {
    return getLF(i).exists();
  }

  private static VFile2 getLF(int i) {
    return new VFile2(mc.mcDataDir, "level" + i + ".mclevel");
  }
}
