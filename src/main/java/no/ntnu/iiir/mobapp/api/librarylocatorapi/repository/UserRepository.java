package no.ntnu.iiir.mobapp.api.librarylocatorapi.repository;

import no.ntnu.iiir.mobapp.api.librarylocatorapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findById(long id);

  User findByUsername(String username);

}
