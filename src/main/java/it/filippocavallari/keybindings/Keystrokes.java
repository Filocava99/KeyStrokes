package it.filippocavallari.keybindings;

import it.filippocavallari.keybindings.listener.ClickListener;
import it.filippocavallari.keybindings.listener.KeyboardListener;
import it.filippocavallari.keybindings.listener.RenderListener;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(Keystrokes.MODID)
public class Keystrokes {

    public static final String MODID = "keystrokes";

    public Keystrokes() {
        MinecraftForge.EVENT_BUS.register(new RenderListener());
        MinecraftForge.EVENT_BUS.register(new ClickListener());
        MinecraftForge.EVENT_BUS.register(new KeyboardListener());
    }

}
