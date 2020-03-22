package MaxPointsPatricipantsMV.BBT;

import MaxPointsPatricipantsMV.domain.Student;
import MaxPointsPatricipantsMV.repository.StudentXMLRepository;
import MaxPointsPatricipantsMV.validation.StudentValidator;
import MaxPointsPatricipantsMV.validation.Validator;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class StudentServiceTest {

    @Test
    @Test
    public void testAddStudentService() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "C:\\\\Users\\rotar\\Desktop\\Projects\\vvss\\Lab2\\src\\test\\java\\MaxPointsParticipants\\studenti2.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "C:\\\\Users\\rotar\\Desktop\\Projects\\vvss\\Lab2\\src\\test\\java\\MaxPointsParticipants\\teme2.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "C:\\\\Users\\rotar\\Desktop\\Projects\\vvss\\Lab2\\src\\test\\java\\MaxPointsParticipants\\note2.xml");

        Service studentService = new Service(fileRepository1,fileRepository2,fileRepository3);
        studentService.saveTema("2", "De la mic la mare inaintam tare",31,1);
        studentService.saveStudent("1", "nume", 916);
        studentService.saveNota("1","2",32,20,"Formidable! :)");

        assertTrue(studentService.updateStudent("1","Ghencea Mihai",936)==1);
    }


}


