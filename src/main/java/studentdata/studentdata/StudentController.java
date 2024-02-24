package studentdata.studentdata;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.servlet.view.RedirectView;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class StudentController {
    @Autowired
    private StudentRespository studentRepo;

    @GetMapping("/")
    public RedirectView process() {
        return new RedirectView("home");
    }
    @GetMapping("/home")
    public String showHome() {
        return "students/home";
    }
    @GetMapping("/add")
    public String showAdd() {
        return "students/add";
    }
   @PostMapping("/students/add")
    public String addStudent(@RequestParam Map<String, String> newstudent, HttpServletResponse response) {
        System.out.println("Add new student");
        String newName = newstudent.get("name");
        String newHairColour = newstudent.get("hairColour");
        double newHeight = Double.parseDouble(newstudent.get("height"));
        double newWeight = Double.parseDouble(newstudent.get("weight"));
        double newGPA = Double.parseDouble(newstudent.get("gpa"));
        String facultyParam = newstudent.get("faculty");
        Faculties newFaculty = Faculties.valueOf(facultyParam);

        // Check for duplicate
        List<Student> existingStudents = studentRepo.findByNameAndHairColourAndHeightAndWeightAndGpaAndFaculty(newName, newHairColour, newHeight, newWeight, newGPA, newFaculty);
        if (existingStudents.isEmpty()) {
            studentRepo.save(new Student(newName, newHairColour, newHeight, newWeight, newGPA, newFaculty));
            response.setStatus(200);
        }

        return "/students/home";
    }
    @GetMapping("/list")
    public String getAllStudents(Model model) {
        List<Student> students = studentRepo.findAll();
        model.addAttribute("sdns", students);
        return "students/list";
    }
    @GetMapping("/change")
    public String changeStudent(@RequestParam int sid, Model model) {
        Student student = studentRepo.findBySid(sid);
        model.addAttribute("student", student);
        return "students/change";
    }
    @PostMapping("/students/change")
    public String updateStudent(@RequestParam Map<String, String> updatedStudent, HttpServletResponse response) {
        String updatedName = updatedStudent.get("name");
        String updatedHairColour = updatedStudent.get("hairColour");
        double updatedHeight = Double.parseDouble(updatedStudent.get("height"));
        double updatedWeight = Double.parseDouble(updatedStudent.get("weight"));
        double updatedGpa = Double.parseDouble(updatedStudent.get("gpa"));
        String facultyParam = updatedStudent.get("faculty");
        Faculties updatedFaculty = Faculties.valueOf(facultyParam);
        
        System.out.println("Updated student information");
        Student student = studentRepo.findBySid(Integer.parseInt(updatedStudent.get("sid")));

        student.setName(updatedName);
        student.setHairColour(updatedHairColour);
        student.setHeight(updatedHeight);
        student.setWeight(updatedWeight);
        student.setGpa(updatedGpa);
        student.setFaculty(updatedFaculty);
        studentRepo.save(student);
        response.setStatus(201);

        return "students/home";
    }

    @GetMapping("/delete")
    public String deleteConfirmation(@RequestParam int sid, Model model) {
        Student student = studentRepo.findBySid(sid);
        model.addAttribute("student", student);
        return "students/delete";
    }
    @PostMapping("/students/delete")
    public String deleteStudent(@RequestParam int sid, HttpServletResponse response) {
        Student student = studentRepo.findBySid(sid);
        studentRepo.delete(student);
        response.setStatus(200);
        return "students/home";
    }

    @GetMapping("/display")
    public String displayStudents(Model model) {
        List<Student> students = studentRepo.findAll();
        model.addAttribute("students", students);
        return "students/display";
    }
}
 