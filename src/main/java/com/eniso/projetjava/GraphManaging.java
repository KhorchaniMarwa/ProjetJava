
package com.eniso.projetjava;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

    public class GraphManaging implements IGraphManaging {
    private Connection connection;

    public GraphManaging(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean createGraph(String name) {
        try (Statement stmt = (Statement) connection.createStatement()) {
            String sql = "CREATE TABLE " + name + " ("
                    + "vertex INT PRIMARY KEY)";
            stmt.executeUpdate(sql);
            System.out.println("Table '" + name + "' created successfully");
            return true;
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateGraph(String name, Graph graph) {
    // Update vertices
    try {
        // First, clear existing data in the table
        clearGraphData(name);

        // Add vertices to the table
        List<Integer> vertices = graph.vertices;
        for (int vertex : vertices) {
            addVertex(name, vertex);
        }

        // Add edges to the table
        List<List<Integer>> edges = graph.edges;
        for (List<Integer> edge : edges) {
            addEdge(name, edge.get(0), edge.get(1));
        }

        System.out.println("Graph '" + name + "' updated successfully");
        return true;
    } catch (SQLException e) {
        System.err.println("Error updating graph: " + e.getMessage());
        return false;
    }
}

// Helper method to add a vertex to the graph table
private void addVertex(String tableName, int vertex) throws SQLException {
    String sql = "INSERT INTO " + tableName + "(vertex) VALUES (?)";
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setInt(1, vertex);
        pstmt.executeUpdate();
    }
}

// Helper method to add an edge to the graph table
private void addEdge(String tableName, int u, int v) throws SQLException {
    String sql = "INSERT INTO " + tableName + "(vertex1, vertex2) VALUES (?, ?)";
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setInt(1, u);
        pstmt.setInt(2, v);
        pstmt.executeUpdate();
    }
}

// Helper method to clear existing data in the graph table
private void clearGraphData(String tableName) throws SQLException {
    String sql = "DELETE FROM " + tableName;
    try (java.sql.Statement stmt = connection.createStatement()) {
        stmt.executeUpdate(sql);
    }
}

    @Override
    public boolean deleteGraph(String name) {
        try (java.sql.Statement stmt = connection.createStatement()) {
            String sql = "DROP TABLE IF EXISTS " + name;
            stmt.executeUpdate(sql);
            System.out.println("Table '" + name + "' deleted successfully");
            return true;
        } catch (SQLException e) {
            System.err.println("Error deleting table: " + e.getMessage());
            return false;
        }
    }

}
