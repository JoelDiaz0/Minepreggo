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
    
    private static int totalTicksOfHungryP0;
    private static int totalTicksOfHungryP1;
    private static int totalTicksOfHungryP2;
    private static int totalTicksOfHungryP3;
    private static int totalTicksOfHungryP4;
    private static int totalTicksOfHungryP5;
    private static int totalTicksOfHungryP6;
    private static int totalTicksOfHungryP7;

    private static int totalTicksOfCravingP1;
    private static int totalTicksOfCravingP2;
    private static int totalTicksOfCravingP3;
    private static int totalTicksOfCravingP4;
    private static int totalTicksOfCravingP5;
    private static int totalTicksOfCravingP6;
    private static int totalTicksOfCravingP7;
    
    private static int totalTicksOfMilkingP2;
    private static int totalTicksOfMilkingP3;
    private static int totalTicksOfMilkingP4;
    private static int totalTicksOfMilkingP5;
    private static int totalTicksOfMilkingP6;
    private static int totalTicksOfMilkingP7;
    
    private static int totalTicksOfBellyRubsP3;
    private static int totalTicksOfBellyRubsP4;
    private static int totalTicksOfBellyRubsP5;
    private static int totalTicksOfBellyRubsP6;
    private static int totalTicksOfBellyRubsP7;
    
    private static int totalTicksOfHornyP4;
    private static int totalTicksOfHornyP5;
    private static int totalTicksOfHornyP6;
    private static int totalTicksOfHornyP7;
    
    private static float babyCreeperGirlProbability;
    private static float babyZombieGirlProbability;

    private static boolean enablePreggoMobsMoans;  
    private static boolean enablePlayerMoans;
    
    public static int getTotalTicksByDay() {
    	return totalTickByDays;
    }
      
    public static int getTicksToStartPregnancy() {
    	return ticksToStartPregnancy;
    }
    
    public static int getTotalTicksOfHungryP0() {
    	return totalTicksOfHungryP0;
    }

    public static int getTotalTicksOfHungryP1() {
    	return totalTicksOfHungryP1;
    }
    
    public static int getTotalTicksOfHungryP2() {
    	return totalTicksOfHungryP2;
    }
    
    public static int getTotalTicksOfHungryP3() {
    	return totalTicksOfHungryP3;
    }
    
    public static int getTotalTicksOfHungryP4() {
    	return totalTicksOfHungryP4;
    }
    
    public static int getTotalTicksOfHungryP5() {
    	return totalTicksOfHungryP5;
    }
    
    public static int getTotalTicksOfHungryP6() {
    	return totalTicksOfHungryP6;
    }
    
    public static int getTotalTicksOfHungryP7() {
    	return totalTicksOfHungryP7;
    }
    
    public static int getTotalTicksOfMilkingP2() {
    	return totalTicksOfMilkingP2;
    }
    
    public static int getTotalTicksOfMilkingP3() {
    	return totalTicksOfMilkingP3;
    }
    
    public static int getTotalTicksOfMilkingP4() {
    	return totalTicksOfMilkingP4;
    }
    
    public static int getTotalTicksOfMilkingP5() {
    	return totalTicksOfMilkingP5;
    }
    
    public static int getTotalTicksOfMilkingP6() {
    	return totalTicksOfMilkingP6;
    }
    
    public static int getTotalTicksOfMilkingP7() {
    	return totalTicksOfMilkingP7;
    }
    
    public static int getTotalTicksOfBellyRubsP3() {
    	return totalTicksOfBellyRubsP3;
    }
    
    public static int getTotalTicksOfBellyRubsP4() {
    	return totalTicksOfBellyRubsP4;
    }
    
    public static int getTotalTicksOfBellyRubsP5() {
    	return totalTicksOfBellyRubsP5;
    }
    
    public static int getTotalTicksOfBellyRubsP6() {
    	return totalTicksOfBellyRubsP6;
    }
    
    public static int getTotalTicksOfBellyRubsP7() {
    	return totalTicksOfBellyRubsP7;
    }
    
    public static int getTotalTicksOfCravingP1() {
    	return totalTicksOfCravingP1;
    }
    
    public static int getTotalTicksOfCravingP2() {
    	return totalTicksOfCravingP2;
    }
    
    public static int getTotalTicksOfCravingP3() {
    	return totalTicksOfCravingP3;
    }
    
    public static int getTotalTicksOfCravingP4() {
    	return totalTicksOfCravingP4;
    }
    
    public static int getTotalTicksOfCravingP5() {
    	return totalTicksOfCravingP5;
    }
    
    public static int getTotalTicksOfCravingP6() {
    	return totalTicksOfCravingP6;
    }
    
    public static int getTotalTicksOfCravingP7() {
    	return totalTicksOfCravingP7;
    }
    
    public static int getTotalTicksOfHornyP4() {
    	return totalTicksOfHornyP4;
    }
    
    public static int getTotalTicksOfHornyP5() {
    	return totalTicksOfHornyP5;
    }
    
    public static int getTotalTicksOfHornyP6() {
    	return totalTicksOfHornyP6;
    }
    
    public static int getTotalTicksOfHornyP7() {
    	return totalTicksOfHornyP7;
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
        	ticksToStartPregnancy = COMMON.ticksToStartPregnancy.get();
        	totalTicksOfHungryP0 = COMMON.totalTicksOfHungry.get();
        	totalTicksOfCravingP1 = COMMON.totalTicksOfCraving.get();
        	totalTicksOfMilkingP2 = COMMON.totalTicksOfMilking.get();
        	totalTicksOfBellyRubsP3 = COMMON.totalTicksOfBellyRubs.get();
        	totalTicksOfHornyP4 = COMMON.totalTicksOfHorny.get();
        	
        	calculateHungryValues();
        	calculateCravingValues();
        	calculateMilkingValues();
        	calculateBellyRubsValues();
        	calculateHornyValues();      	
        }
        else if (event.getConfig().getSpec() == CLIENT_SPEC) {
        	enablePlayerMoans = CLIENT.enablePlayerMoans.get();
        	enablePreggoMobsMoans = CLIENT.enablePreggoMobsMoans.get();
        }
        else if (event.getConfig().getSpec() == SERVER_SPEC) {
        	babyCreeperGirlProbability = (float) SERVER.babyCreeperGirlProbability.get().doubleValue();
        	babyZombieGirlProbability = (float) SERVER.babyZombieGirlProbability.get().doubleValue();    
        }
    }
    
    
    
    
    
    
    static class Common {
    	
        final ForgeConfigSpec.IntValue ticksToStartPregnancy;
        final ForgeConfigSpec.IntValue totalTickByDays;
        final ForgeConfigSpec.IntValue totalTicksOfHungry;
        final ForgeConfigSpec.IntValue totalTicksOfCraving;
        final ForgeConfigSpec.IntValue totalTicksOfMilking;
        final ForgeConfigSpec.IntValue totalTicksOfBellyRubs;
        final ForgeConfigSpec.IntValue totalTicksOfHorny;
        
        
        Common(ForgeConfigSpec.Builder builder) {
            builder.push("Common"); // category name

            totalTickByDays = builder
                    .comment("Maximum number of entities allowed.")
                    .defineInRange("totalTickByDays", 24000, 100, 24000);

            ticksToStartPregnancy = builder
                    .comment("Maximum number of entities allowed.")
                    .defineInRange("ticksToStartPregnancy", 6000, 100, 24000);
            
            totalTicksOfHungry = builder
                    .comment("Maximum number of entities allowed.")
                    .defineInRange("totalTicksOfHungry", 6000, 100, 24000);
            
            totalTicksOfCraving = builder
                    .comment("Maximum number of entities allowed.")
                    .defineInRange("totalTicksOfCraving", 7200, 100, 24000);
            
            totalTicksOfMilking = builder
                    .comment("Maximum number of entities allowed.")
                    .defineInRange("totalTicksOfMilking", 7200, 100, 24000);
            
            totalTicksOfBellyRubs = builder
                    .comment("Maximum number of entities allowed.")
                    .defineInRange("totalTicksOfBellyRubs", 7200, 100, 24000);
            
            totalTicksOfHorny = builder
                    .comment("Maximum number of entities allowed.")
                    .defineInRange("totalTicksOfHorny", 7200, 100, 24000);
                  
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


        final ForgeConfigSpec.DoubleValue babyCreeperGirlProbability;   
        final ForgeConfigSpec.DoubleValue babyZombieGirlProbability;
        
        
        Server(ForgeConfigSpec.Builder builder) {
            builder.push("Server");
            
            babyCreeperGirlProbability = builder
                    .comment("Maximum number of entities allowed.")
                    .defineInRange("babyCreeperGirlProbability", 0.2, 0.0, 1.0);

            babyZombieGirlProbability = builder
                    .comment("Maximum number of entities allowed.")
                    .defineInRange("babyZombieGirlProbability", 0.3, 0.0, 1.0);
            
            builder.pop();
        }
    }
    
    private static void calculateHungryValues() { 	
    	totalTicksOfHungryP1 = (int) Math.ceil(totalTicksOfHungryP0 - totalTicksOfHungryP0 * 0.05F);
    	totalTicksOfHungryP2 = (int) Math.ceil(totalTicksOfHungryP0 - totalTicksOfHungryP0 * 0.1F);
    	totalTicksOfHungryP3 = (int) Math.ceil(totalTicksOfHungryP0 - totalTicksOfHungryP0 * 0.15F);
    	totalTicksOfHungryP4 = (int) Math.ceil(totalTicksOfHungryP0 - totalTicksOfHungryP0 * 0.2F);
    	totalTicksOfHungryP5 = (int) Math.ceil(totalTicksOfHungryP0 - totalTicksOfHungryP0 * 0.25F);
    	totalTicksOfHungryP6 = (int) Math.ceil(totalTicksOfHungryP0 - totalTicksOfHungryP0  * 0.3F);
    	totalTicksOfHungryP7 = (int) Math.ceil(totalTicksOfHungryP0 - totalTicksOfHungryP0 * 0.35F);
    }
    
    private static void calculateCravingValues() { 	
    	totalTicksOfCravingP2 = (int) Math.ceil(totalTicksOfCravingP1 * 0.06F - totalTicksOfCravingP1);
    	totalTicksOfCravingP3 = (int) Math.ceil(totalTicksOfCravingP1 * 0.12F - totalTicksOfCravingP1);
    	totalTicksOfCravingP4 = (int) Math.ceil(totalTicksOfCravingP1 * 0.18F - totalTicksOfCravingP1);
    	totalTicksOfCravingP5 = (int) Math.ceil(totalTicksOfCravingP1 * 0.24F - totalTicksOfCravingP1);
    	totalTicksOfCravingP6 = (int) Math.ceil(totalTicksOfCravingP1 * 0.30F - totalTicksOfCravingP1);
    	totalTicksOfCravingP7 = (int) Math.ceil(totalTicksOfCravingP1 * 0.36F - totalTicksOfCravingP1);
    }
    
    private static void calculateMilkingValues() { 	
    	totalTicksOfMilkingP3 = (int) Math.ceil(totalTicksOfMilkingP2 * 0.07F - totalTicksOfMilkingP2);
    	totalTicksOfMilkingP4 = (int) Math.ceil(totalTicksOfMilkingP2 * 0.14F - totalTicksOfMilkingP2);
    	totalTicksOfMilkingP5 = (int) Math.ceil(totalTicksOfMilkingP2 * 0.21F - totalTicksOfMilkingP2);
    	totalTicksOfMilkingP6 = (int) Math.ceil(totalTicksOfMilkingP2 * 0.28F - totalTicksOfMilkingP2);
    	totalTicksOfMilkingP7 = (int) Math.ceil(totalTicksOfMilkingP2 * 0.35F - totalTicksOfMilkingP2);
    }
    
    private static void calculateBellyRubsValues() { 	
    	totalTicksOfBellyRubsP4 = (int) Math.ceil(totalTicksOfBellyRubsP3 * 0.09F - totalTicksOfBellyRubsP3);
    	totalTicksOfBellyRubsP5 = (int) Math.ceil(totalTicksOfBellyRubsP3 * 0.18F - totalTicksOfBellyRubsP3);
    	totalTicksOfBellyRubsP6 = (int) Math.ceil(totalTicksOfBellyRubsP3 * 0.27F - totalTicksOfBellyRubsP3);
    	totalTicksOfBellyRubsP7 = (int) Math.ceil(totalTicksOfBellyRubsP3 * 0.35F - totalTicksOfBellyRubsP3);

    }
   
    private static void calculateHornyValues() { 	
    	totalTicksOfHornyP5 = (int) Math.ceil(totalTicksOfHornyP4 * 0.12F - totalTicksOfHornyP4);
    	totalTicksOfHornyP6 = (int) Math.ceil(totalTicksOfHornyP4 * 0.24F - totalTicksOfHornyP4);
    	totalTicksOfHornyP7 = (int) Math.ceil(totalTicksOfHornyP4 * 0.36F - totalTicksOfHornyP4);
    }
    
    
}
