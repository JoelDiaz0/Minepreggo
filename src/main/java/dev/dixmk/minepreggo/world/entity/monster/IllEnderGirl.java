package dev.dixmk.minepreggo.world.entity.monster;

import net.minecraftforge.network.PlayMessages;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractEnderGirl;

public class IllEnderGirl extends AbstractEnderGirl implements Ill {

	public IllEnderGirl(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.ILL_ENDER_GIRL.get(), world);
	}

	public IllEnderGirl(EntityType<IllEnderGirl> type, Level world) {
		super(type, world);
		xpReward = 12;
		setNoAi(false);
		setMaxUpStep(0.6f);
		setPersistenceRequired();
	}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return false;
	}
	
	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
		return InteractionResult.FAIL;
	}
	
	@Override
	public void tameByIllager(ScientificIllager illagerScientific) {
		this.setTame(true);
		this.setOwnerUUID(illagerScientific.getUUID());
	}
	
	@Override
	@Nullable
	public LivingEntity getOwner() {	
        if (this.getOwnerUUID() == null || this.level().isClientSide) return null;
        return (LivingEntity) ((ServerLevel) this.level()).getEntity(this.getOwnerUUID());
	}
	
	@Override
	public void removeIllagerOwner() {
    	this.setTame(false);	
		this.setOwnerUUID(null);	
	}
	
	@Override
	public boolean isAlliedTo(Entity other) {
	    if (other instanceof Ill || other instanceof AbstractIllager) 
	        return true;    
	    return super.isAlliedTo(other);
	}
	
    @Override
    protected void registerGoals() {
    	this.addBehaviourGoals();
    	Ill.addBehaviourGoals(this);
    }
}
