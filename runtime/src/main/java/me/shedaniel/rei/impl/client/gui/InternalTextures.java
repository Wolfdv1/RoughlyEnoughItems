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

package me.shedaniel.rei.impl.client.gui;

import net.minecraft.resources.ResourceLocation;

public class InternalTextures {
    public static final ResourceLocation ARROW_LEFT_TEXTURE = ResourceLocation.fromNamespaceAndPath("roughlyenoughitems", "textures/gui/arrow_left.png");
    public static final ResourceLocation ARROW_RIGHT_TEXTURE = ResourceLocation.fromNamespaceAndPath("roughlyenoughitems", "textures/gui/arrow_right.png");
    public static final ResourceLocation ARROW_LEFT_SMALL_TEXTURE = ResourceLocation.fromNamespaceAndPath("roughlyenoughitems", "textures/gui/arrow_left_small.png");
    public static final ResourceLocation ARROW_RIGHT_SMALL_TEXTURE = ResourceLocation.fromNamespaceAndPath("roughlyenoughitems", "textures/gui/arrow_right_small.png");
    public static final ResourceLocation CHEST_GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath("roughlyenoughitems", "textures/gui/recipecontainer.png");
    public static final ResourceLocation CHEST_GUI_TEXTURE_DARK = ResourceLocation.fromNamespaceAndPath("roughlyenoughitems", "textures/gui/recipecontainer_dark.png");
    @Deprecated(forRemoval = true)
    public static final ResourceLocation LEGACY_DIRT = ResourceLocation.tryParse("textures/block/dirt.png");
}
