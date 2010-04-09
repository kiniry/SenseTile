package ie.ucd.sensetile.sos.dataprovider;

import net.opengis.om._1.ObservationType;

public class Observation {
    
    private ObservationType obs;
    private String time;
    private String id;

    public void setObs(ObservationType obs,String time,String id) {
        obs = obs;
        this.time = time;
        this.id = id;
    }

    public ObservationType getObs() {
        return obs;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
