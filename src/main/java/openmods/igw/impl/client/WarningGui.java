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
package openmods.igw.impl.client;

import com.google.common.collect.Lists;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiYesNo;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import openmods.igw.api.OpenModsIGWApi;
import openmods.igw.api.service.IConstantRetrieverService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

@SideOnly(Side.CLIENT)
public class WarningGui extends GuiYesNo {

    private static boolean shouldShow;
    private static final String TITLE = OpenModsIGWApi.get().translate("missing.title");
    private static final String MESSAGE = OpenModsIGWApi.get().translate("missing.text");
    private static final String CONTINUE_BUTTON_LABEL = OpenModsIGWApi.get().translate("button.continue");
    private static final String INSTALL_BUTTON_LABEL = OpenModsIGWApi.get().translate("button.install");
    private static final String INSTALL_BUTTON_WARNING = OpenModsIGWApi.get().translate("button.install.warning");
    private static final String IGW_URL = "http://www.curse.com/mc-mods/minecraft/223815-in-game-wiki-mod";
    private static final int CONTINUE = 0;
    private static final int INSTALL = 1;
    private static final int EXIT_CODE_INTERNAL = "IGW-Hot-Load".hashCode();

    @SuppressWarnings({"ConstantConditions", "WeakerAccess", "unused"})
    public WarningGui() {
        super(null, TITLE, MESSAGE, CONTINUE_BUTTON_LABEL, INSTALL_BUTTON_LABEL, 0);
        OpenModsIGWApi.get().log().info("IGW Mod not found. Gui constructed and shown");
        shouldShow = false;
    }

    @SuppressWarnings("ConstantConditions")
    public static void markShow() {
        if (!OpenModsIGWApi.get().obtainService(IConstantRetrieverService.class).get()
                .cast().getBooleanConfigConstant("enableMissingModWarningMessage").get()) return;
        shouldShow = true;
    }

    @SuppressWarnings("WeakerAccess")
    public static boolean shallShow() {
        return shouldShow;
    }

    @Override
    protected void actionPerformed(final GuiButton button) {
        switch (button.id) {
            case CONTINUE:
                this.mc.displayGuiScreen(null);
                break;
            case INSTALL:
                try {
                    java.awt.Desktop.getDesktop().browse(new java.net.URL(IGW_URL).toURI());
                } catch (final MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (final URISyntaxException e) {
                    throw new RuntimeException(e);
                } catch (final IOException e) {
                    OpenModsIGWApi.get().log().severe("Why would you run a client in a headless environment?");
                }
                // Hot loading is not possible, so...
                net.minecraftforge.fml.common.FMLCommonHandler.instance().exitJava(EXIT_CODE_INTERNAL, false);
                break;
            default:
                throw new IllegalStateException("Invalid button ID in WarningGui @ OpenMods-IGW");
        }
    }

    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float renderPartialTicks) {
        // We want to add a tooltip to the INSTALL button because it opens
        // a web page: I think it is better to warn the user.
        super.drawScreen(mouseX, mouseY, renderPartialTicks);

        final GuiButton btn = this.buttonList.get(INSTALL);

        if (!btn.isMouseOver()) return;

        final List<String> text = Lists.newArrayList();
        text.add(INSTALL_BUTTON_WARNING);
        text.add(IGW_URL);
        this.drawHoveringText(text, mouseX, mouseY, this.fontRendererObj);
    }
}
