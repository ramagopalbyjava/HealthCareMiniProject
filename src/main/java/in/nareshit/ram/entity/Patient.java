package in.nareshit.ram.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="patient_tab")
public class Patient {
	
	@Id
	@Column(name="pat_id_col")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name="pat_name_col")
	private Integer name;
	
	@Column(name="pat_mob_col")
	private String mobile;

	@Column(name="pat_gen_col")
	private String gender;
	
	
	@Column(name="pat_dob_col")
	private String dateofbirth;
	
	@Column(name="pat_marial_col")
	private String marialStatus;
	
	@Column(name="pat_preaddr_col")
	private String presentAddress;
	
	@Column(name="pat_comaddr_col")
	private String CommunicationAddress;
	
	
	
	
	
	
	
	@Column(name="pat_other_col")
	private String otherdetails;
}
