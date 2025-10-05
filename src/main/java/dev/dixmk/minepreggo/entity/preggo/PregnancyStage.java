package dev.dixmk.minepreggo.entity.preggo;

public enum PregnancyStage {
    P0(0),
    P1(1),
    P2(2),
    P3(3),
    P4(4),
    P5(5),
    P6(6),
    P7(7);
    
    private final int value;
  
	PregnancyStage(int value) {
		this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}