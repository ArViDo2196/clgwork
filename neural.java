package annML;
import java.util.ArrayList;
import java.util.Scanner;

public class neural {
    private ArrayList<Layer> layers;

    public neural(int nLayers, int[] nodesPerLayer) {
        layers = new ArrayList<>();
        for (int i = 0; i < nLayers; i++) {
            layers.add(new Layer(nodesPerLayer[i]));
        }
    }

    public void setEdgeWeights() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < layers.size() - 1; i++) {
            Layer currentLayer = layers.get(i);
            Layer nextLayer = layers.get(i + 1);
            for (int j = 0; j < currentLayer.getNodes().size(); j++) {
                Node currentNode = currentLayer.getNodes().get(j);
                for (int k = 0; k < nextLayer.getNodes().size(); k++) {
                    Node nextNode = nextLayer.getNodes().get(k);
                    double weight = scanner.nextDouble();
                    currentNode.addEdge(nextNode, weight);
                }
            }
        }
    }

    public double getEdgeWeight(int layerIndex, int nodeIndex, int nextLayerIndex, int nextNodeIndex) {
        Layer currentLayer = layers.get(layerIndex);
        Layer nextLayer = layers.get(nextLayerIndex);
        Node currentNode = currentLayer.getNodes().get(nodeIndex);
        Node nextNode = nextLayer.getNodes().get(nextNodeIndex);
        return currentNode.getEdgeWeight(nextNode);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("no. of layers: ");
        int numLayers = scanner.nextInt();
        System.out.println("no. of nodes each layer: ");
        int[] nodesPerLayer = new int[numLayers];
        for (int i = 0; i < numLayers; i++) {
            nodesPerLayer[i] = scanner.nextInt();
        }
        neural neuralNetwork = new neural(numLayers, nodesPerLayer);
        neuralNetwork.setEdgeWeights();
        System.out.println("index - layer,node,nextlayer,nextnode to get the weight: ");
        int layerIndex = scanner.nextInt();
        int nodeIndex = scanner.nextInt();
        int nextLayerIndex = scanner.nextInt();
        int nextNodeIndex = scanner.nextInt();
        double weight = neuralNetwork.getEdgeWeight(layerIndex, nodeIndex, nextLayerIndex, nextNodeIndex);
        System.out.println("The weight of the edge between the specified nodes is: " + weight);
    }
}

class Layer {
    private ArrayList<Node> nodes;

    public Layer(int numNodes) {
        nodes = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            nodes.add(new Node());
        }
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }
}

class Node {
    private ArrayList<Edge> edges;

    public Node() {
        edges = new ArrayList<>();
    }

    public void addEdge(Node nextNode, double weight) {
        edges.add(new Edge(nextNode, weight));
    }

    public double getEdgeWeight(Node nextNode) {
        for (Edge edge : edges) {
            if (edge.getNextNode() == nextNode) {
                return edge.getWeight();
            }
        }
        return 0;
    }
}

class Edge {
    private Node nextNode;
    private double weight;

    public Edge(Node nextNode, double weight) {
        this.nextNode = nextNode;
        this.weight = weight;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public double getWeight() {
    	return weight;
    }
   }
       

