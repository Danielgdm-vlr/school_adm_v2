package com.gdm.school_adm_v2.teacher;

import com.gdm.school_adm_v2.teacher_personal_details.TeacherPersonalDetails;

import java.time.LocalDate;
import java.util.List;

public class TeacherConfig {

    public static void createAndSaveTeachers(TeacherService teacherService){

        teacherService.saveOrUpdateAll(List.of(
                new Teacher(
                        true,
                        "6010926131083",
                        new TeacherPersonalDetails(
                                    true,
                                    "Constantin",
                                    "Goran",
                                    LocalDate.of(1972, 2, 15))

                ),
                new Teacher(
                        true,
                        "1971118228631",
                        new TeacherPersonalDetails(
                                true,
                                "Titu",
                                "Mastan",
                                LocalDate.of(1972, 2, 15))
                ),
                new Teacher(
                        true,
                        "1970313118016",
                        new TeacherPersonalDetails(
                                true,
                                "Lucia",
                                "Tudor",
                                LocalDate.of(1972, 2, 15))
            ),
                new Teacher(
                        true,
                        "2891004371082",
                        new TeacherPersonalDetails(
                                true,
                                "Carmen",
                                "Andrei",
                                LocalDate.of(1972, 2, 15))
                ),
                new Teacher(
                        true,
                        "6020430139802",
                        new TeacherPersonalDetails(
                                true,
                                "Marian",
                                "Ionescu",
                                LocalDate.of(1972, 2, 15))
                ),
                new Teacher(
                        true,
                        "1930308426115",
                        new TeacherPersonalDetails(
                                true,
                                "Maria",
                                "Danu",
                                LocalDate.of(1972, 2, 15))
                ),
                new Teacher(
                        true,
                        "1920924351408",
                        new TeacherPersonalDetails(
                                true,
                                "Ramona",
                                "Ancuta",
                                LocalDate.of(1972, 2, 15))
                ),
                new Teacher(
                        true,
                        "2881110262306",
                        new TeacherPersonalDetails(
                                true,
                                "Mariana",
                                "Bulbuc",
                                LocalDate.of(1972, 2, 15))
                ),
                new Teacher(
                        true,
                        "2940216306251",
                        new TeacherPersonalDetails(
                                true,
                                "Silvia",
                                "Cabas",
                                LocalDate.of(1972, 2, 15))
                ),
                new Teacher(
                        true,
                        "6031227367151",
                        new TeacherPersonalDetails(
                                true,
                                "Liliana",
                                "Comarnic",
                                LocalDate.of(1972, 2, 15))
                ),
                new Teacher(
                        true,
                        "2980419035600",
                        new TeacherPersonalDetails(
                                true,
                                "Ciprian",
                                "Ghise",
                                LocalDate.of(1972, 2, 15))
                ),
                new Teacher(
                        true,
                        "1970727177491",
                        new TeacherPersonalDetails(
                                true,
                                "Manuela",
                                "Zorca",
                                LocalDate.of(1972, 2, 15))
                ),
                new Teacher(
                        true,
                        "1910317257466",
                        new TeacherPersonalDetails(
                                true,
                                "Carmen",
                                "Stoica",
                                LocalDate.of(1972, 2, 15))
                ),
                new Teacher(
                        true,
                        "2880825266382",
                        new TeacherPersonalDetails(
                                true,
                                "Alexandra",
                                "Trandabur",
                                LocalDate.of(1972, 2, 15))
                ),
                new Teacher(
                        true,
                        "5020422138884",
                        new TeacherPersonalDetails(
                                true,
                                "Aurelia",
                                "Negus",
                                LocalDate.of(1972, 2, 15))
                ),
                new Teacher(
                        true,
                        "1900916468801",
                        new TeacherPersonalDetails(
                                true,
                                "Mihaela",
                                "Tudose",
                                LocalDate.of(1972, 2, 15))
                ),
                new Teacher(
                        true,
                        "1870608368946",
                        new TeacherPersonalDetails(
                                true,
                                "Stenuta",
                                "Gall",
                                LocalDate.of(1972, 2, 15))
                ),
                new Teacher(
                        true,
                        "1870420467473",
                        new TeacherPersonalDetails(
                                true,
                                "Carmen",
                                "Manate",
                                LocalDate.of(1972, 2, 15))
                ),
                new Teacher(
                        true,
                        "6020705177793",
                        new TeacherPersonalDetails(
                                true,
                                "Mihaela",
                                "Oltei",
                                LocalDate.of(1972, 2, 15))
                ),
                new Teacher(
                        true,
                        "6040303157373",
                        new TeacherPersonalDetails(
                                true,
                                "Valentin",
                                "Oceanu",
                                LocalDate.of(1972, 2, 15))
                ),
                new Teacher(
                        true,
                        "6010408129968",
                        new TeacherPersonalDetails(
                                true,
                                "Marinela",
                                "Rekkert",
                                LocalDate.of(1972, 2, 15))
                ),
                new Teacher(
                        true,
                        "1900209348199",
                        new TeacherPersonalDetails(
                                true,
                                "Delia",
                                "Minea",
                                LocalDate.of(1972, 2, 15))
                ),
                new Teacher(
                        true,
                        "1940101352117",
                        new TeacherPersonalDetails(
                                true,
                                "Mihaela",
                                "Naghi",
                                LocalDate.of(1972, 2, 15))
                ),
                new Teacher(
                        true,
                        "",
                        new TeacherPersonalDetails(
                                true,
                                "Adina",
                                "Sasu",
                                LocalDate.of(1972, 2, 15))
                ),
                new Teacher(
                        true,
                        "1900602157811",
                        new TeacherPersonalDetails(
                                true,
                                "Cristina",
                                "Dinu",
                                LocalDate.of(1972, 2, 15))
                )
        ));
}
}
