
package com.hemebiotech.analytics.service.counter;

import java.util.List;
import java.util.Map;

public interface ISymptomCount {

    /**
     * Counts occurrences of each symptom.
     *
     * @param symptoms the symptoms to count
     * @return symptom counts sorted alphabetically
     */
    Map<String, Integer> countSymptoms(List<String> symptoms);
}