/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Open Mods
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
package openmods.igw.impl.integration.openblocks;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.apache.commons.lang3.tuple.Pair;

import igwmod.gui.IReservedSpace;
import igwmod.gui.LocatedTexture;

import openmods.igw.prefab.handler.OpenModsWikiTab;

import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;

/*
 * WARNING!
 * The addition of new static pages most likely requires the editing of the
 * search bar's position and the amount of items shown in the wiki
 * tab. These edits have to be performed in the methods:
 * pagesPerTab() (where you need to edit the amount of items shown, usually diminishing by 2 every time)
 * getReservedSpaces() (where the y of the located texture must be edited to allow for a design-compatible number).
 */
public final class OpenBlocksWikiTab extends OpenModsWikiTab {

    private static final int SEARCH_AND_SCROLLBAR_Y = 98;

    private static final int ITEM_GRID_DEFAULT_X = 40;
    private static final int ITEM_GRID_DEFAULT_Y = 110;
    private static final int ITEM_GRID_DEFAULT_SLOT_DIMENSION = 16;
    private static final int ITEM_GRID_DEFAULT_WIDTH = ITEM_GRID_DEFAULT_SLOT_DIMENSION * 2 + 4;
    private static final int ITEM_GRID_DEFAULT_HEIGHT = ITEM_GRID_DEFAULT_SLOT_DIMENSION * 6 + 12;

    public OpenBlocksWikiTab(final List<Pair<String, ItemStack>> stacks,
                             final Map<String, ItemStack> allClaimedPages,
                             final Map<String, Class<? extends Entity>> allClaimedEntities) {

        super(stacks, allClaimedPages, allClaimedEntities, new IItemPositionProvider() {
            @Override
            public int getX(final int entryId) {
                return 4 * 10 + 1 + entryId % 2 * (10 + 8);
            }

            @Override
            public int getY(final int entryId) {
                return 100 + 10 + 1 + entryId / 2 * (10 + 8);
            }
        });

        this.addPageToStaticPages(createStaticPageFactory("about", this, CommonPositionProviders.STATIC_PAGES));
        this.addPageToStaticPages(createStaticPageFactory("credits", this, CommonPositionProviders.STATIC_PAGES));
        this.addPageToStaticPages(createStaticPageFactory("obUtils", this, CommonPositionProviders.STATIC_PAGES));
        this.addPageToStaticPages(createStaticPageFactory("bKey", this, CommonPositionProviders.STATIC_PAGES));
        this.addPageToStaticPages(createStaticPageFactory("enchantments", this, CommonPositionProviders.STATIC_PAGES));
        this.addPageToStaticPages(createStaticPageFactory("changelog", this, CommonPositionProviders.STATIC_PAGES));
    }

    @Override
    public String getTabName() {
        return "wiki.openblocks.tab";
    }

    @Override
    public String getPageName() {
        return "wiki.openblocks.page";
    }

    @Override
    public List<IReservedSpace> getReservedSpaces() {
        final List<IReservedSpace> reservedSpaces = Lists.newArrayList();
        final ResourceLocation textureLocation = new ResourceLocation("openmods-igw",
                "textures/gui/wiki/6x2.png");
        reservedSpaces.add(
                new LocatedTexture(textureLocation, ITEM_GRID_DEFAULT_X, ITEM_GRID_DEFAULT_Y, ITEM_GRID_DEFAULT_WIDTH,
                        ITEM_GRID_DEFAULT_HEIGHT));
        return reservedSpaces;
    }

    @Override
    public int getSearchBarAndScrollStartY() {
        return SEARCH_AND_SCROLLBAR_Y;
    }

    @Override
    public int pagesPerTab() {
        return 10 + 2;
    }

    @Override
    public int pagesPerScroll() {
        return 2;
    }

    @Nonnull
    @Override
    protected List<Block> getBlockCandidates() {
        return Lists.newArrayList(OpenBlocksItemHolder.FLAG, Blocks.SPONGE);
    }
}
