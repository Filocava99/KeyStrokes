package it.filippocavallari.keybindings;

public class HUD {

    private float scale = 0.4f;
    private int xOffset = (int) (50 * scale);
    private int yOffset = (int) (100 * scale);
    private int margin = (int) (6 * scale);
    private int quadSize = (int) (46 * scale);
    private int clickBoxWidth = (int) (72 * scale);
    private int quadPadding = quadSize / 3;
    private int jumpWidth = (int) (50 * scale);
    private int jumpHeight = (int) (5 * scale);
    private int jumpPadding = (int) ((150 / 2 * scale) - (jumpWidth / 2) - (margin / 2));
    private int clickPadding = (int) (clickBoxWidth / 6 * scale);
    private int sneakHeight = (int) (96 * scale);
    private float fontScale = 0.7f;
    private final int hudWidth = jumpWidth + margin + quadSize;
    private final int hudHeight = quadSize * 4 + margin * 3;

    public float getScale() {
        return scale;
    }

    public int getHudWidth() {
        return hudWidth;
    }

    public int getHudHeight() {
        return hudHeight;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public int getQuadSize() {
        return quadSize;
    }

    public void setQuadSize(int quadSize) {
        this.quadSize = quadSize;
    }

    public int getClickBoxWidth() {
        return clickBoxWidth;
    }

    public void setClickBoxWidth(int clickBoxWidth) {
        this.clickBoxWidth = clickBoxWidth;
    }

    public int getQuadPadding() {
        return quadPadding;
    }

    public void setQuadPadding(int quadPadding) {
        this.quadPadding = quadPadding;
    }

    public int getJumpWidth() {
        return jumpWidth;
    }

    public void setJumpWidth(int jumpWidth) {
        this.jumpWidth = jumpWidth;
    }

    public int getJumpHeight() {
        return jumpHeight;
    }

    public void setJumpHeight(int jumpHeight) {
        this.jumpHeight = jumpHeight;
    }

    public int getJumpPadding() {
        return jumpPadding;
    }

    public void setJumpPadding(int jumpPadding) {
        this.jumpPadding = jumpPadding;
    }

    public int getClickPadding() {
        return clickPadding;
    }

    public void setClickPadding(int clickPadding) {
        this.clickPadding = clickPadding;
    }

    public int getSneakHeight() {
        return sneakHeight;
    }

    public void setSneakHeight(int sneakHeight) {
        this.sneakHeight = sneakHeight;
    }

    public float getFontScale() {
        return fontScale;
    }

    public void setFontScale(float fontScale) {
        this.fontScale = fontScale;
    }

    public boolean isPointOnHUD(double x, double y){
        return x >= xOffset && x <= xOffset+hudWidth && y >= yOffset && y <= yOffset+hudHeight;
    }
}
