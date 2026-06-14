package com.example.crystalautoclicker.logic;

import com.example.crystalautoclicker.config.CrystalConfig;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class CrystalPlacer {
    private static int tickCounter = 0;
    private static final int TICKS_PER_SECOND = 20;

    public static void tryPlaceCrystal(ClientPlayerEntity player, World world, int crystalsPerSecond) {
        int interval = TICKS_PER_SECOND / Math.max(1, crystalsPerSecond);
        if (tickCounter++ % interval != 0) return;

        int crystalSlot = findCrystalSlot(player);
        if (crystalSlot == -1 && CrystalConfig.REQUIRE_CRYSTAL_IN_HAND) return;

        Vec3d eyePos = player.getCameraPosVec(1.0f);
        Vec3d lookVec = player.getRotationVec(1.0f);
        double maxDist = CrystalConfig.MAX_PLACE_DISTANCE;

        BlockHitResult hit = world.raycast(new net.minecraft.world.RaycastContext(
            eyePos, eyePos.add(lookVec.multiply(maxDist)),
            net.minecraft.world.RaycastContext.ShapeType.OUTLINE,
            net.minecraft.world.RaycastContext.FluidHandling.NONE, player
        ));

        if (hit == null || hit.getType() != HitResult.Type.BLOCK) return;

        BlockPos placePos = hit.getBlockPos().offset(hit.getSide());
        double dist = player.squaredDistanceTo(Vec3d.ofCenter(placePos));
        if (dist < CrystalConfig.MIN_PLACE_DISTANCE * CrystalConfig.MIN_PLACE_DISTANCE) return;

        if (!world.getBlockState(placePos).isReplaceable()) return;

        int originalSlot = player.getInventory().selectedSlot;
        if (crystalSlot != -1 && crystalSlot != originalSlot && crystalSlot < 9) {
            player.getInventory().selectedSlot = crystalSlot;
        }

        MinecraftClient.getInstance().interactionManager.interactBlock(player, crystalSlot == 40 ? Hand.OFF_HAND : Hand.MAIN_HAND, hit);

        if (player.getInventory().selectedSlot != originalSlot) {
            player.getInventory().selectedSlot = originalSlot;
        }
    }

    private static int findCrystalSlot(ClientPlayerEntity player) {
        for (int i = 0; i < 9; i++) {
            if (player.getInventory().getStack(i).isOf(Items.END_CRYSTAL)) return i;
        }
        if (player.getOffHandStack().isOf(Items.END_CRYSTAL)) return 40;
        return -1;
    }
}
