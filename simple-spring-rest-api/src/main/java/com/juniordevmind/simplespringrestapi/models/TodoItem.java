package com.juniordevmind.simplespringrestapi.models;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoItem {
    private int id;
    @NotBlank(message = "Title must not be blank.")
    private String title;
}
