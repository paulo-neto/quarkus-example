package com.ciandt.pauloneto.controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ciandt.pauloneto.dto.UsuarioDTO;
import com.ciandt.pauloneto.service.UsuarioService;

import org.apache.http.HttpException;

import java.util.List;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioController {

    @Inject
    private UsuarioService usuarioService;

    @GET
    public Response list() {
        List<UsuarioDTO> all = usuarioService.getAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/{login}")
    public Response getByLogin(@PathParam("login") String login){
        UsuarioDTO usuario = usuarioService.getByLogin(login);
        return usuario != null ? Response.ok(usuario).build() : Response.noContent().build();
    }

    @POST
    public Response add(UsuarioDTO usuario) {
        usuarioService.add(usuario);
        return Response.status(Status.CREATED).header("Location", "/usuario/" + usuario.getLogin()).build();
    }

    @PUT
    @Path("/{login}")
    public Response edit(@PathParam("login") String login, UsuarioDTO usuario)throws HttpException{
        usuarioService.edit(usuario, login);
        return Response.ok().header("Location", "/usuario/" + usuario.getLogin()).build();
    }

    @DELETE
    @Path("/{login}")
    public Response delete(@PathParam("login") String login) throws HttpException {
        usuarioService.remove(login);
        return Response.ok().build();
    }
}
