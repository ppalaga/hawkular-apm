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
package org.hawkular.apm.api.model.config.txn;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * This class represents expression that can be applied to JSON data.
 *
 * @author gbrown
 */
public class JSONExpression extends DataExpression {

    @JsonInclude
    private String jsonpath;

    /**
     * @return the jsonpath
     */
    public String getJsonpath() {
        return jsonpath;
    }

    /**
     * @param jsonpath the jsonpath to set
     */
    public void setJsonpath(String jsonpath) {
        this.jsonpath = jsonpath;
    }

    @Override
    public String toString() {
        return "JSONExpression [jsonpath=" + jsonpath + ", getSource()=" + getSource() + ", getKey()=" + getKey()
                + "]";
    }

}
