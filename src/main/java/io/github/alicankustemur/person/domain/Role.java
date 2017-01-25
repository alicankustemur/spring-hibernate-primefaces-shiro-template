package io.github.alicankustemur.person.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import io.github.alicankustemur.person.domain.base.AbstractEntity;

@Entity
@Table(name = "ROLE")
@Where( clause = "RECORD_IS_DELETED = 0" )
public class Role extends AbstractEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
