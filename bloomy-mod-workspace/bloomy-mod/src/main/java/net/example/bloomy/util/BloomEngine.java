package net.example.bloomy.util;
import net.example.bloomy.config.ModConfiguration;
import net.minecraft.client.MinecraftClient;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

public class BloomEngine {
    public static void generateCinematicGlow(World world, double x, double y, double z, int hexColor) {
        if (!ModConfiguration.enableBloom) return;
        double r = ((hexColor >> 16) & 0xFF) / 255.0;
        double g = ((hexColor >> 8) & 0xFF) / 255.0;
        double b = (hexColor & 0xFF) / 255.0;
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.particleManager == null) return;
        if (world.getRandom().nextInt(5) == 0) mc.particleManager.addParticle(ParticleTypes.GLOW, x, y, z, r * 0.1, g * 0.1, b * 0.1);
        if (world.getRandom().nextInt(14) == 0) {
            double sx = x + (world.getRandom().nextDouble() - 0.5) * 0.3;
            double sy = y + (world.getRandom().nextDouble() * 0.1);
            double sz = z + (world.getRandom().nextDouble() - 0.5) * 0.3;
            mc.particleManager.addParticle(ParticleTypes.END_ROD, sx, sy, sz, 0.0, 0.002, 0.0);
        }
    }
}