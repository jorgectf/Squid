/*
 * Copyright 2016 James F. Bowring and CIRDLES.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cirdles.squid.tasks.expressions.operations;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.cirdles.squid.shrimp.ShrimpFractionExpressionInterface;
import org.cirdles.squid.tasks.TaskInterface;
import org.cirdles.squid.tasks.expressions.expressionTrees.ExpressionTreeInterface;

import java.util.List;

/**
 * @author James F. Bowring
 */
@XStreamAlias("Operation")
public class Pexp extends Operation {

    /**
     *
     */
    public Pexp() {
        name = "fenced expression";
        argumentCount = 1;
        precedence = 4;//todo: rethink
        rowCount = 1;
        colCount = 1;
        labelsForOutputValues = new String[][]{{"Fenced"}};
    }

    /**
     * Denotes an expression to be wrapped in parentheses using only the
     * leftChild.
     *
     * @param childrenET      the value of childrenET
     * @param shrimpFractions the value of shrimpFraction
     * @param task
     * @return the double[][]
     */
    @Override
    public Object[][] eval(
            List<ExpressionTreeInterface> childrenET, List<ShrimpFractionExpressionInterface> shrimpFractions, TaskInterface task) {

        double retVal;
        try {
            retVal = (double) childrenET.get(0).eval(shrimpFractions, task)[0][0];
        } catch (Exception e) {
            retVal = 0.0;
        }
        return new Object[][]{{retVal}};
    }

    /**
     * @param childrenET the value of childrenET
     * @return
     */
    @Override
    public String toStringMathML(List<ExpressionTreeInterface> childrenET) {
        String retVal
                = "<mrow>\n"
                + "<mo>(</mo>\n"
                + toStringAnotherExpression(childrenET.get(0))
                + "<mo>)</mo>\n"
                + "</mrow>\n";

        return retVal;
    }

}