package dev.dixmk.minepreggo;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import org.apache.commons.lang3.tuple.Pair;


// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MinepreggoModConfig {
	
	private MinepreggoModConfig() {}
	
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final ForgeConfigSpec SERVER_SPEC;
 
    static final Common COMMON;
    static final Client CLIENT;
    static final Server SERVER;
    
    
    private static int totalTickByDays;
    
    
    
    
    

    private static int ticksToStartPregnancy;
    private static int totalTicksOfMorningSickness;
    private static float morningSicknessProbability;
    private static int totalTicksOfHungry;
    private static int minHungryToHeal;
    private static int minHungryToTameAgain;
    
    private static float babyCreeperGirlProbability;
    private static float babyZombieGirlProbability;

    private static boolean enablePreggoMobsMoans;  
    private static boolean enablePlayerMoans;
    
    public static int getTotalTicksByDay() {
    	return totalTickByDays;
    }
    
    public static int getTotalTicksOfHungry() {
    	return totalTicksOfHungry;
    }
    
    public static int getTicksToStartPregnancy() {
    	return ticksToStartPregnancy;
    }
    
    public static float getMorningSicknessProbability() {
    	return morningSicknessProbability;
    }
    
    public static int getTotalTicksOfMorningSickness() {
    	return totalTicksOfMorningSickness;
    }
    
    public static int getMinHungryToHeal() {
    	return minHungryToHeal;
    }
    
    public static int getMinHungryToTameAgain() {
    	return minHungryToTameAgain;
    }
    
    public static float getBabyCreeperGirlProbability() {
    	return babyCreeperGirlProbability;
    }
    
    public static float getBabyZombieGirlProbability() {
    	return babyZombieGirlProbability;
    }
    
    public static boolean isPlayerMoansEnable() {
    	return enablePlayerMoans;
    }
    
    public static boolean isPreggoMobsMoansEnable() {
    	return enablePreggoMobsMoans;
    }
    
    
    
    static {
    	Pair<Client, ForgeConfigSpec> client = 
                new ForgeConfigSpec.Builder().configure(Client::new);     
        CLIENT_SPEC = client.getRight();
        CLIENT = client.getLeft();
    	
        final Pair<Common, ForgeConfigSpec> common = 
            new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = common.getRight();
        COMMON = common.getLeft();
        
        
        final Pair<Server, ForgeConfigSpec> server = 
                new ForgeConfigSpec.Builder().configure(Server::new);
        SERVER_SPEC = server.getRight();
        SERVER = server.getLeft();
    }
    

    @SubscribeEvent
    static void onLoad(final ModConfigEvent.Loading event) {  	
    	  	
        if (event.getConfig().getSpec() == COMMON_SPEC) {
        	totalTickByDays = COMMON.totalTickByDays.get();
        }
        else if (event.getConfig().getSpec() == CLIENT_SPEC) {
        	enablePlayerMoans = CLIENT.enablePlayerMoans.get();
        	enablePreggoMobsMoans = CLIENT.enablePreggoMobsMoans.get();
        }
        else if (event.getConfig().getSpec() == SERVER_SPEC) {
        	ticksToStartPregnancy = SERVER.ticksToStartPregnancy.get();
        	morningSicknessProbability = (float) SERVER.morningSicknessProbability.get().doubleValue();
        	totalTicksOfMorningSickness = SERVER.totalTicksOfMorningSickness.get();
        	totalTicksOfHungry = SERVER.totalTicksOfHungry.get();
        	minHungryToHeal = SERVER.minHungryToHeal.get();
        	minHungryToTameAgain = SERVER.minHungryToTameAgain.get();
        	babyCreeperGirlProbability = (float) SERVER.babyCreeperGirlProbability.get().doubleValue();
        	babyZombieGirlProbability = (float) SERVER.babyZombieGirlProbability.get().doubleValue();    
        }
    }
    
    static class Common {
        final ForgeConfigSpec.IntValue totalTickByDays;

        Common(ForgeConfigSpec.Builder builder) {
            builder.push("Common"); // category name

            totalTickByDays = builder
                    .comment("Maximum number of entities allowed.")
                    .defineInRange("totalTickByDays", 24000, 100, 24000);

     
            builder.pop();
        }
    }
    
    static class Client {
        final ForgeConfigSpec.BooleanValue enablePlayerMoans;
        final ForgeConfigSpec.BooleanValue enablePreggoMobsMoans;

        Client(ForgeConfigSpec.Builder builder) {
            builder.push("Client"); // category name

            enablePlayerMoans = builder
                    .comment("Should the special feature be enabled?")
                    .define("enablePlayerMoans", true);

            enablePreggoMobsMoans = builder
                    .comment("Maximum number of entities allowed.")
                    .define("enablePreggoMobsMoans", true);

            
            builder.pop();
        }
    }
    
    
    static class Server {
        final ForgeConfigSpec.IntValue ticksToStartPregnancy;
        final ForgeConfigSpec.IntValue totalTicksOfMorningSickness;
        final ForgeConfigSpec.IntValue totalTicksOfHungry;
        final ForgeConfigSpec.IntValue minHungryToHeal;
        final ForgeConfigSpec.IntValue minHungryToTameAgain;
        final ForgeConfigSpec.DoubleValue morningSicknessProbability;
        
        final ForgeConfigSpec.DoubleValue babyCreeperGirlProbability;   
        final ForgeConfigSpec.DoubleValue babyZombieGirlProbability;
        
        
        Server(ForgeConfigSpec.Builder builder) {
            builder.push("Server");

            ticksToStartPregnancy = builder
                    .comment("Maximum number of entities allowed.")
                    .defineInRange("ticksToStartPregnancy", 6000, 1, 24000);
     
            totalTicksOfHungry= builder
                    .comment("Maximum number of entities allowed.")
                    .defineInRange("totalTicksOfHungry", 6000, 1, 24000);
            
            minHungryToHeal= builder
                    .comment("Maximum number of entities allowed.")
                    .defineInRange("minHungryToHeal", 16, 1, 20);
        
            minHungryToTameAgain= builder
                    .comment("Maximum number of entities allowed.")
                    .defineInRange("minHungryToTameAgain", 10, 1, 20);
            
            totalTicksOfMorningSickness = builder
                    .comment("Maximum number of entities allowed.")
                    .defineInRange("totalTicksOfMorningSickness", 300, 1, 24000);
                   
            morningSicknessProbability = builder
                    .comment("Maximum number of entities allowed.")
                    .defineInRange("morningSicknessProbability", 0.0001, 0.0, 1.0);
            
            babyCreeperGirlProbability = builder
                    .comment("Maximum number of entities allowed.")
                    .defineInRange("babyCreeperGirlProbability", 0.2, 0.0, 1.0);

            babyZombieGirlProbability = builder
                    .comment("Maximum number of entities allowed.")
                    .defineInRange("babyZombieGirlProbability", 0.3, 0.0, 1.0);
            
            builder.pop();
        }
    }
}
