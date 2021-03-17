package com.ciandt.pauloneto.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class UsuarioDTO implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    private String login;
    private String email;
    private Date dataCadastro;

    

}
