package MaxPointsPatricipantsMV.repository;


import MaxPointsPatricipantsMV.domain.Nota;
import MaxPointsPatricipantsMV.domain.Pair;
import MaxPointsPatricipantsMV.validation.Validator;

public class NotaRepository extends AbstractCRUDRepository<Pair<String, String>, Nota> {
    public NotaRepository(Validator<Nota> validator) {
        super(validator);
    }
}
