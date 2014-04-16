package parkinglot;

import java.util.List;

public class Reporter {
    private int depth;
    private final StringBuilder stringBuilder;

    public Reporter(int depth, StringBuilder stringBuilder) {
        this.depth = depth;
        this.stringBuilder = stringBuilder;
    }

    void visitManager(List<Parkable> parkerList) {
        for (int i = 0; i < depth; i++) {
            stringBuilder.append("--");
        }
        stringBuilder.append("parkinglot.Manager:\n");
        for (Parkable p : parkerList) {
            this.depth = depth + 1;
            p.apply(new Reporter(depth, stringBuilder));
            this.depth = depth - 1;
        }
    }

    void visitParker(List<ParkingLot> parkingLots) {
        for (int i = 0; i< depth; i++)
        {
            stringBuilder.append("--");
        }
        stringBuilder.append("parkinglot.Parker:");
        int counter = getCounter(parkingLots);
        stringBuilder.append(counter);
        stringBuilder.append("\n");
    }

    private int getCounter(List<ParkingLot> parkingLots) {
        int counter1 = 0;
        for (ParkingLot parkingLot : parkingLots) {
            counter1 += parkingLot.getCars().size();
        }
        return counter1;
    }
}
