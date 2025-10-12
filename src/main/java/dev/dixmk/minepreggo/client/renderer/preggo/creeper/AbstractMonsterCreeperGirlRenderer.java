package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper.MonsterCreeperGirlExpressionLayer;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractMonsterCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterCreeperGirlRenderer 
	<E extends AbstractMonsterCreeperGirl, M extends AbstractMonsterCreeperGirlModel<E>> extends AbstractCreeperGirlRenderer<E, M> {

	protected AbstractMonsterCreeperGirlRenderer(Context context, M main, M inner, M outter, M layer) {
		super(context, main, inner, outter, layer);
		this.addFacialExpresions();
	}

	protected void addFacialExpresions() {
		this.addLayer(new MonsterCreeperGirlExpressionLayer<>(this));
	}
}
