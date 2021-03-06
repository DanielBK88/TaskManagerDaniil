package ru.volnenko.se.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Denis Volnenko
 */
@Entity
@Table(name = "TMNG_TASK")
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Task implements Serializable{

    @Id
    @Column(name = "TASK_NAME")
    private String name = "";

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    @Column(name = "DATE_BEGIN")
    private LocalDate dateBegin;

    @Column(name = "DATE_END")
    private LocalDate dateEnd;

    public void test() {
        System.out.println("HELLO");
    }

}
