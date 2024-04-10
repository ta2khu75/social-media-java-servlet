/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.Iterator;
import org.apache.poi.EncryptedDocumentException;

/**
 *
 * @author ta2khu75
 */
public class Excel {

	public static List<Method> getAllGetters(Method[] methods) {
		List<Method> getters=new ArrayList<>();

		// Lọc ra các phương thức getter
		for (Method method : methods) {
			// Kiểm tra nếu là phương thức getter
			if (isGetter(method)) {
				getters.add(method);
			}
		}

		return getters;
	}

	public static boolean isGetter(Method method) {
		// Kiểm tra tên phương thức bắt đầu bằng "get" hoặc "is", không có tham số, và không phải là static
		return (method.getName().startsWith("get") || method.getName().startsWith("is"))
                && method.getParameterCount() == 0
				&& !List.class.equals(method.getReturnType())
                && !void.class.equals(method.getReturnType()) // Không trả về void
                && !void.class.equals(method.getDeclaringClass());
	}
	public static boolean isListType(Class<?> type) {
        return List.class.isAssignableFrom(type);
    }
//	public static void main(String[] args) {
//		System.out.println("hahaha");
//	}
	public static <T> void importFromExcel(T object, String pathfile) {
        try (FileInputStream fis = new FileInputStream(pathfile)) {
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet

            // Read the title row and convert column titles to uppercase
            Row titleRow = sheet.getRow(0);
            String[] columnTitles = new String[titleRow.getLastCellNum()];
            for (int i = 0; i < titleRow.getLastCellNum(); i++) {
                columnTitles[i] = titleRow.getCell(i).getStringCellValue().toUpperCase();
            }

            // Iterate through data rows and map to Person objects
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // Skip the title row
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                for (int i = 0; i < columnTitles.length; i++) {
                    String columnTitle = columnTitles[i];
                    Cell cell = row.getCell(i);
                    setFieldValue(object, columnTitle, cell.getStringCellValue());
                }
                // Do something with the Person object
                System.out.println(object);
            }
        } catch (IOException | EncryptedDocumentException  e) {
            e.printStackTrace();
        }
    }

    // Set the value of a field in the object using reflection
    private static <T> void setFieldValue(T person, String fieldName, String value) {
        try {
            person.getClass().getDeclaredField(fieldName).set(person, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

	public static <T> void exportToExcel(List<T> list, String filePath) throws IOException {
		List<Method> getters = new ArrayList<>();
		List<Field> fields = new ArrayList<>();
		if (!list.isEmpty()) {
			fields = new ArrayList(List.of(list.get(0).getClass().getDeclaredFields()).stream().filter((t) -> !isListType(t.getType())).toList());
			getters = getAllGetters(list.get(0).getClass().getDeclaredMethods());
			// sap xep de xuat excel cho khop
			getters.sort(Comparator.comparing(Method::getName));
			fields.sort(Comparator.comparing(Field::getName));
		}
		// Sắp xếp danh sách các phương thức getter và các field theo tên
		
		try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet("DataSheet");

			// Tạo hàng tiêu đề
			Row headerRow = sheet.createRow(0);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			

			for (int i = 0; i < fields.size(); i++) {
				Cell headerCell1 = headerRow.createCell(i);
				headerCell1.setCellValue(fields.get(i).getName().toString().toUpperCase());
				headerCell1.setCellStyle(headerCellStyle);
			}
			for (int i = 0; i < list.size(); i++) {
				Row dataRow = sheet.createRow(i + 1);
				T item = list.get(i);
				int cellNum = 0;
				for (Method method : getters) {
					try {
						Object value = method.invoke(item);
						// Ghi dữ liệu vào ô trong hàng
						Cell cell = dataRow.createCell(cellNum++);
						cell.setCellValue(value != null ? value.toString() : "");
					} catch (Exception e) {
						e.printStackTrace();
						throw new Exception(e);
					}
				}
			}
			// Tự động điều chỉnh cột
			for (int i = 0; i < list.size(); i++) {
				sheet.autoSizeColumn(i);
			}

			// Ghi vào tệp
			try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
				workbook.write(fileOut);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	}
}
