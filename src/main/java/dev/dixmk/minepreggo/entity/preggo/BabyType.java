package dev.dixmk.minepreggo.entity.preggo;

import javax.annotation.Nullable;


public enum BabyType {
    ZOMBIE("Zombie", 1),
    HUMANOID_CREEPER("HumanoidCreeper", 2),
    QUADRUPED_CREEPER("QuadrupedCreeper", 3),
    HUMAN("Human", 0);

    private final String name;
    private final int id;
    
    BabyType(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getString() {
        return name;
    }
    
    public int getId() {
        return id;
    }
    
    public static @Nullable BabyType fromId(int id) {
        for (var b : BabyType.values()) {
            if (b.id == id) {
                return b;
            }
        }
		return null;
    }
}
