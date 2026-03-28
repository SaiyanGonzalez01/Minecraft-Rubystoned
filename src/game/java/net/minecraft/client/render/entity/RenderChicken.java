package net.minecraft.client.render.entity;

import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.render.entity.RenderLiving;
import net.minecraft.game.entity.EntityLiving;
import net.minecraft.game.entity.Entity;
import net.minecraft.game.entity.animal.EntityChicken;
import org.lwjgl.opengl.GL11;
import util.MathHelper;

public class RenderChicken extends RenderLiving {
	public RenderChicken(ModelBase var1, float var2) {
		super(var1, var2);
	}

	public void renderChicken(EntityChicken var1, float var2, float var4, float var6, float var8, float var9) {
		super.doRender(var1, var2, var4, var6, var8, var9);
	}

	protected float getWingRotation(EntityChicken var1, float var2) {
		float var3 = var1.prevWingRotation + (var1.wingRotation - var1.prevWingRotation) * var2;
		float var4 = var1.prevDestPos + (var1.destPos - var1.prevDestPos) * var2;
		return (MathHelper.sin(var3) + 1.0F) * var4;
	}

	protected float handleRotationFloat(EntityLiving var1, float var2) {
		return this.getWingRotation((EntityChicken)var1, var2);
	}

	public void doRenderLiving(EntityLiving var1, float var2, float var4, float var6, float var8, float var9) {
		this.renderChicken((EntityChicken)var1, var2, var4, var6, var8, var9);
	}

	public void doRender(Entity var1, float var2, float var4, float var6, float var8, float var9) {
		this.renderChicken((EntityChicken)var1, var2, var4, var6, var8, var9);
	}
}