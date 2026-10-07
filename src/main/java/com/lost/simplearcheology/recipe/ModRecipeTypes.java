package com.lost.simplearcheology.recipe;

import java.util.Optional;

import com.lost.simplearcheology.CreateSimpleArcheology;
import com.simibubi.create.content.processing.recipe.StandardProcessingRecipe;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public enum ModRecipeTypes implements IRecipeTypeInfo {

    AGEING(AgeingRecipe::new),
    BRUSHING(BrushingRecipe::new);

    public final ResourceLocation id;

    private final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<?>> serializer;
    private final DeferredHolder<RecipeType<?>, RecipeType<?>> type;

    ModRecipeTypes(StandardProcessingRecipe.Factory<?> factory) {
        String name = name().toLowerCase();

        id = ResourceLocation.fromNamespaceAndPath(
                CreateSimpleArcheology.MODID,
                name
        );

        serializer = Registers.SERIALIZERS.register(
                name,
                () -> new StandardProcessingRecipe.Serializer<>(factory)
        );

        type = Registers.TYPES.register(
                name,
                () -> RecipeType.simple(id)
        );
    }

    public static void register(IEventBus eventBus) {
        Registers.SERIALIZERS.register(eventBus);
        Registers.TYPES.register(eventBus);
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends RecipeSerializer<?>> T getSerializer() {
        return (T) serializer.get();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <I extends RecipeInput, R extends Recipe<I>>
    RecipeType<R> getType() {
        return (RecipeType<R>) type.get();
    }

    public <I extends RecipeInput, R extends Recipe<I>>
    Optional<RecipeHolder<R>> find(I input, Level level) {
        return level.getRecipeManager().getRecipeFor(
                getType(),
                input,
                level
        );
    }

    private static class Registers {

        private static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
                DeferredRegister.create(
                        BuiltInRegistries.RECIPE_SERIALIZER,
                        CreateSimpleArcheology.MODID
                );

        private static final DeferredRegister<RecipeType<?>> TYPES =
                DeferredRegister.create(
                        net.minecraft.core.registries.Registries.RECIPE_TYPE,
                        CreateSimpleArcheology.MODID
                );
    }
}
