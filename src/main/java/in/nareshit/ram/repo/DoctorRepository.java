package in.nareshit.ram.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nareshit.ram.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Long >{

}
