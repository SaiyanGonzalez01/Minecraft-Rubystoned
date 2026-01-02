package rubystoned.item.recipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import net.minecraft.game.item.Item;
import net.minecraft.game.item.ItemStack;
import net.minecraft.game.level.block.Block;


public final class CarvingManager {
	private static final CarvingManager instance = new CarvingManager();
	private List recipes = new ArrayList();

	public static final CarvingManager getInstance() {
		return instance;
	}

	private CarvingManager() {
		this.addRecipe(new ItemStack(Block.stairSingle, 6), new Object[]{"###", Character.valueOf('#'), Block.cobblestone});
		this.addRecipe(new ItemStack(Block.brick, 2), new Object[]{"##", "##", Character.valueOf('#'), Item.brick});
		this.addRecipe(new ItemStack(Block.brickStone, 4), new Object[]{"##", "##", Character.valueOf('#'), Block.cobblestone});
		this.addRecipe(new ItemStack(Block.brickObsidian, 4), new Object[]{"##", "##", Character.valueOf('#'), Block.cobblestone});
		this.addRecipe(new ItemStack(Block.stoneSand, 2), new Object[]{"##", "##", Character.valueOf('#'), Block.sand});
		this.addRecipe(new ItemStack(Block.pillarStone, 4), new Object[]{"##", "##", "##", Character.valueOf('#'), Block.stone});
		this.addRecipe(new ItemStack(Block.pillarLitestone, 4), new Object[]{"##", "##", "##", Character.valueOf('#'), Block.stoneLite});
		this.addRecipe(new ItemStack(Block.woodBlock, 4), new Object[]{"##", "##", Character.valueOf('#'), Block.planks});
			
		Collections.sort(this.recipes, new CarvingSorter(this));
		System.out.println(this.recipes.size() + " recipes");
	}

	final void addRecipe(ItemStack var1, Object... var2) {
		String var3 = "";
		int var4 = 0;
		int var5 = 0;
		int var6 = 0;
		if(var2[0] instanceof String[]) {
			++var4;
			String[] var11 = (String[])var2[0];

			for(int var8 = 0; var8 < var11.length; ++var8) {
				String var9 = var11[var8];
				++var6;
				var5 = var9.length();
				var3 = var3 + var9;
			}
		} else {
			while(var2[var4] instanceof String) {
				String var7 = (String)var2[var4++];
				++var6;
				var5 = var7.length();
				var3 = var3 + var7;
			}
		}

		HashMap var12;
		int var15;
		for(var12 = new HashMap(); var4 < var2.length; var4 += 2) {
			Character var13 = (Character)var2[var4];
			var15 = 0;
			if(var2[var4 + 1] instanceof Item) {
				var15 = ((Item)var2[var4 + 1]).shiftedIndex;
			} else if(var2[var4 + 1] instanceof Block) {
				var15 = ((Block)var2[var4 + 1]).blockID;
			}

			var12.put(var13, Integer.valueOf(var15));
		}

		int[] var14 = new int[var5 * var6];

		for(var15 = 0; var15 < var5 * var6; ++var15) {
			char var10 = var3.charAt(var15);
			if(var12.containsKey(Character.valueOf(var10))) {
				var14[var15] = ((Integer)var12.get(Character.valueOf(var10))).intValue();
			} else {
				var14[var15] = -1;
			}
		}

		this.recipes.add(new CarvingRecipe(var5, var6, var14, var1));
	}

	public final ItemStack findMatchingRecipe(int[] var1) {
		for(int var2 = 0; var2 < this.recipes.size(); ++var2) {
			CarvingRecipe var3 = (CarvingRecipe)this.recipes.get(var2);
			if(var3.matchRecipe(var1)) {
				return var3.createResult();
			}
		}

		return null;
	}
}
