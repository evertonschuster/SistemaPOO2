package br.com.schuster.everton.restlabs.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Greeting {

    private final long id;
    private final String content;

}
