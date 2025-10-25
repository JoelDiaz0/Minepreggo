package dev.dixmk.minepreggo.client.renderer.preggo.creeper.quadruped;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.quadruped.IllQuadrupedCreeperGirlP0Model;
import dev.dixmk.minepreggo.world.entity.monster.IllQuadrupedCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IllQueadrupedCreeperGirlRenderer extends AbstractQuadrupedCreeperGirlRenderer<IllQuadrupedCreeperGirl, IllQuadrupedCreeperGirlP0Model> {

	protected IllQueadrupedCreeperGirlRenderer(Context context, IllQuadrupedCreeperGirlP0Model main,
			IllQuadrupedCreeperGirlP0Model armor) {
		super(context, main, armor);
	}

	@Override
	public ResourceLocation getTextureLocation(IllQuadrupedCreeperGirl p_115812_) {
		return CREEPER_GIRL_P0_LOCATION;
	}
}
