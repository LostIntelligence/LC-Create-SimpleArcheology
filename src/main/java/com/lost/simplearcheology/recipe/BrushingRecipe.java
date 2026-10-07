package com.lost.simplearcheology.recipe;

import java.util.List;

import com.simibubi.create.content.processing.recipe.ProcessingRecipeParams;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.content.processing.recipe.StandardProcessingRecipe;

import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
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
        return 64;
    }

    @Override
    public List<ItemStack> rollResults(List<ProcessingOutput> rollableResults, RandomSource randomSource) {
        double totalWeight = rollableResults.stream()
                .mapToDouble(ProcessingOutput::getChance)
                .sum();
        if (totalWeight <= 0 || rollableResults.isEmpty())
            return List.of();

        double draw = randomSource.nextDouble() * totalWeight;
        for (ProcessingOutput output : rollableResults) {
            draw -= output.getChance();
            if (draw < 0)
                return List.of(output.getStack());
        }

        return List.of(rollableResults.getLast().getStack());
    }
}
