package dev.dixmk.minepreggo.entity.preggo;


public enum BabyType {
    ZOMBIE("Zombie"),
    HUMANOID_CREEPER("HumanoidCreeper"),
    QUADRUPED_CREEPER("QuadrupedCreeper"),
    HUMAN("Human");

    private final String name;
    
    BabyType(String name) {
        this.name = name;
    }

    public String getString() {
        return name;
    }
}
