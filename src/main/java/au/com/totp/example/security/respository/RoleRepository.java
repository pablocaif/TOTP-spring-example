package au.com.totp.example.security.respository;

import au.com.totp.example.security.model.SBTUserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pablocaif on 16/09/15.
 */
@Repository
public interface RoleRepository extends CrudRepository<SBTUserRole, String> {
}
