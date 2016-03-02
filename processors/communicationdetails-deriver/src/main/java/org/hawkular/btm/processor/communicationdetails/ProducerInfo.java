/*
 * Copyright 2015-2016 Red Hat, Inc. and/or its affiliates
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
package org.hawkular.btm.processor.communicationdetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * This class represents information cached about a producer, to enable it to be
 * correlated to a consumer.
 *
 * @author gbrown
 */
public class ProducerInfo implements Externalizable {

    private String originUri;

    private long timestamp = 0;

    private double duration = 0;

    private String fragmentId;

    private String hostName;

    private String hostAddress;

    /**
     * @return the originUri
     */
    public String getOriginUri() {
        return originUri;
    }

    /**
     * @param originUri the originUri to set
     */
    public void setOriginUri(String originUri) {
        this.originUri = originUri;
    }

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
     * @return the duration
     */
    public double getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * @return the fragmentId
     */
    public String getFragmentId() {
        return fragmentId;
    }

    /**
     * @param fragmentId the fragmentId to set
     */
    public void setFragmentId(String fragmentId) {
        this.fragmentId = fragmentId;
    }

    /**
     * @return the hostName
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * @param hostName the hostName to set
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * @return the hostAddress
     */
    public String getHostAddress() {
        return hostAddress;
    }

    /**
     * @param hostAddress the hostAddress to set
     */
    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    /* (non-Javadoc)
     * @see java.io.Externalizable#readExternal(java.io.ObjectInput)
     */
    @Override
    public void readExternal(ObjectInput ois) throws IOException, ClassNotFoundException {
        ois.readInt(); // Read version

        originUri = ois.readUTF();
        timestamp = ois.readLong();
        duration = ois.readDouble();
        fragmentId = ois.readUTF();
        hostName = ois.readUTF();
        hostAddress = ois.readUTF();
    }

    /* (non-Javadoc)
     * @see java.io.Externalizable#writeExternal(java.io.ObjectOutput)
     */
    @Override
    public void writeExternal(ObjectOutput oos) throws IOException {
        oos.writeInt(1); // Write version

        oos.writeUTF(originUri);
        oos.writeLong(timestamp);
        oos.writeDouble(duration);
        oos.writeUTF(fragmentId);
        oos.writeUTF(hostName);
        oos.writeUTF(hostAddress);
    }

}