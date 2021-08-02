package web.core.persistence.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "exercisetype")
public class ExerciseTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer exerciseTypeId;

    @Column(name = "name")
    private Timestamp name;

    @Column(name = "code")
    private Timestamp code;

    @Column(name = "createddate")
    private Timestamp createdDate;

    @Column(name = "modifieddate")
    private Timestamp modifiedDate;

    @OneToMany(mappedBy = "exercisetype", fetch = FetchType.LAZY)
    private List<ExerciseTypeEntity> exercises;

    public Integer getExerciseTypeId() {
        return exerciseTypeId;
    }

    public void setExerciseTypeId(Integer exerciseTypeId) {
        this.exerciseTypeId = exerciseTypeId;
    }

    public Timestamp getName() {
        return name;
    }

    public void setName(Timestamp name) {
        this.name = name;
    }

    public Timestamp getCode() {
        return code;
    }

    public void setCode(Timestamp code) {
        this.code = code;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public List<ExerciseTypeEntity> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseTypeEntity> exercises) {
        this.exercises = exercises;
    }
}
