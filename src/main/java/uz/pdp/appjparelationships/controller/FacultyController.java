package uz.pdp.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationships.entity.Faculty;
import uz.pdp.appjparelationships.entity.University;
import uz.pdp.appjparelationships.payload.FacultyDto;
import uz.pdp.appjparelationships.repository.FacultyRepository;
import uz.pdp.appjparelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UniversityRepository universityRepository;

    @PostMapping
    public String  addFaculty(@RequestBody FacultyDto facultyDto){
        boolean exists = facultyRepository.existsByNameAndUniversityId(facultyDto.getFacultyName(), facultyDto.getUniversityId());
if (exists){
return "This university such faculty exist";
}
        Faculty faculty=new Faculty();


        faculty.setName(facultyDto.getFacultyName());
        Optional<University> optionalUniversity= universityRepository.findById(facultyDto.getUniversityId());
        if (!optionalUniversity.isPresent()) {
            return "Not found university ";
        }
        faculty.setUniversity(optionalUniversity.get());
            facultyRepository.save(faculty);
            return "Faculty added";
        }
        //UNIVERSITET XODIMI UCHUN
        @GetMapping("/byUniversityId/{universityId}")
    public List<Faculty> getFacultisUniversityId(@PathVariable Integer universityId){
            List<Faculty> allByUniversityId = facultyRepository.findAllByUniversityId(universityId);
    return allByUniversityId;
        }
        //DELETE
        @DeleteMapping("/{id}")
public  String deleteFaculty(@PathVariable Integer id){
        try{
        facultyRepository.deleteById(id);
        return "Faculty deleted";}
        catch (Exception e){
            return "Error in deleting";
        }
        }
        //UPDATE
        @PutMapping("/{id}")
        public String editFaculty(@PathVariable Integer id,@RequestBody FacultyDto facultyDto){
            Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
            if (optionalFaculty.isPresent()){

                Faculty faculty=optionalFaculty.get();
                faculty.setName(facultyDto.getFacultyName());
                Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
                if (!optionalUniversity.isPresent()){
                    return "University not found";}
                    faculty.setUniversity(optionalUniversity.get());
                facultyRepository.save(faculty);
                return "Faculty Edited";
            }
return "Faculty not fonud";
        }
        //VAZIRLIK UCHUN
    @GetMapping
    public  List<Faculty> getFaculty(){
     return    facultyRepository.findAll();
    }
}
