package net.minecraft.game.entity.monster;

import net.minecraft.game.item.Item;
import net.minecraft.game.level.World;

public class EntitySkinwalker extends EntityMob {
	public EntitySkinwalker(World var1) {
		super(var1);
		this.texture = "/mob/mimic1.png";
		this.moveSpeed = 1.0F;
		this.attackStrength = 6;
	}

	protected String getHurtSound() {
		return "mob.skinwalkerhurt";
	}

	protected String getDeathSound() {
		return "mob.skinwalkerdeath";
	}

	protected final String getEntityString() {
		return "Skinwalker";
	}

	protected final int scoreValue() {
		return Item.dyeRed.shiftedIndex;
	}
}
