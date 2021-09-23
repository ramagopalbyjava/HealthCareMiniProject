package in.nareshit.ram.service;

import java.util.List;

import in.nareshit.ram.entity.Specialization;

public interface ISpecializationService {

	public Long saveSpecialization(Specialization spec);
	public List<Specialization> getAllSpecializations();
	public void removeSpecialization(Long id);
	public Specialization getOneSpecialization(Long id);
	public void updateSpecialization(Specialization spec);
	
	
	public boolean isSpecCodeExist(String specCode); 
	public boolean isSpecNameExist(String specName);
}
