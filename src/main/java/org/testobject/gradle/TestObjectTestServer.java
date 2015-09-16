package org.testobject.gradle;

import org.gradle.api.GradleScriptException;
import org.gradle.api.logging.Logger;
import org.testobject.api.TestObjectClient;

public class TestObjectTestServer {

	private final Logger logger;
	private final TestObjectExtension extension;

	public TestObjectTestServer(TestObjectExtension extension, Logger logger) {
		this.extension = extension;
		this.logger = logger;
	}

	public void startQualityReport() {
        String baseUrl = extension.getBaseUrl();
        logger.info(String.format("using baseUrl '%s'", baseUrl));

        TestObjectClient client = TestObjectClient.Factory.create(baseUrl, getProxySettings());

        String username = extension.getUsername();
        String password = extension.getPassword();
        String appId = extension.getApp();
		String teamId = extension.getTeam() != null && extension.getTeam().isEmpty() == false ? extension.getTeam() : username;

		login(client, username, password);

//		createAppVersion(appApk, client, teamId, appId);

		long qualityReportId = client.startQualityReport(teamId, appId);

		logger.info(String.format("Started Quality Report: %s", qualityReportId));
	}

	private void login(TestObjectClient client, String user, String password) {
		try {
			client.login(user, password);
			logger.info(String.format("user %s successfully logged in", user));
		} catch (Exception e) {
			throw new GradleScriptException(String.format("unable to login user %s", user), e);
		}
	}

//	private void createAppVersion(File appApk, TestObjectClient client, String teamId, String appId) {
//		try {
//			client.createAppVersion(teamId, appId, appApk);
//			logger.info(String.format("Uploaded appAPK: %s, created new app version", appApk.getAbsolutePath()));
//
//		} catch (Exception e) {
//			throw new GradleScriptException(String.format("unable to create app version"), e);
//		}
//	}

	private static TestObjectClient.ProxySettings getProxySettings() {
		String proxyHost = System.getProperty("http.proxyHost");
		String proxyPort = System.getProperty("http.proxyPort");
		String proxyUser = System.getProperty("http.proxyUser");
		String proxyPassword = System.getProperty("http.proxyPassword");

		return proxyHost != null ? new TestObjectClient.ProxySettings(proxyHost, Integer.parseInt(proxyPort), proxyUser, proxyPassword)
				: null;
	}

}
