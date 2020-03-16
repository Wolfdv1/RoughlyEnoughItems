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

package me.shedaniel.rei.api;

import me.shedaniel.rei.api.widgets.Tooltip;
import me.shedaniel.rei.gui.widget.QueuedTooltip;
import me.shedaniel.rei.gui.widget.TextFieldWidget;
import me.shedaniel.rei.impl.ScreenHelper;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface REIHelper {
    
    /**
     * @return the instance of {@link REIHelper}
     */
    static REIHelper getInstance() {
        return ScreenHelper.getInstance();
    }
    
    boolean isDarkThemeEnabled();
    
    TextFieldWidget getSearchTextField();
    
    List<ItemStack> getInventoryStacks();
    
    /**
     * @deprecated Use {@link #queueTooltip(Tooltip)} or {@link Tooltip#queue()}
     */
    @Deprecated
    @ApiStatus.ScheduledForRemoval
    default void addTooltip(@Nullable QueuedTooltip tooltip) {
        queueTooltip(tooltip);
    }
    
    void queueTooltip(@Nullable Tooltip tooltip);
    
}
