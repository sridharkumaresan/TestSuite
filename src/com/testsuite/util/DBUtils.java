package com.testsuite.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.testsuite.bean.TestDetail;
public class DBUtils {
	  public static List<TestDetail> queryTestSummary(Connection conn) throws Exception {
//	      String sql = "SELECT * FROM TESTDETAILRESULTS ";
		  String sql = 
			" Select "
		  		+ "Testid" 
		  		+",TestDescription"
		  		+",TestType"
		  		+",TestPriority"
		  		+",DATE_FORMAT(Datekey, '%d %b %y') as DateKey"	
		  		+",platformkey"
		  		+",Testoutcome"
		  		+",Count(RowID) AS Records" 
		  	+" FROM " 
		  		+" TestDetailResults "
		  	+" WHERE " 
		  		+" TestType = 'Value Check' "
		  	+" group  by " 
		  		+" Testid, TestDescription,Datekey,TestType,TestPriority,platformkey,Testoutcome "
		  	+" UNION ALL" 
		  	+" Select"  
			  	+" Testid" 
			  	+", TestDescription"
			  	+", TestType"
			  	+", TestPriority"
			  	+", DATE_FORMAT(Datekey, '%d %b %y') as DateKey"
			  	+", platformkey"
			  	+", Testoutcome"
			  	+", ABS(Actual) AS Records"
		  	+" FROM "	 
		  		+" TestDetailResults "
		  	+" WHERE " 
		  		+" TestType = 'CountCheck' "
		  	+" Order by " 
		  		+" Testid";
	      
	      PreparedStatement pstm = conn.prepareStatement(sql);
	 
	      ResultSet rs = pstm.executeQuery();
	      List<TestDetail> list = new ArrayList<TestDetail>();
	      while (rs.next()) {
	    	  
			int testID = rs.getInt("TestID");
			String testDescription = rs.getString("TestDescription");
			String testType = rs.getString("TestType");
			String testPriority = rs.getString("TestPriority");
			String datekey = rs.getString("DateKey");
			int platformkey = rs.getInt("PlatformKey");
//			int keyID = rs.getInt("KeyID");
//			String expected = rs.getString("Expected");
//			String actual = rs.getString("Actual");
			String testOutcome = rs.getString("TestOutcome");
			int recCount = rs.getInt("Records");
			TestDetail testDetail = new TestDetail();
			testDetail.setTestID(testID);
			testDetail.setTestDescription(testDescription);
			testDetail.setTestType(testType);
			testDetail.setTestPriority(testPriority);
			testDetail.setDatekey(datekey);
			testDetail.setPlatformkey(platformkey);
//			testDetail.setKeyID(keyID);
//			testDetail.setExpected(expected);
//			testDetail.setActual(actual);
			testDetail.setTestOutcome(testOutcome);
			testDetail.setRecCount(recCount);
	        
			list.add(testDetail);
	      }
	      return list;
	  }
	  
	  public static List<TestDetail> queryTestSummarySP(Connection conn) throws Exception {
		  List<TestDetail> list = null;
		  try {
			  
			  CallableStatement statement = conn.prepareCall("{call get_test_summary()}");
			  boolean hadResults = statement.execute();
			  while(hadResults){
				  ResultSet rs = statement.getResultSet();
				  list = new ArrayList<TestDetail>();
			      while (rs.next()) {
			    	  
					int testID = rs.getInt("TestID");
					String testDescription = rs.getString("TestDescription");
					String testType = rs.getString("TestType");
					String testPriority = rs.getString("TestPriority");
					String datekey = rs.getString("DateKey");
					int platformkey = rs.getInt("PlatformKey");
					String testOutcome = rs.getString("TestOutcome");
					int recCount = rs.getInt("Records");
					TestDetail testDetail = new TestDetail();
					testDetail.setTestID(testID);
					testDetail.setTestDescription(testDescription);
					testDetail.setTestType(testType);
					testDetail.setTestPriority(testPriority);
					testDetail.setDatekey(datekey);
					testDetail.setPlatformkey(platformkey);
					testDetail.setTestOutcome(testOutcome);
					testDetail.setRecCount(recCount);
			        
					list.add(testDetail);
			      }			
			      hadResults = statement.getMoreResults();
			  }
			  statement.close();
		  } 
		  catch (SQLException e) {
				e.printStackTrace();
		  }
	      return list;
	  }
	  
