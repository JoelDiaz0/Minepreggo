package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import java.util.List;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractMonsterCreeperGirl;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.monster.Ill;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.PlayMessages;
 
public class IllCreeperGirl extends AbstractMonsterHumanoidCreeperGirl implements Ill {

	public IllCreeperGirl(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.ILL_CREEPER_GIRL.get(), world);
	}

	public IllCreeperGirl(EntityType<IllCreeperGirl> type, Level world) {
		super(type, world);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
		setPersistenceRequired();
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
		addDefaultGoals(this);
	}

	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {					
		return IllCreeperGirl.mobInteract(this, sourceentity, hand);
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return AbstractMonsterHumanoidCreeperGirl.getBasicAttributes(0.235);
	}
	
	static<E extends AbstractMonsterCreeperGirl & Ill> void addDefaultGoals(E target) {
    	Ill.addBehaviourGoals(target);
    	target.goalSelector.addGoal(1, new IllQuadrupedCreeperGirl.SwellGoal<>(target) {		
			@Override
			public boolean canUse() {												
				return super.canUse() && target.canExplode();
			}
		});
		
    	target.goalSelector.addGoal(3, new MeleeAttackGoal(target, 1.0, false));
    	target.goalSelector.addGoal(3, new FloatGoal(target));
    	target.goalSelector.addGoal(6, new RandomLookAroundGoal(target));
    	target.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(target, Cat.class, true));
    	target.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(target, Ocelot.class, true));
    	target.goalSelector.addGoal(7, new LookAtPlayerGoal(target, Player.class, 8.0F));
	}
	
	static<E extends AbstractMonsterCreeperGirl & Ill> InteractionResult mobInteract(E target, Player sourceentity, InteractionHand hand) {
		ItemStack itemstack = sourceentity.getItemInHand(hand);
		if (itemstack.is(ItemTags.CREEPER_IGNITERS)) {	
			target.setTarget(sourceentity);
			AABB aabb = AABB.unitCubeFromLowerCorner(target.position()).inflate(12, 8, 12);
	        List<Mob> list = target.level().getEntitiesOfClass(Mob.class, aabb, EntitySelector.NO_SPECTATORS);
	        for (var mob : list) {
                if (target != mob
                		&& !(mob.isAlliedTo(sourceentity))
                		&& (mob instanceof Ill || mob instanceof AbstractIllager)) {
                		mob.setTarget(sourceentity);
                }   	
	        }	
			return InteractionResult.sidedSuccess(target.level().isClientSide);
		}	
		
		return InteractionResult.FAIL;
	}
}
