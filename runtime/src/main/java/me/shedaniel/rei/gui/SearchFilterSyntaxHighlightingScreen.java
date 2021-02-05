package me.shedaniel.rei.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class SearchFilterSyntaxHighlightingScreen extends Screen {
    protected SearchFilterSyntaxHighlightingScreen(Component component) {
        super(component);
    }
    
    
    
    @Override
    public void render(PoseStack matrices, int mouseX, int mouseY, float delta) {
        renderHoleBackground(matrices, 0, height, 32, 255, 255);
        super.render(matrices, mouseX, mouseY, delta);
        
    }
    
    protected void renderHoleBackground(PoseStack matrices, int y1, int y2, int tint, int alpha1, int alpha2) {
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder buffer = tesselator.getBuilder();
        this.minecraft.getTextureManager().bind(BACKGROUND_LOCATION);
        Matrix4f matrix = matrices.last().pose();
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        float float_1 = 32.0F;
        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
        buffer.vertex(matrix, 0, y2, 0.0F).uv(0.0F, y2 / 32.0F).color(tint, tint, tint, alpha2).endVertex();
        buffer.vertex(matrix, this.width, y2, 0.0F).uv(this.width / 32.0F, y2 / 32.0F).color(tint, tint, tint, alpha2).endVertex();
        buffer.vertex(matrix, this.width, y1, 0.0F).uv(this.width / 32.0F, y1 / 32.0F).color(tint, tint, tint, alpha1).endVertex();
        buffer.vertex(matrix, 0, y1, 0.0F).uv(0.0F, y1 / 32.0F).color(tint, tint, tint, alpha1).endVertex();
        tesselator.end();
    }
}