package MaxPointsPatricipantsMV.WBT;


import MaxPointsPatricipantsMV.domain.Nota;
import MaxPointsPatricipantsMV.domain.Student;
import MaxPointsPatricipantsMV.domain.Tema;
import MaxPointsPatricipantsMV.repository.*;
import MaxPointsPatricipantsMV.service.Service;
import MaxPointsPatricipantsMV.validation.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddTemaTest {

    Validator<Student> studentValidator ;
    Validator<Tema> temaValidator ;
    Validator<Nota> notaValidator ;

    StudentXMLRepository fileRepository1;
    TemaXMLRepository fileRepository2;
    NotaXMLRepository fileRepository3;


    Service temaService;
    CRUDRepository repo;

    @Before
    public void init() {

        resetInputFiles();

        studentValidator = new StudentValidator();
        temaValidator = new TemaValidator();
        notaValidator = new NotaValidator();

        fileRepository1 = new StudentXMLRepository(studentValidator, "studenti2.xml");
        fileRepository2 = new TemaXMLRepository(temaValidator, "teme2.xml");
        fileRepository3 = new NotaXMLRepository(notaValidator, "note2.xml");

        temaService = new Service(fileRepository1, fileRepository2, fileRepository3);
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
    public void testAddAssignmentExistingId() {
       temaService.saveTema("1", "tema_existingID", 5, 5);
        Assert.assertEquals(1, temaService.saveTema("1", "tema_existingID2", 6, 6));
    }

    @Test
    public void testAddAssignmentIdValid() {

        Assert.assertEquals(0, temaService.saveTema("2", "tema_Id", 5, 5));
    }
    @Test
    public void testSaveAssignmentEntityValid()
    {
        Tema t = new Tema("3","tema_id3",10,5);
        Assert.assertEquals(t, fileRepository2.save(t));
    }
    @Test
    public void testSaveAssignmentEntityExist()
    {
        Tema t = new Tema("3","tema_id3",10,5);
        fileRepository2.save(t);
        Tema t1 = new Tema("3","tema_id33",10,5);
        Assert.assertEquals(null, fileRepository2.save(t1));
    }
    @Test
    public void testAddAssignmentIdNotValid()
    {
        Assert.assertEquals(1, temaService.saveTema("", "tema_Id", 5, 5));
    }

    @Test
    public void testAddAssignmentIdIsNull()
    {
        Assert.assertEquals(1, temaService.saveTema(null, "tema_Id", 5, 5));
    }
    @Test
    public void testAddAssignmentDescisNotValid()
    {
        Assert.assertEquals(1, temaService.saveTema("1", "", 5, 5));
    }

    @Test
    public void testAddAssignmentDescIsNull()
    {
        Assert.assertEquals(1, temaService.saveTema("6", null, 5, 5));
    }
    @Test
    public void testAddAssigmentStartLineTooSmall()
    {
        Assert.assertEquals(1, temaService.saveTema("6", "desc", 5, 0));
    }
    @Test
    public void testAddAssigmentStartLineTooBig()
    {
        Assert.assertEquals(1, temaService.saveTema("6", "desc", 5, 15));
    }
    @Test
    public void testAddAssigmentStartLineNotValid()
    {
        Assert.assertEquals(1, temaService.saveTema("6", "desc", 5, 6));
    }
    @Test
    public void testAddAssigmentDeadLineTooSmall()
    {
        Assert.assertEquals(1, temaService.saveTema("6", "desc", 0, 5));
    }
    @Test
    public void testAddAssigmentDeadLineTooBig()
    {
        Assert.assertEquals(1, temaService.saveTema("6", "desc", 15, 5));
    }
    @Test
    public void testAddAssigmentDeadLineNotValid()
    {
        Assert.assertEquals(1, temaService.saveTema("6", "desc", 4, 5));
    }


}

