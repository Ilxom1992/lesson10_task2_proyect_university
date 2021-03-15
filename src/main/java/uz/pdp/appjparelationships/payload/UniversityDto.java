package uz.pdp.appjparelationships.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniversityDto {//MALUMOTLARNI TASHISH UCHUN HIZMAT QILADI
private  String name;
private  String city;
private  String district;
private  String street;
}
