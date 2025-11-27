package rubystoned.item.recipe;

import java.util.Comparator;


final class CarvingSorter implements Comparator {
	CarvingSorter(CarvingManager var1) {
	}

	public final int compare(Object var1, Object var2) {
		CarvingRecipe var10000 = (CarvingRecipe)var1;
		CarvingRecipe var4 = (CarvingRecipe)var2;
		CarvingRecipe var3 = var10000;
		return var4.b() < var3.b() ? -1 : (var4.b() > var3.b() ? 1 : 0);
	}
}
