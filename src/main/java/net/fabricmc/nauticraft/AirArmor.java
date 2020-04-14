package net.fabricmc.nauticraft;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AirArmor extends ArmorItem {

    private EquipmentSlot slotIn;
    private Integer breathingTime;
    private Integer remainingTime;

    public AirArmor(ArmorMaterial material, EquipmentSlot slot, Settings settings, Integer tickTime) {
        super(material, slot, settings);
        slotIn = slot;
        breathingTime = tickTime;
        remainingTime = breathingTime;
    }

    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        PlayerEntity user;
        if (entity instanceof PlayerEntity) {
            user = (PlayerEntity)entity;
            if(user.getEquippedStack(slotIn) == stack && user.getEquippedStack(EquipmentSlot.HEAD).getItem() == NauticaMod.AIR_MASK){
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, remainingTime, 0, false, false, true)); //add effect when equip is on.
                if(user.isSubmergedInWater() && remainingTime > 0){
                    remainingTime--;
                }else if(!user.isSubmergedInWater() && stack.getItem() == NauticaMod.AIR_TANK && remainingTime <= breathingTime){ //Only air tank can recharge in air.
                    remainingTime += 5;
                }
            }
            if(user.getEquippedStack(slotIn) == ItemStack.EMPTY || user.getEquippedStack(EquipmentSlot.HEAD) == ItemStack.EMPTY || remainingTime <= 0) {
                user.removeStatusEffect(StatusEffects.WATER_BREATHING);
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        remainingTime = breathingTime;
        super.onCraft(stack, world, player);
    }
}
