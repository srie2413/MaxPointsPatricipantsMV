package MaxPointsPatricipantsMV.BBT;

import MaxPointsPatricipantsMV.domain.Nota;
import MaxPointsPatricipantsMV.domain.Student;
import MaxPointsPatricipantsMV.domain.Tema;
import MaxPointsPatricipantsMV.repository.NotaXMLRepository;
import MaxPointsPatricipantsMV.repository.StudentXMLRepository;
import MaxPointsPatricipantsMV.repository.TemaXMLRepository;
import MaxPointsPatricipantsMV.service.Service;
import MaxPointsPatricipantsMV.validation.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddStudentTest {

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
    public void addStudentIdNull() {
        Integer repoInitLen = fileRepository1.getLength();
        service.saveStudent(null, "nume", 936);
        assertTrue(repoInitLen.equals(fileRepository1.getLength()));

    }

    @Test
    public void addStudentIdEmptyStr() {
        Integer repoInitLen = fileRepository1.getLength();
        service.saveStudent("", "nume", 936);
        assertTrue(repoInitLen.equals(fileRepository1.getLength()));
    }

    @Test
    public void addStudentNumeNull() {
        Integer repoInitLen = fileRepository1.getLength();
        service.saveStudent("1", null, 936);
        assertTrue(repoInitLen.equals(fileRepository1.getLength()));
    }

    @Test
    public void addStudentNumeEmptyStr() {
        Integer repoInitLen = fileRepository1.getLength();
        service.saveStudent("1", "", 936);
        assertTrue(repoInitLen.equals(fileRepository1.getLength()));
    }

    @Test
    public void addStudentGrupaNull() {
        Integer repoInitLen = fileRepository1.getLength();
        service.saveStudent("1", "nume", 0);
        assertTrue(repoInitLen.equals(fileRepository1.getLength()));
    }

    @Test
    public void addStudentGrupaSub0() {
        Integer repoInitLen = fileRepository1.getLength();
        service.saveStudent("1", "nume", -1);
        assertTrue(repoInitLen.equals(fileRepository1.getLength()));
    }

    @Test
    public void addStudentExisting() {
        service.saveStudent("1", "nume", 936);
        Integer repoInitLen = fileRepository1.getLength();

        service.saveStudent("1", "nume2", 937);
        assertTrue(repoInitLen.equals(fileRepository1.getLength()));
    }

    @Test
    public void addStudentValid() {
        Integer repoInitLen = fileRepository1.getLength();
        service.saveStudent("1", "nume", 936);
        assertTrue(repoInitLen + 1 == (fileRepository1.getLength()));
    }

    @Test
    public void addStudentMaxIntGrVal() {
        Integer repoInitLen = fileRepository1.getLength();
        service.saveStudent("1", "nume", Integer.MAX_VALUE);
        assertTrue(repoInitLen == (fileRepository1.getLength()));
    }

    @Test
    public void addStudentMinIntGrVal() {
        Integer repoInitLen = fileRepository1.getLength();
        service.saveStudent("1", "nume", Integer.MIN_VALUE);
        assertTrue(repoInitLen == (fileRepository1.getLength()));
    }

    @Test
    public void addStudentJustOverMaxGrVal() {
        Integer repoInitLen = fileRepository1.getLength();
        service.saveStudent("1", "nume", 938);
        assertTrue(repoInitLen == (fileRepository1.getLength()));
    }

    @Test
    public void addStudentJustBelowMinGrVal() {
        Integer repoInitLen = fileRepository1.getLength();
        service.saveStudent("1", "nume", 110);
        assertTrue(repoInitLen == (fileRepository1.getLength()));
    }

    @Test
    public void addStudentMinGrVal() {
        Integer repoInitLen = fileRepository1.getLength();
        service.saveStudent("1", "nume", 111);
        assertTrue(repoInitLen + 1 == (fileRepository1.getLength()));
    }

    @Test
    public void addStudentMaxGrVal() {
        Integer repoInitLen = fileRepository1.getLength();
        service.saveStudent("1", "nume", 937);
        assertTrue(repoInitLen + 1 == (fileRepository1.getLength()));
    }

    @Test
    public void addStudentBelowMaxGrVal() {
        Integer repoInitLen = fileRepository1.getLength();
        service.saveStudent("1", "nume", 936);
        assertTrue(repoInitLen + 1 == (fileRepository1.getLength()));
    }

    @Test
    public void addStudentAboveMinGrVal() {
        Integer repoInitLen = fileRepository1.getLength();
        service.saveStudent("1", "nume", 112);
        assertTrue(repoInitLen + 1 == (fileRepository1.getLength()));
    }


}
