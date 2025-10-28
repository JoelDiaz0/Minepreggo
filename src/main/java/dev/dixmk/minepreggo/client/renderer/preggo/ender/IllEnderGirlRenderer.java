package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractEnderGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.IllEnderGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.ender.IllEnderGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IllEnderGirlRenderer extends AbstractMonsterEnderGirlRenderer<IllEnderGirl, IllEnderGirlModel> {

	private static final RenderType ILL_ENDER_EYES = RenderType.eyes(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/ill_ender_girl_eyes.png"));
	
	public IllEnderGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main) {
		super(context, new IllEnderGirlModel(context.bakeLayer(main)), ILL_ENDER_EYES);
	}
	
	public IllEnderGirlRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractEnderGirlModel.LAYER_LOCATION_P0);
	}

	@Override
	public ResourceLocation getTextureLocation(IllEnderGirl p_115812_) {
		return ENDER_GIRL_P0_LOCATION;
	}
}
