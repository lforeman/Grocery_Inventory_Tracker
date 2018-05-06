package org.lforeman.models.data;

import org.lforeman.models.Grocery;
import org.lforeman.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/*ClassLoaderFactory*
 * Created by LaunchCode
 */
@Repository
@Transactional
public interface GroceryDao extends CrudRepository<Grocery, Integer> {
    //List<Grocery> findByUser(User user);

}
