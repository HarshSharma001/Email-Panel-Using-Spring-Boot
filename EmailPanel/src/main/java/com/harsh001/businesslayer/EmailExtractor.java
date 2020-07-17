package com.harsh001.businesslayer;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;
@Component
public class EmailExtractor 
{
    private String filepath, email=null;
    private int i = 0;

    private ArrayList<String> emails = new ArrayList<String>();

    

    public void setFilepath(String filepath) {
		this.filepath = filepath;
	}



	public ArrayList<String> extractMail_IDs() 
    {
        try {
            Workbook wb = WorkbookFactory.create(new FileInputStream(filepath));
            Sheet sheet = wb.getSheetAt(0);

            for (int j = 0; j < sheet.getLastRowNum() + 1; j++) {
                Row row = sheet.getRow(j);

                while (true) {

                    Cell cell = row.getCell(i); //get third cell
                    email = cell.toString();

                    if (email.contains("@gmail.com")) 
                    {
                        i = cell.getColumnIndex();
                        emails.add(email);
                        break;
                    } 
                    else if (!email.contains("@gmail.com") && i == row.getLastCellNum() - 1) 
                    {
                        email = null;
                        break;
                    } 
                    else 
                    {
                        i = i + 1;
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emails;
    }
}
