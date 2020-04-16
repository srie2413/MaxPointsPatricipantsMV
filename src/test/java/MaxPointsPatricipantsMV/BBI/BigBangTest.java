package MaxPointsPatricipantsMV.BBI;

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
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BigBangTest {

    Validator<Student> studentValidator ;
    Validator<Tema> temaValidator ;
    Validator<Nota> notaValidator ;

    StudentXMLRepository fileRepository1;
    TemaXMLRepository fileRepository2;
    NotaXMLRepository fileRepository3;

    Service service;

    @Before
    public void init() {

        resetInputFiles();

        studentValidator = new StudentValidator();
        temaValidator = new TemaValidator();
        notaValidator = new NotaValidator();

        fileRepository1 = new StudentXMLRepository(studentValidator, "studenti2.xml");
        fileRepository2 = new TemaXMLRepository(temaValidator, "teme2.xml");
        fileRepository3 = new NotaXMLRepository(notaValidator, "note2.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    private void resetInputFiles(){
        String[] filenames = {"note2.xml", "studenti2.xml", "teme2.xml"};
        //String prefix = "src\test\java\MaxPointsParticipants\";

        for(int i = 0; i < filenames.length; i++){
            File file = new File(filenames[i]);

            if (file.exists())
                file.delete();

            try {
                File newFile = new File(filenames[i]);
                if(newFile.createNewFile()){
                    FileWriter myWriter = new FileWriter(filenames[i]);
                    myWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                            "<Entitati/>");
                    myWriter.close();
                }

            } catch (IOException e) {
                System.out.println("An error occurred. The could not reset the file");
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testAddAssignment() {

        Assert.assertEquals(1, service.saveTema("1", "tema", 5, 5));
    }

    @Test
    public void testAddStudent() {

        Assert.assertEquals(1, service.saveStudent("2", "Alin Alin", 937));
    }

    @Test
    public void testAddGrade() {
        Assert.assertEquals(-1, service.saveNota("5", "5", 1.0, 14,""));
        //service.deleteTema("4");
    }
    @Test
    public void bigBang() {

        testAddAssignment();
        testAddStudent();
        testAddGrade();
        System.out.println("Bv ai trecut big bang");
    }



}
