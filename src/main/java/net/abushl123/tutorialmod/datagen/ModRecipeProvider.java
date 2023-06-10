package net.abushl123.tutorialmod.datagen;

import net.abushl123.tutorialmod.MyFirstMod;
import net.abushl123.tutorialmod.block.ModBlocks;
import net.abushl123.tutorialmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        oreSmelting(consumer, List.of(ModItems.RAW_BLACK_OPAL.get()), RecipeCategory.MISC,
                ModItems.BLACK_OPAL.get(), 0.7f, 200, "black_opal");

        oreBlasting(consumer, List.of(ModItems.RAW_BLACK_OPAL.get()), RecipeCategory.MISC,
                ModItems.BLACK_OPAL.get(), 0.7f, 200, "black_opal");

//        nineBlockStorageRecipes(consumer, RecipeCategory.BUILDING_BLOCKS, ModItems.BLACK_OPAL.get(), RecipeCategory.MISC,
//                ModBlocks.BLACK_OPAL_BLOCK.get());

        nineBlockRecipe(consumer, ModItems.BLACK_OPAL.get(), ModBlocks.BLACK_OPAL_BLOCK.get(), RecipeCategory.MISC, RecipeCategory.BUILDING_BLOCKS);
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> p_248775_, List<ItemLike> p_251504_, RecipeCategory p_248846_, ItemLike p_249735_, float p_248783_, int p_250303_, String p_251984_) {
        oreCooking(p_248775_, RecipeSerializer.BLASTING_RECIPE, p_251504_, p_248846_, p_249735_, p_248783_, p_250303_, p_251984_, "_from_blasting");
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> p_250654_, List<ItemLike> p_250172_, RecipeCategory p_250588_, ItemLike p_251868_, float p_250789_, int p_252144_, String p_251687_) {
        oreCooking(p_250654_, RecipeSerializer.SMELTING_RECIPE, p_250172_, p_250588_, p_251868_, p_250789_, p_252144_, p_251687_, "_from_smelting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> p_250791_, RecipeSerializer<? extends AbstractCookingRecipe> p_251817_, List<ItemLike> p_249619_, RecipeCategory p_251154_, ItemLike p_250066_, float p_251871_, int p_251316_, String p_251450_, String p_249236_) {

        for (ItemLike itemlike : p_249619_) {
            SimpleCookingRecipeBuilder
                    .generic(Ingredient.of(itemlike), p_251154_, p_250066_, p_251871_, p_251316_, p_251817_)
                    .group(p_251450_)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(p_250791_, new ResourceLocation(MyFirstMod.MOD_ID, getItemName(p_250066_)) + p_249236_ + "_" + getItemName(itemlike));
        }

    }

    protected static void nineBlockRecipe(Consumer<FinishedRecipe> writer,
                                          ItemLike unit, ItemLike whole,
                                          RecipeCategory toWholeRecipe,
                                          RecipeCategory toUnitRecipe) {
        /*
        Pattern:
            9 units -> 1 whole
            1 whole -> 9 units
         */

        ShapedRecipeBuilder.shaped(toWholeRecipe, whole) // whole is a result of the recipe
                .define('#', unit)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .group(null)
                .unlockedBy(getHasName(unit), has(unit))
                .save(writer, new ResourceLocation(MyFirstMod.MOD_ID, getSimpleRecipeName(whole))); // here we define the location of json file, and we define that the name of that file will be the name of resulting product (whole in this case)

        ShapelessRecipeBuilder.shapeless(toUnitRecipe, unit, 9) // nine is the number of result products that we get
                .requires(whole)
                .group(null)
                .unlockedBy(getHasName(whole), has(whole))
                .save(writer, new ResourceLocation(MyFirstMod.MOD_ID, getSimpleRecipeName(unit)));
    }
}
