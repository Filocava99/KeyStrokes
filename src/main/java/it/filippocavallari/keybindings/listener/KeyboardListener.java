package it.filippocavallari.keybindings.listener;

import it.filippocavallari.keybindings.gui.EmptyGui;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyboardListener {

    @SubscribeEvent
    public void onKeyPressed(InputEvent.KeyInputEvent event){
        if(event.getAction() == 1 && event.getKey() == 344){
            Minecraft.getInstance().displayGuiScreen(new EmptyGui());
        }
    }

}
