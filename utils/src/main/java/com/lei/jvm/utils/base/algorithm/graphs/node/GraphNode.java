package com.lei.jvm.utils.base.algorithm.graphs.node;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {

	public List<GraphEdge> edgeList = null;
	
	private String label = "";
	
	public GraphNode(String label) {
		this.label = label;
		if (edgeList == null) {
			edgeList = new ArrayList<>();
		}
	}
	
	/**
	 * 给当前节点加入一条边
	 * GraphNode
	 * @param edge
	 * 			加入的边
	 */
	public void addEdgeList(GraphEdge edge) {
		edgeList.add(edge);
	}
	
	public String getLabel() {
		return label;
	}
}