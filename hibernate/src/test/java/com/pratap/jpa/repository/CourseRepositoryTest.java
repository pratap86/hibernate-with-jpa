package com.pratap.jpa.repository;

import com.pratap.jpa.HibernateApplication;
import com.pratap.jpa.entity.Course;
import com.pratap.jpa.entity.Review;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HibernateApplication.class)
class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void testFindById_Basic() {
        Course course = courseRepository.findById(10001L);
        assertEquals("Hibernate in 100 steps", course.getName());
    }

    @Test
    @DirtiesContext//reset the data for further use
    void testDeleteById_Basic(){
        courseRepository.deleteById(10001L);
        assertNull(courseRepository.findById(10001L));
    }

    @Test
    void testSave_New_Record(){
        Course course = new Course("AWS");
        Course newCourse = courseRepository.save(course);
        assertNotNull(newCourse);
        assertEquals("AWS", newCourse.getName());
    }

    @Test
    @DirtiesContext
    void testSave_Existing_Record(){
        Course course = courseRepository.findById(10001L);
        course.setName("AngularJS");
        Course updatedCourse = courseRepository.save(course);
        assertNotNull(updatedCourse);
        assertEquals("AngularJS", updatedCourse.getName());
    }

    @Test
    @Transactional
    @DirtiesContext
    void testAddReviewsForCourse(){

        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review(5, "Great Course"));
        reviews.add(new Review(5, "Very awesome course"));
        Course course = courseRepository.addReviewsForCourse(10003L, reviews);
        assertNotNull(course);
        assertEquals(3, course.getReviews().size());

    }

    @Test
    @Transactional
    void testGetReviewsForCourse(){

        Course course = entityManager.find(Course.class, 10001L);
        assertEquals(2, course.getReviews().size());

    }

    @Test
    @Transactional
    void testGetCourseForReview(){

        Review review = entityManager.find(Review.class, 50001L);
        assertEquals("Hibernate in 100 steps", review.getCourse().getName());
        assertEquals(2, review.getCourse().getReviews().size());
    }
}