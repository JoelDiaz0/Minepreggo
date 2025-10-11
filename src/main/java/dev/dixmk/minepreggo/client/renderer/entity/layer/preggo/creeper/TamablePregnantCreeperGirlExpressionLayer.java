package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

import java.util.Optional;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractTamablePregnantCreeperGirlModel;
import dev.dixmk.minepreggo.entity.preggo.PregnancySymptom;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamablePregnantCreeperGirlExpressionLayer 
	<E extends AbstractTamablePregnantCreeperGirl<?>, M extends AbstractTamablePregnantCreeperGirlModel<E>> extends TamableCreeperGirlExpressionLayer<E, M> {
	
	protected static final RenderType ANGRY = RenderType.armorCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/expressions/creeper_girl_face_angry1.png"));
	protected static final RenderType SEX = RenderType.armorCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/expressions/creeper_girl_face_horny2.png"));
	protected static final RenderType KICKING = RenderType.armorCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/expressions/creeper_girl_face_pain1.png"));
	protected static final RenderType CONTRACTION = RenderType.armorCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/expressions/creeper_girl_face_pain2.png"));
	protected static final RenderType PREBIRTH = RenderType.armorCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/expressions/creeper_girl_face_pain3.png"));
	protected static final RenderType BIRTH = RenderType.armorCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/expressions/creeper_girl_face_pain4.png"));
	protected static final RenderType MISCARRIAGE = RenderType.armorCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/expressions/creeper_girl_face_pain5.png"));
	protected static final RenderType SAD = RenderType.armorCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/expressions/creeper_girl_face_sad1.png"));
	
	public TamablePregnantCreeperGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}
	
	@Override
	public Optional<RenderType> renderType(E creeperGirl) {		
		switch (creeperGirl.getPregnancyPain()) {
		case MORNING_SICKNESS: {		
			return Optional.of(NAUSEOUS);
		}
		case KICKING: {		
			return Optional.of(KICKING);
		}
		case CONTRACTION: {		
			return Optional.of(CONTRACTION);
		}
		case MISCARRIAGE: {		
			return Optional.of(MISCARRIAGE);
		}
		case PREBIRTH: {		
			return Optional.of(PREBIRTH);
		}
		case BIRTH: {		
			return Optional.of(BIRTH);
		}
		default:
			break;
		}
		
		if (creeperGirl.getPregnancySymptom() != PregnancySymptom.NONE) {
			return Optional.of(SAD);
		}
		else if (creeperGirl.isWaiting()) {
			return Optional.of(WAITING);
		}
		else if (creeperGirl.isSavage()) {
			return Optional.of(SAD);
		}

		return Optional.empty();
	}
}
