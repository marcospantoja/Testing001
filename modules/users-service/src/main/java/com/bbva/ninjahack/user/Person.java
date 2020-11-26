package com.bbva.ninjahack.user;

import io.quarkus.hibernate.orm.PersistenceUnit;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTypeDeserializer;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by E052826 on 21/11/2020.
 */
@Entity
public class Person extends PanacheEntity {
	
	public Person() {
		super();
	}

    @Size(min = 3, max = 50)
    public String firstName;

    @Size(min = 3, max = 50)
    public String lastName;

    @NotNull
    @Size(min = 3, max = 50)
    public String email;

    @Size(min = 3, max = 50)
    public String password;

}