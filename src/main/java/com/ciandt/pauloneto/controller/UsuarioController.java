package com.ciandt.pauloneto.controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ciandt.pauloneto.dto.UsuarioDTO;
import com.ciandt.pauloneto.service.UsuarioService;

import org.apache.http.HttpException;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "usuario", description = "Serviços relacionados ao CRUD de Usuario")
public class UsuarioController {

    @Inject
    @ConfigProperty(name = "quarkus.application.name")
    private String aplicationMame;
    
    @Inject
    private UsuarioService usuarioService;

    @GET
    @Operation(operationId = "buscandoTodos")
    @APIResponse(
        responseCode = "200",
        description = "Retorna todos os usuários",
        content = @Content(
            mediaType = "application/json",
            schema =  @Schema(implementation = UsuarioDTO[].class)
        ))
    public Response list() {
        List<UsuarioDTO> all = usuarioService.getAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/{login}")
    @Operation(operationId = "buscandoPorLogin")
    @APIResponse(
        responseCode = "200",
        description = "Retorna o usuário encontrado",
        content = @Content(
            mediaType = "application/json",
            schema =  @Schema(implementation = UsuarioDTO.class)
        ))
    @APIResponse(
        responseCode = "204",
        description = "Usuário não encontrado")        
    public Response getByLogin(@PathParam("login") String login){
        UsuarioDTO usuario = usuarioService.getByLogin(login);
        return usuario != null ? Response.ok(usuario).build() : Response.noContent().build();
    }

    @POST
    @Operation(operationId = "criaUmNovoUsuario")
    @APIResponse(
        responseCode = "201",
        description = "Cria um novo usuário",
        content = @Content(
            mediaType = "application/json",
            schema =  @Schema(implementation = UsuarioDTO.class)
        ))
    public Response add(UsuarioDTO usuario) {
        usuarioService.add(usuario);
        return Response.status(Status.CREATED).header("Location", "/usuario/" + usuario.getLogin()).build();
    }

    @PUT
    @Path("/{login}")
    @Operation(operationId = "editaUmUsuario")
    @APIResponse(
        responseCode = "200",
        description = "Edita um usuário",
        content = @Content(
            mediaType = "application/json",
            schema =  @Schema(implementation = UsuarioDTO.class)
        ))
    public Response edit(@PathParam("login") String login, UsuarioDTO usuario)throws HttpException{
        usuarioService.edit(usuario, login);
        return Response.ok().header("Location", "/usuario/" + usuario.getLogin()).build();
    }

    @DELETE
    @Path("/{login}")
    @Operation(operationId = "removeUmUsuario")
    @APIResponse(
        responseCode = "200",
        description = "Remove um usuário",
        content = @Content(
            mediaType = "application/json",
            schema =  @Schema(implementation = UsuarioDTO.class)
        ))
    public Response delete(@PathParam("login") String login) throws HttpException {
        usuarioService.remove(login);
        return Response.ok().build();
    }
}
