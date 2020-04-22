public class Runner {
    public static void main(String[] args) {
        FurnitureEnum[][] currentLayout = new FurnitureEnum[][]{
                new FurnitureEnum[]{FurnitureEnum.Table, FurnitureEnum.Chair, FurnitureEnum.Cupboard},
                new FurnitureEnum[]{FurnitureEnum.Chair, FurnitureEnum.EmptySpace, FurnitureEnum.Armchair}
        };

        var startLayout = new RoomLayout(currentLayout,null);
        DFS dfs = new DFS();
        var path = dfs.Solve(startLayout);

        int steps = 0;
        for (var step : path) {
            steps++;
            System.out.println("Step #" + steps);
            System.out.println(step);
        }
    }
}
