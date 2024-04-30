package pl.hibernate.example.modules.examples;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.hibernate.example.modules.entities.ChildModel;
import pl.hibernate.example.modules.entities.Model;

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

        Model modelFirst = new Model();
        modelFirst.setName("First");
        modelFirst.getChildModel().clear();
        modelFirst.getChildModel().add(childModelFirst);

        childModelFirst.setModel(modelFirst);

        modelExecutorRepository.save(modelFirst);

//        ChildModel childModelSecond = new ChildModel();
//        childModelSecond.setName("Second");
//
//        Model modelSecond = new Model();
//        modelSecond.setName("Second");
//        modelSecond.getChildModel().clear();
//        modelSecond.getChildModel().add(childModelSecond);
//
//        childModelSecond.setModel(modelSecond);
//
//        modelExecutorRepository.save(modelSecond);

    }


}
