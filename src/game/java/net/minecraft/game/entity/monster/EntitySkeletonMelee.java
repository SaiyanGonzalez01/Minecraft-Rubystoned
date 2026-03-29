package net.minecraft.game.entity.monster;

import net.minecraft.game.item.Item;
import net.minecraft.game.level.World;

public class EntitySkeletonMelee extends EntityMob {
	public EntitySkeletonMelee(World var1) {
		super(var1);
		this.texture = "/mob/skeletonmelee.png";
		this.moveSpeed = 1.0F;
		this.attackStrength = 4;
	}

	public final void onLivingUpdate() {
		if(this.worldObj.skylightSubtracted > 7) {
			float var1 = this.getEntityBrightness(1.0F);
			if(var1 > 0.5F && this.worldObj.canBlockSeeTheSky((int)this.posX, (int)this.posY, (int)this.posZ) && this.rand.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F) {
				this.fire = 300;
			}
		}

		super.onLivingUpdate();
	}

	protected String getHurtSound() {
		return "mob.skeletonhurt";
	}

	protected String getDeathSound() {
		return "mob.skeletondeath";
	}

	protected final String getEntityString() {
		return "Melee Skeleton";
	}

	protected final int scoreValue() {
		return Item.feather.shiftedIndex;
	}
}
