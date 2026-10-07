package com.lost.simplearcheology.compat.jei;

import com.simibubi.create.compat.jei.category.ProcessingViaFanCategory;
import com.simibubi.create.compat.jei.category.animations.AnimatedKinetics;
import com.simibubi.create.content.processing.recipe.StandardProcessingRecipe;

import net.createmod.catnip.gui.element.GuiGameElement;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.level.block.state.BlockState;

public class SimpleArcheologyFanCategory<T extends StandardProcessingRecipe<?>>
        extends ProcessingViaFanCategory.MultiOutput<T> {
    private final BlockState attachedBlock;

    public SimpleArcheologyFanCategory(Info<T> info, BlockState attachedBlock) {
        super(info);
        this.attachedBlock = attachedBlock;
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