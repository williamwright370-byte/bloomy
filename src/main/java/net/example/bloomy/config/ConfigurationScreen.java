package net.example.bloomy.config;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ConfigurationScreen extends Screen {
    private final Screen parent;
    public ConfigurationScreen(Screen parent) { super(Text.literal("Bloomy Settings")); this.parent = parent; }
    @Override
    protected void init() {
        int w = 220; int x = this.width / 2 - 110; int y = 40;
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Biome Lava: " + (ModConfiguration.enableBiomeLava?"ON":"OFF")), b -> { ModConfiguration.enableBiomeLava = !ModConfiguration.enableBiomeLava; b.setMessage(Text.literal("Biome Lava: " + (ModConfiguration.enableBiomeLava?"ON":"OFF"))); }).dimensions(x, y, w, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Bloom: " + (ModConfiguration.enableBloom?"ON":"OFF")), b -> { ModConfiguration.enableBloom = !ModConfiguration.enableBloom; b.setMessage(Text.literal("Bloom: " + (ModConfiguration.enableBloom?"ON":"OFF"))); }).dimensions(x, y+=24, w, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Leaves: " + (ModConfiguration.enableFallingLeaves?"ON":"OFF")), b -> { ModConfiguration.enableFallingLeaves = !ModConfiguration.enableFallingLeaves; b.setMessage(Text.literal("Leaves: " + (ModConfiguration.enableFallingLeaves?"ON":"OFF"))); }).dimensions(x, y+=24, w, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Water Edge: " + (ModConfiguration.enableWaterOutline?"ON":"OFF")), b -> { ModConfiguration.enableWaterOutline = !ModConfiguration.enableWaterOutline; b.setMessage(Text.literal("Water Edge: " + (ModConfiguration.enableWaterOutline?"ON":"OFF"))); }).dimensions(x, y+=24, w, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Fog: " + (FogConfiguration.enableCustomFog?"ON":"OFF")), b -> { FogConfiguration.enableCustomFog = !FogConfiguration.enableCustomFog; b.setMessage(Text.literal("Fog: " + (FogConfiguration.enableCustomFog?"ON":"OFF"))); }).dimensions(x, y+=24, w, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Fog Style: " + FogConfiguration.PRESET_NAMES[FogConfiguration.activeFogColorIndex]), b -> { FogConfiguration.activeFogColorIndex = (FogConfiguration.activeFogColorIndex + 1) % FogConfiguration.PRESET_NAMES.length; b.setMessage(Text.literal("Fog Style: " + FogConfiguration.PRESET_NAMES[FogConfiguration.activeFogColorIndex])); }).dimensions(x, y+=24, w, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Done"), b -> this.client.setScreen(this.parent)).dimensions(x, y+=30, w, 20).build());
    }
    @Override
    public void render(DrawContext c, int mx, int my, float d) { this.renderBackground(c, mx, my, d); c.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 15, 0xFFFFFF); super.render(c, mx, my, d); }
}