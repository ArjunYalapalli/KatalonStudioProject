package common

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.*

import com.kms.katalon.core.util.KeywordUtil

class FileHandlers {

    // ==========================
    // 🔹 GET WORKBOOK
    // ==========================
    static XSSFWorkbook getWorkbook(String path) {
        FileInputStream fis = new FileInputStream(new File(path))
        return new XSSFWorkbook(fis)
    }

    // ==========================
    // 🔹 READ CELL DATA
    // ==========================
    static String getCellData(String path, String sheetName, int rowNum, int colNum) {
        try {
            XSSFWorkbook workbook = getWorkbook(path)
            XSSFSheet sheet = workbook.getSheet(sheetName)

            Row row = sheet.getRow(rowNum)
            Cell cell = row.getCell(colNum)

            DataFormatter formatter = new DataFormatter()
            String value = formatter.formatCellValue(cell)

            workbook.close()
            return value

        } catch (Exception e) {
            KeywordUtil.markFailed("Error reading Excel: " + e.getMessage())
            return null
        }
    }

    // ==========================
    // 🔹 WRITE CELL DATA
    // ==========================
    static void setCellData(String path, String sheetName, int rowNum, int colNum, String value) {
        try {
            File file = new File(path)
            XSSFWorkbook workbook

            if (file.exists()) {
                workbook = new XSSFWorkbook(new FileInputStream(file))
            } else {
                workbook = new XSSFWorkbook()
            }

            XSSFSheet sheet = workbook.getSheet(sheetName)
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName)
            }

            Row row = sheet.getRow(rowNum)
            if (row == null) {
                row = sheet.createRow(rowNum)
            }

            Cell cell = row.getCell(colNum)
            if (cell == null) {
                cell = row.createCell(colNum)
            }

            cell.setCellValue(value)

            FileOutputStream fos = new FileOutputStream(path)
            workbook.write(fos)

            fos.close()
            workbook.close()

            KeywordUtil.logInfo("Data written to Excel")

        } catch (Exception e) {
            KeywordUtil.markFailed("Error writing Excel: " + e.getMessage())
        }
    }

    // ==========================
    // 🔹 GET ROW COUNT
    // ==========================
    static int getRowCount(String path, String sheetName) {
        XSSFWorkbook workbook = getWorkbook(path)
        XSSFSheet sheet = workbook.getSheet(sheetName)

        int rowCount = sheet.getLastRowNum()

        workbook.close()
        return rowCount
    }

    // ==========================
    // 🔹 GET COLUMN COUNT
    // ==========================
    static int getColumnCount(String path, String sheetName, int rowNum) {
        XSSFWorkbook workbook = getWorkbook(path)
        XSSFSheet sheet = workbook.getSheet(sheetName)

        Row row = sheet.getRow(rowNum)
        int colCount = row.getLastCellNum()

        workbook.close()
        return colCount
    }

    // ==========================
    // 🔹 READ FULL DATA (2D LIST)
    // ==========================
    static List<List<String>> getSheetData(String path, String sheetName) {

        List<List<String>> data = []

        XSSFWorkbook workbook = getWorkbook(path)
        XSSFSheet sheet = workbook.getSheet(sheetName)

        DataFormatter formatter = new DataFormatter()

        for (Row row : sheet) {
            List<String> rowData = []
            for (Cell cell : row) {
                rowData.add(formatter.formatCellValue(cell))
            }
            data.add(rowData)
        }

        workbook.close()
        return data
    }
}