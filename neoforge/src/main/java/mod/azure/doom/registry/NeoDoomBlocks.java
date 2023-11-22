package mod.azure.doom.registry;

import mod.azure.doom.MCDoom;
import mod.azure.doom.blocks.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NeoDoomBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MCDoom.MOD_ID);

    public static final RegistryObject<Block> TOTEM = BLOCKS.register("totem", () -> new TotemBlock());

    public static final RegistryObject<Block> GUN_TABLE = BLOCKS.register("gun_table", () -> new GunTableBlock());

    public static final RegistryObject<Block> BARREL_BLOCK = BLOCKS.register("barrel", () -> new BarrelBlock());

    public static final RegistryObject<Block> ARGENT_BLOCK = BLOCKS.register("argent_block", () -> new ArgentBlock());

    public static final RegistryObject<Block> ARGENT_LAMP_BLOCK = BLOCKS.register("argent_lamp_block", () -> new ArgentLampBlock());

    public static final RegistryObject<Block> DOOM_SAND = BLOCKS.register("doom_sand", () -> new DoomSandBlock());

    public static final RegistryObject<Block> JUMP_PAD = BLOCKS.register("jump_pad", JumppadBlock::new);

    public static final RegistryObject<Block> ICON_WALL1 = BLOCKS.register("icon_wall1", () -> new DoomWallBlock());
    public static final RegistryObject<Block> ICON_WALL2 = BLOCKS.register("icon_wall2", () -> new DoomWallBlock());
    public static final RegistryObject<Block> ICON_WALL3 = BLOCKS.register("icon_wall3", () -> new DoomWallBlock());
    public static final RegistryObject<Block> ICON_WALL4 = BLOCKS.register("icon_wall4", () -> new DoomWallBlock());
    public static final RegistryObject<Block> ICON_WALL5 = BLOCKS.register("icon_wall5", () -> new DoomWallBlock());
    public static final RegistryObject<Block> ICON_WALL6 = BLOCKS.register("icon_wall6", () -> new DoomWallBlock());
    public static final RegistryObject<Block> ICON_WALL7 = BLOCKS.register("icon_wall7", () -> new DoomWallBlock());
    public static final RegistryObject<Block> ICON_WALL8 = BLOCKS.register("icon_wall8", () -> new DoomWallBlock());
    public static final RegistryObject<Block> ICON_WALL9 = BLOCKS.register("icon_wall9", () -> new DoomWallBlock());
    public static final RegistryObject<Block> ICON_WALL10 = BLOCKS.register("icon_wall10", () -> new DoomWallBlock());
    public static final RegistryObject<Block> ICON_WALL11 = BLOCKS.register("icon_wall11", () -> new DoomWallBlock());
    public static final RegistryObject<Block> ICON_WALL12 = BLOCKS.register("icon_wall12", () -> new DoomWallBlock());
    public static final RegistryObject<Block> ICON_WALL13 = BLOCKS.register("icon_wall13", () -> new DoomWallBlock());
    public static final RegistryObject<Block> ICON_WALL14 = BLOCKS.register("icon_wall14", () -> new DoomWallBlock());
    public static final RegistryObject<Block> ICON_WALL15 = BLOCKS.register("icon_wall15", () -> new DoomWallBlock());
    public static final RegistryObject<Block> ICON_WALL16 = BLOCKS.register("icon_wall16", () -> new DoomWallBlock());

    public static final RegistryObject<Block> E1M1_1 = BLOCKS.register("e1m1_block1", () -> new ArgentLampBlock());
    public static final RegistryObject<Block> E1M1_2 = BLOCKS.register("e1m1_block2", () -> new ArgentLampBlock());
    public static final RegistryObject<Block> E1M1_3 = BLOCKS.register("e1m1_block3", () -> new ArgentLampBlock());
    public static final RegistryObject<Block> E1M1_4 = BLOCKS.register("e1m1_block4", () -> new ArgentLampBlock());
    public static final RegistryObject<Block> E1M1_5 = BLOCKS.register("e1m1_block5", () -> new ArgentLampBlock());
    public static final RegistryObject<Block> E1M1_6 = BLOCKS.register("e1m1_block6", () -> new ArgentLampBlock());
    public static final RegistryObject<Block> E1M1_7 = BLOCKS.register("e1m1_block7", () -> new E1M1StairsBlock(E1M1_5.get().defaultBlockState(), BlockBehaviour.Properties.copy(E1M1_5.get())));
    public static final RegistryObject<Block> E1M1_8 = BLOCKS.register("e1m1_block8", () -> new E1M1StairsBlock(E1M1_4.get().defaultBlockState(), BlockBehaviour.Properties.copy(E1M1_4.get())));
    public static final RegistryObject<Block> E1M1_9 = BLOCKS.register("e1m1_block9", () -> new E1M1TurnableBlock());
    public static final RegistryObject<Block> E1M1_10 = BLOCKS.register("e1m1_block10", () -> new E1M1TurnableBlock());
    public static final RegistryObject<Block> E1M1_11 = BLOCKS.register("e1m1_block11", () -> new E1M1TurnableHurtBlock());
    public static final RegistryObject<Block> E1M1_12 = BLOCKS.register("e1m1_block12", () -> new E1M1TurnableBlock());
    public static final RegistryObject<Block> E1M1_13 = BLOCKS.register("e1m1_block13", () -> new E1M1TurnableBlock());
    public static final RegistryObject<Block> E1M1_14 = BLOCKS.register("e1m1_block14", () -> new E1M1TurnableBlock());
    public static final RegistryObject<Block> E1M1_15 = BLOCKS.register("e1m1_block15", () -> new E1M1TurnableBlock());
    public static final RegistryObject<Block> E1M1_16 = BLOCKS.register("e1m1_block16", () -> new E1M1TurnableBlock());
    public static final RegistryObject<Block> E1M1_17 = BLOCKS.register("e1m1_block17", () -> new E1M1TurnableBlock());
    public static final RegistryObject<Block> E1M1_18 = BLOCKS.register("e1m1_block18", () -> new E1M1TurnableBlock());
    public static final RegistryObject<Block> E1M1_19 = BLOCKS.register("e1m1_block19", () -> new E1M1TurnableBlock());
    public static final RegistryObject<Block> E1M1_20 = BLOCKS.register("e1m1_block20", () -> new E1M1TurnableBlock());
    public static final RegistryObject<Block> E1M1_21 = BLOCKS.register("e1m1_block21", () -> new E1M1TurnableBlock());
    public static final RegistryObject<Block> E1M1_22 = BLOCKS.register("e1m1_block22", () -> new E1M1TurnableBlock());
    public static final RegistryObject<Block> E1M1_23 = BLOCKS.register("e1m1_block23", () -> new E1M1TurnableBlock());
    public static final RegistryObject<Block> E1M1_24 = BLOCKS.register("e1m1_block24", () -> new E1M1TurnableBlock());
    public static final RegistryObject<Block> E1M1_25 = BLOCKS.register("e1m1_block25", () -> new E1M1TurnableBlock());
    public static final RegistryObject<Block> E1M1_26 = BLOCKS.register("e1m1_block26", () -> new E1M1TurnableBlock());
    public static final RegistryObject<Block> E1M1_27 = BLOCKS.register("e1m1_block27", () -> new E1M1StairsBlock(E1M1_5.get().defaultBlockState(), BlockBehaviour.Properties.copy(E1M1_5.get())));
    public static final RegistryObject<Block> E1M1_28 = BLOCKS.register("e1m1_block28", () -> new E1M1StairsBlock(E1M1_5.get().defaultBlockState(), BlockBehaviour.Properties.copy(E1M1_5.get())));
    public static final RegistryObject<Block> E1M1_29 = BLOCKS.register("e1m1_block29", () -> new E1M1StairsBlock(E1M1_5.get().defaultBlockState(), BlockBehaviour.Properties.copy(E1M1_5.get())));


}
