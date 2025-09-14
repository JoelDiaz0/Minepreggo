package dev.dixmk.minepreggo.entity.preggo.girl;

public enum PregnancyStage {
    P0(0),
    P1(1),
    P2(2),
    P3(3),
    P4(4),
    P5(5),
    P6(6),
    P7(7);

    private final int id;

    PregnancyStage(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}