package net.fabricmc.nauticraft;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import reborncore.common.powerSystem.PowerSystem;
import reborncore.common.util.ItemDurabilityExtensions;
import reborncore.common.util.ItemUtils;
import team.reborn.energy.Energy;
import team.reborn.energy.EnergyHolder;
import team.reborn.energy.EnergySide;
import team.reborn.energy.EnergyTier;

public class UnderwaterMovementItem extends Item  implements EnergyHolder, ItemDurabilityExtensions {

    float speedMult;
    public final double cost = 1;
    public final EnergyTier tier;
    public final int maxCharge;

    public UnderwaterMovementItem(Settings settings, float speed, int maxStoredPower, EnergyTier energyTier){
        super(settings);
        speedMult = speed;
        this.maxCharge = maxStoredPower;
        this.tier = energyTier;
    }

    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        PlayerEntity user;
        if (entity instanceof PlayerEntity) {
            user = (PlayerEntity)entity;
            if(user.isSwimming() && user.getMainHandStack() == stack && Energy.of(stack).getEnergy() >= cost){
                Vec3d rot = user.getRotationVector();
                Vec3d vel = user.getVelocity();

                user.setVelocity(vel.add(rot.x * speedMult, rot.y *speedMult, rot.z * speedMult));
                Energy.of(stack).use(cost);
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    // ItemDurabilityExtensions
    @Override
    public double getDurability(ItemStack stack) {
        return 1 - ItemUtils.getPowerForDurabilityBar(stack);
    }

    @Override
    public boolean showDurability(ItemStack stack) {
        return true;
    }

    @Override
    public int getDurabilityColor(ItemStack stack) {
        return PowerSystem.getDisplayPower().colour;
    }

    // Item
    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public double getMaxStoredPower() {
        return maxCharge;
    }

    @Override
    public EnergyTier getTier() {
        return tier;
    }

    @Override
    public double getMaxOutput(EnergySide side) {
        return 0;
    }
}




