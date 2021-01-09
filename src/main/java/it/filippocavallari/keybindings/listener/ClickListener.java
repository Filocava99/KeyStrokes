package it.filippocavallari.keybindings.listener;

import it.filippocavallari.keybindings.ClickManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClickListener {

    private final ClickManager clickManager;

    public ClickListener() {
        this.clickManager = ClickManager.get();
    }

    @SubscribeEvent
    public void onKeyboardClick(final InputEvent.MouseInputEvent event){
        if(event.getAction() == 1) clickManager.registerKeyPressed(event.getButton());
    }

    @SubscribeEvent
    public void onMouseClick(final InputEvent.KeyInputEvent event){
        if(event.getAction() == 1) clickManager.registerKeyPressed(event.getKey());
    }
}
