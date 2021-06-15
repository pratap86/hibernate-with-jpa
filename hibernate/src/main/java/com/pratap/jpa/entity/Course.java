package com.pratap.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries(value = {
        @NamedQuery(name = "get_all_course_query", query = "Select c from Course c"),
        @NamedQuery(name = "get_100_steps_query", query = "Select c from Course c where c.name like '%100 steps'")
})
@Cacheable
@SQLDelete(sql = "update Course set is_deleted=true where id=?")
@Where(clause = "is_deleted=false")
public class Course {

    @Getter
    @Id
    @GeneratedValue
    private Long id;

    @Getter
    @Setter
    private String name;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    private boolean isDeleted;

    @Getter
    @OneToMany(mappedBy = "course")
    private List<Review> reviews = new ArrayList<>();

    @Getter
    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    protected Course(){}

    public Course(String name){
        this.name = name;
    }

    public void removeReview(Review review){
        this.reviews.remove(review);
    }

    public void addReview(Review review){
        this.reviews.add(review);
    }

    public void addStudent(Student student){
        this.students.add(student);
    }

    @PreRemove//in case of cacheable Entity would be updated
    public void preRemove(){
        this.isDeleted=true;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
