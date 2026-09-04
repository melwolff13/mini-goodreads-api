package com.minigoodreads.api.repositories;

import com.minigoodreads.api.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmailAndIdNot(String email, Long usuarioLogadoId);
    boolean existsByNickAndIdNot(String nick, Long usuarioLogadoId);
    UserDetails findByNick(String nick);
}
