package com.lost.simplearcheology.recipe;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.lost.simplearcheology.CreateSimpleArcheology;
import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;
import com.simibubi.create.foundation.recipe.RecipeApplier;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BrushingFanType implements FanProcessingType {
    @Override
    public boolean isValidAt(Level level, BlockPos pos) {
        return level.getBlockState(pos)
                .is(CreateSimpleArcheology.FAN_PROCESSING_CATALYSTS_BRUSHING);
    }

    @Override
    public int getPriority() {
        return 5000;
    }

    @Override
    public boolean canProcess(ItemStack stack, Level level) {
        boolean canProcess = ModRecipeTypes.BRUSHING
                .find(new SingleRecipeInput(stack), level)
                .isPresent();
        return canProcess;
    }

    @Override
    public @Nullable List<ItemStack> process(
            ItemStack stack,
            Level level) {

        return ModRecipeTypes.BRUSHING
                .find(new SingleRecipeInput(stack), level)
                .map(RecipeHolder::value)
                .map(recipe -> RecipeApplier.applyRecipeOn(
                        level,
                        stack,
                        recipe,
                        true))
                .orElse(null);
    }

    @Override
    public void spawnProcessingParticles(Level level, Vec3 pos) {
    }

    @Override
    public void morphAirFlow(
            AirFlowParticleAccess particleAccess,
            RandomSource random) {
    }

    @Override
    public void affectEntity(Entity entity, Level level) {
    }
}
