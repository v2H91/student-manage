package vn.studentmanagement.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.studentmanagement.api.entity.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, String> {
}
