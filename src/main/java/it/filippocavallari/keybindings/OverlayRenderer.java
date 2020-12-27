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

    private final float scale = 0.4f;
    private final int xOffset = (int) (50 * scale);
    private final int yOffset = (int) (100 * scale);
    private final int margin = (int) (6 * scale);
    private final int quadSize = (int) (46 * scale);
    private final int clickBoxWidth = (int) (72 * scale);
    private final int quadPadding = quadSize / 3;
    private final int jumpWidth = (int) (50 * scale);
    private final int jumpHeight = (int) (5 * scale);
    private final int jumpPadding = (int) ((150 / 2 * scale) - (jumpWidth / 2) - (margin / 2));
    private final int clickPadding = (int) (clickBoxWidth / 6 * scale);
    private final int sneakHeight = (int) (96 * scale);
    private final float fontScale = 0.7f;

    public OverlayRenderer(final ClickManager clickManager) {
        this.clickManager = clickManager;
    }

    public void render() {
        beginRendering();
        renderGui();
        renderFonts();
        endRendering();
    }

    private void renderGui() {
        //forward key
        AbstractGui.fill(xOffset + margin + quadSize, yOffset, xOffset + margin + quadSize * 2, yOffset + quadSize, 0x1f888888);
        //left key
        AbstractGui.fill(xOffset, yOffset + margin + quadSize, xOffset + quadSize, yOffset + margin + quadSize * 2, 0x1f888888);
        //backward key
        AbstractGui.fill(xOffset + margin + quadSize, yOffset + margin + quadSize, xOffset + margin + quadSize * 2, yOffset + margin + quadSize * 2, 0x1f888888);
        //right key
        AbstractGui.fill(xOffset + margin * 2 + quadSize * 2, yOffset + margin + quadSize, xOffset + margin * 2 + quadSize * 3, yOffset + margin + quadSize * 2, 0x1f888888);
        //LMP
        AbstractGui.fill(xOffset, yOffset + margin * 2 + quadSize * 2, xOffset + clickBoxWidth, yOffset + margin * 2 + quadSize * 3, 0x1f888888);
        //RMP
        AbstractGui.fill(xOffset + margin + clickBoxWidth, yOffset + margin * 2 + quadSize * 2, xOffset + margin + clickBoxWidth * 2, yOffset + margin * 2 + quadSize * 3, 0x1f888888);
        //space bar
        AbstractGui.fill(xOffset, yOffset + margin * 3 + quadSize * 3, xOffset + margin * 2 + quadSize * 3, yOffset + margin * 3 + quadSize * 4, 0x1f888888);
        //sneak
        AbstractGui.fill(xOffset + margin * 2 + clickBoxWidth * 2, yOffset + margin * 2 + quadSize * 2, xOffset + margin * 2 + clickBoxWidth * 2 + quadSize, yOffset + margin * 2 + quadSize * 2 + sneakHeight, 0x1f888888);
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
        fontRenderer.drawString(forwardKey, xOffset + margin + quadSize + quadPadding, yOffset + quadPadding, color);
        //backward
        color = gameSettings.keyBindBack.isKeyDown() ? 0xFFFFd700 : 0xFF000000;
        fontRenderer.drawString(backwardKey, xOffset + margin + quadSize + quadPadding, yOffset + margin + quadSize + quadPadding, color);
        //left
        color = gameSettings.keyBindLeft.isKeyDown() ? 0xFFFFd700 : 0xFF000000;
        fontRenderer.drawString(leftKey, xOffset + quadPadding, yOffset + margin + quadSize + quadPadding, color);
        //right
        color = gameSettings.keyBindRight.isKeyDown() ? 0xFFFFd700 : 0xFF000000;
        fontRenderer.drawString(rightKey, xOffset + margin * 2 + quadSize * 2 + quadPadding, yOffset + margin + quadSize + quadPadding, color);
        //jump
        color = gameSettings.keyBindJump.isKeyDown() ? 0xFFFFd700 : 0xFF000000;
        AbstractGui.fill(xOffset + jumpPadding, yOffset + margin * 3 + quadSize * 3 + quadPadding, xOffset + jumpPadding + jumpWidth, yOffset + margin * 3 + quadSize * 3 + quadPadding + jumpHeight, color);
        //lmb
        color = gameSettings.keyBindAttack.isKeyDown() ? 0xFFFFd700 : 0xFF000000;
        fontRenderer.drawString(attackKey, xOffset + clickPadding * 3, yOffset + margin * 2 + quadSize * 2 + clickPadding, color);
        //rmb
        color = gameSettings.keyBindUseItem.isKeyDown() ? 0xFFFFd700 : 0xFF000000;
        fontRenderer.drawString(useKey, xOffset + margin + clickBoxWidth + clickPadding * 3, yOffset + margin * 2 + quadSize * 2 + clickPadding, color);
        setRenderScale(fontScale);
        //lcps
        int attackClicks = clickManager.getClicks(gameSettings.keyBindAttack.getKey().getKeyCode());
        color = getColor(attackClicks);
        fontRenderer.drawString(attackClicks + " CPS", (xOffset + clickPadding * 2) / fontScale, (yOffset + margin * 2 + quadSize * 2 + quadPadding * 2) / fontScale, color);
        //rcps
        int useClicks = clickManager.getClicks(gameSettings.keyBindUseItem.getKey().getKeyCode());
        color = getColor(useClicks);
        fontRenderer.drawString(useClicks + " CPS", (xOffset + margin + clickBoxWidth + clickPadding * 2) / fontScale, (yOffset + margin * 2 + quadSize * 2 + quadPadding * 2) / fontScale, color);
        //sneak
        color = Minecraft.getInstance().player.isCrouching() ? 0xFFFFd700 : 0xFF000000;
        drawVerticalString(fontRenderer, "SNEAK", (int) ((xOffset + margin * 2 + clickBoxWidth * 2 + quadPadding) / fontScale), (int) ((yOffset + margin * 2 + quadSize * 2 + quadPadding) / fontScale), color);
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
        final float fontSize = 11 * fontScale;
        final char[] chars = string.toCharArray();
        for(int i = 0; i < chars.length; i++){
            fontRenderer.drawString(chars[i]+"", x, y + fontSize * i, color);
        }
    }

}
