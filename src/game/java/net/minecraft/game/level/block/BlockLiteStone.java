package net.minecraft.game.level.block;

import net.lax1dude.eaglercraft.Random;
import net.minecraft.game.level.material.Material;

public final class BlockLiteStone extends Block {
	public BlockLiteStone(int var1, int var2) {
		super(var1, var2, Material.rock, "Litestone");
	}

	public final int idDropped(int var1, Random var2) {
		return Block.cobblestoneLite.blockID;
	}
}