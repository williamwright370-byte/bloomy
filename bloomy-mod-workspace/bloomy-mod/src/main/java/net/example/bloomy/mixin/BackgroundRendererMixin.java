package net.example.bloomy.mixin;
import net.example.bloomy.config.FogConfiguration;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {
    @Inject(method = "render", at = @At("TAIL"))
    private static void injectBloomyAtmosphericFog(Camera camera, float tickDelta, ClientWorld world, int rd, float sd, CallbackInfo ci) {
        if (!FogConfiguration.enableCustomFog) return;
        float[] rgb = FogConfiguration.getCurrentFogRGB();
        com.mojang.blaze3d.systems.RenderSystem.clearColor(rgb[0], rgb[1], rgb[2], 1.0f);
    }
}