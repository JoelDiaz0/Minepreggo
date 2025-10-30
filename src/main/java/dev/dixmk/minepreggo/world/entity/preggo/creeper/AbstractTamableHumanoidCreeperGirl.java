package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.PreggoMobSystem;
import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractCreeperGirl;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractTamableHumanoidCreeperGirl<S extends PreggoMobSystem<?>> extends AbstractTamableCreeperGirl<S> {

	protected AbstractTamableHumanoidCreeperGirl(EntityType<? extends PreggoMob> p_21803_, Level p_21804_) {
		super(p_21803_, p_21804_);
	}

	@Override
	protected boolean canReplaceCurrentItem(ItemStack p_21428_, ItemStack p_21429_) {	
		return AbstractCreeperGirl.canReplaceCurrentItem(this, p_21428_, p_21429_, PregnancyStage.getNonPregnancyStage());
	}
	
	@Override
	protected void pickUpItem(ItemEntity p_21471_) {
		AbstractTamableCreeperGirl.pickUpItem(this, p_21471_);
	}
	
	public static AttributeSupplier.Builder getBasicAttributes(double movementSpeed) {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 22D)
				.add(Attributes.ATTACK_DAMAGE, 2D)
				.add(Attributes.FOLLOW_RANGE, 35D)
				.add(Attributes.MOVEMENT_SPEED, movementSpeed);
	}
}
