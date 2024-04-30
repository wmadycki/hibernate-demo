package pl.hibernate.example.modules.examples;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.hibernate.example.modules.entities.ChildModel;
import pl.hibernate.example.modules.entities.ParentModel;

@Service
public class ModelExecutor {

    @Autowired
    ModelExecutorRepository modelExecutorRepository;
    @Autowired
    ChildModelRepository childModelRepository;

    @Transactional
    public void createModel() {
        ChildModel childModelFirst = new ChildModel();
        childModelFirst.setName("First");
        childModelRepository.save(childModelFirst);

        ParentModel parentModelFirst = new ParentModel();
        parentModelFirst.setName("First");
        parentModelFirst.getChildModel().clear();
        parentModelFirst.getChildModel().add(childModelFirst);

        childModelFirst.setParentModel(parentModelFirst);

        modelExecutorRepository.save(parentModelFirst);
    }


}
