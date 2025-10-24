package dev.dixmk.minepreggo.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractZombieGirl;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.utils.PreggoMobHelper;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID)
public class EntityEventHandler {

	private EntityEventHandler() {}
	
	@SubscribeEvent
	public static void onEntityJoin(EntityJoinLevelEvent event) {	
		var entity = event.getEntity();
		
		if (entity instanceof AbstractVillager villager) {
			villager.goalSelector.addGoal(2, new AvoidEntityGoal<>(villager, AbstractZombieGirl.class, 6F, 1F, 1.2F));
		} 
		else if (entity instanceof IronGolem golem) {
			golem.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(golem, AbstractZombieGirl.class, false, false));
		} 				
	}
	
	
    @SubscribeEvent
    public static void onFinalizeSpawn(MobSpawnEvent.FinalizeSpawn event) {
        var mob = event.getEntity();
        
        if (mob instanceof AbstractTamablePregnantZombieGirl<?,?> zombieGirl && zombieGirl.getSpawnType() != MobSpawnType.CONVERSION) {
        	PreggoMobHelper.startPregnancy(zombieGirl);
        	zombieGirl.setCanPickUpLoot(mob.getRandom().nextFloat() < 0.55F * event.getDifficulty().getSpecialMultiplier());
        }
        else if (mob instanceof AbstractTamablePregnantCreeperGirl<?,?> creeperGirl && creeperGirl.getSpawnType() != MobSpawnType.CONVERSION) {
        	PreggoMobHelper.startPregnancy(creeperGirl);
        	creeperGirl.setCanPickUpLoot(mob.getRandom().nextFloat() < 0.35F * event.getDifficulty().getSpecialMultiplier());
        }
        else if (mob.getType() == MinepreggoModEntities.MONSTER_CREEPER_GIRL_P0.get()
        		&& mob.getRandom().nextFloat() < MinepreggoModConfig.getBabyCreeperGirlProbability()) {
        	mob.setBaby(true);
        	mob.setCanPickUpLoot(mob.getRandom().nextFloat() < 0.55F * event.getDifficulty().getSpecialMultiplier());
        }
        else if (mob.getType() == MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P0.get()
        		&& mob.getRandom().nextFloat() < MinepreggoModConfig.getBabyZombieGirlProbability()) {
        	mob.setBaby(true);
        	mob.setCanPickUpLoot(mob.getRandom().nextFloat() < 0.35F * event.getDifficulty().getSpecialMultiplier());
        }
    }
	
	
}
