package vn.studentmanagement.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.studentmanagement.api.entity.Clazz;

public interface ClassRepository extends JpaRepository<Clazz, Integer> {


}
