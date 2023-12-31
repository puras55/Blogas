package com.example.springbootblogapplication.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Authority implements Serializable {

    @Id
    @Column(length = 16)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Authority authority1 = (Authority) o;

        return name.equals(authority1.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Authority{" +
            "name='" + name + "'" +
            "}";
    }
//metodai yra perdefinuoti, kad galėtume palyginti Authority objektus, generuoti unikalų hash kodą ir
// spausdinti objekto informaciją teksto formatu.

}
