package UAP;
import java.util.*;

class Edge {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class Subset {
    int parent, rank;
}

class DigraphMatrix {
    int[][] data;
    int size;

    public DigraphMatrix(int size) {
        this.size = size;
        data = new int[size][size];
    }

    public void getNodes() {
        System.out.print("Nodes: ");
        for (int i = 0; i < size; i++) {
            System.out.print(i + 1);
            if (i != size - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public void addEdge(int node1, int node2) {
        data[node1 - 1][node2 - 1] = 1;
    }

    public void printMatrix() {
        for (int i = 0; i < size; i++) {
            System.out.println(Arrays.toString(data[i]));
        }
    }

    public void BFS(int start) {
        boolean[] visited = new boolean[size];
        Queue queue = new Queue();
        visited[start - 1] = true;
        queue.enqueue(start);
        System.out.print("BFS: ");
        while (queue.getSize() != 0) {
            start = queue.poll();
            System.out.print(start + " ");
            for (int i = 0; i < size; i++) {
                if (data[start - 1][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.enqueue(i + 1);
                }
            }
        }
        System.out.println();
    }

    public void DFS(int start) {
        boolean[] visited = new boolean[size];
        System.out.print("DFS: ");
        DFS(start, visited);
        System.out.println();
    }

    public void DFS(int start, boolean[] visited) {
        visited[start - 1] = true;
        System.out.print(start + " ");
        for (int i = 0; i < size; i++) {
            if (data[start - 1][i] == 1 && !visited[i]) {
                DFS(i + 1, visited);
            }
        }
    }
}

class UndigraphMatrix {
    int[][] data;
    int size;
    Edge[] edges;
    int edgeCount;

    public UndigraphMatrix(int size, int edgeCount) {
        this.size = size;
        data = new int[size][size];
        edges = new Edge[edgeCount];
        this.edgeCount = 0;
    }

    public void addEdge(int node1, int node2, int weight) {
        data[node1 - 1][node2 - 1] = weight;
        data[node2 - 1][node1 - 1] = weight;
        edges[edgeCount++] = new Edge(node1 - 1, node2 - 1, weight);
    }

    public void printMatrix() {
        for (int i = 0; i < size; i++) {
            System.out.println(Arrays.toString(data[i]));
        }
    }
    
    public void BFS(int start) {
        boolean[] visited = new boolean[size];
        Queue queue = new Queue();
        visited[start - 1] = true;
        queue.enqueue(start);
        System.out.print("BFS: ");
        while (queue.getSize() != 0) {
            start = queue.poll();
            System.out.print(start + " ");
            for (int i = 0; i < size; i++) {
                if (data[start - 1][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.enqueue(i + 1);
                }
            }
        }
        System.out.println();
    }

    public void DFS(int start) {
        boolean[] visited = new boolean[size];
        System.out.print("DFS: ");
        DFS(start, visited);
        System.out.println();
    }

    public void DFS(int start, boolean[] visited) {
        visited[start - 1] = true;
        System.out.print(start + " ");
        for (int i = 0; i < size; i++) {
            if (data[start - 1][i] == 1 && !visited[i]) {
                DFS(i + 1, visited);
            }
        }
    }
    

    public int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    public void union(Subset[] subsets, int x, int y) {
        int rootX;
        int rootY;
        
        rootX = find(subsets, x);
        rootY = find(subsets, y);

        if (subsets[rootX].rank < subsets[rootY].rank) {
            subsets[rootX].parent = rootY;
        } else if (subsets[rootX].rank > subsets[rootY].rank) {
            subsets[rootY].parent = rootX;
        } else {
            subsets[rootY].parent = rootX;
            subsets[rootX].rank++;
        }
    }

    public void bubbleSort() {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    Edge temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    public void MST() {
        int totalWeightMST;
        int[][] mst;
        Edge[] result;
        Subset[] subsets;
        int e;
        int i;
                
        bubbleSort();

        result = new Edge[size - 1];
        e = 0;
        i = 0;

        subsets = new Subset[size];
        for (int v = 0; v < size; ++v) {
            subsets[v] = new Subset();
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        while (e < size - 1) {
            int x;
            int y;
            
            Edge next_edge = edges[i++];
            x = find(subsets, next_edge.src);
            y = find(subsets, next_edge.dest);

            if (x != y) {
                result[e++] = next_edge;
                union(subsets, x, y);
            }
        }

        totalWeightMST = 0;
        mst = new int[size][size];
        
        for (i = 0; i < e; ++i) {
            mst[result[i].src][result[i].dest] = result[i].weight;
            mst[result[i].dest][result[i].src] = result[i].weight;
            totalWeightMST += result[i].weight;
        }

        System.out.println("\nTotal bobot dari graph: " + totalWeightMST);

        System.out.println("Graph setelah MST");
        for (int[] row : mst) {
            System.out.println(Arrays.toString(row));
        }
    }
}

class NodeQueue {
    int data;
    NodeQueue next;

    public NodeQueue(int data) {
        this.data = data;
        this.next = null;
    }
}

class Queue {
    NodeQueue head, tail;
    int size = 0;

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public void enqueue(int data) {
        NodeQueue input = new NodeQueue(data);
        if (isEmpty()) {
            head = tail = input;
        }
        else {
            tail.next = input;
            tail = input;
        }
        size++;
    }

    public void dequeue() {
        if (!isEmpty()) {
            head = head.next;
            size--;
        }
    }

    public int poll() {
        int temp = head.data;
        dequeue();
        return temp;
    }
}

public class UAP_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size;
        String[] edges;
        UndigraphMatrix graph;
        int totalWeightGraph;
        
        // input size
        size = scanner.nextInt();
        scanner.nextLine();
        
        // imput edges
        edges = scanner.nextLine().split(" ");
        
        // pake yang undigraph
        graph = new UndigraphMatrix(size, edges.length);

        // masukin ke graph
        for (String edge : edges) {
            String[] parts = edge.split(",");
            int node1;
            int node2;
            int weight;
            
            node1 = Integer.parseInt(parts[0]);
            node2 = Integer.parseInt(parts[1]);
            weight = Integer.parseInt(parts[2]);
            
            graph.addEdge(node1, node2, weight);
        }

        System.out.println("Graph sebelum MST:");
        graph.printMatrix();

        totalWeightGraph = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                totalWeightGraph += graph.data[i][j];
            }
        }

        graph.MST();
    }
}
