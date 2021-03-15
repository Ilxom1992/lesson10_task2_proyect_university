package uz.pdp.appjparelationships.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private String firstName;
    private String lastName;
    private List<Integer> subjectListId;
    private String city;
    private String district;
    private String street;
    private Integer groupId;

}
