package MaxPointsPatricipantsMV.BBT;
import MaxPointsPatricipantsMV.domain.Nota;
import MaxPointsPatricipantsMV.domain.Student;
import MaxPointsPatricipantsMV.domain.Tema;
import MaxPointsPatricipantsMV.repository.StudentXMLRepository;
import MaxPointsPatricipantsMV.validation.NotaValidator;
import MaxPointsPatricipantsMV.validation.StudentValidator;
import MaxPointsPatricipantsMV.validation.TemaValidator;
import MaxPointsPatricipantsMV.validation.Validator;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class StudentControlerTest {

    @Test
    public void testAddStudentController() {

        Validator<Student> studentValidator = new StudentValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "C:\\Users\\Rares\\Documents\\VVSS\\MaxPointsPatricipantsMV\\src\\test\\java\\MaxPointsPatricipantsMV\\studenti2.xml");

        Student student = new Student("1", "nume", 916);
        fileRepository1.save(student);
        assertTrue(fileRepository1.findOne(student.getID()).equals(student));
        fileRepository1.delete(student.getID());


    }

}
