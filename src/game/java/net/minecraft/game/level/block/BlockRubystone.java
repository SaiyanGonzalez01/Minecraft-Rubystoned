package net.minecraft.game.level.block;

import net.minecraft.game.entity.player.EntityPlayer;
import net.minecraft.game.level.World;
import net.minecraft.game.level.material.Material;

public final class BlockRubystone extends Block {
	protected BlockRubystone(int var1) {
		super(64, Material.rock, "Rubystone");
        this.blockIndexInTexture = 84;
	}

	public final boolean blockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5) {
		var5.displayRubystoneGUI();
		return true;
	}
}
