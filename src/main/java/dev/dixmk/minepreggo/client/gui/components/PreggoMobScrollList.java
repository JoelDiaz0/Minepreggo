package dev.dixmk.minepreggo.client.gui.components;

import java.util.Optional;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class PreggoMobScrollList extends ObjectSelectionList<PreggoMobScrollList.PreggoMobEntry> {
	
    public PreggoMobScrollList(Minecraft mc, int width, int height, int top, int bottom, int itemHeight) {
        super(mc, width, height, top, bottom, itemHeight);   
        this.setRenderBackground(false);
        this.setRenderTopAndBottom(false);
    }
    
    public void addEntry(String entityName, ResourceLocation icon, int textureWidth, int textureHeight) {
        this.addEntry(new PreggoMobEntry(entityName, icon, textureWidth, textureHeight));
    }
    
    public void addEntry(String entityName, ResourceLocation icon, int textureWidth, int textureHeight, Optional<Runnable> onClick) {
        this.addEntry(new PreggoMobEntry(entityName, icon, textureWidth, textureHeight, onClick));
    }
    
    public class PreggoMobEntry extends ObjectSelectionList.Entry<PreggoMobEntry> {
        private final String entityName;    
        Optional<Runnable> onClick;   
        private final int textureWidth;
        private final int textureHeight;
        private ResourceLocation icon;
                
        public PreggoMobEntry(String entityName, ResourceLocation icon, int textureWidth, int textureHeight, Optional<Runnable> onClick) {
            this.entityName = entityName;
            this.icon = icon;
			this.textureHeight = textureHeight;
			this.textureWidth = textureWidth;
			this.onClick = onClick;
        }

        public PreggoMobEntry(String entityName, ResourceLocation icon, int textureWidth, int textureHeight) {
        	this(entityName, icon, textureWidth, textureHeight, Optional.empty());
        }
           
        @Override
        public void render(GuiGraphics guiGraphics, int index, int top, int left, int width, int height,
                           int mouseX, int mouseY, boolean hovered, float partialTick) {
    		RenderSystem.setShaderColor(1, 1, 1, 1);
    		RenderSystem.enableBlend();
    		RenderSystem.defaultBlendFunc();              	
    		guiGraphics.drawString(Minecraft.getInstance().font, this.entityName, left + 78, top, 0xFFFFFF);     
            guiGraphics.blit(icon, left + 57, top, 16, 16, 8, 8, 8, 8, textureWidth, textureHeight);      	          
            RenderSystem.disableBlend();          
        }

        
        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {   	
        	 if (button == 0) {
        		 this.onClick.ifPresent(e -> e.run());
        		 return true;
        	 }   
        	 return false;   	
        }

		@Override
		public Component getNarration() {
			return Component.literal("Searching PreggoMob");
		}
    }
}
