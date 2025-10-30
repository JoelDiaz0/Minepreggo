package dev.dixmk.minepreggo.world.entity.preggo;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public abstract class PreggoMob extends TamableAnimal {
	public final AnimationState loopAnimationState = new AnimationState();
	public final AnimationState attackAnimationState = new AnimationState();
	
	protected PreggoMob(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_) {
		super(p_21803_, p_21804_);
	}

	public abstract String getSimpleName();
	
	public abstract boolean canBeTamedByPlayer();
	
	public abstract boolean isFoodToTame(ItemStack stack);
	
	public abstract boolean hasCustomHeadAnimation();
	
    @Override
    public void handleEntityEvent(byte id) {
        if (id == 4) { // vanilla "swing/attack" animation event
            this.attackAnimationState.start(this.tickCount); 
        }
        else {
            super.handleEntityEvent(id);
        }
    }
	
	@Override
	public void baseTick() {
		super.baseTick();
		this.refreshDimensions();
	}
	
    public boolean isAttacking() {
        return this.attackAnimationState.isStarted();
    }
	
	@Override
	public void tick() {
		super.tick();	
		if (this.level().isClientSide() && !this.loopAnimationState.isStarted()) {
			this.loopAnimationState.start(this.tickCount);
		}		
	}
	
    @Override
    public boolean doHurtTarget(Entity target) {
        boolean result = super.doHurtTarget(target);
        if (result) 
            this.level().broadcastEntityEvent(this, (byte)4); // triggers handleEntityEvent client-side     
        return result;
    }
    
	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {	
		ItemStack itemstack = sourceentity.getItemInHand(hand);

		if (this.isBaby() || !this.canBeTamedByPlayer() ) {
			return InteractionResult.FAIL;	
		}
		else if (!this.isTame() && this.isFoodToTame(itemstack)) {
			this.usePlayerItem(sourceentity, hand, itemstack);
			if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, sourceentity)) {
				this.tame(sourceentity);
				this.level().broadcastEntityEvent(this, (byte) 7);						
			} else {
				this.level().broadcastEntityEvent(this, (byte) 6);
			}
			this.setPersistenceRequired();
			return InteractionResult.sidedSuccess(this.level().isClientSide());
		} 
		
		return InteractionResult.PASS;
	}
	
	@Override
	public EntityDimensions getDimensions(Pose p_33597_) {
		return super.getDimensions(p_33597_).scale(1F);
	}
	
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	@Override
	protected void tickDeath() {
		++this.deathTime;
		if (this.deathTime == 20) {
			this.remove(Entity.RemovalReason.KILLED);
			this.dropExperience();
		}
	}
}
