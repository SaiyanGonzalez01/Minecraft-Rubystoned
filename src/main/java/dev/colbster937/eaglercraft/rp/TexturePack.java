package dev.colbster937.eaglercraft.rp;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import dev.colbster937.eaglercraft.storage.DirStorage;
import net.lax1dude.eaglercraft.EagRuntime;
import net.lax1dude.eaglercraft.EaglercraftUUID;
import net.lax1dude.eaglercraft.internal.FileChooserResult;
import net.lax1dude.eaglercraft.internal.vfs2.VFile2;
import net.lax1dude.eaglercraft.opengl.ImageData;
import net.minecraft.client.LoadingScreenRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.RenderEngine;

public class TexturePack {
  private static final List<DefaultCustomTexturePack> DEFAULT_TEXTURE_PACKS = new ArrayList<>();

  public static DirStorage i;

  private static Minecraft mc = null;
  private static boolean mod = false;
  private static TexturePack selectedPack = null;
  private static List<TexturePack> texturePacksCache = null;

  private final String name;
  private final VFile2 pack;
  private final VFile2 meta;
  private final VFile2 _files;

  private String[] description = new String[] { "", "" };
  private ImageData icon = null;
  private int iconTexture = -1;
  private String id = "";

  public static void init(Minecraft _mc) {
    mc = _mc;
    i = new DirStorage(_mc, "texturepacks");
    selectedPack = new DefaultTexturePack();
    if (!_mc.options.skin.equals("Default"))
      selectedPack = new TexturePack(_mc.options.skin);
    else
      selectedPack = DefaultTexturePack.instance;
  }

  protected TexturePack(String name, String[] description) {
    this.name = name;
    this.pack = new VFile2(i.root, name);
    this.meta = new VFile2(pack, "pack.txt");
    this._files = new VFile2(pack, "files.json");
    this.init(description);
  }

  protected TexturePack(String name) {
    this(name, new String[0]);
  }

  private void init(String[] description) {
    if (description.length > 0) {
      for (int i = 0; i < Math.min(2, description.length); i++) {
        String line = description[i];
        if (line != null)
          this.description[i] = truncateDescLine(line);
      }
    } else if (meta.exists()) {
      String[] lines = meta.getAllLines();
      for (int i = 0; i < Math.min(2, lines.length); i++) {
        String line = lines[i];
        if (line != null)
          this.description[i] = truncateDescLine(line);
      }
    }
    this.icon = ImageData.loadImageFile(this.getResourceStream("pack.png"));
    EaglercraftUUID nameId = EaglercraftUUID.nameUUIDFromBytes(this.name.getBytes());
    EaglercraftUUID descId = EaglercraftUUID
        .nameUUIDFromBytes((this.description[0] + "\n " + this.description[1]).getBytes());
    EaglercraftUUID packId = EaglercraftUUID.nameUUIDFromBytes((nameId + "-" + descId).getBytes());
    this.id = packId.toString();
  }

  public String getName() {
    return this.name;
  }

  public String[] getDescription() {
    return this.description;
  }

  public ImageData getIcon() {
    return this.icon;
  }

  public String getID() {
    return this.id;
  }

  public boolean supportsDarkGUI() {
    return this.isDefaultPack() || this.getResourceExists("dark_gui_support.txt");
  }

  public InputStream getResourceStream(String path) {
    VFile2 file = new VFile2(this.pack, path);
    if (file.exists())
      return file.getInputStream();
    else
      return EagRuntime.getResourceStream(path);
  }

  public boolean getResourceExists(String path) {
    return (new VFile2(this.pack, path)).exists();
  }

