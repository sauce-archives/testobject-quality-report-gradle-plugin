testobject-quality-report-gradle-plugin
===================

TestObject Quality Report Gradle Plugin

Use this plugin to execute a Quality Report on app.testobject.com.

Add the following to your build.gradle file:

```
buildscript {
        repositories {
                maven { url 'http://nexus.testobject.org/nexus/content/repositories/testobject-public-repo' }
        }
 
        dependencies {
                classpath group: 'org.testobject', name: 'testobject-quality-report-gradle-plugin', version: '0.0.1'
        }
}

apply plugin: 'testobject-quality-report'

testobject-quality-report {
	username "your-username" // username you use for login into testobject, not your email
	password "your-password" // password you use for login into testobject
	team "your-team-name" // name of the team you belong to (optional)
	app "your-app-name" // name of your app
}
```
