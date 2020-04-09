package net.fabricmc.nauticraft;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class UnderwaterMovementItem extends Item {

    float speedMult = 1;

    public UnderwaterMovementItem(Settings settings, float speed){
        super(settings);
        speedMult = speed;
    }

    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        PlayerEntity user;
        if (entity instanceof PlayerEntity) {
            user = (PlayerEntity)entity;
            if(user.isSwimming() && user.getMainHandStack() == stack){
                Vec3d rot = user.getRotationVector();
                Vec3d vel = user.getVelocity();

                user.setVelocity(vel.add(rot.x * speedMult, rot.y *speedMult, rot.z * speedMult));
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}




