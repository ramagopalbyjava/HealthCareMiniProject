package in.nareshit.ram.service;

import java.util.List;

import in.nareshit.ram.entity.Patient;

public interface IPatientService {

	public Long savePatient(Patient patient);
	public List<Patient> getAllPatients();
	public Patient getOnePatient(Long id);
	public  void removeDoctor(Long id);
	public void updatePatient(Patient patient);
}
