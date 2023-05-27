import java.util.ArrayList;
import java.util.List;

class Vertex {
  String name;
  List<Vertex> adjacentVertices;
  boolean colored;
  String color;

  public Vertex(String name) {
    this.name = name;
    this.adjacentVertices = new ArrayList<>();
    this.colored = false;
    this.color = "";
  }

  // Connect two vertices bi-directionally
  public void addNeighbor(Vertex vertex) {
    this.adjacentVertices.add(vertex);
    vertex.adjacentVertices.add(this);
  }
}

class Coloring {
  String[] colors;
  int colorCount;
  int numberOfVertices;

  public Coloring(String[] colors, int N) {
    this.colors = colors;
    this.numberOfVertices = N;
  }

  public boolean setColors(Vertex vertex) {
    // Step 1
    for (int colorIndex = 0; colorIndex < colors.length; colorIndex++) {
      // Step 1.1: Checking validity
      if (!canColorWith(colorIndex, vertex))
        continue;

      // Step 1.2: Continue coloring
      vertex.color = colors[colorIndex];
      vertex.colored = true;
      colorCount++;

      // Step 1.3: Check whether all vertices are colored
      if (colorCount == numberOfVertices) // Base case
        return true;

      // Step 1.4: Next uncolored vertex
      for (Vertex nbrvertex : vertex.adjacentVertices) {
        if (!nbrvertex.colored) {
          if (setColors(nbrvertex))
            return true;
        }
      }
    }

    // Step 4: Backtrack
    vertex.colored = false;
    vertex.color = "";
    return false;
  }

  // Function to check whether it is valid to color with colors[colorIndex]
  boolean canColorWith(int colorIndex, Vertex vertex) {
    for (Vertex nbrvertex : vertex.adjacentVertices) {
      if (nbrvertex.colored && nbrvertex.color.equals(colors[colorIndex]))
        return false;
    }
    return true;
  }
}

public class VertexColor {
  public static void main(String args[]) {
    // Define vertices
    Vertex vertices[] = { new Vertex("A"), new Vertex("B"), new Vertex("C"), new Vertex("D") };

    // Join vertices
    vertices[0].addNeighbor(vertices[1]);
    vertices[1].addNeighbor(vertices[2]);
    vertices[2].addNeighbor(vertices[3]);
    vertices[0].addNeighbor(vertices[3]);

    // Define colors
    String colors[] = { "Green", "Blue" };

    // Create Coloring object
    Coloring coloring = new Coloring(colors, vertices.length);

    // Start coloring with vertex-A
    boolean hasSolution = coloring.setColors(vertices[0]);

    // Check if coloring was successful
    if (!hasSolution)
      System.out.println("No Solution");
    else {
      for (Vertex vertex : vertices) {
        System.out.println(vertex.name + " " + vertex.color + "\n");
      }
    }
  }
}
