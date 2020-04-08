package net.fabricmc.nauticraft;

import io.github.ladysnake.pal.VanillaAbilities;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.nauticraft.items.AbilityToggleItem;
import net.fabricmc.nauticraft.items.SprintItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class NauticaMod implements ModInitializer {

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
		Registry.register(Registry.ITEM, id("example_item"), new SprintItem(new Item.Settings().group(ItemGroup.MISC), 2));
		Registry.register(Registry.ITEM, id("flight_ring"), new AbilityToggleItem(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1), VanillaAbilities.ALLOW_FLYING, id("flight_ring")));
	}
}
