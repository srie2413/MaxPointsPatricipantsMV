package MaxPointsPatricipantsMV.WBT;


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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddTemaTest {

    Validator<Student> studentValidator ;
    Validator<Tema> temaValidator ;
    Validator<Nota> notaValidator ;

    StudentXMLRepository fileRepository1;
    TemaXMLRepository fileRepository2;
    NotaXMLRepository fileRepository3;

    Service temaService;

    @Before
    public void init() {

        studentValidator = new StudentValidator();
        temaValidator = new TemaValidator();
        notaValidator = new NotaValidator();

        fileRepository1 = new StudentXMLRepository(studentValidator, "C:\\Users\\Rares\\Documents\\VVSS\\MaxPointsPatricipantsMV\\studenti2.xml");
        fileRepository2 = new TemaXMLRepository(temaValidator, "C:\\Users\\Rares\\Documents\\VVSS\\MaxPointsPatricipantsMV\\teme2.xml");
        fileRepository3 = new NotaXMLRepository(notaValidator, "C:\\Users\\Rares\\Documents\\VVSS\\MaxPointsPatricipantsMV\\note2.xml");

        temaService = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    public void testAddAssignmentIdNotValid() {

        Assert.assertEquals(0, temaService.saveTema("null", "tema_noId", 5, 5));
    }

    @Test
    public void testAddAssignmentIdValid() {
        
        Assert.assertEquals(1, temaService.saveTema("4", "tema_Id", 5, 5));
        temaService.deleteTema("4");
    }

}

