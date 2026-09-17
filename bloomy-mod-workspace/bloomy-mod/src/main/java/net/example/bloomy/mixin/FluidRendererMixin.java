package net.example.bloomy.mixin;
import net.example.bloomy.config.ModConfiguration;
import net.example.bloomy.util.BloomEngine;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.block.FluidRenderer;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FluidRenderer.class)
public class FluidRendererMixin {
    @Inject(method = "render", at = @At("HEAD"))
    private void processAdvancedLavaBloom(BlockRenderView world, BlockPos pos, net.minecraft.client.render.VertexConsumer vc, BlockState bs, FluidState fs, CallbackInfoReturnable<Boolean> cir) {
        if (!ModConfiguration.enableBiomeLava || !fs.isIn(FluidTags.LAVA)) return;
        RegistryEntry<Biome> biomeEntry = world.getBiome(pos);
        int targetHex = 0xFFFFFF;
        if (biomeEntry.matchesKey(BiomeKeys.SOUL_SAND_VALLEY)) targetHex = 0x36D9E6;
        else if (biomeEntry.matchesKey(BiomeKeys.WARPED_FOREST)) targetHex = 0x00FFCC;
        else if (biomeEntry.matchesKey(BiomeKeys.DEEP_DARK)) targetHex = 0x12253B;
        else if (biomeEntry.matchesKey(BiomeKeys.CRIMSON_FOREST)) targetHex = 0xE61919;
        if (targetHex != 0xFFFFFF && world instanceof World activeWorld) {
            double ex = pos.getX() + activeWorld.getRandom().nextDouble();
            double ey = pos.getY() + fs.getHeight(world, pos) + 0.05;
            double ez = pos.getZ() + activeWorld.getRandom().nextDouble();
            BloomEngine.generateCinematicGlow(activeWorld, ex, ey, ez, targetHex);
        }
    }
}