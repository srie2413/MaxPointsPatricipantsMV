package MaxPointsPatricipantsMV.BBT;

import MaxPointsPatricipantsMV.domain.Nota;
import MaxPointsPatricipantsMV.domain.Student;
import MaxPointsPatricipantsMV.domain.Tema;
import MaxPointsPatricipantsMV.repository.NotaXMLRepository;
import MaxPointsPatricipantsMV.repository.StudentXMLRepository;
import MaxPointsPatricipantsMV.repository.TemaXMLRepository;
import MaxPointsPatricipantsMV.service.Service;
import MaxPointsPatricipantsMV.validation.NotaValidator;
import MaxPointsPatricipantsMV.validation.StudentValidator;
import MaxPointsPatricipantsMV.validation.TemaValidator;
import MaxPointsPatricipantsMV.validation.Validator;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class StudentServiceTest {

    @Test
    public void testAddStudentService() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "C:\\Users\\Rares\\Documents\\VVSS\\MaxPointsPatricipantsMV\\studenti2.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "C:\\Users\\Rares\\Documents\\VVSS\\MaxPointsPatricipantsMV\\teme2.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "C:\\Users\\Rares\\Documents\\VVSS\\MaxPointsPatricipantsMV\\note2.xml");

        Service studentService = new Service(fileRepository1,fileRepository2,fileRepository3);
        studentService.saveTema("2", "tema",31,1);
        studentService.saveStudent("1", "nume", 936);
        studentService.saveNota("1","2",32,20,"");

        assertTrue(studentService.updateStudent("1","Ghencea Mihai",936)==1);
    }


}


