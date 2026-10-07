package com.lost.simplearcheology.recipe;

import com.simibubi.create.content.processing.recipe.ProcessingRecipeParams;
import com.simibubi.create.content.processing.recipe.StandardProcessingRecipe;

import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.Level;

public class BrushingRecipe extends StandardProcessingRecipe<RecipeInput> {

    public BrushingRecipe(ProcessingRecipeParams params) {
        super(ModRecipeTypes.BRUSHING, params);
    }

    @Override
    public boolean matches(RecipeInput input, Level level) {
        if (input.size() == 0)
            return false;

        return ingredients.getFirst().test(input.getItem(0));
    }

    @Override
    protected int getMaxInputCount() {
        return 1;
    }

    @Override
    protected int getMaxOutputCount() {
        return 1;
    }
}
