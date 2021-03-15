package uz.pdp.appjparelationships.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationships.entity.Address;
import uz.pdp.appjparelationships.entity.Group;
import uz.pdp.appjparelationships.entity.Student;
import uz.pdp.appjparelationships.entity.Subject;
import uz.pdp.appjparelationships.payload.StudentDto;
import uz.pdp.appjparelationships.repository.AddressRepository;
import uz.pdp.appjparelationships.repository.GroupRepository;
import uz.pdp.appjparelationships.repository.StudentRepository;
import uz.pdp.appjparelationships.repository.SubjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    final StudentRepository studentRepository;
    final AddressRepository addressRepository;
    final SubjectRepository subjectRepository;
    final GroupRepository groupRepository;
    public StudentController(StudentRepository studentRepository, AddressRepository addressRepository, SubjectRepository subjectRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.addressRepository = addressRepository;
        this.subjectRepository = subjectRepository;
        this.groupRepository = groupRepository;
    }

    //1.MINISTRY
    @GetMapping(value = "/forMinistry")
    public Page<Student> getStudentListForMinistry(@RequestParam int page){
        Pageable pageable= PageRequest.of(page,10);
        return studentRepository.findAll(pageable);
    }

    //2.UNIVERSITY
    @GetMapping(value = "/forUniversity/{universityId}")
    public Page<Student> getStudentListForUniversity(@RequestParam int page,
                                                     @PathVariable Integer universityId){
        Pageable pageable= PageRequest.of(page,10);
        Page<Student>studentPage = studentRepository.findAllByGroup_Faculty_UniversityId(universityId, pageable);
        return studentPage;

    }
    //3.FACULTY UNIVERSITY
    @GetMapping(value = "/forFaculty/{facultyId}")
    public Page<Student> getStudentListForFaculty(@RequestParam int page,
                                                     @PathVariable Integer facultyId){
        Pageable pageable= PageRequest.of(page,10);
        Page<Student>studentPage = studentRepository.findAllByGroup_Faculty_Id(facultyId,pageable);
        return studentPage;
    }
    //4.GROUP OWNER
    @GetMapping(value = "/forGroup/{groupId}")
    public Page<Student> getStudentListForGroup(@RequestParam int page,
                                                     @PathVariable Integer groupId){
        Pageable pageable= PageRequest.of(page,10);
        Page<Student>studentPage = studentRepository.findAllByGroup_Faculty_UniversityId(groupId, pageable);
        return studentPage;
    }
    //DELETE
    @DeleteMapping("delete{id}")
    public String deleteStudent(@PathVariable Integer id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (!optionalStudent.isPresent()){return "Bunday Id li Student Mavjud emas";}
        studentRepository.deleteById(id);
        return "Student Deleted";
    }
    //CREATE
    @PostMapping
    public String addStudent(@RequestBody StudentDto studentDto){
        Student student=new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        Address address=new Address();
        address.setStreet(studentDto.getStreet());
        address.setDistrict(studentDto.getDistrict());
        address.setCity(studentDto.getCity());
        Address saveAdress = addressRepository.save(address);
        student.setAddress(saveAdress);

        Optional<Group> optionalGroup = groupRepository.findById(studentDto.getGroupId());
        if (!optionalGroup.isPresent()){
            return "Bunday Gruh bazada Yo'q";
        }
        student.setGroup(optionalGroup.get());
        List<Subject> subjectList=new ArrayList<>();
        for (Integer n:studentDto.getSubjectListId()) {
            Optional<Subject> optionalSubject = subjectRepository.findById(n);
            if (!optionalSubject.isPresent()){
                return "Bazada Bunday Fan Topilmadi";
            }
            subjectList.add(optionalSubject.get());
        }
        student.setSubjects(subjectList);
        studentRepository.save(student);
        return "Student Bazaga Saqlandi";
    }
    //UPDATE
    @RequestMapping("update/{id}")
    public String updateStudent(@RequestBody StudentDto studentDto,@PathVariable Integer id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (!optionalStudent.isPresent()){
            return "Bunday Id li Student Bazada Mavjud emas";
        }
        Student student= optionalStudent.get();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        Address address=new Address();
        address.setStreet(studentDto.getStreet());
        address.setDistrict(studentDto.getDistrict());
        address.setCity(studentDto.getCity());
        Address saveAddress = addressRepository.save(address);
        student.setAddress(saveAddress);

        Optional<Group> optionalGroup = groupRepository.findById(studentDto.getGroupId());
        if (!optionalGroup.isPresent()){
            return "Bunday Gruh bazada Yo'q";
        }
        student.setGroup(optionalGroup.get());
        List<Subject> subjectList=new ArrayList<>();
        for (Integer n:studentDto.getSubjectListId()) {
            Optional<Subject> optionalSubject = subjectRepository.findById(n);
            if (!optionalSubject.isPresent()){
                return "Bazada Bunday Fan Topilmadi";
            }
            subjectList.add(optionalSubject.get());
        }
        student.setSubjects(subjectList);
        studentRepository.save(student);
        return "Student Bazaga Saqlandi";
    }
    //READ BY ID
    @RequestMapping("/{id}")
    public Optional<Student> getStudent(@PathVariable Integer id){
        return studentRepository.findById(id);
    }
}
