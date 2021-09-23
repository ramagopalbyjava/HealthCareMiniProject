package in.nareshit.ram.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.ram.entity.Specialization;

public interface SpecializationRepository extends JpaRepository<Specialization, Long>{

	@Query("SELECT COUNT(specCode) FROM Specialization  WHERE specCode=:specCode")
	Integer getSpecCodeCount(String specCode);

	@Query("SELECT COUNT (specName) FROM Specialization WHERE specName=:specName")
	Integer getSpecNameCount(String specName);

}
