package net.minecraft.client.gui;

import net.minecraft.client.player.EntityPlayerSP;
import org.lwjgl.opengl.GL11;

import dev.colbster937.eaglercraft.FormattingCodes;
import dev.colbster937.eaglercraft.storage.SaveUtils;

public final class GuiGameOver extends GuiScreen {
	public final void initGui() {
		this.controlList.clear();
		this.controlList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 72, "Respawn"));
		this.controlList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96, "Back to title screen"));
		if (this.mc.session == null) {
			((GuiButton) this.controlList.get(1)).enabled = false;
		}

	}

	protected final void keyTyped(char var1, int var2) {
	}

	protected final void actionPerformed(GuiButton var1) {
		if (var1.id == 0) {
			this.mc.thePlayer.isDead = false;
			this.mc.thePlayer.preparePlayerToSpawn();
			this.mc.displayGuiScreen(null);
			this.mc.setIngameFocus();
		}

		if (var1.id == 1) {
			SaveUtils.save(SaveUtils.loadedLevel, true);
			this.mc.setLevel(null);
			this.mc.displayGuiScreen(this.mc.menu);
		}

	}

	public final void drawScreen(int var1, int var2, float var3) {
		drawGradientRect(0, 0, this.width, this.height, 1615855616, -1602211792);
		GL11.glPushMatrix();
		GL11.glScalef(2.0F, 2.0F, 2.0F);
		drawCenteredString(this.fontRenderer, "Game Over!", this.width / 2 / 2, 30, 16777215);
		GL11.glPopMatrix();
		FontRenderer var10000 = this.fontRenderer;
		StringBuilder var10001 = (new StringBuilder()).append("Score: " + FormattingCodes.YELLOW);
		EntityPlayerSP var4 = this.mc.thePlayer;
		drawCenteredString(var10000, var10001.append(var4.getScore).toString(), this.width / 2, 100, 16777215);
		super.drawScreen(var1, var2, var3);
	}

	public final boolean doesGuiPauseGame() {
		return false;
	}

	public void updateScreen() {
		SaveUtils.tick();
	}
}
