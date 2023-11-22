package mod.azure.doom.helper;

import mod.azure.doom.MCDoom;
import mod.azure.doom.registry.FabricDoomBlocks;
import mod.azure.doom.registry.FabricDoomItems;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.Arrays;

public class DoomVillagerTrades {

    public static void addTrades() {
        if (MCDoom.config.enable_weaponsmith_trades) {
            final var weapon_trades = new ArrayList<>(Arrays.asList(VillagerTrades.TRADES.get(VillagerProfession.WEAPONSMITH).get(3)));
            final var result = new ItemListing[]{};
            weapon_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 1, 1, FabricDoomItems.ARGENT_ENERGY, 6, 12, 5));
            weapon_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomItems.BULLETS, 6, 12, 5));
            weapon_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 3, FabricDoomItems.CHAINGUN_BULLETS, 6, 12, 5));
            weapon_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 3, FabricDoomItems.SHOTGUN_SHELLS, 6, 12, 5));
            weapon_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 3, FabricDoomItems.ARGENT_BOLT, 6, 12, 5));
            weapon_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 3, FabricDoomItems.ROCKET, 6, 12, 5));
            weapon_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 3, FabricDoomItems.ENERGY_CELLS, 6, 12, 5));
            weapon_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 3, FabricDoomItems.UNMAKRY_BOLT, 6, 12, 5));
            VillagerTrades.TRADES.get(VillagerProfession.WEAPONSMITH).put(3, weapon_trades.toArray(result));
        }

        if (MCDoom.config.enable_toolsmith_trades) {
            final var result = new ItemListing[]{};
            final var tool_trades = new ArrayList<>(Arrays.asList(VillagerTrades.TRADES.get(VillagerProfession.TOOLSMITH).get(3)));
            tool_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomItems.ARGENT_PICKAXE, 1, 12, 5));
            tool_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomItems.ARGENT_AXE, 1, 12, 5));
            tool_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomItems.ARGENT_HOE, 1, 12, 5));
            tool_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomItems.ARGENT_SHOVEL, 1, 12, 5));
            VillagerTrades.TRADES.get(VillagerProfession.TOOLSMITH).put(3, tool_trades.toArray(result));
        }

        if (MCDoom.config.enable_mason_trades) {
            final var block_trades = new ArrayList<>(Arrays.asList(VillagerTrades.TRADES.get(VillagerProfession.MASON).get(3)));
            final var result = new ItemListing[]{};
            block_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomBlocks.E1M1_1.asItem(), 1, 12, 5));
            block_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomBlocks.E1M1_2.asItem(), 1, 12, 5));
            block_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomBlocks.E1M1_3.asItem(), 1, 12, 5));
            block_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomBlocks.E1M1_4.asItem(), 1, 12, 5));
            block_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomBlocks.E1M1_5.asItem(), 1, 12, 5));
            block_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomBlocks.E1M1_6.asItem(), 1, 12, 5));
            block_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomBlocks.E1M1_7.asItem(), 1, 12, 5));
            block_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomBlocks.E1M1_8.asItem(), 1, 12, 5));
            block_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomBlocks.E1M1_9.asItem(), 1, 12, 5));
            block_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomBlocks.E1M1_10.asItem(), 1, 12, 5));
            block_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomBlocks.E1M1_11.asItem(), 1, 12, 5));
            block_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomBlocks.E1M1_12.asItem(), 1, 12, 5));
            block_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomBlocks.E1M1_13.asItem(), 1, 12, 5));
            block_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomBlocks.E1M1_14.asItem(), 1, 12, 5));
            block_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomBlocks.E1M1_15.asItem(), 1, 12, 5));
            block_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomBlocks.E1M1_16.asItem(), 1, 12, 5));
            block_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomBlocks.E1M1_17.asItem(), 1, 12, 5));
            block_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomBlocks.E1M1_18.asItem(), 1, 12, 5));
            block_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomBlocks.E1M1_19.asItem(), 1, 12, 5));
            block_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomBlocks.E1M1_20.asItem(), 1, 12, 5));
            block_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomBlocks.E1M1_21.asItem(), 1, 12, 5));
            block_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomBlocks.E1M1_22.asItem(), 1, 12, 5));
            block_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomBlocks.E1M1_23.asItem(), 1, 12, 5));
            block_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomBlocks.E1M1_24.asItem(), 1, 12, 5));
            block_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomBlocks.E1M1_25.asItem(), 1, 12, 5));
            block_trades.add(new VillagerTrades.ItemsAndEmeraldsToItems(Items.EMERALD, 2, FabricDoomBlocks.E1M1_26.asItem(), 1, 12, 5));
            VillagerTrades.TRADES.get(VillagerProfession.MASON).put(3, block_trades.toArray(result));
        }
    }
}