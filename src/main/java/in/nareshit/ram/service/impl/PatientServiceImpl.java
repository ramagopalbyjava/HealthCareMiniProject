package in.nareshit.ram.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.ram.entity.Patient;
import in.nareshit.ram.exception.PatientNotFoundException;
import in.nareshit.ram.repo.PatientRepository;
import in.nareshit.ram.service.IPatientService;

@Service
public class PatientServiceImpl implements IPatientService {

	@Autowired
	private PatientRepository repo;
	@Override
	public Long savePatient(Patient patient) {
		return repo.save(patient).getId();
	}

	@Override
	public List<Patient> getAllPatients() {
		
		return repo.findAll();
	}

	@Override
	public Patient getOnePatient(Long id) {
		
		return repo.findById(id).orElseThrow(()->
		new PatientNotFoundException(id+",Not Exist")
				);
	}

	@Override
	public void removeDoctor(Long id) {
		repo.delete(getOnePatient(id));
		
	}

	@Override
	public void updatePatient(Patient patient) {
		if(repo.existsById(patient.getId()))
			repo.save(patient);
		
		else
			throw new PatientNotFoundException(patient.getId()+", not exist");
		
	}

}
