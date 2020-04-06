package net.fabricmc.nauticamod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class NauticaMod implements ModInitializer {

	public static final Item EXAMPLE_ITEM = new Item(new Item.Settings().group(ItemGroup.MISC));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		System.out.println("Hello Fabric world!");

		//Add Item to game.
		Registry.register(Registry.ITEM, new Identifier("nauticamod", "example_item"), EXAMPLE_ITEM);
	}
}
