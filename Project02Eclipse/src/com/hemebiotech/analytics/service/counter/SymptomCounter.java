
package com.hemebiotech.analytics.service.counter;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SymptomCounter implements ISymptomCount {

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Integer> countSymptoms(List<String> symptoms) {

        Map<String, Integer> counts = new TreeMap<>();

        for (String symptom : symptoms) {
            counts.put(
                    symptom,
                    counts.getOrDefault(symptom, 0) + 1
            );
        }

        return counts;
    }
}