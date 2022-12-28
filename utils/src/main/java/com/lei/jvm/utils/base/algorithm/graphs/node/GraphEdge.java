package com.lei.jvm.utils.base.algorithm.graphs.node;

public class GraphEdge {

	private GraphNode nodeLeft;
	
	private GraphNode nodeRight;

	/**
	 * @param nodeLeft
	 * 			边的左端
	 * @param nodeRight
	 * 			边的右端
	 */
	public GraphEdge(GraphNode nodeLeft, GraphNode nodeRight) {
		this.nodeLeft = nodeLeft;
		this.nodeRight = nodeRight;
	}

	public GraphNode getNodeLeft() {
		return nodeLeft;
	}

	public GraphNode getNodeRight() {
		return nodeRight;
	}
	
}