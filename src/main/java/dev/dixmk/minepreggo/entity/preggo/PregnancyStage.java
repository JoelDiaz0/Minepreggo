package dev.dixmk.minepreggo.entity.preggo;

import net.minecraft.util.RandomSource;

public enum PregnancyStage {
    P0,
    P1,
    P2,
    P3,
    P4,
    P5,
    P6,
    P7;

    static PregnancyStage getRandomStage(int begin, int end, RandomSource randomSource) {
    	return PregnancyStage.values()[randomSource.nextInt(begin, end)];
    }
    
    static PregnancyStage getRandomFinalStage(RandomSource randomSource) {
    	return getRandomStage(4, 7, randomSource);
    }
}