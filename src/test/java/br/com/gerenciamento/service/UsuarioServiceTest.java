package br.com.gerenciamento.service;

import br.com.gerenciamento.enums.Curso;
import br.com.gerenciamento.enums.Status;
import br.com.gerenciamento.enums.Turno;
import br.com.gerenciamento.exception.EmailExistsException;
import br.com.gerenciamento.model.Aluno;
import br.com.gerenciamento.model.Usuario;
import jakarta.validation.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    ServiceUsuario serviceUsuario;

    @Test
    public void testSalvarUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("renato@email.com");
        usuario.setSenha("1234");
        usuario.setUser("Renato");

            serviceUsuario.salvarUsuario(usuario);

    }

    @Test
    public void testCadastroUsuarioEmailJaExistente() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("renato@email.com");
        usuario.setSenha("1234");
        usuario.setUser("Renato");
        serviceUsuario.salvarUsuario(usuario);

        Usuario usuario2 = new Usuario();
        usuario2.setEmail("renato@email.com");
        usuario2.setSenha("1234");
        usuario2.setUser("Rena");
        Assert.assertThrows(EmailExistsException.class, () -> {
            serviceUsuario.salvarUsuario(usuario2);});
    }

    @Test
    public void testSalvarUsuarioComEmailInvalido() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("renato?gmail.com");
        usuario.setSenha("1234");
        usuario.setUser("Renato");
        Assert.assertThrows(ConstraintViolationException.class, () -> {
            serviceUsuario.salvarUsuario(usuario);});

    }

    @Test
    public void testSalvarUsuarioComUserInvalido() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("renato@email.com");
        usuario.setSenha("1234");
        usuario.setUser("Re");
        Assert.assertThrows(ConstraintViolationException.class, () -> {
            serviceUsuario.salvarUsuario(usuario);});

    }


}
