package pl.hibernate.example.modules.examples;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.hibernate.example.modules.entities.ParentModel;
import pl.hibernate.example.modules.entities.RevisionModelId;

@Repository
public interface ModelExecutorRepository extends JpaRepository<ParentModel, RevisionModelId> {

}
