package net.minecraft.game.level.block;

import net.lax1dude.eaglercraft.Random;
import net.minecraft.game.item.Item;
import net.minecraft.game.level.material.Material;

public final class BlockClay extends Block {
	public BlockClay(int var1, int var2, String var3) {
		super(69, 53, Material.ground, var3);
	}

  public int idDropped(int var1, Random var2) {
		return Item.clay.shiftedIndex;
	}

  public int quantityDropped(Random var1) {
		return 4;
	}
}
