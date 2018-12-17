package com.pmo.api.beans;

public class AssetsOverview {
	
	public String getCodeCoverage() {
		return codeCoverage;
	}
	public void setCodeCoverage(String codeCoverage) {
		this.codeCoverage = codeCoverage;
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
	

	public String getDataDrivenTesting() {
		return dataDrivenTesting;
	}
	public void setDataDrivenTesting(String dataDrivenTesting) {
		this.dataDrivenTesting = dataDrivenTesting;
	}


	private String codeCoverage;
	private String dataDrivenTesting;
	private String loggingFramework;
	private String esqlGenerator;
	
	
}
