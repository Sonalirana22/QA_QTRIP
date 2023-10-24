package qtriptest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DP {
    @DataProvider(name="DatasetsforQTrip")
    public Object [][] DatasetsforQTrip(Method m) throws IOException{
        try {
            File flObj = new File("/home/crio-user/workspace/ranasonali143-ME_QTRIP_QA_V2/app/src/test/resources/DatasetsforQTrip.xlsx");
            FileInputStream fileInputObj = new FileInputStream(flObj);
            try (XSSFWorkbook wbObj = new XSSFWorkbook(fileInputObj)) {
                switch (m.getName()) {
   
                  case "TestCase01":
                  XSSFSheet sheet1 = wbObj.getSheet("TestCase01");
                  int TC1_rowCount = sheet1.getPhysicalNumberOfRows();
                  int TC1_colCount = sheet1.getRow(0).getLastCellNum();
                  
                  Object[][] testData1 = new Object[TC1_rowCount-1][TC1_colCount-1];
                  
                  for (int i = 1; i < TC1_rowCount; i++) {
                    for(int j = 1; j < TC1_colCount; j++ ){
                      String value = sheet1.getRow(i).getCell(j).toString();
                      testData1[i-1][j-1] = value;
                    }
                  }

                  return testData1;

      }
            }
    }catch(Exception e)
            {
            e.printStackTrace();
          }
        return null;
        }
}