  public void bindIconTexture() {
    if (this.getIcon() != null) {
      if (this.iconTexture < 0)
        this.iconTexture = mc.renderEngine.allocateAndSetupTexture(this.getIcon());
      RenderEngine.bindTexture(this.iconTexture);
    } else {
      GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("/gui/unknown_pack.png"));
    }
  }

  public void delete(LoadingScreenRenderer prog) {
    i.delete(prog, this.name);

    this._files.delete();
    this.pack.delete();

    this.dispose();

    mod = true;
  }

  public void delete() {
    this.delete(null);
  }

  public void dispose() {
    if (this.iconTexture >= 0) {
      mc.renderEngine.deleteTexture(iconTexture);
      this.iconTexture = -1;
    }
    this.icon = null;
  }

  public boolean isPack(TexturePack pack) {
    return this.id.equals(pack.id);
  }

  public boolean isDefaultPack() {
    return isDefaultPack(this);
  }

  public static List<TexturePack> getTexturePacks() {
    boolean changed = mod;
    mod = false;

    if (texturePacksCache == null || changed) {
      if (texturePacksCache != null)
        for (TexturePack pack : texturePacksCache)
          pack.dispose();
      List<TexturePack> lst = new ArrayList<>();
      lst.add(DefaultTexturePack.instance);
      for (DefaultCustomTexturePack pack : DEFAULT_TEXTURE_PACKS)
        lst.add(pack);
      for (String pack : i.getFilesInManifest())
        lst.add(new TexturePack(pack));
      return texturePacksCache = lst;
    } else {
      return texturePacksCache;
    }
  }

  public static TexturePack getSelectedPack() {
    return selectedPack;
  }

  public static void setSelectedPack(TexturePack pack, LoadingScreenRenderer prog) {
    if (isSelectedPack(pack))
      return;
    selectedPack = pack;
    mc.options.skin = pack.getName();
    mc.options.saveOptions();
    mc.renderEngine.refreshTextures();
    try {
      mc.renderGlobal.loadRenderers();
    } catch (Throwable t) {
    }
    // updateProgress(prog, "Refreshing Texture Pack");
  }

  public static void setDefaultPack(LoadingScreenRenderer prog) {
    setSelectedPack(DefaultTexturePack.instance, prog);
  }

  public static void setSelectedPack(TexturePack pack) {
    setSelectedPack(pack, null);
  }

  public static void setDefaultPack() {
    setDefaultPack(null);
  }

  public static boolean isSelectedPack(TexturePack pack) {
    return selectedPack.isPack(pack);
  }

  public static boolean isSelectedPack(int pack) {
    return isSelectedPack(getTexturePacks().get(pack));
  }

  public static void addTexturePack(LoadingScreenRenderer prog, String name, byte[] data) {
    i._import(prog, name, data);
    mod = true;
  }

  public static void addTexturePack(LoadingScreenRenderer prog, FileChooserResult result) {
    addTexturePack(prog, result.fileName, result.fileData);
  }

  public static void addTexturePack(String name, byte[] data) {
    addTexturePack(null, name, data);
  }

  public static void addTexturePack(FileChooserResult result) {
    addTexturePack(result.fileName, result.fileData);
  }

  public static InputStream getResourceAsStream(String path) {
    if (selectedPack != null)
      return selectedPack.getResourceStream(path);
    else
      return EagRuntime.getResourceStream(path);
  }

  public static boolean isDefaultPack(TexturePack pack) {
    return pack instanceof DefaultTexturePack || pack instanceof DefaultCustomTexturePack;
  }

  private static String truncateDescLine(String var1) {
    if (var1 != null && var1.length() > 34)
      var1 = var1.substring(0, 34);
    return var1;
  }

  private static class DefaultTexturePack extends TexturePack {
    private static final DefaultTexturePack instance = new DefaultTexturePack();

    private final ImageData icon = ImageData.loadImageFile(this.getResourceStream("pack.png"));

    protected DefaultTexturePack() {
      super("Default", EagRuntime.getResourceLines("pack.txt").toArray(new String[0]));
    }

    @Override
    public ImageData getIcon() {
      return this.icon;
    }

    @Override
    public boolean supportsDarkGUI() {
      return true;
    }

    @Override
    public InputStream getResourceStream(String path) {
      return EagRuntime.getResourceStream(path);
    }
  }

  private static class DefaultCustomTexturePack extends TexturePack {
    private static final String root = "/default_packs";

    protected DefaultCustomTexturePack(String name) {
      super(name);
    }

    private String getPath(String path) {
      return root + "/" + this.getName() + "/" + path;
    }

    public InputStream getResourceStream(String path) {
      return EagRuntime.getResourceStream(this.getPath(path));
    }
    
    public boolean getResourceExists(String path) {
      return EagRuntime.getResourceExists(this.getPath(path));
    }
  }
}