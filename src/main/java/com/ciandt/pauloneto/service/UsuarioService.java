package com.ciandt.pauloneto.service;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import com.ciandt.pauloneto.dto.UsuarioDTO;

import org.apache.http.HttpException;

@ApplicationScoped
public class UsuarioService {

    private final List<UsuarioDTO> usuarios = Collections
            .synchronizedList(new LinkedList<>());
    private int indexOf;

    @PostConstruct
    private void init() {
        UsuarioDTO u = new UsuarioDTO();
        u.setDataCadastro(new Date());
        u.setEmail("quarkus@ciandt.com");
        u.setLogin("quarkus");
        usuarios.add(u);
    }
    
    public List<UsuarioDTO> getAll(){
        return this.usuarios;
    }

    public UsuarioDTO getByLogin(String login) {
        List<UsuarioDTO> usu = usuarios.stream().filter(u -> login.equals(u.getLogin())).collect(Collectors.toList());
        return usu != null && !usu.isEmpty() ? usu.get(0) : null;
    }

    public void add(UsuarioDTO usuario){
        usuario.setDataCadastro(new Date());
        this.usuarios.add(usuario);
    }

    public void edit(UsuarioDTO usuarioDTO, String login)throws HttpException{
        remove(login);
        this.usuarios.add(usuarioDTO);
    }

    public void remove(String login)throws HttpException{
        UsuarioDTO byLogin = getByLogin(login);
        if(byLogin == null)
            throw new HttpException("Usuário não encontrado");
        
        indexOf = this.usuarios.indexOf(byLogin);
        this.usuarios.remove(indexOf);
    }
}