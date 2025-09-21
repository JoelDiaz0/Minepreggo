package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractAnimatedCreeperGirlP0Model;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AnimatedMonsterCreeperGirlP0Model;
import dev.dixmk.minepreggo.entity.preggo.creeper.MonsterCreeperGirlP0;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterCreeperGirlP0Renderer extends AbstractCreeperGirlP0Renderer<MonsterCreeperGirlP0> {
	
	public MonsterCreeperGirlP0Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractAnimatedCreeperGirlP0Model.LAYER_LOCATION, AbstractCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION);
	}
	
	public MonsterCreeperGirlP0Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
		super(context, new AnimatedMonsterCreeperGirlP0Model(context.bakeLayer(main)), new AnimatedMonsterCreeperGirlP0Model(context.bakeLayer(inner)), new AnimatedMonsterCreeperGirlP0Model(context.bakeLayer(outter)));
	}
	
	@Override
	public void render(MonsterCreeperGirlP0 p_115455_, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_, int p_115460_) {

		if (p_115455_.isBaby()) {
			p_115458_.scale(0.575F, 0.575F, 0.575F);
		}
				
		super.render(p_115455_, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
	}
}
