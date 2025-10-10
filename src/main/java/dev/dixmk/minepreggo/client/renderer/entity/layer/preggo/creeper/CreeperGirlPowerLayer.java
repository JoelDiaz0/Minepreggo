package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractCreeperGirlModel;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractCreeperGirl;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CreeperGirlPowerLayer<E extends AbstractCreeperGirl, M extends AbstractCreeperGirlModel<E>> extends EnergySwirlLayer<E, M> {

	private static final ResourceLocation POWER_LOCATION = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/creeper_girl_armor.png");
	private final M model;
	
	public CreeperGirlPowerLayer(RenderLayerParent<E, M> p_174471_, EntityModelSet p_174472_, M main) {
		super(p_174471_);
		this.model = main;
	}
		
	
	@Override
	public void render(PoseStack poseStack, MultiBufferSource p_116971_, int p_116972_, E p_116973_, float p_116974_, float p_116975_, float p_116976_, float p_116977_, float p_116978_, float p_116979_) {
		poseStack.scale(1.125F, 1.125F, 1.125F);
		super.render(poseStack, p_116971_, p_116972_, p_116973_, p_116974_, p_116975_, p_116976_, p_116977_, p_116978_, p_116979_);
	}
	
	
	@Override
	protected float xOffset(float p_116683_) {
		return p_116683_ * 0.005F;
	}

	@Override
	protected ResourceLocation getTextureLocation() {
		return POWER_LOCATION;
	}
	
	@Override
	protected EntityModel<E> model() {
		return this.model;
	}
}
