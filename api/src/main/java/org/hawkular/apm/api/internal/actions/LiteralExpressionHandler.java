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
package org.hawkular.apm.api.internal.actions;

import java.util.ArrayList;
import java.util.Map;

import org.hawkular.apm.api.model.Severity;
import org.hawkular.apm.api.model.config.Direction;
import org.hawkular.apm.api.model.config.txn.Expression;
import org.hawkular.apm.api.model.config.txn.LiteralExpression;
import org.hawkular.apm.api.model.config.txn.Processor;
import org.hawkular.apm.api.model.config.txn.ProcessorAction;
import org.hawkular.apm.api.model.trace.Issue;
import org.hawkular.apm.api.model.trace.Node;
import org.hawkular.apm.api.model.trace.ProcessorIssue;
import org.hawkular.apm.api.model.trace.Trace;

/**
 * This class provides the XML expression handler implementation.
 *
 * @author gbrown
 */
public class LiteralExpressionHandler extends ExpressionHandler {

    private boolean predicateResult = false;

    /**
     * @param expression
     */
    public LiteralExpressionHandler(Expression expression) {
        super(expression);
    }

    @Override
    public void init(Processor processor, ProcessorAction action, boolean predicate) {
        LiteralExpression expr = (LiteralExpression) getExpression();

        if (predicate) {
            if (!expr.getValue().equalsIgnoreCase("true") && !expr.getValue().equalsIgnoreCase("false")) {
                ProcessorIssue pi = new ProcessorIssue();
                pi.setProcessor(processor.getDescription());
                pi.setAction(action.getDescription());
                pi.setSeverity(Severity.Error);
                pi.setDescription("Literal expression must have a boolean "
                        + "(true/false) value when used as a predicate");

                if (getIssues() == null) {
                    setIssues(new ArrayList<Issue>());
                }
                getIssues().add(pi);
            } else {
                predicateResult = new Boolean(((LiteralExpression) getExpression()).getValue());
            }
        }
    }

    @Override
    public boolean test(Trace trace, Node node, Direction direction, Map<String, ?> headers,
            Object[] values) {
        return predicateResult;
    }

    @Override
    public String evaluate(Trace trace, Node node, Direction direction, Map<String, ?> headers,
            Object[] values) {
        return ((LiteralExpression) getExpression()).getValue();
    }

}
