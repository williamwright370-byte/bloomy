package net.example.bloomy.mixin;
import net.example.bloomy.config.ModConfiguration;
import net.example.bloomy.util.BloomEngine;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LeavesBlock.class)
public class LeavesBlockMixin {
    @Inject(method = "randomDisplayTick", at = @At("TAIL"))
    private void injectCinematicLeafBloomTrails(BlockState state, World world, BlockPos pos, Random random, CallbackInfo ci) {
        if (!ModConfiguration.enableFallingLeaves) return;
        if (world.isAir(pos.down()) && random.nextInt(20) == 0) {
            double sx = pos.getX() + random.nextDouble();
            double sy = pos.getY() - 0.02;
            double sz = pos.getZ() + random.nextDouble();
            double mx = (random.nextDouble() - 0.5) * 0.04;
            double my = -0.05;
            double mz = (random.nextDouble() - 0.5) * 0.04;
            MinecraftClient.getInstance().particleManager.addParticle(ParticleTypes.CHERRY_LEAVES, sx, sy, sz, mx, my, mz);
            if (ModConfiguration.enableBloom && world.getBiome(pos).matchesKey(net.minecraft.world.biome.BiomeKeys.DARK_FOREST)) {
                BloomEngine.generateCinematicGlow(world, sx, sy, sz, 0x2E8B57);
            }
        }
    }
}