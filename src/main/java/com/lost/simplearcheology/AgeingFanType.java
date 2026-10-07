package com.lost.simplearcheology;

import java.util.List;

import org.jetbrains.annotations.Nullable;

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

public class AgeingFanType implements FanProcessingType {

    @Override
    public boolean isValidAt(Level level, BlockPos pos) {
        return level.getBlockState(pos)
                .is(CreateSimpleArcheology.BULK_AGEING_CATALYST.get());
    }

    @Override
    public int getPriority() {
        return 5000;
    }

    @Override
    public boolean canProcess(ItemStack stack, Level level) {
        boolean canProcess = ModRecipeTypes.AGEING
                .find(new SingleRecipeInput(stack), level)
                .isPresent();

        CreateSimpleArcheology.LOGGER.info(
                "AGEING recipe lookup: item={}, found={}",
                stack.getItem(),
                canProcess
        );

        return canProcess;
    }

    @Override
    public @Nullable List<ItemStack> process(
            ItemStack stack,
            Level level) {

        return ModRecipeTypes.AGEING
                .find(new SingleRecipeInput(stack), level)
                .map(RecipeHolder::value)
                .map(recipe -> RecipeApplier.applyRecipeOn(
                        level,
                        stack,
                        recipe,
                        true
                ))
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
