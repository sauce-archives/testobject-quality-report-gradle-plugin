testobject-quality-report-gradle-plugin
===================

TestObject Quality Report Gradle Plugin

Use this plugin to execute Quality Reports on app.testobject.com.

Add the following to your build.gradle file:

```
buildscript {
	repositories {
		mavenCentral()
		maven { url 'http://nexus.testobject.org/nexus/content/repositories/testobject-public-repo' }
	}

	dependencies {
		classpath group: 'org.testobject', name: 'testobject-quality-report-gradle-plugin', version: '0.0.3'
	}
}

apply plugin: 'testobjectQualityReport'

testobjectQualityReport {
	username "your_username" // username you use for login into TestObject, not your email
	password "your_password" // password you use for login into TestObject
	team "your_team_name" // name of the team you belong to (optional)
	app "your_app_name" // name of your app
}
```
