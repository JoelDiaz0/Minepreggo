package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.IllZombieGirlP0Model;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.IllZombieGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IllZombieGirlRenderer extends AbstractMonsterZombieGirlRenderer<IllZombieGirl, IllZombieGirlP0Model> {

	private static final ResourceLocation ILL_ZOMBIE_GIRL = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/ill_zombie_girl_p0.png");
	
	public IllZombieGirlRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractZombieGirlModel.LAYER_LOCATION_P0, AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION);
	}
	
	public IllZombieGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
		super(context, new IllZombieGirlP0Model(context.bakeLayer(main)), new IllZombieGirlP0Model(context.bakeLayer(inner)), new IllZombieGirlP0Model(context.bakeLayer(outter)), false);
	}
	
	@Override
	public ResourceLocation getTextureLocation(IllZombieGirl p_115812_) {
		return ILL_ZOMBIE_GIRL;
	}
}
