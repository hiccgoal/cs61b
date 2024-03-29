package hw4.puzzle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import edu.princeton.cs.algs4.MinPQ;

public class Solver {
    private class SearchNode {
        private WorldState state;
        private int moves = 0;
        private SearchNode prev;

        private SearchNode(WorldState state, int moves, SearchNode prev) {
            this.state = state;
            this.moves = moves;
            this.prev = prev;
        }
    }

    private class NodeComparator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode o1, SearchNode o2) {
            int o1Edtg = getEdtg(o1);
            int o2Edtg = getEdtg(o2);
            int o1Priority = o1.moves + o1Edtg;
            int o2Priority = o2.moves + o2Edtg;
            return o1Priority - o2Priority;
        }

        private int getEdtg(SearchNode sn) {
            if (!edtgCaches.containsKey(sn.state)) {
                edtgCaches.put(sn.state, sn.state.estimatedDistanceToGoal());
            }
            return edtgCaches.get(sn.state);
        }
    }

    private List<WorldState> solution = new ArrayList<>();
    private Map<WorldState, Integer> edtgCaches = new HashMap<>();

    public Solver(WorldState initial) {
        MinPQ<SearchNode> pq = new MinPQ<>(new NodeComparator());
        SearchNode currentNode = new SearchNode(initial, 0, null);
        pq.insert(currentNode);

        while (!pq.isEmpty()) {
            currentNode = pq.delMin();
            if (currentNode.state.isGoal()) {
                break;
            }
            for (WorldState nextState : currentNode.state.neighbors()) {
                SearchNode newNode = new SearchNode(nextState, currentNode.moves + 1, currentNode);

                if (currentNode.prev != null && nextState.equals(currentNode.prev.state)) {
                    continue;
                }
                pq.insert(newNode);
            }
        }

        Stack<WorldState> path = new Stack<>();
        for (SearchNode n = currentNode; n != null; n = n.prev) {
            path.push(n.state);
        }
        while (!path.isEmpty()) {
            solution.add(path.pop());
        }
    }

    public int moves() {
        return solution.size() - 1;
    }

    public Iterable<WorldState> solution() {
        return solution;
    }

}
