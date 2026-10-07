package com.lost.simplearcheology;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;

import org.joml.Vector3f;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ArchaeologyFanType implements FanProcessingType {

   @Override
public boolean isValidAt(Level level, BlockPos pos) {
    return level.getBlockState(pos)
        .is(ModTags.Blocks.FAN_PROCESSING_CATALYSTS_AGEING);
}


    @Override
    public void spawnProcessingParticles(Level level, Vec3 pos) {
        RandomSource random = level.random;
        if (random.nextInt(4) == 0) {
            DustParticleOptions dust = new DustParticleOptions(new Vector3f(0.82F, 0.72F, 0.55F), 1.0F);
            level.addParticle(
                dust,
                pos.x + (random.nextFloat() - 0.5D) * 0.4D,
                pos.y + (random.nextFloat() - 0.5D) * 0.4D,
                pos.z + (random.nextFloat() - 0.5D) * 0.4D,
                0.0D,
                0.04D,
                0.0D
            );
        }
    }

    @Override
    public int getPriority() {
        return 600;
    }

    @Override
    public boolean canProcess(ItemStack stack, Level level) {
        return !stack.isEmpty();
    }

    @Override
    public @Nullable List<ItemStack> process(ItemStack stack, Level level) {
        return List.of();
    }

    @Override
    public void morphAirFlow(AirFlowParticleAccess particleAccess, RandomSource random) {
        particleAccess.setColor(0xD9C29B);
        particleAccess.setAlpha(0.8F);
        particleAccess.spawnExtraParticle(new DustParticleOptions(new Vector3f(0.82F, 0.72F, 0.55F), 1.0F), 0.15F);
    }

    @Override
    public void affectEntity(Entity entity, Level level) {
        entity.setDeltaMovement(entity.getDeltaMovement().add(0.0D, 0.02D, 0.0D));
    }
}
