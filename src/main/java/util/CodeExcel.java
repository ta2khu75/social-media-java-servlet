package util;
import jakarta.servlet.ServletException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import models.Video;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import util.Jpa;
public class CodeExcel{
	public static void excelToDatabase(String pathFile) throws ServletException, IOException {
		String excelFilePath = pathFile;
//đường dẫn này ô thay đổi theo đường dẫn trong file excel của ô
				
        try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
			
            for (Row row : sheet) {
				if (row.getRowNum() == 0) {
					continue;
				}
                Video video = new Video();
				video.setDescription(row.getCell(0).getStringCellValue());
			//	video.setId(Integer.parseInt(row.getCell(1).getStringCellValue()));
                video.setTitle(row.getCell(2).getStringCellValue());
                video.setVideo(row.getCell(3).getStringCellValue());
				video.setViews(Integer.parseInt(row.getCell(4).getStringCellValue()));
                Jpa.presist(video);
            }
            System.out.println("Data imported successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}