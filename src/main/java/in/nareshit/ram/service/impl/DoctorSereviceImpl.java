package in.nareshit.ram.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.ram.entity.Doctor;
import in.nareshit.ram.exception.DectorNotFoundException;
import in.nareshit.ram.repo.DoctorRepository;
import in.nareshit.ram.service.IDoctorService;

@Service
public class DoctorSereviceImpl implements IDoctorService {
	
	@Autowired
	private DoctorRepository repo;

	@Override
	public Long saveDoctor(Doctor doc) {
		
		return repo.save(doc).getId();
	}

	@Override
	public List<Doctor> getAllDoctors() {
		return repo.findAll();
	}

	@Override
	public void removeDoctor(Long id) {
		repo.delete(getOneDoctor(id));

	}

	@Override
	public Doctor getOneDoctor(Long id) {
		
		return repo.findById(id).orElseThrow(
				()->new DectorNotFoundException(id+", not exist")
				);
	}

	@Override
	public void updateDoctor(Doctor doc) {
		if(repo.existsById(doc.getId()))
			repo.save(doc);
		else 
			throw new DectorNotFoundException(doc.getId()+", not exist"); 
	}

}
