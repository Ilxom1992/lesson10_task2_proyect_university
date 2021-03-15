package uz.pdp.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationships.entity.Address;
import uz.pdp.appjparelationships.entity.Student;
import uz.pdp.appjparelationships.entity.Subject;
import uz.pdp.appjparelationships.entity.University;
import uz.pdp.appjparelationships.payload.StudentDto;
import uz.pdp.appjparelationships.payload.UniversityDto;
import uz.pdp.appjparelationships.repository.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {
@Autowired
    UniversityRepository universityRepository;
@Autowired
    AddressRepository addressRepository;
@Autowired
    GroupRepository groupRepository;
@Autowired
    StudentRepository studentRepository;
@Autowired
    SubjectRepository subjectRepository;
//READ
@RequestMapping(value = "/university",method = RequestMethod.GET)
    public List<University> getUniversity(){
    List<University> universities = universityRepository.findAll();
return universities;
}
//CREATE
    @RequestMapping(value = "/university",method = RequestMethod.POST)
    public  String addUniversity(@RequestBody UniversityDto universityDto){
    //YANGI ADDRESS OCHIB OLDIK
        Address address=new Address();
        address.setCity(universityDto.getCity());
        address.setDistrict(universityDto.getDistrict());
        address.setStreet(universityDto.getStreet());
        //YASAB OLGAN ADDRESS OBYEKTIMIZNI DB GA SAQLADIK VA U BAZAGA SAQLAGAN ADDRSSNI BERDIK
        Address saveAddress = addressRepository.save(address);
        //YANGI  UNIVERSITIET YASAB OLDIK
University university =new University();
university.setName(universityDto.getName());
university.setAddress(saveAddress);
universityRepository.save(university);
return "University added";
    }
//UPDATE
    @RequestMapping(value = "/university/{id}",method = RequestMethod.PUT)
    public String editUniversity(@PathVariable Integer id,@RequestBody UniversityDto universityDto){
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent()){
            University university=new University();
            university.setName(universityDto.getName());
            //UNIVERSITET ADDRESSI
            Address address=university.getAddress();
            address.setCity(universityDto.getCity());
            address.setDistrict(universityDto.getDistrict());
            address.setStreet(universityDto.getStreet());
            addressRepository.save(address);

            universityRepository.save(university);
            return "University edited";
        }
        return "university not found";
    }
}
