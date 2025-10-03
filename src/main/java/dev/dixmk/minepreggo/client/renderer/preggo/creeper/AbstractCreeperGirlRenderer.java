package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractCreeperGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.CreeperGirlPowerLayer;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractCreeperGirl;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractCreeperGirlRenderer<E extends AbstractCreeperGirl, M extends AbstractCreeperGirlModel<E>> extends HumanoidMobRenderer<E, M> {
	protected AbstractCreeperGirlRenderer(EntityRendererProvider.Context context, M main, M inner, M outter) {
		super(context, main, 0.5F);
		this.addLayer(new HumanoidArmorLayer<>(this, inner, outter, context.getModelManager()));
		this.addLayer(new CreeperGirlPowerLayer<>(this, context.getModelSet(), main));
	}
	
	@Override
	protected void scale(E creeperGirl, PoseStack p_114047_, float p_114048_) {
		float f = creeperGirl.getSwelling(p_114048_);
		float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
		f = Mth.clamp(f, 0.0F, 1.0F);
		f *= f;
		f *= f;
		float f2 = (1.0F + f * 0.4F) * f1;
		float f3 = (1.0F + f * 0.1F) / f1;
		p_114047_.scale(f2, f3, f2);
	}
	
	@Override
	protected float getWhiteOverlayProgress(E creeperGirl, float p_114044_) {
		float f = creeperGirl.getSwelling(p_114044_);
		return (int)(f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
	}
}
