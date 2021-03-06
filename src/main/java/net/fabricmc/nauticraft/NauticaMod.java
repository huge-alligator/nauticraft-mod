package net.fabricmc.nauticraft;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.reborn.energy.EnergyTier;

public class NauticaMod implements ModInitializer {

	// Dynamically create identifiers
	public static Identifier id(String path){ return new Identifier("nauticraft", path); }
	//Ugh
	public static Item AIR_MASK = new ArmorItem(ArmorMaterials.LEATHER, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.TOOLS));
	public static Item AIR_TANK = new AirArmor(ArmorMaterials.LEATHER, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.TOOLS), 1200);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		// System.out.println("Hello Fabric world!");

		//Items.
		Registry.register(Registry.ITEM, id("example_item"), new Item(new Item.Settings().group(ItemGroup.MISC)));
		Registry.register(Registry.ITEM, id("quicklime"), new Item(new Item.Settings().group(ItemGroup.MISC)));

		//Movement Stuff.
		Registry.register(Registry.ITEM, id("dpv_basic"), new UnderwaterMovementItem(new Item.Settings().group(ItemGroup.TRANSPORTATION).maxCount(1), 0.05f, 10000, EnergyTier.LOW));

		//Breathing stuff.
		Registry.register(Registry.ITEM, id("air_mask"), AIR_MASK);
		Registry.register(Registry.ITEM, id("re_breather"), new AirArmor(ArmorMaterials.LEATHER, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.TOOLS), 6000));
		Registry.register(Registry.ITEM, id("air_tank"), AIR_TANK);
	}
}