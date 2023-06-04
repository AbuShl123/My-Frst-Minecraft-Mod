package net.abushl123.tutorialmod.datagen;

import net.abushl123.tutorialmod.block.ModBlocks;
import net.abushl123.tutorialmod.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    protected ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.BLACK_OPAL_BLOCK.get());

        add(ModBlocks.BLACK_OPAL_ORE.get(),
                block -> createOreDrop(ModBlocks.BLACK_OPAL_ORE.get(), ModItems.RAW_BLACK_OPAL.get()));
        add(ModBlocks.DEEPSLATE_BLACK_OPAL_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_BLACK_OPAL_ORE.get(), ModItems.RAW_BLACK_OPAL.get()));
        add(ModBlocks.ENDSTONE_BLACK_OPAL_ORE.get(),
                block -> createOreDrop(ModBlocks.ENDSTONE_BLACK_OPAL_ORE.get(), ModItems.RAW_BLACK_OPAL.get()));
        add(ModBlocks.NETHERRACK_BLACK_OPAL_ORE.get(),
                block -> createOreDrop(ModBlocks.NETHERRACK_BLACK_OPAL_ORE.get(), ModItems.RAW_BLACK_OPAL.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
//      return () -> ModBlocks.BLOCKS.getEntries().stream().map(entry -> entry.get()).iterator(); // the same as above line of code
    }
}
