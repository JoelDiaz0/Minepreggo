package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractMonsterPregnantCreeperGirlModel;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractMonsterPregnantCreeperGirl;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterPregnantCreeperGirlExpressionLayer 
	<E extends AbstractMonsterPregnantCreeperGirl, M extends AbstractMonsterPregnantCreeperGirlModel<E>> extends MonsterCreeperGirlExpressionLayer<E, M> {

	protected static final RenderType HOSTIL_PAIN = RenderType.entityTranslucent(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/expressions/creeper_girl_face_hostil_pain.png"));

	public MonsterPregnantCreeperGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}
	
	@Override
	public void render(PoseStack p_117349_, MultiBufferSource p_117350_, int p_117351_, E p_117352_, float p_117353_,
			float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {
		
		if (p_117352_.hasPregnancyPain()) {
			getParentModel().head.render(p_117349_, p_117350_.getBuffer(HOSTIL_PAIN), p_117351_, OverlayTexture.NO_OVERLAY);	
		}
		else {
			super.render(p_117349_, p_117350_, p_117351_, p_117352_, p_117353_, p_117354_, p_117355_, p_117356_, p_117357_, p_117358_);
		}
	}
}
