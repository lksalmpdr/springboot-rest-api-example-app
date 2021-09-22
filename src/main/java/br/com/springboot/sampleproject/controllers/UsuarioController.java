package br.com.springboot.sampleproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.sampleproject.repository.UsuarioRepository;

import br.com.springboot.sampleproject.model.Usuario;

@RestController
public class UsuarioController {
	
	@Autowired /*Injeção de dependencia*/
    private UsuarioRepository usuarioRepository;
	/**
     *
     * @param name the name to greet
     * @return greeting text
     */
//    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public String greetingText(@PathVariable String name) {
//        return "Hello " + name + "!";
//    }
//    
    @RequestMapping(value="/usuariosLista/{token}", method = RequestMethod.GET)
    @ResponseBody /*retorna dados para o corpo da resposta*/
    public ResponseEntity<List<Usuario>> listaUsuarios(@PathVariable String token) {
    	List<Usuario> usuarios = usuarioRepository.findAll();
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);

    }
    
    @RequestMapping(value="/salvarUsuario", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Usuario> salvaUsuario(@RequestBody Usuario usuario){
    	Usuario user = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/apagarUsuario", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> apagaUsuario(@RequestParam Long idUser){
    	usuarioRepository.deleteById(idUser);
    	
    	return new ResponseEntity<String>("User " + idUser + " apagado com sucesso", HttpStatus.OK);
    }
    
    @RequestMapping(value="/pesquisarUsuarioId", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Usuario> pesquisaUsuarioId(@RequestParam(name="idUser") Long idUser){
    	Usuario user = usuarioRepository.findById(idUser).get();
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
    
    @RequestMapping(value="/atualizarUsuario", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> atualizaUsuario(@RequestBody Usuario usuario){
    	
    	if(usuario.getId()==null) {
    		return new ResponseEntity<String>("É necessário informar o Id do usuário para atualizar", HttpStatus.OK);
    	}
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }   
    
    @RequestMapping(value="/pesquisarUsuarioNome", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Usuario>> pesquisaUsuarioNome(@RequestParam(name="nome") String nome){
    	List<Usuario> users = usuarioRepository.buscaPorNome(nome.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(users, HttpStatus.OK);
    }
    
    
    
}
