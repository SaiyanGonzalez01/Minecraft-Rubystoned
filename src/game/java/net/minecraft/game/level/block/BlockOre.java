package net.minecraft.game.level.block;

import net.lax1dude.eaglercraft.Random;
import net.minecraft.game.item.Item;
import net.minecraft.game.level.material.Material;

public final class BlockOre extends Block {
	public BlockOre(int var1, int var2, String var3) {
		super(var1, var2, Material.rock, var3);
	}

	public final int idDropped(int var1, Random var2) {
		return this.blockID == Block.oreCoal.blockID ? Item.coal.shiftedIndex
<<<<<<< HEAD
				: (this.blockID == Block.oreDiamond.blockID ? Item.diamond.shiftedIndex
						: (this.blockID == Block.oreRuby.blockID ? Item.ruby.shiftedIndex : this.blockID));
=======
				: (this.blockID == Block.oreDiamond.blockID ? Item.diamond.shiftedIndex : this.blockID);
>>>>>>> 82abaf8552aa45e8355877fdb10070bc66ca2896
	}

	public final int quantityDropped(Random var1) {
		return 1;
	}
}
