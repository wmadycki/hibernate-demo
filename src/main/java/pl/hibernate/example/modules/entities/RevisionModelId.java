package pl.hibernate.example.modules.entities;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class RevisionModelId implements Serializable {

    private String code;
    private long revision;

    public RevisionModelId(String code, Long revision){
        this.code = code;
        this.revision = revision;
    }

    /**
     * Please do not change the content and formatting toString() method.
     * Following method is required to correct audit working .
     */
    @Override
    public String toString() {
        return "CompositeId: code='" + code + "' | revision='" + revision + "'";
    }



}
