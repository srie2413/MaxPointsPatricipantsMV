package MaxPointsPatricipantsMV.BBT;

import MaxPointsPatricipantsMV.domain.Student;
import MaxPointsPatricipantsMV.repository.StudentXMLRepository;
import MaxPointsPatricipantsMV.validation.StudentValidator;
import MaxPointsPatricipantsMV.validation.Validator;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class StudentServiceTest {

    @Test
    public void testAddStudentService() {

        Validator<Student> studentValidator = new StudentValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "C:\\Users\\Rares\\Documents\\VVSS\\MaxPointsPatricipantsMV\\src\\test\\java\\MaxPointsPatricipantsMV\\studenti2.xml");

        Student student = new Student("1", "nume", 916);
        fileRepository1.save(student);
        assertTrue(fileRepository1.findOne(student.getID()).equals(student));
        fileRepository1.delete(student.getID());


    }

}


