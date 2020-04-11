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
import reborncore.common.util.ItemDurabilityExtensions;
import team.reborn.energy.EnergyHolder;

public class AirArmor extends ArmorItem //implements EnergyHolder, ItemDurabilityExtensions {
{
    private  EquipmentSlot slotIn;
    private  Integer breathingTime;

    public AirArmor(ArmorMaterial material, EquipmentSlot slot, Settings settings, Integer tickTime) {
        super(material, slot, settings);
        slotIn = slot;
        breathingTime = tickTime;
    }

    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        PlayerEntity user;
        if (entity instanceof PlayerEntity) {
            user = (PlayerEntity)entity;
            if(!user.isSubmergedInWater() && user.getEquippedStack(slotIn) == stack && user.getEquippedStack(EquipmentSlot.HEAD).getItem() == NauticaMod.AIR_MASK){
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, breathingTime, 0, false, false, true));
            }else if(user.getEquippedStack(slotIn) == ItemStack.EMPTY || user.getEquippedStack(EquipmentSlot.HEAD) == ItemStack.EMPTY){
                user.removeStatusEffect(StatusEffects.WATER_BREATHING);
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
