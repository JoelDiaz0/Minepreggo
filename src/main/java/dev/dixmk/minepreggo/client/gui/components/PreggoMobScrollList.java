package dev.dixmk.minepreggo.client.gui.components;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.utils.PreggoGUIHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.ForgeRegistries;

public class PreggoMobScrollList extends ObjectSelectionList<PreggoMobScrollList.PreggoMobEntry> {

	protected Player player;
	protected Entity illagerScientific;
	
    public PreggoMobScrollList(Minecraft mc, Player player, Entity illagerScientific, int width, int height, int top, int bottom, int itemHeight) {
        super(mc, width, height, top, bottom, itemHeight);
        this.setRenderBackground(false);
        this.setRenderTopAndBottom(false);
        this.player = player;
        this.illagerScientific = illagerScientific;
    }
    
    
    public void addEntry(String text, String className, int preggoEntityId) {
        this.addEntry(new PreggoMobEntry(text, className, preggoEntityId));
    }
    
    public class PreggoMobEntry extends ObjectSelectionList.Entry<PreggoMobEntry> {
        private final String text;
        private final int preggoEntityId;
        private int textureWidth = 64;
        private int textureHeight = 96;
        private ResourceLocation icon;
                
        public PreggoMobEntry(String text, String className, int preggoEntityId) {
            this.text = text;
			this.preggoEntityId = preggoEntityId;
			
			if (className.equals(AbstractTamableZombieGirl.class.getName())) 
				icon = PreggoGUIHelper.DEFAULT_ICON_ZOMBIE_GIRL_TEXTURE;
			else if (className.equals(AbstractTamableCreeperGirl.class.getName()))
				icon = PreggoGUIHelper.DEFAULT_ICON_CREEPER_GIRL_TEXTURE;
			/*
			else {			
				if (player.getCapability(MinepreggoModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MinepreggoModVariables.PlayerVariables()).isPlayerUsingCustomSkin
						&& player instanceof AbstractClientPlayer abstractClient) {
					icon = abstractClient.getSkinTextureLocation();
				} 
				else {
					icon = PreggoGUIHelper.DEFAULT_ICON_HUMAN_TEXTURE;	
				}
				textureHeight = 64;
			}	
			*/		
        }
     
        @Override
        public void render(GuiGraphics guiGraphics, int index, int top, int left, int width, int height,
                           int mouseX, int mouseY, boolean hovered, float partialTick) {
    		RenderSystem.setShaderColor(1, 1, 1, 1);
    		RenderSystem.enableBlend();
    		RenderSystem.defaultBlendFunc();      	
        	
    		guiGraphics.drawString(Minecraft.getInstance().font, text, left + 78, top, 0xFFFFFF);     
            guiGraphics.blit(icon, left + 57, top, 16, 16, 8, 8, 8, 8, textureWidth, textureHeight);   
    		
            
            RenderSystem.disableBlend();          
        }

        
        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {   	
        	 if (button == 0) {
        		var x = (int) player.getX();
        		var y = (int) player.getY();
        		var z = (int) player.getZ();
        		var world = player.level();
        		
        		/*
   				MinepreggoMod.PACKET_HANDLER.sendToServer(new PreggoMobMedicalCheckUpGUIMessage(x, y, z, preggoEntityId, illagerScientific.getId()));
   				PreggoMobMedicalCheckUpGUIMessage.handleButtonAction(player, x, y, z, preggoEntityId, illagerScientific.getId());       		   		     		 
   				*/

        		if (world.isClientSide()) {
        			world.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("ui.button.click")), SoundSource.NEUTRAL, 1, 1, false);
        		}
        		
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
