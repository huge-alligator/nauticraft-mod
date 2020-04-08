package net.fabricmc.nauticraft.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SprintItem extends Item {

    float speedMult = 1;

    public SprintItem(Settings settings, float speed){
        super(settings);
        speedMult = speed;
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){

        if(user.isSwimming()){

            Vec3d rot = user.getRotationVector();
            Vec3d vel = user.getVelocity();

            user.setVelocity(vel.add(rot.x * speedMult, rot.y *speedMult, rot.z * speedMult));
        }

        return TypedActionResult.success(user.getStackInHand(hand));
    }

    public void inventoryTick(ItemStack stack, World world, PlayerEntity user, int slot, boolean selected) {
        /* if(user.isSwimming() && user.getItemsHand() == stack.getItem()){
            Vec3d rot = user.getRotationVector();
            Vec3d vel = user.getVelocity();

            user.setVelocity(vel.add(rot.x * speedMult, rot.y *speedMult, rot.z * speedMult));
        } */

        if(user.getItemsHand() == stack.getItem()){
            user.addChatMessage(new LiteralText("Inventory tick"), false);
        }
    }
}




