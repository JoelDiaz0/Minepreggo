package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractCreeperGirl;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractMonsterCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public abstract class AbstractMonsterHumanoidCreeperGirl extends AbstractMonsterCreeperGirl {

	protected AbstractMonsterHumanoidCreeperGirl(EntityType<? extends PreggoMob> p_21803_, Level p_21804_) {
		super(p_21803_, p_21804_);
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource p_219165_, DifficultyInstance p_219166_) {			
		AbstractCreeperGirl.populateDefaultEquipmentSlots(this, p_219165_, p_219166_);
	}	
	
	public static AttributeSupplier.Builder getBasicAttributes(double movementSpeed) {
		return Mob.createMobAttributes()
		.add(Attributes.MAX_HEALTH, 20)
		.add(Attributes.ATTACK_DAMAGE, 2)
		.add(Attributes.FOLLOW_RANGE, 35)
		.add(Attributes.MOVEMENT_SPEED, movementSpeed);
	}
	
	@Override
	public boolean hasCustomHeadAnimation() {
		return false;
	}
}
