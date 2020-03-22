package MaxPointsPatricipantsMV.repository;


import MaxPointsPatricipantsMV.domain.Student;
import MaxPointsPatricipantsMV.validation.Validator;

public class StudentRepository extends AbstractCRUDRepository<String, Student> {
    public StudentRepository(Validator<Student> validator) {
        super(validator);
    }
}

