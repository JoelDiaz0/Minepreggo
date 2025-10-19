package dev.dixmk.minepreggo.client.screen.effect;

import net.minecraft.resources.ResourceLocation;

public class SexOverlay {

	public static final ResourceLocation VIGNETTE = ResourceLocation.withDefaultNamespace("textures/misc/vignette.png");
	
	private SexOverlay() {}

    private static int overlayTimer = 0;
    public static final int DURATION = 200; // ticks (5 seconds)
    
    
    public static void triggerSleepOverlay(int durationTicks) {
    	overlayTimer = durationTicks;
    }

    public static void tick() {
        if (overlayTimer > 0) {
        	overlayTimer--;
        }
    }
    
    public static void trigger() {
        overlayTimer = DURATION;
    }

    public static float getAlpha() {
        if (overlayTimer <= 0) return 0.0F;
        int t = overlayTimer;
        // Fade in (first 10 ticks), full (middle), fade out (last 10)
        if (t < 10) {
            return (10 - t) * 0.09F;
        } else if (t > DURATION - 10) {
            return (t - (DURATION - 10)) * 0.09F;
        }
        return 0.9F;
    }

    public static boolean isActive() {
        return overlayTimer > 0;
    }
}
