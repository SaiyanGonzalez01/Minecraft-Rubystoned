package net.minecraft.client.gui;

import dev.colbster937.eaglercraft.EaglercraftVersion;
import util.MathHelper;
import net.lax1dude.eaglercraft.EagRuntime;

public final class GuiRubystone extends GuiScreen {

	public final void initGui() {
		this.controlList.clear();
		this.controlList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4, "Visit Rubydung"));
		//this.controlList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 24, "Visit Nuvila"));
		//this.controlList.add(new GuiButton(2, this.width / 2 - 100, this.height / 4 + 48, "Visit Bovinux"));
		//this.controlList.add(new GuiButton(3, this.width / 2 - 100, this.height / 4 + 72, "Visit Neo-Ruby"));
		this.controlList.add(new GuiButton(4, this.width / 2 - 100, this.height / 4 + 120, "Realms"));
		this.controlList.add(new GuiButton(5, this.width / 2 - 100, this.height / 4 + 144, "Cancel"));

	}

	protected final void actionPerformed(GuiButton var1) {
		if (var1.id == 0) {
			EagRuntime.openLink(EaglercraftVersion.OLD_SCHOOL);
		}

		if (var1.id == 1) {
			this.mc.displayGuiScreen((GuiScreen) null);
			this.mc.setIngameFocus();
		}

        if (var1.id == 2) {
			this.mc.displayGuiScreen((GuiScreen) null);
			this.mc.setIngameFocus();
		}

        if (var1.id == 3) {
			this.mc.displayGuiScreen((GuiScreen) null);
			this.mc.setIngameFocus();
		}

		if (var1.id == 4) {
			this.mc.displayGuiScreen(new GuiRealmSelect());
		}

		if (var1.id == 5) {
			this.mc.displayGuiScreen((GuiScreen) null);
			this.mc.setIngameFocus();
		}

	}

	public final void drawScreen(int var1, int var2, float var3) {
		this.drawDefaultBackground();
		drawCenteredString(this.fontRenderer, "Enter The Rubyverse", this.width / 2, 40, 16777215);
		super.drawScreen(var1, var2, var3);
	}

}
