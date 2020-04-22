import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RoomLayout {


    public RoomLayout(FurnitureEnum[][] currentRoomLayout, RoomLayout parentLayout) {
        this.parentLayout = parentLayout;
        Moves = new ArrayList<RoomLayout>();
        this.currentRoomLayout = currentRoomLayout;
    }

    public RoomLayout parentLayout;

    public List<RoomLayout> Moves;


    public FurnitureEnum[][] currentRoomLayout;



    // to return copy of current layout

    public FurnitureEnum[][] copyLayout(FurnitureEnum[][] layout) {
        FurnitureEnum[][] copyFurniture = new FurnitureEnum[][]{
                new FurnitureEnum[]{null, null, null},
                new FurnitureEnum[]{null, null, null}
        };

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                copyFurniture[i][j] = layout[i][j];
            }
        }
        return copyFurniture;
    }

    // is the layouts equal

    public Boolean equalLayouts(FurnitureEnum[][] layout1, FurnitureEnum[][] layout2) {
        if (layout1 == null || layout2 == null || layout1.length != layout2.length || layout1[0].length != layout2[0].length) {
            return false;
        }
        for (int i = 0; i < layout1.length; i++) {
            for (int j = 0; j < layout1.length; j++) {
                if (!layout1[i][j].equals(layout2[i][j]))
                    return false;
            }
        }
        return true;
    }

    private void moveFurniture(Cell furniture, Cell emptySpace) {
        FurnitureEnum[][] newRoomLayout = copyLayout(this.currentRoomLayout);

        FurnitureEnum value = this.currentRoomLayout[furniture.getX()][furniture.getY()];
        newRoomLayout[furniture.getX()][furniture.getY()] = FurnitureEnum.EmptySpace;
        newRoomLayout[emptySpace.getX()][emptySpace.getY()] = value;

        // if there was not such layout before

        if (this.parentLayout == null || !this.equalLayouts(newRoomLayout, this.parentLayout.currentRoomLayout)) {
            //Create with such layout and add to possible moves
            RoomLayout newLayout = new RoomLayout(newRoomLayout, this);
            this.Moves.add(newLayout);
        }
    }

    // lets find an empty space

    private Cell findEmptySpace() {
        for (int i = 0; i < currentRoomLayout.length; i++) {
            for (int j = 0; j < currentRoomLayout[i].length; j++) {
                //when find return coordinates
                if (currentRoomLayout[i][j].equals(FurnitureEnum.EmptySpace))
                    return new Cell(i, j);
            }
        }
        //if not return bad coordinates
        return new Cell(-1, -1);
    }

    public void findMoves() {
        //Находим координаты пустого места
        var emptySpace = findEmptySpace();

        //Если можно двигать предмет вверх, двигаем
        if (emptySpace.getX() - 1 >= 0)
            moveFurniture(new Cell(emptySpace.getX() - 1, emptySpace.getY()), emptySpace);

        //Если можно двигать предмет вниз, двигаем
        if (emptySpace.getX() + 1 < currentRoomLayout.length)
            moveFurniture(new Cell(emptySpace.getX() + 1, emptySpace.getY()), emptySpace);

        //Если можно двигать предмет влево, двигаем
        if (emptySpace.getY() - 1 >= 0)
            moveFurniture(new Cell(emptySpace.getX(), emptySpace.getY() - 1), emptySpace);

        //Если можно двигать предмет вправо, двигаем
        if (emptySpace.getY() + 1 < currentRoomLayout[emptySpace.getX()].length)
            moveFurniture(new Cell(emptySpace.getX(), emptySpace.getY() + 1), emptySpace);

    }

    public Boolean IsTargetLayout() {
        if (currentRoomLayout[0][2].equals(FurnitureEnum.Armchair) && currentRoomLayout[1][2].equals(FurnitureEnum.Cupboard))
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < currentRoomLayout[i].length; j++) {
                result += currentRoomLayout[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {

        RoomLayout layout = (RoomLayout) o;

        for (int i = 0; i < currentRoomLayout.length; i++) {
            for (int j = 0; j < currentRoomLayout[i].length; j++) {
                if (!currentRoomLayout[i][j].equals(layout.currentRoomLayout[i][j]))
                    return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(parentLayout, Moves);
        result = 31 * result + Arrays.hashCode(currentRoomLayout);
        return result;
    }

}
