// Source: 
//  -https://www.testdome.com/questions/java/route-planner/41102
//  -https://stackoverflow.com/questions/64272121/route-planner-two-dimensional-array

import java.util.*;
public class RoutePlanner {
    static HashMap<String, ArrayList<String>> graph;

    public static boolean routeExists(int fromRow, int fromColumn, int toRow, int toColumn, boolean[][] mapMatrix) {
        if(fromRow < 0 || fromColumn < 0 || toRow < 0 || toColumn < 0) {
            return false;
        }// if
        if(fromRow >= mapMatrix.length || fromColumn >= mapMatrix[0].length || toRow >= mapMatrix.length || toColumn >= mapMatrix[0].length) {
            return false; 
        }// if
        if(!mapMatrix[fromRow][fromColumn] || !mapMatrix[toRow][toColumn]) {
            return false;
        }// if       
        if(fromRow == toRow && fromColumn == toColumn) {
            return true;
        }// if       
        constructGraph(mapMatrix);
        return bfs(fromRow + "-" + fromColumn, toRow + "-" + toColumn);
    }// routeExists

    public static void constructGraph(boolean[][] mapMatrix) {
        graph = new HashMap<String, ArrayList<String>>();
        
        for(int i = 0; i < mapMatrix.length; i++) {
            for(int j = 0; j < mapMatrix[i].length; j++) {
                if(!mapMatrix[i][j]) {
                    continue;
                }// if
                String currId = i + "-" + j;
                if(i-1 >= 0) {
                    if(mapMatrix[i-1][j]) {
                        addEdge(currId, (i-1) + "-" + j);
                    }// if
                }// if
                if(i+1 < mapMatrix.length) {
                    if(mapMatrix[i+1][j]) {
                        addEdge(currId, (i+1) + "-" + j);
                    }// if
                }// if
                if(j-1 >= 0) {
                    if(mapMatrix[i][j-1]) {
                        addEdge(currId, i + "-" + (j-1));
                    }// if
                }// if
                if(j+1 < mapMatrix[i].length) {
                    if(mapMatrix[i][j+1]) {
                        addEdge(currId, i + "-" + (j+1));
                    }// if
                }// if
            }// for
        }// for
    }// constuctGraph

    public static void addEdge(String from, String to) {
        if(graph.containsKey(from)) {
            graph.get(from).add(to);
        } else { // if
            ArrayList<String> neighbour = new ArrayList<String>();
            neighbour.add(to);
            graph.put(from, neighbour);
        }// else
    }// addEdge

    public static boolean bfs(String start, String end) {
        LinkedList<String> queue = new LinkedList<String>();        // FIFO queue for BFS
        HashSet<String> visited = new HashSet<String>();
        queue.add(start);
        visited.add(start);
        String curr;

        while(!queue.isEmpty()) {
            curr = queue.poll();            
            if(curr.equals(end)) {
                return true;
            }// if            
            if(!graph.containsKey(curr)) {
                return false;
            }// if           
            for(String next : graph.get(curr)) {
                if(!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }
            }// for
        }// while        
        return false;
    }// bfs
    
    public static void main(String[] args) {
        boolean[][] mapMatrix = {
            {true,  false, false},
            {true,  true,  false},
            {false, true,  true}
        }; // mapMaxtix
        System.out.println(routeExists(0, 0, 2, 2, mapMatrix));        
    }// main
}// RoutePlanner