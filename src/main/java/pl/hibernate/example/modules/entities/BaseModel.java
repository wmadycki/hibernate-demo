package pl.hibernate.example.modules.entities;

import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import pl.hibernate.example.modules.examples.ModelGenerator;

@Getter
@MappedSuperclass
@IdClass(RevisionModelId.class)
public abstract class BaseModel {

    @Id
    protected String code;

    @Id
    protected long revision = 0L;

    public abstract String getName();

    @PrePersist
    private void prePersist() {
        if (code == null) {
            code = ModelGenerator.getInstance().generateCode(this);
        }
    }
}
