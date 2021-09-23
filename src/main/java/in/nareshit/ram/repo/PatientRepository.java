package in.nareshit.ram.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nareshit.ram.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient,Long>{

}
