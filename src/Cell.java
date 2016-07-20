import java.awt.*;

/**
 * Created by Artem Klots on 7/19/16.
 */
public class Cell {
    private String color;
    private int value;
    private Point position;

    public Cell(Point position, int value) {
        this.position = position;
        this.value = value;
    }

    public void joinNextCells(Cell cell) {
        this.value += cell.getValue();
        cell.setValue(0);
    }

    public String getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    // TODO: 7/20/16 Смена цвета при изменении значения
    public void setValue(int value) {
        this.value = value;
    }

    public Point getPosition() {
        return position;

    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public boolean isEmpty() {
        return value == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;

        return value == cell.value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.getValue());
    }
}
