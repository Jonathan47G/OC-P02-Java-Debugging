
package com.hemebiotech.analytics;

import com.hemebiotech.analytics.service.SymptomAnalytics;

public class Main {

	/**
	 * Starts the symptom analysis application.
	 *
	 * @param args command-line arguments (unused)
	 */
	public static void main(String[] args) {

		SymptomAnalytics analytics = new SymptomAnalytics();

		analytics.run();
	}
}