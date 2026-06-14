package com.example.crystalautoclicker.input;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class CrystalKeybind {
    public static final KeyBinding TOGGLE_MODE = KeyBindingHelper.registerKeyBinding(new KeyBinding(
        "key.crystalautoclicker.toggle",
        GLFW.GLFW_KEY_UNKNOWN,
        "category.crystalautoclicker.general"
    ));
}
