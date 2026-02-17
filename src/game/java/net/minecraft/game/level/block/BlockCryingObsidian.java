package net.minecraft.game.level.block;

import net.minecraft.game.entity.player.EntityPlayer;
import net.minecraft.game.level.World;
import net.minecraft.game.level.material.Material;

public final class BlockCryingObsidian extends Block {
    protected BlockCryingObsidian(int var1) {
        super(var1, Material.rock, "Crying Obsidian");
        this.blockIndexInTexture = 129;
    }

    public final boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
        world.setSpawnLocation(x, y, z, player.rotationYaw);
        try {
            net.minecraft.client.Minecraft mc = net.minecraft.client.Minecraft.getMinecraft();
            if (mc != null && mc.ingameGUI != null) {
                mc.ingameGUI.addChatMessage("Spawn point set to: " + x + ", " + y + ", " + z);
            }
        } catch (Throwable t) {
        }

        return true;
    }
}
