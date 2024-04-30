package pl.hibernate.example.modules.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name= "s_model")
@IdClass(RevisionModelId.class)
public class ParentModel extends BaseModel {

    public RevisionModelId getId() {
        return new RevisionModelId(code, revision);
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parentModel", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<ChildModel> childModel = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    private String name;

}
