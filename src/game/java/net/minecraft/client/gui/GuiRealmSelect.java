package net.minecraft.client.gui;

import util.MathHelper;

public final class GuiRealmSelect extends GuiScreen {

    public final void initGui() {
		this.controlList.clear();
		this.controlList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 24, "Create Realm"));
		this.controlList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 48, "Join Realm"));
		this.controlList.add(new GuiButton(2, this.width / 2 - 100, this.height / 4 + 120, "Cancel"));

	}

    protected final void actionPerformed(GuiButton var1) {
        if (var1.id == 0) {
			this.mc.displayGuiScreen((GuiScreen) null);
			this.mc.setIngameFocus();
		}

        if (var1.id == 1) {
			this.mc.displayGuiScreen((GuiScreen) null);
			this.mc.setIngameFocus();
		}

        if (var1.id == 2) {
			this.mc.displayGuiScreen((GuiScreen) null);
			this.mc.setIngameFocus();
		}
    }

    public final void drawScreen(int var1, int var2, float var3) {
		this.drawDefaultBackground();
		drawCenteredString(this.fontRenderer, "Select Realm Option", this.width / 2, 40, 16777215);
		super.drawScreen(var1, var2, var3);
	}
}