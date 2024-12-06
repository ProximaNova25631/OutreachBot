package org.firstinspires.ftc.teamcode.common.util.wrappers;

import java.util.HashMap;
import java.util.Map;

public class ActuatorPositions<K> {
    private Map<K, Double> map;
    private Double defaultValue;

    public ActuatorPositions(Double defaultValue) {
        map = new HashMap<>();
        this.defaultValue = defaultValue;
    }

    public void put(K key, Double value) {
        map.put(key, value);
    }

    public Double get(K key) {
        Double value = map.get(key);
        return value == null ? 0.0 : defaultValue;
    }

    public Double getDefaultValue() {
        return defaultValue;
    }
}
