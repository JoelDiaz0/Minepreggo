package dev.dixmk.minepreggo.world.entity.monster;

import net.minecraftforge.network.PlayMessages;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.util.GoalUtils;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.nbt.CompoundTag;

import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.ScientificIllagerTrades;


public class ScientificIllager extends AbstractIllager implements Merchant {
    private MerchantOffers offers;
    private Player tradingPlayer;
    private boolean spawnIllEnderGirl = true;
    private boolean spawnIllZombieGirl = true;
    private boolean spawnIllQuadrupedCreeperGirl = true;
    private boolean spawnIllCreeperGirl = true;
    
	public ScientificIllager(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.SCIENTIFIC_ILLAGER.get(), world);
	}
	
	public ScientificIllager(EntityType<ScientificIllager> type, Level world) {
		super(type, world);
		setMaxUpStep(0.6f);
		xpReward = 12;
		setNoAi(false);
	}
	
    private void updateTrades() {
    	final var keys = ScientificIllagerTrades.TRADES.keySet().toIntArray();  	
		if (keys != null) {	
			final var randomKey = ScientificIllagerTrades.TRADES.get(keys[this.random.nextInt(keys.length)]);
			for (var trade : randomKey) {
				this.offers.add(trade.getOffer(this, this.random));
			}					
		}	
    }
	
    
	@Override
	public boolean canJoinRaid() {
		return false;
	}
    
	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}
	
	@Override
	protected boolean shouldDespawnInPeaceful() {
		return false;
	}
    
    @Override
    public void setTradingPlayer(@Nullable Player player) {
        this.tradingPlayer = player;
    }

    @Override
    @Nullable
    public Player getTradingPlayer() {
        return this.tradingPlayer;
    }

    @Override
    public MerchantOffers getOffers() {
        if (this.offers == null) {
            this.offers = new MerchantOffers();
            this.updateTrades();
         }
         return this.offers;
    }

    @Override
    public void overrideOffers(MerchantOffers offers) {
        this.offers = offers;
    }

    @Override
    public void notifyTrade(MerchantOffer offer) {
        // It does not need, It is not a villager
    }

    @Override
    public void notifyTradeUpdated(ItemStack stack) {
    	// It does not need, It is not a villager
    }
    
    @Override
    public int getVillagerXp() {
        return 0;
    }

    @Override
    public void overrideXp(int xp) {
    	// It does not need, It is not a villager
    }

    @Override
    public boolean isClientSide() {
        return this.level().isClientSide;
    }
        
	@Override
	public boolean showProgressBar() {
		return false;
	}

	@Override
	public SoundEvent getNotifyTradeSound() {
	      return SoundEvents.PILLAGER_CELEBRATE;
	}
    
    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!this.level().isClientSide && !player.isShiftKeyDown()) {      	
            this.setTradingPlayer(player);
            this.openTradingScreen(player, this.getDisplayName(), 1);      
        }             
        return InteractionResult.sidedSuccess(this.level().isClientSide);
    }
  
	
	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);				
	    MerchantOffers merchantoffers = this.getOffers();
	    if (!merchantoffers.isEmpty()) {
	    	compound.put("Offers", merchantoffers.createTag());
	    }
	    compound.putBoolean("SpawnIllEnderGirl", spawnIllEnderGirl);
	    compound.putBoolean("SpawnIllZombieGirl", spawnIllZombieGirl);
	    compound.putBoolean("SpawnIllCreeperGirl", spawnIllCreeperGirl);
	    compound.putBoolean("SpawnIllQuadrupedCreeperGirl", spawnIllQuadrupedCreeperGirl);
	}
		
	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);	
		this.offers = new MerchantOffers(compound.getCompound("Offers"));
		this.spawnIllEnderGirl = compound.getBoolean("SpawnIllEnderGirl");
		this.spawnIllZombieGirl = compound.getBoolean("SpawnIllZombieGirl");
		this.spawnIllCreeperGirl = compound.getBoolean("SpawnIllCreeperGirl");
		this.spawnIllQuadrupedCreeperGirl = compound.getBoolean("SpawnIllQuadrupedCreeperGirl");
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(2, new AbstractIllager.RaiderOpenDoorGoal(this));
		this.goalSelector.addGoal(3, new Raider.HoldGroundAttackGoal(this, 10.0F));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Raider.class)).setAlertOthers());
		this.targetSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
		this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6D));
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
	}

	public boolean canIllZombieGirlSpawn() {
		return spawnIllZombieGirl;
	}
	
	public boolean canIllCreeperGirlSpawn() {
		return spawnIllCreeperGirl;
	}
	
	public boolean canIllEnderGirlSpawn() {
		return spawnIllEnderGirl;
	}
	
	public boolean canIllQueadrupedCreeperGirlSpawn() {
		return spawnIllQuadrupedCreeperGirl;
	}
	
	public boolean spawnIllZombieGirlIfCan() {	
		if (!this.spawnIllZombieGirl) return false;
		this.spawnIllZombieGirl = false;
		
		
		return true;
	}
	
	public boolean spawnIllCreeperGirlIfCan() {
		if (!this.spawnIllCreeperGirl) return false;
		this.spawnIllCreeperGirl = false;
		
		return true;
	}
	
	public boolean spawnIllQuadrupedCreeperGirlIfCan() {
		if (!this.spawnIllQuadrupedCreeperGirl) return false;
		this.spawnIllQuadrupedCreeperGirl = false;
		
		
		return true;
	}
	
	public boolean spawnIllEnderGirlIfCan() {
		if (!this.spawnIllEnderGirl) return false;
		this.spawnIllEnderGirl = false;
		
		
		return true;
	}
	
	@Override
	protected void customServerAiStep() {
	    if (!this.isNoAi() && GoalUtils.hasGroundPathNavigation(this)) {
	        boolean flag = ((ServerLevel)this.level()).isRaided(this.blockPosition());
	        ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(flag);
	    }

	    super.customServerAiStep();
	}

	public static AttributeSupplier.Builder createAttributes() {
	    return Monster.createMonsterAttributes()
	    		.add(Attributes.MOVEMENT_SPEED, 0.35)
	    		.add(Attributes.FOLLOW_RANGE, 24.0D)
	    		.add(Attributes.MAX_HEALTH, 24.0D)
	    		.add(Attributes.ATTACK_DAMAGE, 5.0D);
	}

	@Override
	public AbstractIllager.IllagerArmPose getArmPose() {
	    if (this.isAggressive()) {
	        return AbstractIllager.IllagerArmPose.ATTACKING;
	    }
	    else {
	        return this.isCelebrating() ? AbstractIllager.IllagerArmPose.CELEBRATING : AbstractIllager.IllagerArmPose.CROSSED;
	    }
	}

	public SoundEvent getCelebrateSound() {
	    return SoundEvents.VINDICATOR_CELEBRATE;
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_34088_, DifficultyInstance p_34089_, MobSpawnType p_34090_, @Nullable SpawnGroupData p_34091_, @Nullable CompoundTag p_34092_) {
	    SpawnGroupData spawngroupdata = super.finalizeSpawn(p_34088_, p_34089_, p_34090_, p_34091_, p_34092_);
	    ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(true);
	    RandomSource randomsource = p_34088_.getRandom();
	    this.populateDefaultEquipmentSlots(randomsource, p_34089_);
	    this.populateDefaultEquipmentEnchantments(randomsource, p_34089_);
	    return spawngroupdata;
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource p_219149_, DifficultyInstance p_219150_) {
	    if (this.getCurrentRaid() == null) {
	        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_AXE));
	    }

	}

	@Override
	public boolean isAlliedTo(Entity p_34110_) {
	    if (super.isAlliedTo(p_34110_) || p_34110_ instanceof Ill) {
	        return true;
	    }
	    else if (p_34110_ instanceof LivingEntity && ((LivingEntity)p_34110_).getMobType() == MobType.ILLAGER) {
	        return this.getTeam() == null && p_34110_.getTeam() == null;
	    }
	    else {
	        return false;
	    }
	}

	@Override
	protected SoundEvent getAmbientSound() {
	    return SoundEvents.VINDICATOR_AMBIENT;
	}

	@Override
	protected SoundEvent getDeathSound() {
	    return SoundEvents.VINDICATOR_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource p_34103_) {
	    return SoundEvents.VINDICATOR_HURT;
	}

	public void applyRaidBuffs(int p_34079_, boolean p_34080_) {
	    ItemStack itemstack = new ItemStack(Items.IRON_AXE);
	    Raid raid = this.getCurrentRaid();
	    int i = 1;
	    if (p_34079_ > raid.getNumGroups(Difficulty.NORMAL)) {
	        i = 2;
	    }

	    boolean flag = this.random.nextFloat() <= raid.getEnchantOdds();
	    if (flag) {
	        Map<Enchantment, Integer> map = Maps.newHashMap();
	        map.put(Enchantments.SHARPNESS, i);
	        EnchantmentHelper.setEnchantments(map, itemstack);
	    }

	    this.setItemSlot(EquipmentSlot.MAINHAND, itemstack);
	}
}
