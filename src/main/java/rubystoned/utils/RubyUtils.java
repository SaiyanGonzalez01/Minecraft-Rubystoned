package rubystoned.utils;

import util.MathHelper;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import net.lax1dude.eaglercraft.opengl.ImageData;
import net.lax1dude.eaglercraft.profile.EaglerProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.Tessellator;

public class RubyUtils {
  private static int panoramaTick = 0;
  private static int panoramaTex = -1;

  public static void tick() {
    panoramaTick++;
  }

  public static float getPlayerScale(float scale) {
    return ((EaglerProfile.presetSkinId == 22) ? (scale *= 0.75F) : scale);
  }

  private static void renderPanoramaBox(Tessellator tessellator, Minecraft mc) {
    GL11.glMatrixMode(GL11.GL_PROJECTION);
    GL11.glPushMatrix();
    GL11.glLoadIdentity();
    GLU.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
    GL11.glMatrixMode(GL11.GL_MODELVIEW);
    GL11.glPushMatrix();
    GL11.glLoadIdentity();
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
    GL11.glEnable(GL11.GL_BLEND);
    GL11.glDisable(GL11.GL_ALPHA_TEST);
    GL11.glDisable(GL11.GL_CULL_FACE);
    GL11.glDepthMask(false);
    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    byte yee = 8;

    for (int i = 0; i < yee * yee; ++i) {
      GL11.glPushMatrix();
      float a = ((float) (i % yee) / (float) yee - 0.5F) / 64.0F;
      float b = ((float) (i / yee) / (float) yee - 0.5F) / 64.0F;
      float c = 0.0F;
      GL11.glTranslatef(a, b, c);
      GL11.glRotatef(MathHelper.sin(((float) panoramaTick) / 400.0F) * 25.0F + 20.0F, 1.0F, 0.0F, 0.0F);
      GL11.glRotatef(-((float) panoramaTick) * 0.1F, 0.0F, 1.0F, 0.0F);

      for (int j = 0; j < 6; ++j) {
        GL11.glPushMatrix();
        if (j == 1)
          GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        if (j == 2)
          GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        if (j == 3)
          GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        if (j == 4)
          GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
        if (j == 5)
          GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("/panorama/" + j + ".png"));
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_I(16777215, 255 / (i + 1));
        float o = 0.0F;
        tessellator.addVertexWithUV(-1.0D, -1.0D, 1.0D, (double) (0.0F + o), (double) (0.0F + o));
        tessellator.addVertexWithUV(1.0D, -1.0D, 1.0D, (double) (1.0F - o), (double) (0.0F + o));
        tessellator.addVertexWithUV(1.0D, 1.0D, 1.0D, (double) (1.0F - o), (double) (1.0F - o));
        tessellator.addVertexWithUV(-1.0D, 1.0D, 1.0D, (double) (0.0F + o), (double) (1.0F - o));
        tessellator.draw();
        GL11.glPopMatrix();
      }

      GL11.glPopMatrix();
      GL11.glColorMask(true, true, true, false);
    }

    tessellator.setTranslationD(0.0D, 0.0D, 0.0D);
    GL11.glColorMask(true, true, true, true);
    GL11.glMatrixMode(GL11.GL_PROJECTION);
    GL11.glPopMatrix();
    GL11.glMatrixMode(GL11.GL_MODELVIEW);
    GL11.glPopMatrix();
    GL11.glDepthMask(true);
    GL11.glEnable(GL11.GL_CULL_FACE);
    GL11.glEnable(GL11.GL_ALPHA_TEST);
    GL11.glEnable(GL11.GL_DEPTH_TEST);
  }

  private static void renderPanoramaBlur(Tessellator tessellator, Minecraft mc, int w, int h, float z) {
    if (panoramaTex < 0)
      panoramaTex = mc.renderEngine.allocateAndSetupTexture(new ImageData(256, 256, true));

    GL11.glBindTexture(GL11.GL_TEXTURE_2D, panoramaTex);
    GL11.glCopyTexSubImage2D(GL11.GL_TEXTURE_2D, 0, 0, 0, 0, 0, 256, 256);
    GL11.glEnable(GL11.GL_BLEND);
    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    GL11.glColorMask(true, true, true, false);
    tessellator.startDrawingQuads();
    byte yee = 3;

    for (int i = 0; i < yee; ++i) {
      tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F / (float) (i + 1));
      float q = (float) (i - yee / 2) / 256.0F;
      tessellator.addVertexWithUV((double) w, (double) h, (double) z, (double) (0.0F + q),
          0.0D);
      tessellator.addVertexWithUV((double) w, 0.0D, (double) z, (double) (1.0F + q), 0.0D);
      tessellator.addVertexWithUV(0.0D, 0.0D, (double) z, (double) (1.0F + q), 1.0D);
      tessellator.addVertexWithUV(0.0D, (double) h, (double) z, (double) (0.0F + q), 1.0D);
    }

    tessellator.draw();
    GL11.glColorMask(true, true, true, true);
  }

  public static void renderPanorama() {
    Tessellator tessellator = Tessellator.instance;
    Minecraft mc = Minecraft.getMinecraft();
    int w = mc.currentScreen.width;
    int h = mc.currentScreen.height;
    float z = GuiAccessor.getZLevel(mc.currentScreen);

    GL11.glViewport(0, 0, 256, 256);
    renderPanoramaBox(tessellator, mc);
    renderPanoramaBlur(tessellator, mc, w, h, z);
    renderPanoramaBlur(tessellator, mc, w, h, z);
    renderPanoramaBlur(tessellator, mc, w, h, z);
    renderPanoramaBlur(tessellator, mc, w, h, z);
    renderPanoramaBlur(tessellator, mc, w, h, z);
    renderPanoramaBlur(tessellator, mc, w, h, z);
    renderPanoramaBlur(tessellator, mc, w, h, z);
    renderPanoramaBlur(tessellator, mc, w, h, z);

    GL11.glViewport(0, 0, mc.displayWidth, mc.displayHeight);

    GL11.glBindTexture(GL11.GL_TEXTURE_2D, panoramaTex);

    tessellator.startDrawingQuads();
    float a = w > h ? 120.0F / (float) w : 120.0F / (float) h;
    float b = (float) w * a / 256.0F;
    float c = (float) h * a / 256.0F;
    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
    tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
    tessellator.addVertexWithUV(0.0D, (double) h, (double) z, (double) (0.5F - c),
        (double) (0.5F + b));
    tessellator.addVertexWithUV((double) w, (double) h, (double) z, (double) (0.5F - c),
        (double) (0.5F - b));
    tessellator.addVertexWithUV((double) w, 0.0D, (double) z, (double) (0.5F + c),
        (double) (0.5F - b));
    tessellator.addVertexWithUV(0.0D, 0.0D, (double) z, (double) (0.5F + c), (double) (0.5F + b));
    tessellator.draw();

    GuiAccessor.drawGradientRect(0, 0, w, h, -1426063361, 16777215);
		GuiAccessor.drawGradientRect(0, 0, w, h, 0, -1442840576);
  }
}
