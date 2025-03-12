package vn.studentmanagement.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.studentmanagement.api.entity.Ward;

@Repository
public interface WardRepository extends JpaRepository<Ward, String> {
}
