package net.example.bloomy.mixin;
import net.example.bloomy.config.ModConfiguration;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FluidBlock.class)
public class FluidBlockMixin {
    @Inject(method = "randomDisplayTick", at = @At("HEAD"))
    private void injectCrossPlatformWaterOutline(BlockState state, World world, BlockPos pos, Random random, CallbackInfo ci) {
        if (!ModConfiguration.enableWaterOutline) return;
        FluidState fs = world.getFluidState(pos);
        if (fs.isIn(FluidTags.WATER) && fs.isStill() && world.isAir(pos.up())) {
            if (random.nextInt(4) != 0) return;
            for (Direction direction : Direction.Type.HORIZONTAL) {
                BlockPos np = pos.offset(direction);
                if (world.getBlockState(np).isOpaqueFullCube()) {
                    double ex = pos.getX() + 0.5 + (direction.getOffsetX() * 0.5);
                    double ez = pos.getZ() + 0.5 + (direction.getOffsetZ() * 0.5);
                    if (direction.getAxis() == Direction.Axis.X) ez = pos.getZ() + random.nextDouble();
                    else ex = pos.getX() + random.nextDouble();
                    net.minecraft.client.MinecraftClient.getInstance().particleManager.addParticle(ParticleTypes.CLOUD, ex, pos.getY() + 0.88, ez, 0.0, 0.005, 0.0);
                }
            }
        }
    }
}