package dev.dixmk.minepreggo.event;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.screen.effect.SexOverlay;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID, value = Dist.CLIENT)
public class RenderEventHandler {

	private RenderEventHandler() {}
	
    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Post event) {
        if (!SexOverlay.isActive()) return;
        GuiGraphics guiGraphics = event.getGuiGraphics();
         
        float alpha = SexOverlay.getAlpha();
        if (alpha > 0) {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alpha);
            guiGraphics.blit(SexOverlay.VIGNETTE, 0, 0, 
                0, 0, 
                event.getWindow().getGuiScaledWidth(),
                event.getWindow().getGuiScaledHeight(),
                event.getWindow().getGuiScaledWidth(),
                event.getWindow().getGuiScaledHeight()
            );
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        }
        

        // Enable blending manually if needed (GuiGraphics usually handles it)
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, alpha);

        // Draw fullscreen black rectangle
        guiGraphics.fill(0, 0, 
            event.getWindow().getGuiScaledWidth(), 
            event.getWindow().getGuiScaledHeight(), 
            0x00000000 | ((int)(alpha * 255) << 24) // ARGB: alpha in high byte
        );

        // Reset color
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
    }
}
