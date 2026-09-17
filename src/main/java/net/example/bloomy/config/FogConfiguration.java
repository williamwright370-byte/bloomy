package net.example.bloomy.config;

public class FogConfiguration {
    public static boolean enableCustomFog = true;
    public static int activeFogColorIndex = 0;
    public static final String[] PRESET_NAMES = { "Ethereal Violet", "Deep Crimson", "Toxic Neon", "Eerie Cyan" };
    public static final float[][] PRESET_COLORS = {
        { 0.35f, 0.15f, 0.55f },
        { 0.55f, 0.05f, 0.05f },
        { 0.10f, 0.55f, 0.20f },
        { 0.15f, 0.45f, 0.55f }
    };
    public static float[] getCurrentFogRGB() { return PRESET_COLORS[activeFogColorIndex]; }
}