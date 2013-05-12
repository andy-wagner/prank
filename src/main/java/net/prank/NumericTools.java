package net.prank;

import java.util.List;

/**
 *
 * @author dmillett
 *
 * Copyright 2012 David Millett
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 *  limitations under the License.
 */
public class NumericTools {

    /**
     * Average a list of numbers
     */
    public static double average(List<? extends Number> values) {

        if (values == null || values.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;
        int count = values.size();

        for (Number value : values) {
            if (value == null) {
                continue;
            }

            total += value.doubleValue();
        }

        return total / count;
    }

    public static double standardDeviation(double mean, List<? extends Number> values) {

        if (values.size() < 2) {
            return 0.0;
        }

        double squaredSum = 0.0;

        for (Number value : values) {
            if (value == null) {
                continue;
            }

            double delta = value.doubleValue() - mean;
            squaredSum += (delta * delta);
        }

        return Math.sqrt(squaredSum / (values.size() - 1));
    }


    /**
     * Could replace this with commons-math:Mean
     *
     * @param values
     * @return
     */
    public static double averageForLongs(List<Long> values) {

        if (values == null || values.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;
        for (Long time : values) {
            total += time;
        }

        return total / values.size();
    }

    /**
     * Could replace this with commons-math:StandardDeviation
     *
     * @param mean
     * @param values
     * @return
     */
    public static double standardDeviationForLongs(double mean, List<Long> values) {

        if (values.size() < 2) {
            return 0.0;
        }

        double squaredSum = 0.0;
        for (Long time : values) {
            double delta = time - mean;
            squaredSum += (delta * delta);
        }

        return Math.sqrt(squaredSum / (values.size() - 1));
    }

    /**
     * Average for a list of Double(s)
     */
    public static double averageForDoubles(List<Double> values) {

        if (values == null || values.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;

        for (Double value : values) {
            if (value != null) {
                sum += value;
            }
        }

        return sum / values.size();
    }

    /**
     * Standard deviation for a list of doubles
     */
    public static double standardDeviationForDoubles(double mean, List<Double> values) {

        if (values.size() < 2) {
            return 0.0;
        }

        double squaredSum = 0.0;
        for (Double value : values) {
            if (value != null) {
                double delta = value - mean;
                squaredSum += (delta * delta);
            }
        }

        return Math.sqrt(squaredSum / (values.size() - 1));
    }

    public static double max(List<? extends Number> values) {

        if (values == null || values.isEmpty()) {
            return 0.0;
        }

        Double max = null;

        for (Number value : values) {
            if (max == null) {
                max = value.doubleValue();
            }

            if (value == null) {
                continue;
            }

            max = Math.max(max, value.doubleValue());
        }

        return max;
    }

    public static double min(List<? extends Number> values) {

        if (values == null || values.isEmpty()) {
            return 0.0;
        }

        Double min = null;

        for (Number value : values) {
            if (min == null) {
                min = value.doubleValue();
            }

            if (min == null) {
                continue;
            }

            min = Math.min(min, value.doubleValue());
        }

        return min;
    }
}
