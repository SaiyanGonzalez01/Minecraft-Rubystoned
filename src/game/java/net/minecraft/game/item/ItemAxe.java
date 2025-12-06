package net.minecraft.game.item;

import net.minecraft.game.level.block.Block;

public final class ItemAxe extends ItemTool {
	private static Block[] blocksEffectiveAgainst = new Block[]{Block.planks, Block.bookShelf, Block.wood, Block.crate, Block.workbench};

	public ItemAxe(int var1, int var2, String var3) {
		super(var1, 3, var2, blocksEffectiveAgainst, var3);
	}
}
