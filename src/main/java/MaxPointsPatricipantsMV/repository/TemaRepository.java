package MaxPointsPatricipantsMV.repository;


import MaxPointsPatricipantsMV.domain.Tema;
import MaxPointsPatricipantsMV.validation.Validator;

public class TemaRepository extends AbstractCRUDRepository<String, Tema> {
    public TemaRepository(Validator<Tema> validator) {
        super(validator);
    }
}