	  public static List<TestDetail> queryTestDetails(Connection conn) throws Exception {
		  String sql = 
				  		"SELECT "
				  		+ 	"T.Testid, T.TestDescription,DATE_FORMAT(T.Datekey, '%d %b %y') as DateKey,T.Platformkey,TD.Actual, TD.Expected,T.TestOutcome "
				  		+ " FROM "
				  		+ " ( "
				  		+ " Select MIN(RowID) AS Rowid,Testid, TestDescription,Datekey,Platformkey,TestOutcome "
				  		+ " FROM TestDetailResults "
				  		+ " where TestOutcome = 'Fail' "
				  		+ " GROUP BY Testid, TestDescription,Datekey,Platformkey,TestOutcome "
				  		+ " UNION ALL "
				  		+ " Select MIN(RowID) AS RowID,Testid, TestDescription,Datekey,Platformkey,TestOutcome "
				  		+ " FROM TestDetailResults "
				  		+ " where TestOutcome = 'Pass' "
				  		+ " GROUP BY Testid, TestDescription,Datekey,Platformkey,TestOutcome) T "
				  		+ " INNER JOIN TestDetailResults TD ON TD.RowID = T.Rowid "
				  		+ " ORDER BY T.TestID";
	      
	      PreparedStatement pstm = conn.prepareStatement(sql);
	 
	      ResultSet rs = pstm.executeQuery();
	      List<TestDetail> list = new ArrayList<TestDetail>();
	      while (rs.next()) {
	    	  
			int testID = rs.getInt("TestID");
			String testDescription = rs.getString("TestDescription");
			String datekey = rs.getString("DateKey");
			int platformkey = rs.getInt("PlatformKey");
			String expected = rs.getString("Expected");
			String actual = rs.getString("Actual");
			String testOutcome = rs.getString("TestOutcome");
			
			TestDetail testDetail = new TestDetail();
			testDetail.setTestID(testID);
			testDetail.setTestDescription(testDescription);
			testDetail.setDatekey(datekey);
			testDetail.setPlatformkey(platformkey);
			testDetail.setExpected(expected);
			testDetail.setActual(actual);
			testDetail.setTestOutcome(testOutcome);
			
	        
			list.add(testDetail);
	      }
	      return list;
	  }
	  
	  public static List<TestDetail> queryTestDetailsSP(Connection conn) throws Exception {
		  List<TestDetail> list = null;
		  try {
			  CallableStatement statement = conn.prepareCall("{call get_test_detail()}");
			  boolean hadResults = statement.execute();
			  while(hadResults){
				  ResultSet rs = statement.getResultSet();
				  list = new ArrayList<TestDetail>();
			      while (rs.next()) {
					int testID = rs.getInt("TestID");
					String testDescription = rs.getString("TestDescription");
					String datekey = rs.getString("DateKey");
					int platformkey = rs.getInt("PlatformKey");
					String expected = rs.getString("Expected");
					String actual = rs.getString("Actual");
					String testOutcome = rs.getString("TestOutcome");
					
					TestDetail testDetail = new TestDetail();
					testDetail.setTestID(testID);
					testDetail.setTestDescription(testDescription);
					testDetail.setDatekey(datekey);
					testDetail.setPlatformkey(platformkey);
					testDetail.setExpected(expected);
					testDetail.setActual(actual);
					testDetail.setTestOutcome(testOutcome);
					list.add(testDetail);
			      }
			      hadResults = statement.getMoreResults();
			  }
			  statement.close();
		  } catch (SQLException e) {
			e.printStackTrace();
		  }	      
	      return list;
	  }
}
