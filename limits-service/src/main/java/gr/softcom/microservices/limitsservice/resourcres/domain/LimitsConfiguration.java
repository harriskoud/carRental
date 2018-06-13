package gr.softcom.microservices.limitsservice.resourcres.domain;

import java.io.Serializable;

public class LimitsConfiguration implements Serializable{
    private int minimum;
    private int maximum;

    public LimitsConfiguration() {
    }

    public LimitsConfiguration(int minimum, int maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }
}
