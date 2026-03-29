package tn.esprit.championnat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.championnat.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {}