package gol.domain;

public class LivingCell extends Cell {
    public LivingCell(int y, int x) {
        super(y, x);
        this.alive = true;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public boolean shouldLiveNextGeneration(INeighbourhood neighbourhood) {
        return neighbourhood.numberOfLiveNeighboursFor(this) == 2
            || neighbourhood.numberOfLiveNeighboursFor(this) == 3;
    }

}
