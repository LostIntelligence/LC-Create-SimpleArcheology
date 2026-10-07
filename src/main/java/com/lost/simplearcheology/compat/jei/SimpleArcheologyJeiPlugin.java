package com.lost.simplearcheology.compat.jei;

import com.lost.simplearcheology.CreateSimpleArcheology;
import com.lost.simplearcheology.recipe.AgeingRecipe;
import com.lost.simplearcheology.recipe.BrushingRecipe;
import com.lost.simplearcheology.recipe.ModRecipeTypes;
import com.simibubi.create.compat.jei.category.CreateRecipeCategory;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

@JeiPlugin
public class SimpleArcheologyJeiPlugin implements IModPlugin {
    private static final ResourceLocation PLUGIN_ID = ResourceLocation.fromNamespaceAndPath(
            CreateSimpleArcheology.MODID,
            "jei_plugin");

    private CreateRecipeCategory<AgeingRecipe> ageingCategory;
    private CreateRecipeCategory<BrushingRecipe> brushingCategory;

    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_ID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        ageingCategory = new CreateRecipeCategory.Builder<>(AgeingRecipe.class)
                .addTypedRecipes(ModRecipeTypes.AGEING)
                .catalystStack(() -> new ItemStack(createItem("encased_fan")))
                .catalyst(() -> CreateSimpleArcheology.BULK_AGEING_CATALYST.get())
                .doubleItemIcon(createItem("propeller"), CreateSimpleArcheology.BULK_AGEING_CATALYST.get())
                .emptyBackground(178, 72)
                .build(categoryId("ageing"), info -> new SimpleArcheologyFanCategory<>(
                        info,
                        CreateSimpleArcheology.BULK_AGEING_CATALYST.get().defaultBlockState()));

        brushingCategory = new CreateRecipeCategory.Builder<>(BrushingRecipe.class)
                .addTypedRecipes(ModRecipeTypes.BRUSHING)
                .catalystStack(() -> new ItemStack(createItem("encased_fan")))
                .catalyst(() -> CreateSimpleArcheology.BULK_BRUSHING_CATALYST.get())
                .doubleItemIcon(createItem("propeller"), CreateSimpleArcheology.BULK_BRUSHING_CATALYST.get())
                .emptyBackground(178, 72)
                .build(categoryId("brushing"), info -> new SimpleArcheologyFanCategory<>(
                        info,
                        CreateSimpleArcheology.BULK_BRUSHING_CATALYST.get().defaultBlockState()));

        registration.addRecipeCategories(ageingCategory, brushingCategory);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        ageingCategory.registerRecipes(registration);
        brushingCategory.registerRecipes(registration);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        ageingCategory.registerCatalysts(registration);
        brushingCategory.registerCatalysts(registration);
    }

    private static ResourceLocation categoryId(String path) {
        return ResourceLocation.fromNamespaceAndPath(CreateSimpleArcheology.MODID, path);
    }

    private static Item createItem(String path) {
        return BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("create", path));
    }
}