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
package org.hawkular.btm.client.manager.config;

import static org.junit.Assert.assertEquals;

import org.hawkular.btm.api.model.admin.FreeFormAction;
import org.hawkular.btm.api.model.admin.InstrumentBind;
import org.hawkular.btm.api.model.admin.InstrumentRule;
import org.hawkular.btm.api.model.admin.Instrumentation;
import org.hawkular.btm.client.manager.RuleHelper;
import org.junit.Test;

/**
 * @author gbrown
 */
public class TransformerTest {

    /**  */
    private static final String BIND_EXPR2 = "BindExpr2";
    /**  */
    private static final String BIND_TYPE2 = "BindType2";
    /**  */
    private static final String BIND_NAME2 = "BindName2";
    /**  */
    private static final String BIND_EXPR1 = "BindExpr1";
    /**  */
    private static final String BIND_TYPE1 = "BindType1";
    /**  */
    private static final String BIND_NAME1 = "BindName1";
    /**  */
    private static final String TEST_CONDITION_1 = "$1.getAttributes().contains(\"BTMID\")";
    /**  */
    private static final String TEST_PARAM2 = "TestParam2";
    /**  */
    private static final String TEST_PARAM1 = "TestParam1";
    /**  */
    private static final String TEST_METHOD = "TestMethod";
    /**  */
    private static final String TEST_CLASS = "TestClass";
    /**  */
    private static final String TEST_RULE = "TestRule";

    @Test
    public void testTransformNoConditionLocationEntry() {
        InstrumentRule ir = new InstrumentRule();
        FreeFormAction im = new FreeFormAction();

        ir.setRuleName(TEST_RULE);
        ir.setClassName(TEST_CLASS);
        ir.setMethodName(TEST_METHOD);
        ir.setLocation("ENTRY");
        ir.getParameterTypes().add(TEST_PARAM1);
        ir.getParameterTypes().add(TEST_PARAM2);
        ir.getActions().add(im);
        im.setAction("Action1");

        Instrumentation in = new Instrumentation();
        in.getRules().add(ir);

        Transformer transformer = new Transformer();

        String transformed = transformer.transform(in);

        String expected = "RULE " + TEST_RULE + "\r\nCLASS " + TEST_CLASS + "\r\n"
                + "METHOD " + TEST_METHOD + "(" + TEST_PARAM1 + "," + TEST_PARAM2 + ")\r\n"
                + "HELPER " + RuleHelper.class.getName() + "\r\n"
                + "AT ENTRY\r\nIF TRUE\r\n"
                + "DO\r\n  " + im.getAction() + "\r\n"
                + "ENDRULE\r\n\r\n";

        assertEquals(expected, transformed);
    }

    @Test
    public void testTransformConditionLocationExit() {
        InstrumentRule ir = new InstrumentRule();
        FreeFormAction im = new FreeFormAction();

        ir.setRuleName(TEST_RULE);
        ir.setClassName(TEST_CLASS);
        ir.setMethodName(TEST_METHOD);
        ir.setLocation("EXIT");
        ir.setCondition(TEST_CONDITION_1);
        ir.getParameterTypes().add(TEST_PARAM1);
        ir.getParameterTypes().add(TEST_PARAM2);
        ir.getActions().add(im);
        im.setAction("Action1");

        Instrumentation in = new Instrumentation();
        in.getRules().add(ir);

        Transformer transformer = new Transformer();

        String transformed = transformer.transform(in);

        String expected = "RULE " + TEST_RULE + "\r\nCLASS " + TEST_CLASS + "\r\n"
                + "METHOD " + TEST_METHOD + "(" + TEST_PARAM1 + "," + TEST_PARAM2 + ")\r\n"
                + "HELPER " + RuleHelper.class.getName() + "\r\n"
                + "AT EXIT\r\nIF "
                + TEST_CONDITION_1 + "\r\n"
                + "DO\r\n  " + im.getAction() + "\r\n"
                + "ENDRULE\r\n\r\n";

        assertEquals(expected, transformed);
    }

    @Test
    public void testTransformMultipleActions() {
        InstrumentRule ir = new InstrumentRule();
        FreeFormAction im1 = new FreeFormAction();
        FreeFormAction im2 = new FreeFormAction();

        ir.setRuleName(TEST_RULE);
        ir.setClassName(TEST_CLASS);
        ir.setMethodName(TEST_METHOD);
        ir.setHelper("TestHelper");
        ir.setLocation("ENTRY");
        ir.setCondition(TEST_CONDITION_1);
        ir.getParameterTypes().add(TEST_PARAM1);
        ir.getParameterTypes().add(TEST_PARAM2);
        ir.getActions().add(im1);
        ir.getActions().add(im2);

        im1.setAction("Action1");
        im2.setAction("Action2");

        Instrumentation in = new Instrumentation();
        in.getRules().add(ir);

        Transformer transformer = new Transformer();

        String transformed = transformer.transform(in);

        String expected = "RULE " + TEST_RULE + "\r\nCLASS " + TEST_CLASS + "\r\n"
                + "METHOD " + TEST_METHOD + "(" + TEST_PARAM1 + "," + TEST_PARAM2 + ")\r\n"
                + "HELPER TestHelper\r\n"
                + "AT ENTRY\r\nIF "
                + TEST_CONDITION_1 + "\r\n"
                + "DO\r\n  " + im1.getAction() + ";\r\n"
                + "  " + im2.getAction() + "\r\n"
                + "ENDRULE\r\n\r\n";

        assertEquals(expected, transformed);
    }

    @Test
    public void testTransformBind() {
        InstrumentRule ir = new InstrumentRule();
        FreeFormAction im = new FreeFormAction();

        ir.setRuleName(TEST_RULE);
        ir.setClassName(TEST_CLASS);
        ir.setMethodName(TEST_METHOD);
        ir.setLocation("ENTRY");
        ir.getParameterTypes().add(TEST_PARAM1);
        ir.getParameterTypes().add(TEST_PARAM2);
        ir.getActions().add(im);
        im.setAction("Action1");

        InstrumentBind bind1=new InstrumentBind();
        bind1.setName(BIND_NAME1);
        bind1.setType(BIND_TYPE1);
        bind1.setExpression(BIND_EXPR1);
        ir.getBinds().add(bind1);

        InstrumentBind bind2=new InstrumentBind();
        bind2.setName(BIND_NAME2);
        bind2.setType(BIND_TYPE2);
        bind2.setExpression(BIND_EXPR2);
        ir.getBinds().add(bind2);

        Instrumentation in = new Instrumentation();
        in.getRules().add(ir);

        Transformer transformer = new Transformer();

        String transformed = transformer.transform(in);

        String expected = "RULE " + TEST_RULE + "\r\nCLASS " + TEST_CLASS + "\r\n"
                + "METHOD " + TEST_METHOD + "(" + TEST_PARAM1 + "," + TEST_PARAM2 + ")\r\n"
                + "HELPER " + RuleHelper.class.getName() + "\r\n"
                + "AT ENTRY\r\n"
                + "BIND " + BIND_NAME1 + " : " + BIND_TYPE1 + " = " + BIND_EXPR1 + ";\r\n"
                + "     " + BIND_NAME2 + " : " + BIND_TYPE2 + " = " + BIND_EXPR2 + ";\r\n"
                + "IF TRUE\r\n"
                + "DO\r\n  " + im.getAction() + "\r\n"
                + "ENDRULE\r\n\r\n";

        assertEquals(expected, transformed);
    }

}