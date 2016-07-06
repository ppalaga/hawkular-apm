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
package org.hawkular.apm.api.services;

/**
 * @author gbrown
 */
public class TestService1Impl implements TestService, ServiceLifecycle {

    private boolean initialised = false;

    /* (non-Javadoc)
     * @see org.hawkular.apm.api.services.ServiceLifecycle#init()
     */
    @Override
    public void init() {
        initialised = true;
    }

    /* (non-Javadoc)
     * @see org.hawkular.apm.api.services.TestService#isInitialised()
     */
    @Override
    public boolean isInitialised() {
        return initialised;
    }

}
