package it.filippocavallari.keybindings.listener;

import it.filippocavallari.keybindings.OverlayRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class RenderListener {

    private final OverlayRenderer overlayRenderer;

    public RenderListener() {
        this.overlayRenderer = new OverlayRenderer();
    }

    @SubscribeEvent
    public void onRenderGui(final RenderGameOverlayEvent.Post event){
        overlayRenderer.render();
    }

}
