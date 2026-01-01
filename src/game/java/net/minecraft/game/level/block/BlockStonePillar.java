package net.minecraft.game.level.block;

import net.minecraft.game.level.World;
import net.minecraft.game.level.material.Material;

public class BlockStonePillar extends Block {
	public BlockStonePillar(int var1) {
		super(83, Material.rock, "Stone Pillar");
        this.blockIndexInTexture = 120;
	}

  public int getBlockTextureFromSide(int var1) {
		return var1 == 1 ? 121 : (var1 == 0 ? 121 : 120);
	}
}