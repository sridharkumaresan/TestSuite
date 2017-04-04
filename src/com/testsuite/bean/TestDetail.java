package com.testsuite.bean;

public class TestDetail {
	private int testID;
	private String testDescription;
	private String testType;
	private String testPriority;
	private String datekey;
	private int platformkey;
	private int keyID;
	private String expected;
	private String actual;
	private String testOutcome;
	private int recCount;
	
	public TestDetail(){
		
	}

	public TestDetail(int testID, String testDescription, String testType, String testPriority, String datekey,
			int platformkey, int keyID, String expected, String actual, String testOutcome, int recCount) {
		super();
		this.testID = testID;
		this.testDescription = testDescription;
		this.testType = testType;
		this.testPriority = testPriority;
		this.datekey = datekey;
		this.platformkey = platformkey;
		this.keyID = keyID;
		this.expected = expected;
		this.actual = actual;
		this.testOutcome = testOutcome;
		this.recCount = recCount;
	}

	public int getTestID() {
		return testID;
	}

	public void setTestID(int testID) {
		this.testID = testID;
	}

	public String getTestDescription() {
		return testDescription;
	}

	public void setTestDescription(String testDescription) {
		this.testDescription = testDescription;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public String getTestPriority() {
		return testPriority;
	}

	public void setTestPriority(String testPriority) {
		this.testPriority = testPriority;
	}

	public String getDatekey() {
		return datekey;
	}

	public void setDatekey(String datekey) {
		this.datekey = datekey;
	}

	public int getPlatformkey() {
		return platformkey;
	}

	public void setPlatformkey(int platformkey) {
		this.platformkey = platformkey;
	}

	public int getKeyID() {
		return keyID;
	}

	public void setKeyID(int keyID) {
		this.keyID = keyID;
	}

	public String getExpected() {
		return expected;
	}

	public void setExpected(String expected) {
		this.expected = expected;
	}

	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}

	public String getTestOutcome() {
		return testOutcome;
	}

	public void setTestOutcome(String testOutcome) {
		this.testOutcome = testOutcome;
	}

	public int getRecCount() {
		return recCount;
	}

	public void setRecCount(int recCount) {
		this.recCount = recCount;
	}
	
	
}
