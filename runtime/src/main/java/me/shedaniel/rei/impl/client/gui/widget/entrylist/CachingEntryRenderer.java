/*
 * This file is licensed under the MIT License, part of Roughly Enough Items.
 * Copyright (c) 2018, 2019, 2020, 2021, 2022, 2023 shedaniel
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.shedaniel.rei.impl.client.gui.widget.entrylist;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.entry.renderer.BatchedEntryRenderer;
import me.shedaniel.rei.api.client.gui.widgets.Tooltip;
import me.shedaniel.rei.api.client.gui.widgets.TooltipContext;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.impl.client.gui.widget.CachedEntryListRender;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

public class CachingEntryRenderer implements BatchedEntryRenderer<Object, CachedEntryListRender.Sprite> {
    private final CachedEntryListRender.Sprite sprite;
    
    public CachingEntryRenderer(CachedEntryListRender.Sprite sprite) {
        this.sprite = sprite;
    }
    
    @Override
    public CachedEntryListRender.Sprite getExtraData(EntryStack<Object> entry) {
        return sprite;
    }
    
    @Override
    public int getBatchIdentifier(EntryStack<Object> entry, Rectangle bounds, CachedEntryListRender.Sprite extraData) {
        return 0;
    }
    
    @Override
    public void startBatch(EntryStack<Object> entry, CachedEntryListRender.Sprite extraData, GuiGraphics graphics, float delta) {
        RenderSystem.setShaderTexture(0, CachedEntryListRender.cachedTextureLocation);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
    }
    
    @Override
    public void renderBase(EntryStack<Object> entry, CachedEntryListRender.Sprite extraData, GuiGraphics graphics, MultiBufferSource.BufferSource immediate, Rectangle bounds, int mouseX, int mouseY, float delta) {
        VertexConsumer consumer = immediate.getBuffer(CachedEntryListRender.renderType.get());
        Matrix4f pose = graphics.pose().last().pose();
        consumer.addVertex(pose, bounds.x, bounds.getMaxY(), 0).setUv(extraData.u0, extraData.v1);
        consumer.addVertex(pose, bounds.getMaxX(), bounds.getMaxY(), 0).setUv(extraData.u1, extraData.v1);
        consumer.addVertex(pose, bounds.getMaxX(), bounds.y, 0).setUv(extraData.u1, extraData.v0);
        consumer.addVertex(pose, bounds.x, bounds.y, 0).setUv(extraData.u0, extraData.v0);
    }
    
    @Override
    public void afterBase(EntryStack<Object> entry, CachedEntryListRender.Sprite extraData, GuiGraphics graphics, float delta) {
    }
    
    @Override
    public void renderOverlay(EntryStack<Object> entry, CachedEntryListRender.Sprite extraData, GuiGraphics graphics, MultiBufferSource.BufferSource immediate, Rectangle bounds, int mouseX, int mouseY, float delta) {
    }
    
    @Override
    public void endBatch(EntryStack<Object> entry, CachedEntryListRender.Sprite extraData, GuiGraphics graphics, float delta) {
    }
    
    @Override
    public void render(EntryStack<Object> entry, GuiGraphics graphics, Rectangle bounds, int mouseX, int mouseY, float delta) {
        graphics.innerBlit(CachedEntryListRender.cachedTextureLocation, bounds.x, bounds.getMaxX(), bounds.y, bounds.getMaxY(), 0, sprite.u0, sprite.u1, sprite.v0, sprite.v1);
    }
    
    @Override
    @Nullable
    public Tooltip getTooltip(EntryStack<Object> entry, TooltipContext context) {
        return entry.getDefinition().getRenderer().getTooltip(entry.cast(), context);
    }
}
