package com.ibm.api.beans;

public class AssetsOverview {
	
	public String getCodeCoverage() {
		return codeCoverage;
	}
	public void setCodeCoverage(String codeCoverage) {
		this.codeCoverage = codeCoverage;
	}
	public String getCodeReview() {
		return codeReview;
	}
	public void setCodeReview(String codeReview) {
		this.codeReview = codeReview;
	}
	public String getLoggingFramework() {
		return loggingFramework;
	}
	public void setLoggingFramework(String loggingFramework) {
		this.loggingFramework = loggingFramework;
	}
	public String getEsqlGenerator() {
		return esqlGenerator;
	}
	public void setEsqlGenerator(String esqlGenerator) {
		this.esqlGenerator = esqlGenerator;
	}

	private String codeCoverage;
	private String codeReview;
	private String loggingFramework;
	private String esqlGenerator;
	
	
}
