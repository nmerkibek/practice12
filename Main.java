import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/Student.xlsx";

        List<Student> students = readStudentsFromExcel(filePath);

        for (Student student : students) {
            System.out.printf("Имя: %s, Текущая стипендия: %.2f, Новая стипендия: %.2f, Увеличение: %.2f%n",
                    student.getName(),
                    student.getCurrentScholarship(),
                    student.getNewScholarship(),
                    student.getScholarshipIncrease());
        }
    }

    public static List<Student> readStudentsFromExcel(String filePath) {
        List<Student> students = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                String name = row.getCell(0).getStringCellValue();
                double currentScholarship = row.getCell(1).getNumericCellValue();
                double newScholarship = row.getCell(2).getNumericCellValue();

                students.add(new Student(name, currentScholarship, newScholarship));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return students;
    }
}

class Student{
    private String name;
    private double currentScholarship;
    private double newScholarship;


    public Student(String name,double currentScholarship, double newScholarship){
        this.name = name;
        this.currentScholarship = currentScholarship;
        this.newScholarship = newScholarship;
    }


    public String getName(){
        return name;
    }

    public double getCurrentScholarship(){
        return currentScholarship;
    }

    public double getNewScholarship(){
        return newScholarship;
    }

    public double getScholarshipIncrease(){
        return newScholarship - currentScholarship;

    }


}