package net.prank.example;

import net.prank.NumericTools;
import net.prank.Result;
import net.prank.ScoreCard;
import net.prank.ScoreSummary;
import net.prank.ScoringRange;
import net.prank.ScoringTool;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
public class PriceScoreCard
    implements ScoreCard<List<ExampleObject>> {

    public static final String NAME = "PriceScoreCard";

    /** Max points for best scoring solution */
     private final double _maxPoints;
    /** Min points for best scoring solution */
    private final double _minPoints;
    /** How many slices */
    private final int _pointSlices;

    public PriceScoreCard(double minPoints, double maxPoints, int pointSliceCount) {

        _minPoints = minPoints;
        _maxPoints = maxPoints;
        _pointSlices = pointSliceCount;
    }

    public ScoreSummary score(List<ExampleObject> solutions) {

        if (solutions == null || solutions.isEmpty()) {
            return null;
        }

        List<Double> totalPrices = getPrices(solutions);
        double average = NumericTools.averageForDoubles(totalPrices);
        double standardDeviation = NumericTools.standardDeviationForDoubles(average, totalPrices);
        double grossMax = NumericTools.max(totalPrices);
        double grossMin = NumericTools.min(totalPrices);

        ScoringTool tool = new ScoringTool();
        Set<ScoringRange> scoring = tool.scoreSlicesEvenlyLowValueAsHighScore(_minPoints, _maxPoints,
                                                                              _pointSlices, grossMin, grossMax);

        updateSolutionsWithScore(solutions, scoring, average, standardDeviation, tool);

        return null;
    }

    public String getName() {
        return NAME;
    }

    void updateSolutionsWithScore(List<ExampleObject> solutions, Set<ScoringRange> scoringRange,
                                  double average, double standardDeviation, ScoringTool scoringTool) {

        int i = 0;

        for ( ExampleObject solution : solutions )
        {
            if ( solution.getPrice() == null )
            {
                i++;
                continue;
            }

            double totalPrice = solution.getPrice().doubleValue();
            double score = scoringTool.getScoreFromRange(totalPrice, scoringRange);

            Result.ResultBuilder rb = new Result.ResultBuilder(NAME, score);
            rb.position(i);
            rb.original(totalPrice);
            rb.average(average);
            rb.standardDeviation(standardDeviation);
            Result result = rb.build();

            solution.getScoreSummary().addResult(NAME, result);
            i++;
        }
    }


    private List<Double> getPrices(List<ExampleObject> solutions) {

        List<Double> prices = new ArrayList<Double>(solutions.size());

        for ( ExampleObject solution : solutions )
        {
            if ( solution.getPrice() != null )
            {
                double price = solution.getPrice().doubleValue();
                prices.add(price);
            }
        }

        return prices;
    }
}