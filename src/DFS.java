import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DFS {
    private List<RoomLayout> visited;

    private Stack<RoomLayout> stack = new Stack<RoomLayout>();

    public LinkedList<RoomLayout> Solve(RoomLayout startLayout)
    {
        visited = new ArrayList<RoomLayout>();
        stack.push(startLayout);

        //If tehere are layouts
        while (stack.size() != 0)
        {
            //Get layout from stack
            var state = stack.pop();

            //Is it our goal?
            if (state.IsTargetLayout()) {
                System.out.println("found");
                return FindPath(state);
            }


            //If not mark as used
            visited.add(state);

            //Find new possible moves
            state.findMoves();
            List<RoomLayout> moves = state.Moves;

            for (var move:moves){
                if (!visited.contains(move))
                {
                    //Add to stack if its unique layout
                    if (!stack.contains(move))
                        stack.push(move);
                }
            }
        }

        return null;
    }
    private LinkedList<RoomLayout> FindPath(RoomLayout solution)
    {
        LinkedList<RoomLayout> path = new LinkedList<RoomLayout>();

        while (solution != null)
        {
            path.addFirst(solution);
            solution = solution.parentLayout;
        }
        return path;
    }

}
