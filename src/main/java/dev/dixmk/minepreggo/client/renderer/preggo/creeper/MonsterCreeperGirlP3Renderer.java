package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AnimatedMonsterCreeperGirlP3Model;
import dev.dixmk.minepreggo.entity.preggo.creeper.MonsterCreeperGirlP3;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterCreeperGirlP3Renderer extends AbstractMonsterPregnantCreeperGirlRenderer<MonsterCreeperGirlP3, AnimatedMonsterCreeperGirlP3Model> {
	
	public MonsterCreeperGirlP3Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractCreeperGirlModel.LAYER_LOCATION_P3, AbstractCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION);
	}
	
	public MonsterCreeperGirlP3Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
		super(context, new AnimatedMonsterCreeperGirlP3Model(context.bakeLayer(main)), new AnimatedMonsterCreeperGirlP3Model(context.bakeLayer(inner)), new AnimatedMonsterCreeperGirlP3Model(context.bakeLayer(outter)));
	}

	@Override
	public ResourceLocation getTextureLocation(MonsterCreeperGirlP3 p_115812_) {
		return AbstractCreeperGirlRenderer.CREEPER_GIRL_P3_LOCATION;
	}
}