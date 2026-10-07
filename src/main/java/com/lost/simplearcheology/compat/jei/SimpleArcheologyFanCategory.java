package com.lost.simplearcheology.compat.jei;

import java.util.List;

import com.simibubi.create.compat.jei.category.ProcessingViaFanCategory;
import com.simibubi.create.compat.jei.category.animations.AnimatedKinetics;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.content.processing.recipe.StandardProcessingRecipe;

import net.createmod.catnip.gui.element.GuiGameElement;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.level.block.state.BlockState;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;

public class SimpleArcheologyFanCategory<T extends StandardProcessingRecipe<?>>
        extends ProcessingViaFanCategory.MultiOutput<T> {
    private final BlockState attachedBlock;

    public SimpleArcheologyFanCategory(Info<T> info, BlockState attachedBlock) {
        super(info);
        this.attachedBlock = attachedBlock;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, T recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 21, 78)
                .setBackground(getRenderedSlot(), -1, -1)
                .addIngredients(recipe.getIngredients().getFirst());

        List<ProcessingOutput> outputs = recipe.getRollableResults();
        for (int index = 0; index < outputs.size(); index++) {
            ProcessingOutput output = outputs.get(index);
            builder.addSlot(RecipeIngredientRole.OUTPUT, 126 + index % 6 * 19, 18 + index / 6 * 19)
                    .setBackground(getRenderedSlot(output), -1, -1)
                    .addItemStack(output.getStack())
                    .addRichTooltipCallback(addStochasticTooltip(output));
        }
    }

    @Override
    protected void renderAttachedBlock(GuiGraphics graphics) {
        GuiGameElement.of(attachedBlock)
                .scale(SCALE)
                .atLocal(0, 0, 2)
                .lighting(AnimatedKinetics.DEFAULT_LIGHTING)
                .render(graphics);
    }
}