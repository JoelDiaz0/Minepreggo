package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

import java.util.Optional;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractTamablePregnantCreeperGirlModel;
import dev.dixmk.minepreggo.entity.preggo.PreggoMobState;
import dev.dixmk.minepreggo.entity.preggo.PregnancySymptom;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamablePregnantCreeperGirlExpressionLayer 
	<E extends AbstractTamablePregnantCreeperGirl<?,?>, M extends AbstractTamablePregnantCreeperGirlModel<E>> extends TamableCreeperGirlExpressionLayer<E, M> {
	
	protected static final RenderType ANGRY1 = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/expressions/creeper_girl_face_angry1.png"));
	protected static final RenderType HORNY1 = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/expressions/creeper_girl_face_horny1.png"));
	protected static final RenderType PAIN1 = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/expressions/creeper_girl_face_pain1.png"));
	protected static final RenderType PAIN3 = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/expressions/creeper_girl_face_pain3.png"));
	protected static final RenderType PAIN2 = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/expressions/creeper_girl_face_pain2.png"));
	protected static final RenderType SAD1 = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/expressions/creeper_girl_face_sad1.png"));
	protected static final RenderType SURPRISED1 = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/expressions/creeper_girl_face_surprised1.png"));
	
	public TamablePregnantCreeperGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}
	
	@Override
	public Optional<RenderType> renderType(E creeperGirl) {		
		switch (creeperGirl.getPregnancyPain()) {
		case MORNING_SICKNESS: {		
			return Optional.of(PAIN4);
		}
		case KICKING: {		
			return Optional.of(PAIN1);
		}
		case CONTRACTION: {		
			return Optional.of(PAIN2);
		}
		case MISCARRIAGE: {		
			return Optional.of(SURPRISED1);
		}
		case PREBIRTH: {		
			return Optional.of(PAIN3);
		}
		case BIRTH: {		
			return Optional.of(PAIN1);
		}
		default:
			break;
		}
		
		if (creeperGirl.getPregnancySymptom() != PregnancySymptom.NONE) {
			return Optional.of(SAD1);
		}
		else if (creeperGirl.getState() == PreggoMobState.BLUSHED) {
			return Optional.of(HORNY2);
		}
		else if (creeperGirl.isWaiting()) {
			return Optional.of(SAD2);
		}
		else if (creeperGirl.isSavage()) {
			return Optional.of(SAD3);
		}

		return Optional.empty();
	}
}
