package dev.busreliability;

public class BusDelay {
    private String routeId;
    private int delaySeconds;

    public BusDelay(String routeId, int delaySeconds) {
        this.routeId = routeId;
        this.delaySeconds = delaySeconds;
    }

    public String getRouteId() {
        return routeId;
    }

    public int getDelaySeconds() {
        return delaySeconds;
    }

}
