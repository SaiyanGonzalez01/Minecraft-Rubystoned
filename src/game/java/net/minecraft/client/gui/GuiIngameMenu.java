package net.minecraft.client.gui;

import dev.colbster937.eaglercraft.storage.SaveUtils;
import util.MathHelper;

public final class GuiIngameMenu extends GuiScreen {
	private int updateCounter = 0;

	public final void initGui() {
		this.controlList.clear();
		this.controlList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4, "Options..."));
		this.controlList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 24, "Generate new level..."));
		this.controlList.add(new GuiButton(2, this.width / 2 - 100, this.height / 4 + 48, /* "Save */ "Export level.."));
		this.controlList.add(new GuiButton(3, this.width / 2 - 100, this.height / 4 + 72, /* "Load */ "Import level.."));
		this.controlList.add(new GuiButton(4, this.width / 2 - 100, this.height / 4 + 96, "Back to game"));
		this.controlList.add(new GuiButton(5, this.width / 2 - 100, this.height / 4 + 120, "Return to menu"));
		if (this.mc.session == null) {
			((GuiButton) this.controlList.get(2)).enabled = false;
			((GuiButton) this.controlList.get(3)).enabled = false;
		}

	}

	protected final void actionPerformed(GuiButton var1) {
		if (var1.id == 0) {
			this.mc.displayGuiScreen(new GuiOptions(this, this.mc.options));
		}

		if (var1.id == 1) {
			this.mc.displayGuiScreen(new GuiNewLevel(this));
		}

		if (this.mc.session != null) {
			if (var1.id == 2) {
				SaveUtils.export();
			}

			if (var1.id == 3) {
				SaveUtils.load(-1, true);
			}
		}

		if (var1.id == 4) {
			this.mc.displayGuiScreen((GuiScreen) null);
			this.mc.setIngameFocus();
		}

		if (var1.id == 5) {
			SaveUtils.save(SaveUtils.loadedLevel, true);
			this.mc.setLevel(null);
			this.mc.displayGuiScreen(this.mc.menu);
		}

	}

	public final void drawScreen(int var1, int var2, float var3) {
		this.drawDefaultBackground();
		drawCenteredString(this.fontRenderer, "Game menu", this.width / 2, 40, 16777215);
		if (!SaveUtils.levelSaved) {
			float var5 = ((float) (this.updateCounter % 10) + var3) / 10.0F;
			var5 = MathHelper.sin(var5 * (float) Math.PI * 2.0F) * 0.2F + 0.8F;
			int var6 = (int) (255.0F * var5);
			this.drawString(this.fontRenderer, "Saving level..", 8, this.height - 16, var6 << 16 | var6 << 8 | var6);
		}
		super.drawScreen(var1, var2, var3);
	}

	public void updateScreen() {
		SaveUtils.tick(++this.updateCounter);
	}
}
