
package com.hemebiotech.analytics.service;

import com.hemebiotech.analytics.service.counter.ISymptomCount;
import com.hemebiotech.analytics.service.reader.ISymptomReader;
import com.hemebiotech.analytics.service.reader.ReadSymptomDataFromFile;
import com.hemebiotech.analytics.service.counter.SymptomCounter;
import com.hemebiotech.analytics.service.writer.ISymptomsWriter;
import com.hemebiotech.analytics.service.writer.SymptomsFileWriter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SymptomAnalytics {

    /**
     * Runs the symptom analysis process.
     */
    public void run() {

        try {
            ISymptomReader reader =
                    new ReadSymptomDataFromFile("symptoms.txt");

            ISymptomCount counter =
                    new SymptomCounter();

            ISymptomsWriter writer =
                    new SymptomsFileWriter("result.out");

            List<String> symptoms = reader.getSymptoms();

            Map<String, Integer> symptomCounts =
                    counter.countSymptoms(symptoms);

            writer.writeSymptoms(symptomCounts);

        } catch (IOException e) {
            System.err.println(
                    "Error processing symptoms: " + e.getMessage()
            );
        }
    }
}