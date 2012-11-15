package gol.domain;

public abstract class Cell {
    protected int y;
    protected int x;
    protected boolean alive;

    public Cell(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public Cell spawn(Generation generation) {
        if(shouldLiveNextGeneration(generation))
            return new LivingCell(y,x);
        return new DeadCell(y,x);
    }

    public abstract boolean isAlive();
    public abstract boolean shouldLiveNextGeneration(INeighbourhood neighbourhood);

    @Override
    public boolean equals(Object o) {
        Cell cell = (Cell) o;

        if (cell == null
         || getClass() != cell.getClass()
         || alive != cell.alive
         || x != cell.x
         || y != cell.y)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = y;
        result = 31 * result + x;
        result = 31 * result + (alive ? 1 : 0);
        return result;
    }
}
