package graph;

import java.io.Serializable;
import java.util.*;

public class Vertex  {
    private Integer stationNumber;
    private  Map<TypeRoad, List<Edge>> roadMap;

    public Vertex() {
        roadMap = new HashMap<>();
        roadMap.put(TypeRoad.TAXI, new LinkedList<>());
        roadMap.put(TypeRoad.BUS, new LinkedList<>());
        roadMap.put(TypeRoad.METRO, new LinkedList<>());
    }

    public Vertex(Integer stationNumber) {
        this.stationNumber = stationNumber;
        roadMap = new HashMap<>();
        roadMap.put(TypeRoad.TAXI, new LinkedList<>());
        roadMap.put(TypeRoad.BUS, new LinkedList<>());
        roadMap.put(TypeRoad.METRO, new LinkedList<>());
    }

    public Map<TypeRoad, List<Edge>> getRoadMap() {
        return roadMap;
    }

    public void setRoadMap(Map<TypeRoad, List<Edge>> roadMap) {
        this.roadMap = roadMap;
    }

    public Integer getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(Integer stationNumber) {
        this.stationNumber = stationNumber;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Vertex vertex = (Vertex) object;
        return stationNumber.equals(vertex.stationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationNumber);
    }

    @Override
    public String toString() {
        return "{" + stationNumber + "}";
    }
}
