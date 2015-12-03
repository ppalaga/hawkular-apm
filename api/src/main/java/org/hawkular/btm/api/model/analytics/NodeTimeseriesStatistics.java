/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hawkular.btm.api.model.analytics;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * This class represents a set of node summary statistical values.
 *
 * @author gbrown
 */
public class NodeTimeseriesStatistics {

    @JsonInclude
    private long timestamp = 0;

    @JsonInclude
    private Map<String,Double> nodeDurations=new HashMap<String,Double>();

    /**
     * @return the timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the nodeDurations
     */
    public Map<String, Double> getNodeDurations() {
        return nodeDurations;
    }

    /**
     * @param nodeDurations the nodeDurations to set
     */
    public void setNodeDurations(Map<String, Double> nodeDurations) {
        this.nodeDurations = nodeDurations;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "NodeTimeseriesStatistics [timestamp=" + timestamp + ", nodeDurations=" + nodeDurations + "]";
    }

}
