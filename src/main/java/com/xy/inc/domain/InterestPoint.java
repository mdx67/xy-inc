package com.xy.inc.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Matheus Xavier
 */
@NamedQueries({
    @NamedQuery(name = "InterestPoint.listAll", query = "select p from InterestPoint p")
})
@Entity
public class InterestPoint implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @NotNull(message = "Você deve informar a posição X.")
    private Integer x;
    @NotNull(message = "Você deve informar a posição Y.")
    private Integer y;

    public InterestPoint() {
    }

    public InterestPoint(String name, Integer x, Integer y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public InterestPoint(Integer id, String name, Integer x, Integer y) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

}
