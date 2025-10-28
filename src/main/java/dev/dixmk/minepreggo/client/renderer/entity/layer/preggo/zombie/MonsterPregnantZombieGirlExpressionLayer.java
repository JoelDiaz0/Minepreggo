package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.zombie;

import java.util.Optional;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractMonsterPregnantZombieGirlModel;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractMonsterPregnantZombieGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterPregnantZombieGirlExpressionLayer 
	<E extends AbstractMonsterPregnantZombieGirl, M extends AbstractMonsterPregnantZombieGirlModel<E>> extends MonsterZombieGirlExpressionLayer<E, M> {

	protected static final RenderType HOSTIL_PAIN = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_hostil_pain.png"));
	
	public MonsterPregnantZombieGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}

	@Override
	public Optional<RenderType> renderType(E zombieGirl) {		
		if (zombieGirl.hasPregnancyPain()) {
			return Optional.of(HOSTIL_PAIN);
		}	
		return super.renderType(zombieGirl);
	}
}
