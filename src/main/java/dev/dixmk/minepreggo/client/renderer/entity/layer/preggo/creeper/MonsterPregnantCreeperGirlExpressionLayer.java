package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractMonsterPregnantCreeperGirlModel;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractMonsterPregnantCreeperGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
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
	
	@Nonnull
	@Override
	public RenderType renderType(E creeperGirl) {		
		if (creeperGirl.hasPregnancyPain()) {
			return HOSTIL_PAIN;
		}	
		return super.renderType(creeperGirl);
	}
}
