package tn.esprit.championnat.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idCourse;

    String emplacement;
    LocalDate dateCourse;

    @ManyToMany(mappedBy = "courses")
    private List<Championnat> championnats;

    @OneToMany(mappedBy = "course")
    private List<Position> positions;



}

