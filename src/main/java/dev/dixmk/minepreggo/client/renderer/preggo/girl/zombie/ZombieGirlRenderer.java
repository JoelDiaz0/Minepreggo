package dev.dixmk.minepreggo.client.renderer.preggo.girl.zombie;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.ZombieGirlP0Model;
import dev.dixmk.minepreggo.client.model.zombie.SimpleAnimatedZombieGirlP0Model;
import dev.dixmk.minepreggo.entity.preggo.girl.zombie.ZombieGirlEntity;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ZombieGirlRenderer extends AbstractZombieGirlRenderer<ZombieGirlEntity, SimpleAnimatedZombieGirlP0Model<ZombieGirlEntity>> {
	private static final ResourceLocation ZOMBIE_GIRL_LOCATION = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entities/preggo/girl/zombie/zombie_girl_p0.png");

	public ZombieGirlRenderer(EntityRendererProvider.Context context) {
		this(context, ZombieGirlP0Model.LAYER_LOCATION, ZombieGirlP0Model.LAYER_INNER_ARMOR_LOCATION, ZombieGirlP0Model.LAYER_OUTER_ARMOR_LOCATION);
	}
	
	public ZombieGirlRenderer(EntityRendererProvider.Context p_174458_, ModelLayerLocation p_174459_, ModelLayerLocation p_174460_, ModelLayerLocation p_174461_) {
		super(p_174458_, new SimpleAnimatedZombieGirlP0Model<>(p_174458_.bakeLayer(p_174459_)), new SimpleAnimatedZombieGirlP0Model<>(p_174458_.bakeLayer(p_174460_)), new SimpleAnimatedZombieGirlP0Model<>(p_174458_.bakeLayer(p_174461_)));
	}
	
	@Override
	public ResourceLocation getTextureLocation(ZombieGirlEntity entity) {
		return ZOMBIE_GIRL_LOCATION;
	}
}
