DELIMITER // 
CREATE PROCEDURE get_test_summary()
BEGIN
	Select 
		Testid 
		,TestDescription
		,TestType
		,TestPriority
		,DATE_FORMAT(Datekey, '%d %b %y') as DateKey	
		,platformkey
		,Testoutcome
		,Count(RowID) AS Records 
	FROM  
		TestDetailResults 
	WHERE  
		TestType = 'Value Check' 
	group  by  
		Testid, TestDescription,Datekey,TestType,TestPriority,platformkey,Testoutcome 
	UNION ALL 
		Select  
			Testid 
			, TestDescription
			, TestType
			, TestPriority
			, DATE_FORMAT(Datekey, '%d %b %y') as DateKey
			, platformkey
			, Testoutcome
			, ABS(Actual) AS Records
		FROM 	 
			TestDetailResults 
		WHERE  
			TestType = 'CountCheck' 
		Order by  
			Testid;
END //
DELIMITER ; 	


DELIMITER // 
CREATE PROCEDURE get_test_detail()
BEGIN
SELECT 
	T.Testid, T.TestDescription,DATE_FORMAT(T.Datekey, '%d %b %y') as DateKey,T.Platformkey,TD.Actual, TD.Expected,T.TestOutcome 
FROM 
	( 
	Select 
		MIN(RowID) AS Rowid,Testid, TestDescription,Datekey,Platformkey,TestOutcome 
	FROM 
		TestDetailResults 
	where 
		TestOutcome = 'Fail' 
	GROUP BY 
		Testid, TestDescription,Datekey,Platformkey,TestOutcome 
	UNION ALL 
	Select 
		MIN(RowID) AS RowID,Testid, TestDescription,Datekey,Platformkey,TestOutcome 
	FROM 
		TestDetailResults 
	where 
		TestOutcome = 'Pass' 
	GROUP BY 
		Testid, TestDescription,Datekey,Platformkey,TestOutcome) T 
	INNER JOIN 
		TestDetailResults TD ON TD.RowID = T.Rowid 
	ORDER BY 
		T.TestID;
END // 
DELIMITER ; 	