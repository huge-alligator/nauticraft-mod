package net.fabricmc.nauticraft;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.nauticraft.items.UnderwaterMovementItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class NauticaMod implements ModInitializer {

	// Dynamically create identifiers
	public static Identifier id(String path){
		return new Identifier("nauticraft", path);
	}

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		//System.out.println("Hello Fabric world!");

		//Add Item to game.
		Registry.register(Registry.ITEM, id("example_item"), new Item(new Item.Settings().group(ItemGroup.MISC)));
		Registry.register(Registry.ITEM, id("dpv_basic"), new UnderwaterMovementItem(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1), 0.05f));
	}
}
