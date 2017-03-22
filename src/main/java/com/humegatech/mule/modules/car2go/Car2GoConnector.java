package com.humegatech.mule.modules.car2go;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;

import com.humegatech.mule.modules.car2go.config.ConnectorConfig;

@Connector(name = "car2go", friendlyName = "Car2Go")
public class Car2GoConnector {

    @Config
    ConnectorConfig config;

    @Processor
    public JSONArray getLocations() throws JSONException {
        return new JSONArray(config.getClient().getLocations());
    }

    @Processor
    public JSONArray getVehicles(final String location) throws JSONException {
        return new JSONArray(config.getClient().getVehicles(location));
    }

    public ConnectorConfig getConfig() {
        return config;
    }

    public void setConfig(final ConnectorConfig config) {
        this.config = config;
    }

    static List convertJSONArray(final JSONArray jsonArray) {
        final List list = new ArrayList();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                list.add(jsonArray.get(i));
            } catch (final JSONException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
}