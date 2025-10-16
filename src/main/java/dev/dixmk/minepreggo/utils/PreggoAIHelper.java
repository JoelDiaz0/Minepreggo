package dev.dixmk.minepreggo.utils;

import dev.dixmk.minepreggo.entity.preggo.IPreggoMob;
import dev.dixmk.minepreggo.entity.preggo.IPregnancySystem;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractTamableZombieGirl;

import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;

public class PreggoAIHelper {

	private PreggoAIHelper() {}
	
	public static<T extends AbstractTamableZombieGirl<?>> void setTamableZombieGirlGoals(T zombieGirl) {
		setBasicPreggoMobGoals(zombieGirl);
		
		zombieGirl.goalSelector.addGoal(1, new RestrictSunGoal(zombieGirl));

		zombieGirl.goalSelector.addGoal(1, new FleeSunGoal(zombieGirl, 1.2D));
	
		zombieGirl.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(zombieGirl, AbstractVillager.class, false, false) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& (zombieGirl.isSavage() || !zombieGirl.isTame());
			}
		});
		
		zombieGirl.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(zombieGirl, IronGolem.class, false, false){
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !zombieGirl.isWaiting();
			}
		});
		
		zombieGirl.targetSelector.addGoal(8, new NearestAttackableTargetGoal<>(zombieGirl, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR){
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !zombieGirl.isWaiting();
			}
		});

		zombieGirl.goalSelector.addGoal(3, new OwnerHurtByTargetGoal(zombieGirl) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !zombieGirl.isWaiting()
				&& !zombieGirl.isOnFire();				
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse()
				&& !zombieGirl.isOnFire();	
			}
		});
		
		zombieGirl.targetSelector.addGoal(3, new OwnerHurtTargetGoal(zombieGirl) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !zombieGirl.isWaiting()
				&& !zombieGirl.isOnFire();				
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse()
				&& !zombieGirl.isOnFire();	
			}
		});
		
		zombieGirl.goalSelector.addGoal(6, new PreggoMobFollowOwnerGoal<>(zombieGirl, 1.2D, 6F, 2F, false) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !zombieGirl.isWaiting()
				&& !zombieGirl.isOnFire();				
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse()
				&& !zombieGirl.isOnFire();	
			}
		});	
	}
		
	public static<T extends AbstractTamablePregnantZombieGirl<?>> void setTamablePregnantZombieGirlGoals(T zombieGirl) {
		setBasicPregnantPreggoMobGoals(zombieGirl);	
		
		zombieGirl.goalSelector.addGoal(1, new RestrictSunGoal(zombieGirl) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !zombieGirl.isIncapacitated();			
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() 
				&& !zombieGirl.isIncapacitated();	
			}
		});

		zombieGirl.goalSelector.addGoal(1, new FleeSunGoal(zombieGirl, 1.2D) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !zombieGirl.isIncapacitated();			
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() 
				&& !zombieGirl.isIncapacitated();	
			}
		});
	
		zombieGirl.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(zombieGirl, AbstractVillager.class, false, false) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& (zombieGirl.isSavage() || !zombieGirl.isTame())
				&& !zombieGirl.isIncapacitated();
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() 
				&& !zombieGirl.isIncapacitated();
			}
		});
		zombieGirl.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(zombieGirl, IronGolem.class, false, false){

			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !zombieGirl.isIncapacitated()
				&& !zombieGirl.isWaiting();
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() 
				&& !zombieGirl.isIncapacitated();
			}
		});
	
		zombieGirl.targetSelector.addGoal(8, new NearestAttackableTargetGoal<>(zombieGirl, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR){
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !zombieGirl.isIncapacitated()
				&& !zombieGirl.isWaiting();
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() 
				&& !zombieGirl.isIncapacitated();
			}
		});

		zombieGirl.goalSelector.addGoal(3, new PregnantPreggoMobOwnerHurtByTargetGoal<>(zombieGirl) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !zombieGirl.isOnFire();				
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse()
				&& !zombieGirl.isOnFire();	
			}
		});
		zombieGirl.targetSelector.addGoal(3, new PregnantPreggoMobOwnerHurtTargetGoal<>(zombieGirl) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !zombieGirl.isOnFire();				
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse()
				&& !zombieGirl.isOnFire();	
			}
		});
		zombieGirl.goalSelector.addGoal(6, new PregnantPreggoMobFollowOwnerGoal<>(zombieGirl, 1.2D, 6F, 2F, false) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !zombieGirl.isOnFire();				
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse()
				&& !zombieGirl.isOnFire();	
			}
		});	
	}
	
	
	
	public static<T extends AbstractTamablePregnantCreeperGirl<?>> void setTamablePregnantCreeperGirlGoals(T creeperGirl) {
		setBasicPregnantPreggoMobGoals(creeperGirl);
		
		creeperGirl.goalSelector.addGoal(2, new AvoidEntityGoal<>(creeperGirl, Ocelot.class, 6F, 1, 1.2) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !creeperGirl.isIncapacitated();
			}
					
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() 
				&& !creeperGirl.isIncapacitated();
			}	
		});
		creeperGirl.goalSelector.addGoal(2, new AvoidEntityGoal<>(creeperGirl, Cat.class, 6F, 1, 1.2) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !creeperGirl.isIncapacitated();
			}
					
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() 
				&& !creeperGirl.isIncapacitated();
			}	
		});
		
		creeperGirl.goalSelector.addGoal(3, new PregnantPreggoMobOwnerHurtByTargetGoal<>(creeperGirl));
		creeperGirl.targetSelector.addGoal(3, new PregnantPreggoMobOwnerHurtTargetGoal<>(creeperGirl));
		creeperGirl.goalSelector.addGoal(6, new PregnantPreggoMobFollowOwnerGoal<>(creeperGirl, 1.2D, 6F, 2F, false));	
	}	
	
	public static<T extends AbstractTamableCreeperGirl<?>> void setTamableCreeperGirlGoals(T creeperGirl) {
		setBasicPreggoMobGoals(creeperGirl);
		
		creeperGirl.goalSelector.addGoal(2, new AvoidEntityGoal<>(creeperGirl, Ocelot.class, 6F, 1, 1.2));
		creeperGirl.goalSelector.addGoal(2, new AvoidEntityGoal<>(creeperGirl, Cat.class, 6F, 1, 1.2));
		
		creeperGirl.goalSelector.addGoal(3, new OwnerHurtByTargetGoal(creeperGirl) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !creeperGirl.isWaiting();				
			}
		});
		
		creeperGirl.targetSelector.addGoal(3, new OwnerHurtTargetGoal(creeperGirl) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !creeperGirl.isWaiting();			
			}
		});
		
		creeperGirl.goalSelector.addGoal(6, new PreggoMobFollowOwnerGoal<>(creeperGirl, 1.2D, 7F, 2F, false) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !creeperGirl.isWaiting();		
			}
		});
	}
	
	private static<T extends TamableAnimal & IPreggoMob> void setBasicPreggoMobGoals(T preggoMob) {	
		
		preggoMob.targetSelector.addGoal(2, new HurtByTargetGoal(preggoMob));	
		
		preggoMob.goalSelector.addGoal(5, new MeleeAttackGoal(preggoMob, 1.2D, false));
			
		preggoMob.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(preggoMob, 1.0D) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& (preggoMob.isSavage() || !preggoMob.isTame());	
			}
		});
		
		preggoMob.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(preggoMob, Player.class, false, false) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& (preggoMob.isSavage() || !preggoMob.isTame());		
			}
		});	
		
		preggoMob.goalSelector.addGoal(9, new LookAtPlayerGoal(preggoMob, Player.class, 6F) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !preggoMob.isWaiting()
				&& !PreggoMobHelper.hasValidTarget(preggoMob);
			}
			
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse()
				&& !PreggoMobHelper.isTargetStillValid(preggoMob);
			}
		});			
		
		preggoMob.goalSelector.addGoal(10, new RandomLookAroundGoal(preggoMob) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& (preggoMob.isSavage() || !preggoMob.isTame());		
			}
		});
		
		preggoMob.goalSelector.addGoal(11, new FloatGoal(preggoMob));
	}
	
	private static<T extends TamableAnimal & IPreggoMob & IPregnancySystem> void setBasicPregnantPreggoMobGoals(T preggoMob) {	
		
		preggoMob.targetSelector.addGoal(2, new HurtByTargetGoal(preggoMob) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !preggoMob.isIncapacitated();
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse()
				&& !preggoMob.isIncapacitated();
			}
		});
		
		preggoMob.goalSelector.addGoal(5, new MeleeAttackGoal(preggoMob, 1.2D, false){
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !preggoMob.isIncapacitated();
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse()
				&& !preggoMob.isIncapacitated();
			}
		});
		
		preggoMob.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(preggoMob, 1.0D) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& (preggoMob.isSavage() || !preggoMob.isTame())		
				&& !preggoMob.isIncapacitated();
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() 
				&& !preggoMob.isIncapacitated();
			}
		});
		
		preggoMob.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(preggoMob, Player.class, false, false) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& (preggoMob.isSavage() || !preggoMob.isTame())		
				&& !preggoMob.isIncapacitated();
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() 
				&& !preggoMob.isIncapacitated();
			}
		});	
		

		preggoMob.goalSelector.addGoal(9, new LookAtPlayerGoal(preggoMob, Player.class, 6F) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !PreggoMobHelper.hasValidTarget(preggoMob)
				&& !preggoMob.isWaiting()
				&& !preggoMob.isIncapacitated();
				
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() 
				&& !PreggoMobHelper.isTargetStillValid(preggoMob)
				&& !preggoMob.isIncapacitated();
			}
		});			

		preggoMob.goalSelector.addGoal(10, new RandomLookAroundGoal(preggoMob) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& (preggoMob.isSavage() || !preggoMob.isTame())		
				&& !preggoMob.isIncapacitated();
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() 
				&& !preggoMob.isIncapacitated();
			}
		});
		
		preggoMob.goalSelector.addGoal(12, new FloatGoal(preggoMob) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !preggoMob.isIncapacitated();
			}
		});
	}
	
	
	
	
	
	
	
	
	
	private static class PreggoMobFollowOwnerGoal<T extends TamableAnimal & IPreggoMob> extends FollowOwnerGoal {

		public PreggoMobFollowOwnerGoal(T p_25294_, double p_25295_, float p_25296_, float p_25297_,
				boolean p_25298_) {
			super(p_25294_, p_25295_, p_25296_, p_25297_, p_25298_);		
		}

		@Override
		public void tick() {
			this.tamable.getLookControl().setLookAt(this.owner, 10.0F, this.tamable.getMaxHeadXRot());
			if (--this.timeToRecalcPath <= 0) {
				this.timeToRecalcPath = this.adjustedTickDelay(10);
				this.navigation.moveTo(this.owner, this.speedModifier);
			}		
			
			if (!this.tamable.level().isClientSide()
					&& this.tamable.getTarget() != null
					&& this.tamable.distanceToSqr(this.owner) > 169D) {
				this.tamable.setTarget(null);
				this.navigation.stop();
			}		
		}
		
		@Override
		public boolean canUse() {
			return super.canUse() 
			&& !PreggoMobHelper.hasValidTarget(this.tamable);
		}

		@Override
		public boolean canContinueToUse() {
			return super.canContinueToUse()
			&& !PreggoMobHelper.isTargetStillValid(this.tamable);
		}
	}
	
	private static class PregnantPreggoMobOwnerHurtByTargetGoal<T extends TamableAnimal & IPreggoMob & IPregnancySystem> extends OwnerHurtByTargetGoal {

		private final T preggoMob;
		
		public PregnantPreggoMobOwnerHurtByTargetGoal(T p_26107_) {
			super(p_26107_);
			this.preggoMob = p_26107_;
		}
		
		@Override
		public boolean canUse() {
			return super.canUse() 
			&& !preggoMob.isIncapacitated()
			&& !preggoMob.isWaiting()
			&& !preggoMob.isSavage();
		}
		@Override
		public boolean canContinueToUse() {
			return super.canContinueToUse() 
			&& !preggoMob.isIncapacitated();
		}	
	}
	
	private static class PregnantPreggoMobOwnerHurtTargetGoal<T extends TamableAnimal & IPreggoMob & IPregnancySystem> extends OwnerHurtTargetGoal {
		private final T preggoMob;
		
		public PregnantPreggoMobOwnerHurtTargetGoal(T p_26107_) {
			super(p_26107_);
			this.preggoMob = p_26107_;
		}

		@Override
		public boolean canUse() {
			return super.canUse() 
			&& !preggoMob.isIncapacitated()
			&& !preggoMob.isWaiting()
			&& !preggoMob.isSavage();
		}

		@Override
		public boolean canContinueToUse() {
			return super.canContinueToUse()
			&& !preggoMob.isIncapacitated();	
		}
	}

	private static class PregnantPreggoMobFollowOwnerGoal<T extends TamableAnimal & IPreggoMob & IPregnancySystem> extends PreggoMobFollowOwnerGoal<T> {
		private final T preggoMob;
		
		public PregnantPreggoMobFollowOwnerGoal(T p_25294_, double p_25295_, float p_25296_, float p_25297_, boolean p_25298_) {
			super(p_25294_, p_25295_, p_25296_, p_25297_, p_25298_);
			this.preggoMob = p_25294_;	
		}
		
		@Override
		public boolean canUse() {
			return super.canUse() 
			&& !preggoMob.isIncapacitated()
			&& !preggoMob.isWaiting()
			&& !preggoMob.isSavage();
		}

		@Override
		public boolean canContinueToUse() {
			return super.canContinueToUse()
			&& !preggoMob.isIncapacitated();
		}	
	}
}


