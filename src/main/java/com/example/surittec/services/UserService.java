package com.example.surittec.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.surittec.models.Address;
import com.example.surittec.models.enums.TypeUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.surittec.models.User;
import com.example.surittec.repositories.UserRepository;

import static com.example.surittec.models.enums.TypeUser.ADMIN;
import static com.example.surittec.models.enums.TypeUser.CLIENT;

@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(User.class);

    @Autowired
    private UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
        LOG.info("usuario salvo com sucesso");
    }

    public void delete(User user) {
        userRepository.delete(user);
        LOG.info("usuario removido com sucesso");
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    public User searchUser(String cpf, String password) {

        User usuario1 = new User();
		usuario1.setId(1L);
		usuario1.setName("admin");
		usuario1.setPassword("123456");
		usuario1.setUserType(TypeUser.toEnum(ADMIN.getCode()));
		usuario1.setCpf("00000000000");
		usuario1.setAddress(new Address());

        User usuario2 = new User();
		usuario2.setId(2L);
		usuario2.setName("comum");
		usuario2.setPassword("123456");
		usuario2.setUserType(TypeUser.toEnum(CLIENT.getCode()));
		usuario2.setCpf("00000000022");
		usuario2.setAddress(new Address());

		List<User> users = Arrays.asList(usuario1, usuario2);

        return users.stream().filter(u -> u.getName().equals(cpf) && u.getPassword().equals(password)).findFirst().get();

    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User findByCpfAndPassword(String cpf, String password) {
        User user = userRepository.findByCpfAndPassword(cpf, password);
        return user;
    }

    public User findByCpf(String cpf) {
        User user = userRepository.findByCpf(cpf);
        return user;
    }

    public List<User> findAllUsers(Integer userType) {

        return userRepository.findByUserType(userType);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

}
