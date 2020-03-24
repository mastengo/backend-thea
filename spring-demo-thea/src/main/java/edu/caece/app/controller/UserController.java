package edu.caece.app.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.caece.app.config.Hash;
import edu.caece.app.domain.AppResponse;
import edu.caece.app.domain.User;
import edu.caece.app.repository.IUserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private IUserRepository repository;

	@RequestMapping(value = "/users/list", method = RequestMethod.GET)
	public Collection<User> get() {

		List<User> users = new ArrayList<User>();

		repository.findAll().forEach(x -> {
			x.setPassword(null);
			users.add(x);
		});
		
		return users.stream().collect(Collectors.toList());
	}

	@PostMapping("/users/create")
	public ResponseEntity<Object> save(@RequestBody User user) {

		boolean existe = repository.existsByUsername(user.getUsername());
        AppResponse<User> result = new AppResponse<User>();
		
		if (!existe) {
			
			user.setPassword(Hash.sha1(user.getPassword()));
			result.setSuccess(true);
			result.setMessage(null);
			result.setResult(repository.save(user));
			result.getResult().setPassword(null); //Password null para que no la vea el front.
			
			return new ResponseEntity<>(result, HttpStatus.OK); //USER CREATED
		}

		result.setSuccess(false);
		result.setMessage("Usuario Existe"); //USER EXIST
		result.setResult(null);
				
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping("/users/update/{id}")
	public ResponseEntity<Object> update(@RequestBody User user, @PathVariable long id) {

		Optional<User> _userData = repository.findById(id);
		boolean existe_username = repository.existsByUsername(user.getUsername());
		AppResponse<User> result = new AppResponse<User>();
		
		if (_userData.isPresent()) {
			
			User _user = _userData.get();
			
			if (!existe_username || _user.getUsername().equals(user.getUsername())) {
				
				_user.setFirstName(user.getFirstName());
				_user.setLastName(user.getLastName());
				_user.setEmail(user.getEmail());
				_user.setUsername(user.getUsername());
				_user.setRoles(user.getRoles());
				
				user.getPhotos().forEach(x -> {
					x.setUser(user);
				});
				
				_user.setPhotos(user.getPhotos());
				
				
				result.setMessage(null);
				result.setSuccess(true);
				result.setResult(repository.save(_user));
				result.getResult().setPassword(null); //Password null para que no la vea el front.
				
				return new ResponseEntity<>(result, HttpStatus.OK);
				
			} else {
				
				//Username exists.
				result.setMessage("User Exist");
				result.setResult(null);
				result.setSuccess(false);
				return new ResponseEntity<>(result,	HttpStatus.OK);
			}

		} else {
			
			result.setSuccess(false);
			result.setMessage("User not Found"); //USER NOT FOUND.
			result.setResult(null);
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}

	@DeleteMapping("/users/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable long id) {

		Optional<User> _userData = repository.findById(id);
		AppResponse<User> result = new AppResponse<User>();
		
		if (_userData.isPresent()) {
			
			repository.deleteById(id);
			result.setSuccess(true);
			result.setMessage("El usuario ha sido eliminado!"); //USER DELETED.
			result.setResult(null);
			
			return new ResponseEntity<String>("El usuario ha sido eliminado!", HttpStatus.OK);
			
		} else {
			
			result.setSuccess(false);
			result.setMessage("Error al eliminar. EL Usuario no se encuentra en la base de datos"); //USER NOT FOUND.
			result.setResult(null);
			
			return new ResponseEntity<>("Usuario no encontrado!", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/users/edit/{id}")
	public ResponseEntity<User> getById(@PathVariable Long id) {

		Optional<User> _userData = repository.findById(id);

		if (_userData.isPresent()) {
			return new ResponseEntity<User>(repository.findById(id).get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("users/exists/{username}")
	public ResponseEntity<Boolean> existByUsername(@PathVariable String username) {
		return new ResponseEntity<>(repository.existsByUsername(username), HttpStatus.OK);
	}
}