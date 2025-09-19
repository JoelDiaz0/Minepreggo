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
	
	/*
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder(); 
    static final ForgeConfigSpec SPEC = BUILDER.build();
    
    private static final ForgeConfigSpec.IntValue TICKS_BY_DAY = BUILDER
            .comment("A magic number")
            .defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
            .comment("Whether to log the dirt block on common setup")
            .define("logDirtBlock", true);
    private static final ForgeConfigSpec.IntValue MAGIC_NUMBER = BUILDER
            .comment("A magic number")
            .defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);
    public static final ForgeConfigSpec.ConfigValue<String> MAGIC_NUMBER_INTRODUCTION = BUILDER
            .comment("What you want the introduction message to be for the magic number")
            .define("magicNumberIntroduction", "The magic number is... ");
    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
            .comment("A list of items to log on common setup.")
            .defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), Config::validateItemName);
    
    private static boolean validateItemName(final Object obj) {
        return obj instanceof final String itemName && ForgeRegistries.ITEMS.containsKey(ResourceLocation.parse(itemName));
    }
    
        logDirtBlock = LOG_DIRT_BLOCK.get();
        magicNumber = MAGIC_NUMBER.get();
        magicNumberIntroduction = MAGIC_NUMBER_INTRODUCTION.get();

        // convert the list of strings into a set of items
        items = ITEM_STRINGS.get().stream()
                .map(itemName -> ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(itemName)))
                .collect(Collectors.toSet());
     */
    
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    private static int tickByDays;
    
    static {
        final Pair<Common, ForgeConfigSpec> specPair = 
            new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }
    
    public int getTicksByDay() {
    	return tickByDays;
    }

    
    
    
    
    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
    	tickByDays = COMMON.maxEntities.get();
    }
    
    
    
    
    public static class Common {
        private final ForgeConfigSpec.BooleanValue enableFeature;
        private final ForgeConfigSpec.IntValue maxEntities;

        Common(ForgeConfigSpec.Builder builder) {
            builder.push("General"); // category name

            enableFeature = builder
                    .comment("Should the special feature be enabled?")
                    .define("enableFeature", true);

            maxEntities = builder
                    .comment("Maximum number of entities allowed.")
                    .defineInRange("maxEntities", 10, 1, 100);

            builder.pop();
        }
    }
}
