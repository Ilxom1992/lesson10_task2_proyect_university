package uz.pdp.appjparelationships.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class GroupDto {

    private  Integer id;
    private String  name;
    private Integer facultyId;


}
