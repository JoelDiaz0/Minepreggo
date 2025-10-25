package dev.dixmk.minepreggo.world.entity.monster;

import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractMonsterCreeperGirl;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterQuadrupedCreeperGirl;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

import net.minecraftforge.network.PlayMessages;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;

import java.util.List;

import javax.annotation.Nullable;

public class IllQuadrupedCreeperGirl extends AbstractMonsterQuadrupedCreeperGirl implements Ill {

	public IllQuadrupedCreeperGirl(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.ILL_QUADRUPED_CREEPER_GIRL.get(), world);
	}

	public IllQuadrupedCreeperGirl(EntityType<IllQuadrupedCreeperGirl> type, Level world) {
		super(type, world);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
		setPersistenceRequired();
		++this.explosionItensity;
		++this.explosionRadius;
	}
	
	@Override
	protected boolean shouldDespawnInPeaceful() {
		return false;
	}
	
	@Override
	public boolean canBeTamedByPlayer() {
		return false;
	}

	@Override
	public boolean canExplode() {
		return this.getOwner() == null || !this.isTame();
	}

	@Override
	public void tameByIllager(ScientificIllager illagerScientific) {
		this.setTame(true);
		this.setOwnerUUID(illagerScientific.getUUID());
	}
	
	@Override
	public void removeIllagerOwner() {
    	this.setTame(false);	
		this.setOwnerUUID(null);
	}
	
	@Override
	@Nullable
	public LivingEntity getOwner() {	
        if (this.getOwnerUUID() == null || this.level().isClientSide) return null;
        return (LivingEntity) ((ServerLevel) this.level()).getEntity(this.getOwnerUUID());
	}
	
	@Override
	public boolean isAlliedTo(Entity other) {
	    if (other instanceof Ill || other instanceof AbstractIllager) 
	        return true;    
	    return super.isAlliedTo(other);
	}
	
	@Override
	protected void registerGoals() {	
		this.goalSelector.addGoal(2, new IllQuadrupedCreeperGirl.SwellGoal<>(this) {		
			@Override
			public boolean canUse() {												
				return super.canUse() && canExplode();
			}
		});
					
    	Ill.addBehaviourGoals(this);

		this.goalSelector.addGoal(3, new FloatGoal(this));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Cat.class, true));
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Ocelot.class, true));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
	}

	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {					
		ItemStack itemstack = sourceentity.getItemInHand(hand);
		if (itemstack.is(ItemTags.CREEPER_IGNITERS)) {	
			this.setTarget(sourceentity);
			AABB aabb = AABB.unitCubeFromLowerCorner(this.position()).inflate(12, 8, 12);
	        List<Mob> list = this.level().getEntitiesOfClass(Mob.class, aabb, EntitySelector.NO_SPECTATORS);
	        for (var mob : list) {
                if (this != mob
                		&& !(mob.isAlliedTo(sourceentity))
                		&& (mob instanceof Ill || mob instanceof AbstractIllager)) {
                		mob.setTarget(sourceentity);
                }   	
	        }	
			return InteractionResult.FAIL;
		}	
		return InteractionResult.sidedSuccess(this.level().isClientSide);
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return AbstractMonsterCreeperGirl.getBasicAttributes(0.24);
	}
}
