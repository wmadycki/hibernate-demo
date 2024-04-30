package pl.hibernate.example.modules.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "s_model_child")
public class ChildModel extends BaseModel {

    private String name;

    public RevisionModelId getId() {
        return new RevisionModelId(code, revision);
    }

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "model_code", referencedColumnName = "code"),
            @JoinColumn(name = "model_revision", referencedColumnName = "revision")
    })
    private ParentModel parentModel;

}
