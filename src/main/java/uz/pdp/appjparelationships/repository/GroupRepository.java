package uz.pdp.appjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appjparelationships.entity.Group;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group,Integer> {

    List<Group> findAllByFacultyUniversityId(Integer faculty_university_id);


    @Query("select  gr from  groups  gr where gr.faculty.university.id=:universityId")
    List<Group> getGroupByUniversityId(Integer universityId);

    @Query(value = "select  * from  groups join  faculty f on f.id=g.faculty_id join  university u on u.id=f.university_id" +
            "where u.id=:universityId",nativeQuery = true)
    List<Group> getGroupByUniversityIdNative(Integer universityId);


}
