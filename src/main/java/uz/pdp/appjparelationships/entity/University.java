package uz.pdp.appjparelationships.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToOne(optional = false)
    private Address address;
}
//task 1
//      Spring project orqali CRUD (Create, Read, Update, Delete) prinsiplari asosida
//      maktab strukturasini quring, bunda School,
//      Student, Teacher, Subject, Mark, Classes, TimeTable ma'lumotlari bo'lsin.

//task_2
//Spring project orqali CRUD (Create, Read, Update,  Delete) prinsiplari asosida "map" ni ifodalovchi ma'lumotlar omborini quring,
//        bunda Continent (qit'a) , Country (davlat), Region (mintaqa), District (tuman),
//        Address (ko'cha, uy raqami) malumotlarini saqlovchi classlarni  tuzing.

//task_3
//Spring project orqali CRUD (Create, Read, Update, Delete) prinsiplari asosida University
//    malumotlarini saqlovchi struktura
//quring, bunda University, Faculty, Classes, Student, Teacher, Subject ma'lumotlari bo'lsin.
//        2-topshiriqdagi Address bilan University ni bog'lang.