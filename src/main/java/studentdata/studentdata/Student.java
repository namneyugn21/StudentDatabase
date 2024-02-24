package studentdata.studentdata;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sid;
    private String name;
    private String hairColour;
    private double height;
    private double weight;
    private double gpa;
    @Enumerated(EnumType.STRING)
    private Faculties faculty;
    
    public Student() {}

    public Student(String name, String hairColour, double height, double weight, double gpa, Faculties faculty) {
        this.name = name;
        this.hairColour = hairColour;
        this.height = height;
        this.weight = weight;
        this.gpa = gpa;
        this.faculty = faculty;
    }

    public int getSid() {
        return sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHairColour() {
        return hairColour;
    }

    public void setHairColour(String hairColour) {
        this.hairColour = hairColour;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Faculties getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculties faculty) {
        this.faculty = faculty;
    }
}
