package com.lei.jvm.utils.base.algorithm.graphs.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author leihaoyuan
 * @Date 2022/11/20 22:04
 * @Version 1.0
 * @Description
 * https://www.cnblogs.com/shengkai126126/p/16642053.html
 */
@Slf4j
public class App {

    public static void main(String[] args) {

        List<VertexDTO> nodes = Lists.newArrayList();
        nodes.add(new VertexDTO(1L));
        nodes.add(new VertexDTO(2L));
        nodes.add(new VertexDTO(3L));
        nodes.add(new VertexDTO(4L));
        nodes.add(new VertexDTO(5L));
        nodes.add(new VertexDTO(100L));

        List<EdgeDTO> lines = Lists.newArrayList();
        lines.add(new EdgeDTO(1L, 2L));
        lines.add(new EdgeDTO(1L, 100L));
        lines.add(new EdgeDTO(1L, 3L));
        lines.add(new EdgeDTO(2L, 4L));
        lines.add(new EdgeDTO(4L, 5L));
        lines.add(new EdgeDTO(3L, 5L));


        List<List<Long>> finalPath = Lists.newArrayList();
        getPathList(Lists.newArrayList(), nodes, lines, 2L, finalPath);

        log.info("深度遍历结果集={}", JSON.toJSONString(finalPath));
    }

    /**
     * 递归获取起始点的所有路径
     * @param path 临时路径
     * @param vertices 所有顶点
     * @param edges 所有边
     * @param currentVertexId 当前顶点
     * @param finalPath 最终路径
     */
    public static void getPathList(List<Long> path, List<VertexDTO> vertices, List<EdgeDTO> edges, Long currentVertexId, List<List<Long>> finalPath) {
        if (path.contains(currentVertexId)) {
            throw new IllegalArgumentException("当前节点" + currentVertexId + "存在环路");
        }
        //路径添加当前顶点
        path.add(currentVertexId);
        //判断当前顶点是否是终点
        List<VertexDTO> nextVertexList = getNextVertexList(currentVertexId, vertices, edges);
        //没有后续相邻顶点视为终点
        if (CollectionUtils.isNotEmpty(nextVertexList)) {
            for (VertexDTO vertexDTO : nextVertexList) {
                getPathList(path, vertices, edges, vertexDTO.getId(), finalPath);
            }
        } else {
            //当前路径加入结果路径集合
            List<Long> road = new ArrayList<>(path);
            finalPath.add(road);
        }
        //删除最后一位
        path.remove(path.size() - 1);
    }

    /**
     * 获取顶点的相邻顶点集合
     *
     * @param vertexId
     * @param vertices
     * @param edges
     * @return
     */
    public static List<VertexDTO> getNextVertexList(Long vertexId, List<VertexDTO> vertices, List<EdgeDTO> edges) {
        List<VertexDTO> nextVertextList = vertices.stream().filter(v -> {
            for (EdgeDTO edge : edges) {
                if (edge.getFromVertexNo().equals(vertexId) && edge.getToVertexNo().equals(v.getId())) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
        return nextVertextList;
    }

}
