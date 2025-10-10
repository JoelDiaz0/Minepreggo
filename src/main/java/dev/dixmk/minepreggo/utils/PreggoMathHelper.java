package dev.dixmk.minepreggo.utils;

public class PreggoMathHelper {
	
	private PreggoMathHelper() {}

	public static float sigmoid(float pMin, float pMax, float k, float t, float t0) {
		return (pMin + (pMax - pMin) / (float) (1 + Math.exp(-k * (t - t0))));
	}
	
	
	
	
}
