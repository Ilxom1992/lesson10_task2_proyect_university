package uz.pdp.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationships.entity.Faculty;
import uz.pdp.appjparelationships.entity.Group;

import uz.pdp.appjparelationships.payload.GroupDto;
import uz.pdp.appjparelationships.repository.FacultyRepository;
import uz.pdp.appjparelationships.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {
@Autowired
    GroupRepository groupRepository;
@Autowired
FacultyRepository facultyRepository;
    //VAZIRLIK UCHUN
    //READ
@GetMapping
public List<Group> getGroups(){
    return  groupRepository.findAll();

}
//UNIVERSITETNING MASUL XODIMI UCHUN
@GetMapping("/byUniversityId/{universityId}")
    public List<Group> getGroupsByUniversityId(@PathVariable Integer universityId){
    List<Group> groupRepositoryAllByFaculty_university_id = groupRepository.findAllByFacultyUniversityId(universityId);
    List<Group> groupByUniversityId = groupRepository.getGroupByUniversityId(universityId);
    List<Group> groupByUniversityIdNative = groupRepository.getGroupByUniversityIdNative(universityId);
    return groupRepositoryAllByFaculty_university_id;


}
@PostMapping
    public  String addGroup(@RequestBody GroupDto groupDto){
    Group group=new Group();
group.setName(groupDto.getName());

    Optional<Faculty> optionalFaculty= facultyRepository.findById(groupDto.getFacultyId());
if (!optionalFaculty.isPresent()){
    return "Such faculty not found";
}
group.setFaculty(optionalFaculty.get());

    groupRepository.save(group);
    return "Group Added";
}


}
