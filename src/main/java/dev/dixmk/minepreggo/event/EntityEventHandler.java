package dev.dixmk.minepreggo.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractZombieGirl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
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
		else if (entity instanceof SnowGolem snowGolem) {
			snowGolem.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(snowGolem, AbstractZombieGirl.class, false, false));
		} 		
	}
}
