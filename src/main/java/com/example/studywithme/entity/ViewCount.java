package com.example.studywithme.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class ViewCount {
	@Id
	private int postId;
	private int count;

}
