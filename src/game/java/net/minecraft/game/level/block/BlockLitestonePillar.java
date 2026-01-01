package net.minecraft.game.level.block;

import net.minecraft.game.level.World;
import net.minecraft.game.level.material.Material;

public class BlockLitestonePillar extends Block {
	public BlockLitestonePillar(int var1) {
		super(84, Material.rock, "Litestone Pillar");
        this.blockIndexInTexture = 122;
	}

  public int getBlockTextureFromSide(int var1) {
		return var1 == 1 ? 123 : (var1 == 0 ? 123 : 122);
	}
}