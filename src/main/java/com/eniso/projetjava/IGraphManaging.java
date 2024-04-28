
package com.eniso.projetjava;

public interface IGraphManaging {
     boolean createGraph(String name);
    boolean updateGraph(String name, Graph graph);
    boolean deleteGraph(String name);
}
