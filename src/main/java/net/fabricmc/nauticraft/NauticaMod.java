package net.fabricmc.nauticraft;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class NauticaMod implements ModInitializer {

	// Dynamically create identifiers
	public static Identifier id(String path){ return new Identifier("nauticraft", path); }
	public static Item AIR_MASK = new ArmorItem(ArmorMaterials.LEATHER, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.TOOLS));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		//System.out.println("Hello Fabric world!");

		//Add Item to game.
		Registry.register(Registry.ITEM, id("example_item"), new Item(new Item.Settings().group(ItemGroup.MISC)));
		Registry.register(Registry.ITEM, id("dpv_basic"), new UnderwaterMovementItem(new Item.Settings().group(ItemGroup.TRANSPORTATION).maxCount(1), 0.05f));

		//Breathing stuff.
		Registry.register(Registry.ITEM, id("air_mask"), AIR_MASK);
		Registry.register(Registry.ITEM, id("re_breather"), new AirArmor(ArmorMaterials.LEATHER, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.TOOLS), 6000));
		Registry.register(Registry.ITEM, id("air_tank"), new AirArmor(ArmorMaterials.LEATHER, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.TOOLS), 1200));
	}
}
