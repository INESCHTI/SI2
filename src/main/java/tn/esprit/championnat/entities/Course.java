package tn.esprit.championnat.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCourse;

    private String emplacement;
    private LocalDate dateCourse;

    @ManyToMany(mappedBy = "courses")
    private List<Championnnat> championnnats;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Position> positions;

    public Course() {}
}
