package init.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Coche {

	public String matricula;
	public String marca;
	public String modelo;
	public int kilometros;
	public double precio;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	public Date fechaFabricacion;

}
