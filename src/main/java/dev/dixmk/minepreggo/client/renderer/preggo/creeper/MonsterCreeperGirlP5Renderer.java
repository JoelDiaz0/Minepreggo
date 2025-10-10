package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AnimatedMonsterCreeperGirlP5Model;
import dev.dixmk.minepreggo.entity.preggo.creeper.MonsterCreeperGirlP5;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterCreeperGirlP5Renderer extends AbstractMonsterPregnantCreeperGirlRenderer<MonsterCreeperGirlP5, AnimatedMonsterCreeperGirlP5Model> {
	
	public MonsterCreeperGirlP5Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractCreeperGirlModel.LAYER_LOCATION_P5, AbstractCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION);
	}
	
	public MonsterCreeperGirlP5Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
		super(context, new AnimatedMonsterCreeperGirlP5Model(context.bakeLayer(main)), new AnimatedMonsterCreeperGirlP5Model(context.bakeLayer(inner)), new AnimatedMonsterCreeperGirlP5Model(context.bakeLayer(outter)));
	}

	@Override
	public ResourceLocation getTextureLocation(MonsterCreeperGirlP5 p_115812_) {
		return AbstractCreeperGirlRenderer.CREEPER_GIRL_P5_LOCATION;
	}
}
