package rubystoned.block;

import net.minecraft.game.entity.player.EntityPlayer;
import net.minecraft.game.level.World;
import net.minecraft.game.level.block.Block;
import net.minecraft.game.level.material.Material;

public final class BlockStonecutter extends Block {
	public BlockStonecutter(int var1) {
		super(70, Material.rock, "Stonecutter");
		this.blockIndexInTexture = 104;
	}

	public final int getBlockTextureFromSide(int var1) {
		return var1 == 1 ? this.blockIndexInTexture + 1 : (var1 == 0 ? Block.stone.getBlockTextureFromSide(0) : (var1 != 2 && var1 != 4 ? this.blockIndexInTexture : this.blockIndexInTexture - 59));
	}

	public final boolean blockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5) {
		var5.displayStonecutterGUI();
		return true;
	}
}
