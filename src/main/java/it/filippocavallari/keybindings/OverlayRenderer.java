package it.filippocavallari.keybindings;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.opengl.GL11;

import java.util.Locale;

public class OverlayRenderer {

    private final ClickManager clickManager;
    private final HUD hud;

    public OverlayRenderer(final ClickManager clickManager, final HUD hud) {
        this.clickManager = clickManager;
        this.hud = hud;
    }

    public void render() {
        beginRendering();
        renderGui();
        renderFonts();
        endRendering();
    }

    private void renderGui() {
        //forward key
        AbstractGui.fill(hud.getxOffset() + hud.getMargin() + hud.getQuadSize(), hud.getyOffset(), hud.getxOffset() + hud.getMargin() + hud.getQuadSize() * 2, hud.getyOffset() + hud.getQuadSize(), 0x1f888888);
        //left key
        AbstractGui.fill(hud.getxOffset(), hud.getyOffset() + hud.getMargin() + hud.getQuadSize(), hud.getxOffset() + hud.getQuadSize(), hud.getyOffset() + hud.getMargin() + hud.getQuadSize() * 2, 0x1f888888);
        //backward key
        AbstractGui.fill(hud.getxOffset() + hud.getMargin() + hud.getQuadSize(), hud.getyOffset() + hud.getMargin() + hud.getQuadSize(), hud.getxOffset() + hud.getMargin() + hud.getQuadSize() * 2, hud.getyOffset() + hud.getMargin() + hud.getQuadSize() * 2, 0x1f888888);
        //right key
        AbstractGui.fill(hud.getxOffset() + hud.getMargin() * 2 + hud.getQuadSize() * 2, hud.getyOffset() + hud.getMargin() + hud.getQuadSize(), hud.getxOffset() + hud.getMargin() * 2 + hud.getQuadSize() * 3, hud.getyOffset() + hud.getMargin() + hud.getQuadSize() * 2, 0x1f888888);
        //LMP
        AbstractGui.fill(hud.getxOffset(), hud.getyOffset() + hud.getMargin() * 2 + hud.getQuadSize() * 2, hud.getxOffset() + hud.getClickBoxWidth(), hud.getyOffset() + hud.getMargin() * 2 + hud.getQuadSize() * 3, 0x1f888888);
        //RMP
        AbstractGui.fill(hud.getxOffset() + hud.getMargin() + hud.getClickBoxWidth(), hud.getyOffset() + hud.getMargin() * 2 + hud.getQuadSize() * 2, hud.getxOffset() + hud.getMargin() + hud.getClickBoxWidth() * 2, hud.getyOffset() + hud.getMargin() * 2 + hud.getQuadSize() * 3, 0x1f888888);
        //space bar
        AbstractGui.fill(hud.getxOffset(), hud.getyOffset() + hud.getMargin() * 3 + hud.getQuadSize() * 3, hud.getxOffset() + hud.getMargin() * 2 + hud.getQuadSize() * 3, hud.getyOffset() + hud.getMargin() * 3 + hud.getQuadSize() * 4, 0x1f888888);
        //sneak
        AbstractGui.fill(hud.getxOffset() + hud.getMargin() * 2 + hud.getClickBoxWidth() * 2, hud.getyOffset() + hud.getMargin() * 2 + hud.getQuadSize() * 2, hud.getxOffset() + hud.getMargin() * 2 + hud.getClickBoxWidth() * 2 + hud.getQuadSize(), hud.getyOffset() + hud.getMargin() * 2 + hud.getQuadSize() * 2 + hud.getSneakHeight(), 0x1f888888);
    }

