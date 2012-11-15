package gol.domain;

public class DeadCell extends Cell {
    public DeadCell(int y, int x) {
        super(y, x);
        this.alive = false;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public boolean shouldLiveNextGeneration(INeighbourhood neighbourhood) {
        return neighbourhood.numberOfLiveNeighboursFor(this) == 3;
    }
}
