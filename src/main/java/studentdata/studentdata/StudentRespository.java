package studentdata.studentdata;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRespository extends JpaRepository<Student, Integer> {
    Student findBySid(int sid);
    Student findByName(String name);
    Student findByHairColour(String hairColour);
    Student findByHeight(double height);
    Student findByWeight(double weight);
    Student findByGpa(double gpa);
    Student findByFaculty(Faculties faculty);

    List<Student> findByNameAndHairColourAndHeightAndWeightAndGpaAndFaculty(String name, String hairColour, double height, double weight, double gpa, Faculties faculty);
} 
