package net.example.bloomy.mixin;
import net.example.bloomy.config.ConfigurationScreen;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin extends Screen {
    protected GameMenuScreenMixin(Text t) { super(t); }
    @Inject(method = "init", at = @At("TAIL"))
    private void injectPauseMenuConfigButton(CallbackInfo ci) {
        this.addDrawableChild(ButtonWidget.builder(Text.literal("🌋"), b -> this.client.setScreen(new ConfigurationScreen(this))).dimensions(this.width / 2 + 104, this.height / 4 + 24, 20, 20).build());
    }
}