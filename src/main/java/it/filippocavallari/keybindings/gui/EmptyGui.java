package it.filippocavallari.keybindings.gui;

import it.filippocavallari.keybindings.HUD;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;

public class EmptyGui extends Screen {

    private final HUD hud;

    public EmptyGui(final HUD hud) {
        super(new StringTextComponent(""));
        this.hud = hud;
    }

    @Override
    public void init(Minecraft p_init_1_, int p_init_2_, int p_init_3_) {
        super.init(p_init_1_, p_init_2_, p_init_3_);
    }

    @Override
    public boolean mouseDragged(double p_mouseDragged_1_, double p_mouseDragged_3_, int p_mouseDragged_5_, double p_mouseDragged_6_, double p_mouseDragged_8_) {
        //Testing drag and drop only when clicking on HUD
        //System.out.println("var1: " + p_mouseDragged_1_ + "  var2: " + p_mouseDragged_3_ + "  var3: " + p_mouseDragged_5_ + "  var4: " + p_mouseDragged_6_ + "  var5: " + p_mouseDragged_8_);
        //double originX = p_mouseDragged_1_;
        //double originY = p_mouseDragged_3_;
        double xDelta = p_mouseDragged_6_;
        double yDelta = p_mouseDragged_8_;
        //if(hud.isPointOnHUD(originX, originY)){
            hud.setxOffset(hud.getxOffset()+(int)xDelta);
            hud.setyOffset(hud.getyOffset()+(int)yDelta);
            return true;
        //}else{
        //    return false;
        //}
    }


}
