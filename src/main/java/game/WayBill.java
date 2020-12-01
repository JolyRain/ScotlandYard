package game;

import graph.Vertex;

import java.io.Serializable;
import java.util.Map;

public class WayBill  {

    private Map<Vertex, TypeTicket> stepsMap;
    private Integer stepsToShow;
    private Vertex showedStation;

    public WayBill(Map<Vertex, TypeTicket> misterXStepsMap) {
        this.stepsMap = misterXStepsMap;
        stepsToShow = 0;
        showedStation = null;
    }

    public Map<Vertex, TypeTicket> getStepsMap() {
        return stepsMap;
    }

    public void setStepsMap(Map<Vertex, TypeTicket> stepsMap) {
        this.stepsMap = stepsMap;
    }

    public Integer getStepsToShow() {
        return stepsToShow;
    }

    public void setStepsToShow(Integer stepsToShow) {
        this.stepsToShow = stepsToShow;
    }

    public Vertex getShowedStation() {
        return showedStation;
    }

    public void setShowedStation(Vertex showedStation) {
        this.showedStation = showedStation;
    }
}
