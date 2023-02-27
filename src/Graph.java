import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {
    public ArrayList<GraphNode> nodeList;
    public int[][] adjacencyMatrix;
    public Graph(ArrayList<GraphNode> nodeList){
        this.nodeList = nodeList;
        adjacencyMatrix = new int[nodeList.size()][nodeList.size()];
    }
    public void addUndirectedEdge(int i, int j){
        this.adjacencyMatrix[i][j] = 1;
        this.adjacencyMatrix[j][i] = 1;
    }
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("  ");
        for(GraphNode name : this.nodeList){
            s.append(name.name+" ");
        }
        s.append("\n");

        for(int i = 0;i < nodeList.size();i++){
         s.append(nodeList.get(i).name+" ");
         for (int number : adjacencyMatrix[i]){
            s.append(number+" ");
         }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }
    public ArrayList<GraphNode> getUnvistedNodes(GraphNode node){
        ArrayList<GraphNode> list = new ArrayList<GraphNode>();
        int getNodeIndex = node.index;
        for(int i=0;i < adjacencyMatrix.length; i++){
            if(adjacencyMatrix[getNodeIndex][i] == 1){
                list.add(nodeList.get(i));
            }
        }
        return list;
    }

//    BFS
    public void bfsVisitedNode(GraphNode node){
        Queue<GraphNode> q = new LinkedList<GraphNode>();
        q.add(node);
        while(!q.isEmpty()){
            GraphNode currentNode = q.remove();
            ArrayList<GraphNode> neighbors = getUnvistedNodes(currentNode);
            if(!currentNode.isVisted){
                currentNode.isVisted = true;
                System.out.print(currentNode.name+" ");
            }
            for(GraphNode neighbor : neighbors){
                if(!neighbor.isVisted){
                    q.add(neighbor);
                }
            }

        }


    }
    public void bfs(){
        for(GraphNode node : nodeList){
            if(!node.isVisted){
                bfsVisitedNode(node);
            }

        }

    }


//    DFS
    public void dfsVisit(GraphNode node){
        Stack<GraphNode> s = new Stack<GraphNode>();
        s.push(node);

        while(!s.isEmpty()){
            GraphNode currNode = s.pop();
            ArrayList<GraphNode> list = getUnvistedNodes(currNode);
            if(!currNode.isVisted){
                System.out.print(currNode.name+"->");
                currNode.isVisted = true;
                for(GraphNode neighbor : list){
                    if(!neighbor.isVisted){
                        s.push(neighbor);
                    }
                }
            }
        }
    }
    public void dfs(){
        for(GraphNode node : nodeList){
            if(!node.isVisted){
                dfsVisit(node);
            }
        }
    }


    //    Topological sort
    public void topologicalVisited(Stack<GraphNode> stack, GraphNode node){
        ArrayList<GraphNode> list = getUnvistedNodes(node);
        for(GraphNode neighbor : list) {
            if (!node.isVisted) {
                topologicalVisited(stack, neighbor);
            }
        }
        if(!node.isVisted){
            node.isVisted = true;
            stack.push(node);
        }
    }

    public void addDirectedEdge(int i, int j){
        adjacencyMatrix[i][j] = 1;
    }

    public void tls(){
        Stack<GraphNode> stack = new Stack<GraphNode>();
        for(GraphNode node : nodeList){
            if(!node.isVisted){
                topologicalVisited(stack, node);
            }
        }


        while(!stack.isEmpty()){
            GraphNode currNode = stack.pop();
            System.out.print(currNode.name+" ");
        }
    }

//    SSSPPP

    public void pathPrint(GraphNode node){
        if(node.parent != null){
            pathPrint(node.parent);
        }
        System.out.print(node.name+" ");
    }


    public void bfsSspp(GraphNode node){
        Queue<GraphNode> q = new LinkedList<GraphNode>();
        q.add(node);
        while(!q.isEmpty()){
            GraphNode currNode = q.remove();
            currNode.isVisted = true;
            System.out.print("The list is "+currNode.name+": ");
            pathPrint(currNode);
            System.out.println();
            ArrayList<GraphNode> list = getUnvistedNodes(currNode);
            for(GraphNode neighbor : list){
                if(!neighbor.isVisted){
                    neighbor.isVisted = true;
                    neighbor.parent = currNode;
                    q.add(neighbor);
                }
            }
        }

    }





}












