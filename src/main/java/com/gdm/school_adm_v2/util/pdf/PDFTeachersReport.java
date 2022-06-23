package com.gdm.school_adm_v2.util.pdf;

import com.gdm.school_adm_v2.course_hours.CourseHoursDTO;
import com.gdm.school_adm_v2.school.School;
import com.gdm.school_adm_v2.teacher.TeacherDTO;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PDFTeachersReport {

    private School school;
    private List<TeacherDTO> teacherDTOs;

    public void createPDF(){

                try(PdfWriter pdfWriter = new PdfWriter(String.format(
                        "D:\\Workspace\\Licenta\\pdfs\\Rapoarte\\Materii profesori\\%1$s - %2$s.pdf",
                        school.getSchoolDetails().getName() + "_" + school.getId(),
                        LocalDate.now()
                ))) {

                    Document document = new Document(new PdfDocument(pdfWriter));

                    addPDFTitle(document);
                    addSchoolDetails(document);

                    addPDFTeachersTable(document, new float[]{33, 34, 33});
                    addTeacherCoursesList(document, 12);

                    document.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
    }

    private void addPDFTitle(Document document) {

        Text text = new Text(String.format(
                "Raport despre materiile profesorilor\n - %s -", LocalDate.now()))
                .setTextAlignment(TextAlignment.CENTER);

        Paragraph paragraph = new Paragraph(text);
        paragraph.setHorizontalAlignment(HorizontalAlignment.CENTER);
        paragraph.setFontSize(24);

        document.add(paragraph);
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
    }

    private void addSchoolDetails(Document document) {

        Text schoolName = new Text(String.format(
                "Scoala: %s\n", school.getSchoolDetails().getName()));
        Paragraph schoolNameParagraph = new Paragraph(schoolName);

        Text schoolTelephoneNumber = new Text(String.format(
                "Numar de telefon: %s\n", school.getSchoolDetails().getTelephoneNumber()));
        Paragraph schoolTelephoneNumberParagraph = new Paragraph(schoolTelephoneNumber);

        Text schoolEmailAddress = new Text(String.format(
                "Adresa de email: %s\n", school.getSchoolDetails().getEmailAddress()));
        Paragraph schoolEmailAddressParagraph = new Paragraph(schoolEmailAddress);

        Text schoolAddress = new Text(String.format(
                "Adresa: %1$s %2$s, %3$s, %4$s, %5$s\n",
                school.getAddress().getStreet(),
                school.getAddress().getNumber(),
                school.getAddress().getZipCode(),
                school.getAddress().getCity().getNameWithoutDiacritics(),
                school.getAddress().getCity().getCounty().getNameWithoutDiacritics()
        ));
        Paragraph schoolAddressParagraph = new Paragraph(schoolAddress);

        List<Paragraph> paragraphs = List.of(schoolNameParagraph, schoolTelephoneNumberParagraph,
                schoolEmailAddressParagraph, schoolAddressParagraph);
        paragraphs.forEach(paragraph -> {
            paragraph.setHorizontalAlignment(HorizontalAlignment.LEFT);
            paragraph.setFontSize(12);
            document.add(paragraph);
        });

        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
    }

    private void addPDFTeachersTable(Document document, float[] colWidthsTableProducts) {

        Table tableProducts =
                new Table(UnitValue.createPercentArray(colWidthsTableProducts));

        tableProducts.setWidth(UnitValue.createPercentValue(85));
        tableProducts.setHorizontalAlignment(HorizontalAlignment.CENTER);
        addTeachersTableHeader(tableProducts, 16, TextAlignment.CENTER);
        teacherDTOs.forEach(teacherDTO -> {
            addCell(tableProducts, teacherDTO.getFirstName(), 12, TextAlignment.CENTER);
            addCell(tableProducts, teacherDTO.getLastName(), 12, TextAlignment.CENTER);
            addCell(tableProducts, teacherDTO.getCnp(), 12, TextAlignment.CENTER);
        });

        document.add(tableProducts);
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
    }

    private void addTeachersTableHeader(Table table, float fontSize, TextAlignment textAlignment){

        addCell(table,
                "Nume profesor",
                fontSize,
                textAlignment
        );

        addCell(table,
                "Prenume profesor",
                fontSize,
                textAlignment
        );

        addCell(table,
                "CNP",
                fontSize,
                textAlignment
        );
    }

    private void addTeacherCoursesList(Document document, float fontSize){

        Text text = new Text("Lista ore pentru fiecare profesor: ");
        Paragraph paragraph = new Paragraph(text);
        paragraph.setFontSize(fontSize);

        document.add(paragraph);
        document.add(new Paragraph("\n"));

        AtomicReference<List<CourseHoursDTO>> courseHoursDTOS = new AtomicReference<>();
        teacherDTOs.forEach(teacherDTO -> {
            AtomicInteger totalHours = new AtomicInteger();
            teacherDTO.getCourseHoursDTOs().forEach(courseHoursDTO ->
                    totalHours.addAndGet(courseHoursDTO.getNumberOfCourseHours()));

            Text tempText = new Text(String.format(" - %1$s %2$s: Ore in total %3$s, din care",
                    teacherDTO.getFirstName(), teacherDTO.getLastName(), totalHours.get()));
            Paragraph tempParagraph = new Paragraph(tempText);
            tempParagraph.setFontSize(fontSize);
            document.add(tempParagraph);

            courseHoursDTOS.set(teacherDTO.getCourseHoursDTOs());
            courseHoursDTOS.get().forEach(courseHoursDTO -> {
                Text textCourse = new Text(String.format(
                        "       Materie: %1$s, numar de ore: %2$s",
                        courseHoursDTO.getCourseName(),
                        courseHoursDTO.getNumberOfCourseHours()
                ));
                Paragraph paragraphCourse = new Paragraph(textCourse);
                paragraphCourse.setFontSize(9);
                document.add(paragraphCourse);
            });
        });

        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
    }

    private void addCell(Table table, String text, float fontSize, TextAlignment textAlignment){

        Cell cell = new Cell(1, 1);
        cell.add(new Paragraph(text));
        cell.setFontSize(fontSize);
        cell.setTextAlignment(textAlignment);

        table.addCell(cell);
    }
}
