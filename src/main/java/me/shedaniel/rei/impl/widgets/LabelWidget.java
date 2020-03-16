/*
 * This file is licensed under the MIT License, part of Roughly Enough Items.
 * Copyright (c) 2018, 2019, 2020 shedaniel
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

package me.shedaniel.rei.impl.widgets;

import me.shedaniel.math.Point;
import me.shedaniel.math.api.Rectangle;
import me.shedaniel.rei.api.REIHelper;
import me.shedaniel.rei.api.widgets.Label;
import me.shedaniel.rei.api.widgets.Tooltip;
import me.shedaniel.rei.api.widgets.Widgets;
import net.minecraft.client.gui.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public final class LabelWidget extends Label {
    
    private boolean focused = false;
    private boolean clickable = false;
    private int horizontalAlignment = Label.CENTER;
    private boolean hasShadow = true;
    private boolean focusable = true;
    private int color = REIHelper.getInstance().isDarkThemeEnabled() ? 0xFFBBBBBB : -1;
    private int hoveredColor = REIHelper.getInstance().isDarkThemeEnabled() ? -1 : 0xFF66FFCC;
    @NotNull private Point point;
    @Nullable private Function<Label, @Nullable String> tooltip;
    @Nullable private Consumer<Label> onClick;
    @Nullable private Consumer<Label> onRender;
    @NotNull private String text;
    
    public LabelWidget(@NotNull Point point, @NotNull String text) {
        Objects.requireNonNull(this.point = point);
        Objects.requireNonNull(this.text = text);
    }
    
    @Override
    public boolean isClickable() {
        return clickable;
    }
    
    @Override
    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }
    
    @Nullable
    @Override
    public Consumer<Label> getOnClick() {
        return onClick;
    }
    
    @Override
    public void setOnClick(@Nullable Consumer<Label> onClick) {
        this.onClick = onClick;
    }
    
    @Nullable
    @Override
    public Consumer<Label> getOnRender() {
        return onRender;
    }
    
    @Override
    public void setOnRender(@Nullable Consumer<Label> onRender) {
        this.onRender = onRender;
    }
    
    @Override
    public boolean isFocusable() {
        return focusable;
    }
    
    @Override
    public void setFocusable(boolean focusable) {
        this.focusable = focusable;
    }
    
    @Override
    @Nullable
    public String getTooltip() {
        if (tooltip == null)
            return null;
        return tooltip.apply(this);
    }
    
    @Override
    public void setTooltip(@Nullable Function<Label, @Nullable String> tooltip) {
        this.tooltip = tooltip;
    }
    
    @Override
    public int getHorizontalAlignment() {
        return horizontalAlignment;
    }
    
    @Override
    public void setHorizontalAlignment(int horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }
    
    @Override
    public boolean hasShadow() {
        return hasShadow;
    }
    
    @Override
    public void setShadow(boolean hasShadow) {
        this.hasShadow = hasShadow;
    }
    
    @Override
    public int getColor() {
        return color;
    }
    
    @Override
    public void setColor(int color) {
        this.color = color;
    }
    
    @Override
    public int getHoveredColor() {
        return hoveredColor;
    }
    
    @Override
    public void setHoveredColor(int hoveredColor) {
        this.hoveredColor = hoveredColor;
    }
    
    @Override
    public @NotNull Point getPoint() {
        return point;
    }
    
    @Override
    public void setPoint(@NotNull Point point) {
        this.point = Objects.requireNonNull(point);
    }
    
    @Override
    public @NotNull String getText() {
        return text;
    }
    
    @Override
    public void setText(@NotNull String text) {
        this.text = Objects.requireNonNull(text);
    }
    
    @Override
    public Rectangle getBounds() {
        int width = font.getStringWidth(text);
        Point point = getPoint();
        if (getHorizontalAlignment() == LEFT_ALIGNED)
            return new Rectangle(point.x - 1, point.y - 5, width + 2, 14);
        if (getHorizontalAlignment() == RIGHT_ALIGNED)
            return new Rectangle(point.x - width - 1, point.y - 5, width + 2, 14);
        return new Rectangle(point.x - width / 2 - 1, point.y - 5, width + 2, 14);
    }
    
    @Override
    public void render(int mouseX, int mouseY, float delta) {
        if (getOnRender() != null)
            getOnRender().accept(this);
        int color = getColor();
        if (isClickable() && isHovered(mouseX, mouseY))
            color = getHoveredColor();
        Point pos = getPoint();
        int width = font.getStringWidth(getText());
        switch (getHorizontalAlignment()) {
            case LEFT_ALIGNED:
                if (hasShadow())
                    font.drawWithShadow(getText(), pos.x, pos.y, color);
                else
                    font.draw(getText(), pos.x, pos.y, color);
                break;
            case RIGHT_ALIGNED:
                if (hasShadow())
                    font.drawWithShadow(getText(), pos.x - width, pos.y, color);
                else
                    font.draw(getText(), pos.x - width, pos.y, color);
                break;
            case CENTER:
            default:
                if (hasShadow())
                    font.drawWithShadow(getText(), pos.x - width / 2f, pos.y, color);
                else
                    font.draw(getText(), pos.x - width / 2f, pos.y, color);
                break;
        }
        if (isHovered(mouseX, mouseY)) {
            String tooltip = getTooltip();
            if (tooltip != null) {
                if (!focused && containsMouse(mouseX, mouseY))
                    Tooltip.create(tooltip.split("\n")).queue();
                else if (focused)
                    Tooltip.create(point, tooltip.split("\n")).queue();
            }
        }
    }
    
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0 && isClickable() && containsMouse(mouseX, mouseY)) {
            Widgets.produceClickSound();
            if (onClick != null)
                onClick.accept(this);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean keyPressed(int int_1, int int_2, int int_3) {
        if (!isClickable() || !isFocusable() || !focused)
            return false;
        if (int_1 != 257 && int_1 != 32 && int_1 != 335)
            return false;
        Widgets.produceClickSound();
        if (onClick != null)
            onClick.accept(this);
        return true;
    }
    
    @Override
    public boolean changeFocus(boolean boolean_1) {
        if (!isClickable() || !isFocusable())
            return false;
        this.focused = !this.focused;
        return true;
    }
    
    public boolean isHovered(int mouseX, int mouseY) {
        return containsMouse(mouseX, mouseY) || focused;
    }
    
    @Override
    public List<? extends Element> children() {
        return Collections.emptyList();
    }
}
