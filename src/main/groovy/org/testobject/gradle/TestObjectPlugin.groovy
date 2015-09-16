package org.testobject.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class TestObjectQualityReportPlugin implements Plugin<Project> {

	public static final String PLUGIN_NAME = 'testobjectQualityReport'

	private TestObjectExtension extension

	@Override
	void apply(Project project) {
		extension = project.extensions.create(PLUGIN_NAME, TestObjectExtension)
		project.logger
		project.task('startQualityReport') << {
			new TestObjectTestServer(extension, project.logger).startQualityReport()
		}
	}

}