    private void renderFonts() {
        //Must retrieve the bound key at every render because the player could have change it in the meantime
        final GameSettings gameSettings = Minecraft.getInstance().gameSettings;
        final String forwardKey = gameSettings.keyBindForward.getKey().toString().split("\\.")[2].toUpperCase(Locale.ROOT);
        final String backwardKey = gameSettings.keyBindBack.getKey().toString().split("\\.")[2].toUpperCase(Locale.ROOT);
        final String leftKey = gameSettings.keyBindLeft.getKey().toString().split("\\.")[2].toUpperCase(Locale.ROOT);
        final String rightKey = gameSettings.keyBindRight.getKey().toString().split("\\.")[2].toUpperCase(Locale.ROOT);
        String attackKey = gameSettings.keyBindAttack.getKey().toString().split("\\.")[2].toUpperCase(Locale.ROOT);
        if (attackKey.equalsIgnoreCase("LEFT")) attackKey = "LMB";
        String useKey = gameSettings.keyBindUseItem.getKey().toString().split("\\.")[2].toUpperCase(Locale.ROOT);
        if (useKey.equalsIgnoreCase("RIGHT")) useKey = "RMB";
        final FontRenderer fontRenderer = Minecraft.getInstance().fontRenderer;
        //forward
        int color = gameSettings.keyBindForward.isKeyDown() ? 0xFFFFd700 : 0xFF000000;
        fontRenderer.drawString(forwardKey, hud.getxOffset() + hud.getMargin() + hud.getQuadSize() + hud.getQuadPadding(), hud.getyOffset() + hud.getQuadPadding(), color);
        //backward
        color = gameSettings.keyBindBack.isKeyDown() ? 0xFFFFd700 : 0xFF000000;
        fontRenderer.drawString(backwardKey, hud.getxOffset() + hud.getMargin() + hud.getQuadSize() + hud.getQuadPadding(), hud.getyOffset() + hud.getMargin() + hud.getQuadSize() + hud.getQuadPadding(), color);
        //left
        color = gameSettings.keyBindLeft.isKeyDown() ? 0xFFFFd700 : 0xFF000000;
        fontRenderer.drawString(leftKey, hud.getxOffset() + hud.getQuadPadding(), hud.getyOffset() + hud.getMargin() + hud.getQuadSize() + hud.getQuadPadding(), color);
        //right
        color = gameSettings.keyBindRight.isKeyDown() ? 0xFFFFd700 : 0xFF000000;
        fontRenderer.drawString(rightKey, hud.getxOffset() + hud.getMargin() * 2 + hud.getQuadSize() * 2 + hud.getQuadPadding(), hud.getyOffset() + hud.getMargin() + hud.getQuadSize() + hud.getQuadPadding(), color);
        //jump
        color = gameSettings.keyBindJump.isKeyDown() ? 0xFFFFd700 : 0xFF000000;
        AbstractGui.fill(hud.getxOffset() + hud.getJumpPadding(), hud.getyOffset() + hud.getMargin() * 3 + hud.getQuadSize() * 3 + hud.getQuadPadding(), hud.getxOffset() + hud.getJumpPadding() + hud.getJumpWidth(), hud.getyOffset() + hud.getMargin() * 3 + hud.getQuadSize() * 3 + hud.getQuadPadding() + hud.getJumpHeight(), color);
        //lmb
        color = gameSettings.keyBindAttack.isKeyDown() ? 0xFFFFd700 : 0xFF000000;
        fontRenderer.drawString(attackKey, hud.getxOffset() + hud.getClickPadding() * 3, hud.getyOffset() + hud.getMargin() * 2 + hud.getQuadSize() * 2 + hud.getClickPadding(), color);
        //rmb
        color = gameSettings.keyBindUseItem.isKeyDown() ? 0xFFFFd700 : 0xFF000000;
        fontRenderer.drawString(useKey, hud.getxOffset() + hud.getMargin() + hud.getClickBoxWidth() + hud.getClickPadding() * 3, hud.getyOffset() + hud.getMargin() * 2 + hud.getQuadSize() * 2 + hud.getClickPadding(), color);
        setRenderScale(hud.getFontScale());
        //lcps
        int attackClicks = clickManager.getClicks(gameSettings.keyBindAttack.getKey().getKeyCode());
        color = getColor(attackClicks);
        fontRenderer.drawString(attackClicks + " CPS", (hud.getxOffset() + hud.getClickPadding() * 2) / hud.getFontScale(), (hud.getyOffset() + hud.getMargin() * 2 + hud.getQuadSize() * 2 + hud.getQuadPadding() * 2) / hud.getFontScale(), color);
        //rcps
        int useClicks = clickManager.getClicks(gameSettings.keyBindUseItem.getKey().getKeyCode());
        color = getColor(useClicks);
        fontRenderer.drawString(useClicks + " CPS", (hud.getxOffset() + hud.getMargin() + hud.getClickBoxWidth() + hud.getClickPadding() * 2) / hud.getFontScale(), (hud.getyOffset() + hud.getMargin() * 2 + hud.getQuadSize() * 2 + hud.getQuadPadding() * 2) / hud.getFontScale(), color);
        //sneak
        color = Minecraft.getInstance().player.isCrouching() ? 0xFFFFd700 : 0xFF000000;
        drawVerticalString(fontRenderer, "SNEAK", (int) ((hud.getxOffset() + hud.getMargin() * 2 + hud.getClickBoxWidth() * 2 + hud.getQuadPadding()) / hud.getFontScale()), (int) ((hud.getyOffset() + hud.getMargin() * 2 + hud.getQuadSize() * 2 + hud.getQuadPadding()) / hud.getFontScale()), color);
        clearScale();
    }

    private int getColor(final int clicks) {
        int color;
        if (clicks < 2) {
            color = 0xFF000000;
        } else if (clicks < 4) {
            color = 0xFF4ef542;
        } else if (clicks < 6) {
            color = 0xFFf5ed00;
        } else if (clicks < 8) {
            color = 0xfff58700;
        } else {
            color = 0xffe32007;
        }
        return color;
    }

    private void beginRendering() {
        RenderSystem.enableAlphaTest();
        RenderSystem.enableDepthTest();
        RenderSystem.enableBlend();
    }

    private void endRendering() {
        RenderSystem.disableAlphaTest();
        RenderSystem.disableDepthTest();
        RenderSystem.disableBlend();
    }

    private void setRenderScale(final float scale) {
        GL11.glPushMatrix();
        GL11.glScalef(scale, scale, scale);
    }

    private void clearScale() {
        GL11.glPopMatrix();
    }

    private void drawVerticalString(final FontRenderer fontRenderer, final String string, final int x, final int y, final int color){
        final float fontSize = 11 * hud.getFontScale();
        final char[] chars = string.toCharArray();
        for(int i = 0; i < chars.length; i++){
            fontRenderer.drawString(chars[i]+"", x, y + fontSize * i, color);
        }
    }

}
