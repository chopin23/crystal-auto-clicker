package com.example.crystalautoclicker;

import com.example.crystalautoclicker.config.CrystalConfig;
import com.example.crystalautoclicker.logic.CrystalPlacer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class CrystalAutoClickerClient implements ClientModInitializer {

    public static KeyBinding RIGHT_SHIFT_KEYBIND;

    @Override
    public void onInitializeClient() {
        RIGHT_SHIFT_KEYBIND = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.crystalautoclicker.right_shift",
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            "category.crystalautoclicker.general"
        ));

        CrystalConfig.load();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null || client.world == null) return;

            if (RIGHT_SHIFT_KEYBIND.isPressed()) {
                CrystalPlacer.tryPlaceCrystal(client.player, client.world, CrystalConfig.CRYSTALS_PER_SECOND);
            }
        });
    }
}
