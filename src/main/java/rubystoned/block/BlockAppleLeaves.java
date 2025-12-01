package rubystoned.block;

import net.lax1dude.eaglercraft.Random;
import net.minecraft.game.item.Item;
import net.minecraft.game.level.World;
import net.minecraft.game.level.block.Block;
import net.minecraft.game.level.block.BlockLeavesBase;
import net.minecraft.game.level.material.Material;

public final class BlockAppleLeaves extends BlockLeavesBase {
	public BlockAppleLeaves(int var1, int var2, String var3) {
		super(73, 107, Material.leaves, true, var3);
		this.setTickOnLoad(true);
	}

	public final void updateTick(World var1, int var2, int var3, int var4, Random var5) {
		if(!var1.getBlockMaterial(var2, var3 - 1, var4).isSolid()) {
			for(int var8 = var2 - 2; var8 <= var2 + 2; ++var8) {
				for(int var6 = var3 - 1; var6 <= var3; ++var6) {
					for(int var7 = var4 - 2; var7 <= var4 + 2; ++var7) {
						if(var1.getBlockId(var8, var6, var7) == Block.wood.blockID) {
							return;
						}
					}
				}
			}

			this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4));
			var1.setBlockWithNotify(var2, var3, var4, 0);
		}
	}

	public final int quantityDropped(Random var1) {
		return var1.nextInt(1) == 0 ? 1 : 0;
	}

	public final int idDropped(int var1, Random var2) {
		return Item.apple.shiftedIndex;
	}
}
